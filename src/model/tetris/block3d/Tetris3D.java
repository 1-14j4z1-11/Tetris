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
	 * ブロックを最下位置まで移動させ、固定します.
	 */
	public void hardDrop()
	{
		while(this.field.move(0, 0, 1));
		
		this.fixField();
		this.putNextBlock();
	}
	
	/**
	 * ブロックを左回転します.
	 * @return 回転に成功した場合はtrueを返し、失敗した場合はfalseを返します.
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
	 * ブロックを左回転します.
	 * @return 回転に成功した場合はtrueを返し、失敗した場合はfalseを返します.
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
	 * ブロックを左回転します.
	 * @return 回転に成功した場合はtrueを返し、失敗した場合はfalseを返します.
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
	 * 現在のブロックをホールドし、次のブロックor前のホールドブロックと入れ替えます.
	 * @return ホールドに成功した場合はtrueを返し、失敗した場合はfalseを返します.
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
			if(this.field.move(0, 0, 1))
			{
				this.fixCounter = 0;
			}
			else
			{
				break;
			}
		}
		
		// 自動落下ができなかった場合でfixCounterがINTERVALを超えた場合に固定
		// 固定までのフレーム数はMax(FIX_INTERVAL, 1 / Gravity)
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
		int plane = this.field.clearFillPlane();
		
		this.score.update(plane, false);
		
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
	 * フィールドを取得します.
	 */
	public IBlock3D<T> getFiled()
	{
		return this.field;
	}
	
	/**
	 * 次のブロックを取得します.
	 */
	public IBlock3D<T> getNextBlock()
	{
		return this.next;
	}
	
	/**
	 * ホールド中のブロックを取得します.
	 */
	public IBlock3D<T> getHoldBlock()
	{
		return this.hold;
	}
	
	/**
	 * スコアを取得します.
	 */
	public IScore getScore()
	{
		return this.score;
	}
	
	/**
	 * ゲームを終了します.
	 * 設定されているIGameFinishedHandlerを呼び出します
	 */
	public void finish()
	{
		if(this.gamefinished != null)
			this.gamefinished.execute();
	}
}
