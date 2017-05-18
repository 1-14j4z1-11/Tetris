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
	 * 状態を初期化します.
	 */
	public final void reset()
	{
		this.field.reset();
		this.score.reset(1);
		this.next = this.creator.nextBlock();
	}
	
	/**
	 * ブロック固定時に呼び出されるリスナを設定します
	 * @param listener 設定するリスナ
	 */
	public void setBlockFixedListener(IBlockFixedHandler listener)
	{
		this.blockFixed = listener;
	}
	
	/**
	 * ゲーム終了時に呼び出されるリスナを設定します
	 * @param listener 設定するリスナ
	 */
	public void setGameFinishedListener(IGameFinishedHandler listener)
	{
		this.gamefinished = listener;
	}
	
	/**
	 * ブロックを移動します.
	 * @param offsetX 横移動量
	 * @param offsetY 縦移動量
	 * @return 移動に成功した場合はtrueを返し、失敗した場合はfalseを返します.
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
	 * ブロックを最下位置まで移動させ、固定します.
	 */
	public void hardDrop()
	{
		while(this.field.move(0, 1));
		
		this.fixField();
		this.putNextBlock();
	}
	
	/**
	 * ブロックを左回転します.
	 * @return 回転に成功した場合はtrueを返し、失敗した場合はfalseを返します.
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
	 * ブロックを右回転します.
	 * @return 回転に成功した場合はtrueを返し、失敗した場合はfalseを返します.
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
	 * 現在のブロックをホールドし、次のブロックor前のホールドブロックと入れ替えます.
	 * @return ホールドに成功した場合はtrueを返し、失敗した場合はfalseを返します.
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
	 * ゲームのフレームを進めます
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
		
		// 自動落下ができなかった場合でfixCounterがINTERVALを超えた場合に固定
		if((++this.fixCounter >= FIX_INTETVAL) && (this.gCounter >= 1.0))
		{
			this.fixField();
			this.putNextBlock();
			this.gCounter = 0.0;
		}
	}

	/**
	 * ブロックを固定し、ブロックがそろったラインを削除します.</br>
	 * IBlockFixedHandlerを設定している場合は呼び出されます.
	 */
	private void fixField()
	{
		this.gCounter = 0.0;
		this.fixCounter = 0;
		
		this.field.fixMoveBlock();
		int line = this.field.clearFillLine();
		this.score.update(line, false);		// TODO T-Spin判定
		
		if(this.blockFixed != null)
			this.blockFixed.execute(this.score.createSnapShot());
	}
	
	/**
	 * 次のブロックを設置します.</br>
	 * 失敗した場合は設定されているIGameFinishedHandlerが呼び出されます.
	 * @return ブロックの設置に成功した場合はtrueを返し、失敗した場合はfalseを返します.
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
	
	/** フィールドを取得します. */
	public IBlock2D<T> getFiled()
	{
		return this.field;
	}
	
	/** 次のブロックを取得します. */
	public IBlock2D<T> getNextBlock()
	{
		return this.next;
	}
	
	/** ホールド中のブロックを取得します. */
	public IBlock2D<T> getHoldBlock()
	{
		return this.hold;
	}
	
	/** スコアを取得します. */
	public IScore getScore()
	{
		return this.score;
	}
}
