package model.tetris.block3d;

import model.score.*;
import model.tetris.common.*;

public class Tetris3D<T>
{
	private static final int MAX_SCORE = 999999999;
	private static final int FIX_INTETVAL = 15;
	
	private final Field3D<T> field;
	private final IBlockCreator<Block3D<T>> creator;
	private final Score score;
	private Block3D<T> next;
	private Block3D<T> hold;
	private double gCounter = 0.0;
	private int fixCounter = 0;
	
	private IBlockFixedHandler blockFixed;
	private IGameFinishedHandler gamefinished;
	
	public Tetris3D(int width, int height, int depth, BlockCell<T> noneCell, IBlockCreator<Block3D<T>> creator, IScoreCalculator calculator)
	{
		if((noneCell == null) || (creator == null) || (calculator == null))
			throw new NullPointerException();
		
		this.field = new Field3D<T>(width, height, depth, noneCell);
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
	public boolean move(int offsetX, int offsetY, int offsetZ)
	{
		if(this.field.move(offsetX, offsetY, offsetZ))
		{
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
		while(this.field.move(0, 0, 1));
		
		this.fixField();
		this.putNextBlock();
	}
	
	/**
	 * �u���b�N������]���܂�.
	 * @return ��]�ɐ��������ꍇ��true��Ԃ��A���s�����ꍇ��false��Ԃ��܂�.
	 */
	public boolean rotateX()
	{
		if(this.field.rotateX())
		{
			this.fixCounter = 0;
			return true;
		}
		else
		{
			return false;
		}
	}
	
	/**
	 * �u���b�N������]���܂�.
	 * @return ��]�ɐ��������ꍇ��true��Ԃ��A���s�����ꍇ��false��Ԃ��܂�.
	 */
	public boolean rotateY()
	{
		if(this.field.rotateY())
		{
			this.fixCounter = 0;
			return true;
		}
		else
		{
			return false;
		}
	}
	
	/**
	 * �u���b�N������]���܂�.
	 * @return ��]�ɐ��������ꍇ��true��Ԃ��A���s�����ꍇ��false��Ԃ��܂�.
	 */
	public boolean rotateZ()
	{
		if(this.field.rotateZ())
		{
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
		int y = (this.field.getHeight() - this.next.getHeight()) / 2;
		Block3D<T> changeBlock = (this.hold != null) ? this.hold : this.next;
		
		for(int z = -changeBlock.getDepth(); z <= 0; z++)
		{
			if(this.field.canPutBlock(x, y, z, changeBlock))
			{
				if(this.hold == null)
				{
					this.hold = this.field.removeMoveBlock();
					this.putNextBlock();
				}
				else
				{
					Block3D<T> prevHold = this.hold;
					this.hold = this.field.removeMoveBlock();
					this.field.putBlock(x, y, z, prevHold);
				}
				this.gCounter = 0;
				
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
			if(this.field.move(0, 0, 1))
			{
				this.fixCounter = 0;
			}
			else
			{
				break;
			}
		}
		
		// �����������ł��Ȃ������ꍇ��fixCounter��INTERVAL�𒴂����ꍇ�ɌŒ�
		// �Œ�܂ł̃t���[������Max(FIX_INTERVAL, 1 / Gravity)
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
		int plane = this.field.clearFillPlane();
		
		this.score.update(plane, false);
		
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
		int y = (this.field.getHeight() - this.next.getHeight()) / 2;
		
		for(int z = -this.next.getDepth(); z <= 0; z++)
		{
			if(this.field.canPutBlock(x, y, z, this.next))
			{
				this.field.putBlock(x, y, z, this.next);
				this.next = this.creator.nextBlock();
				
				return true;
			}
		}
		
		if(this.gamefinished != null)
			this.gamefinished.execute();
		
		return false;
	}
	
	/**
	 * �t�B�[���h���擾���܂�.
	 */
	public IBlock3D<T> getFiled()
	{
		return this.field;
	}
	
	/**
	 * ���̃u���b�N���擾���܂�.
	 */
	public IBlock3D<T> getNextBlock()
	{
		return this.next;
	}
	
	/**
	 * �z�[���h���̃u���b�N���擾���܂�.
	 */
	public IBlock3D<T> getHoldBlock()
	{
		return this.hold;
	}
	
	/**
	 * �X�R�A���擾���܂�.
	 */
	public IScore getScore()
	{
		return this.score;
	}
	
	/**
	 * �Q�[�����I�����܂�.
	 * �ݒ肳��Ă���IGameFinishedHandler���Ăяo���܂�
	 */
	public void finish()
	{
		if(this.gamefinished != null)
			this.gamefinished.execute();
	}
}
