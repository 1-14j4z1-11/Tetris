package model.tetris.block2d;

import model.score.*;
import model.tetris.common.*;

public class Tetris2D<T>
{
	private enum ActionType
	{
		None, Move, Rotate,
	}
	
	private static final int MAX_SCORE = 999999999;
	private static final int FIX_INTETVAL = 15;
	
	private final Field2D<T> field;
	private final IBlockCreator<Block2D<T>> creator;
	private final Score score;
	private Block2D<T> next;
	private Block2D<T> hold;
	private double gCounter = 0.0;
	private int fixCounter = 0;
	
	private ActionType lastAction = ActionType.None;
	private IBlockFixedHandler blockFixed;
	private IGameFinishedHandler gamefinished;
	
	public Tetris2D(int width, int height, BlockCell<T> noneCell, IBlockCreator<Block2D<T>> creator, IScoreCalculator calculator)
	{
		if((noneCell == null) || (creator == null) || (calculator == null))
			throw new NullPointerException();
		
		this.field = new Field2D<T>(width, height, noneCell);
		this.creator = creator;
		this.score = new Score(MAX_SCORE, calculator);
		
		this.reset();
	}
	
	/**
	 * ��Ԃ����������܂�.
	 */
	public final void reset()
	{
		this.field.reset();
		this.score.reset(1);
		this.next = this.creator.nextBlock();
	}
	
	/**
	 * �u���b�N�Œ莞�ɌĂяo����郊�X�i��ݒ肵�܂�
	 * @param listener �ݒ肷�郊�X�i
	 */
	public void setBlockFixedListener(IBlockFixedHandler listener)
	{
		this.blockFixed = listener;
	}
	
	/**
	 * �Q�[���I�����ɌĂяo����郊�X�i��ݒ肵�܂�
	 * @param listener �ݒ肷�郊�X�i
	 */
	public void setGameFinishedListener(IGameFinishedHandler listener)
	{
		this.gamefinished = listener;
	}
	
	/**
	 * �u���b�N���ړ����܂�.
	 * @param offsetX ���ړ���
	 * @param offsetY �c�ړ���
	 * @return �ړ��ɐ��������ꍇ��true��Ԃ��A���s�����ꍇ��false��Ԃ��܂�.
	 */
	public boolean move(int offsetX, int offsetY)
	{
		if(this.field.move(offsetX, offsetY))
		{
			this.lastAction = ActionType.Move;
			this.fixCounter = 0;
			return true;
		}
		else
		{
			return false;
		}
	}
	
	/**
	 * �u���b�N���ŉ��ʒu�܂ňړ������A�Œ肵�܂�.
	 */
	public void hardDrop()
	{
		while(this.field.move(0, 1));
		
		this.fixField();
		this.putNextBlock();
	}
	
	/**
	 * �u���b�N������]���܂�.
	 * @return ��]�ɐ��������ꍇ��true��Ԃ��A���s�����ꍇ��false��Ԃ��܂�.
	 */
	public boolean rotateL()
	{
		if(this.field.rotateL())
		{
			this.lastAction = ActionType.Rotate;
			this.fixCounter = 0;
			return true;
		}
		else
		{
			return false;
		}
	}
	
	/**
	 * �u���b�N���E��]���܂�.
	 * @return ��]�ɐ��������ꍇ��true��Ԃ��A���s�����ꍇ��false��Ԃ��܂�.
	 */
	public boolean rotateR()
	{
		if(this.field.rotateR())
		{
			this.lastAction = ActionType.Rotate;
			this.fixCounter = 0;
			return true;
		}
		else
		{
			return false;
		}
	}
	
	/**
	 * ���݂̃u���b�N���z�[���h���A���̃u���b�Nor�O�̃z�[���h�u���b�N�Ɠ���ւ��܂�.
	 * @return �z�[���h�ɐ��������ꍇ��true��Ԃ��A���s�����ꍇ��false��Ԃ��܂�.
	 */
	public boolean holdBlock()
	{
		int x = (this.field.getWidth() - this.next.getWidth()) / 2;
		Block2D<T> changeBlock = (this.hold != null) ? this.hold : this.next;

		for(int y = -changeBlock.getHeight(); y <= 0; y++)
		{
			if(this.field.canPutBlock(x, y, changeBlock))
			{
				if(this.hold == null)
				{
					this.hold = this.field.removeMoveBlock();
					this.putNextBlock();
				}
				else
				{
					Block2D<T> prevHold = this.hold;
					this.hold = this.field.removeMoveBlock();
					this.field.putBlock(x, y, prevHold);
				}
				this.gCounter = 0.0;
				
				return true;
			}
		}
		
		return false;
	}
	
	/**
	 * �Q�[���̃t���[����i�߂܂�
	 */
	public void timeTicked()
	{
		if(this.field.getDropBlock() == null)
		{
			this.putNextBlock();
			return;
		}
		
		this.gCounter += this.score.getGravity();
		
		for(; this.gCounter >= 1.0; this.gCounter -= 1.0)
		{
			if(this.field.move(0, 1))
			{
				this.fixCounter = 0;
			}
			else
			{
				break;
			}
		}
		
		// �����������ł��Ȃ������ꍇ��fixCounter��INTERVAL�𒴂����ꍇ�ɌŒ�
		if((++this.fixCounter >= FIX_INTETVAL) && (this.gCounter >= 1.0))
		{
			this.fixField();
			this.putNextBlock();
			this.gCounter = 0.0;
		}
	}

	/**
	 * �u���b�N���Œ肵�A�u���b�N������������C�����폜���܂�.</br>
	 * IBlockFixedHandler��ݒ肵�Ă���ꍇ�͌Ăяo����܂�.
	 */
	private void fixField()
	{
		this.gCounter = 0.0;
		this.fixCounter = 0;
		
		this.field.fixMoveBlock();
		int line = this.field.clearFillLine();
		this.score.update(line, false);		// TODO T-Spin����
		
		if(this.blockFixed != null)
			this.blockFixed.execute(this.score.createSnapShot());
	}
	
	/**
	 * ���̃u���b�N��ݒu���܂�.</br>
	 * ���s�����ꍇ�͐ݒ肳��Ă���IGameFinishedHandler���Ăяo����܂�.
	 * @return �u���b�N�̐ݒu�ɐ��������ꍇ��true��Ԃ��A���s�����ꍇ��false��Ԃ��܂�.
	 */
	private boolean putNextBlock()
	{
		assert(this.next == null);
		
		int x = (this.field.getWidth() - this.next.getWidth()) / 2;
		
		for(int y = -this.next.getHeight(); y <= 0; y++)
		{
			if(this.field.canPutBlock(x, y, this.next))
			{
				this.field.putBlock(x, y, this.next);
				this.next = this.creator.nextBlock();
				
				return true;
			}
		}
		
		if(this.gamefinished != null)
			this.gamefinished.execute();
			
		return false;
	}
	
	/** �t�B�[���h���擾���܂�. */
	public IBlock2D<T> getFiled()
	{
		return this.field;
	}
	
	/** ���̃u���b�N���擾���܂�. */
	public IBlock2D<T> getNextBlock()
	{
		return this.next;
	}
	
	/** �z�[���h���̃u���b�N���擾���܂�. */
	public IBlock2D<T> getHoldBlock()
	{
		return this.hold;
	}
	
	/** �X�R�A���擾���܂�. */
	public IScore getScore()
	{
		return this.score;
	}
}
