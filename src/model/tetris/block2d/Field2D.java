package model.tetris.block2d;

import java.util.*;
import model.tetris.common.BlockCell;
import model.tetris.common.BlockState;
import model.tetris.common.IBlockFixer;

public class Field2D<T> implements IBlock2D<T>
{
	// フィールド
	private final int width;
	private final int height;
	private final List<BlockCell<T>> field;
	private final BlockCell<T> noneCell;
	
	private IBlockFixer<T> fixer = new DefaultFixer<T>();
	
	private Block2D<T> block;
	private int x = -1;
	private int y = -1;
	
	// コンストラクタ
	public Field2D(int width, int height, BlockCell<T> noneCell)
	{
		this.width = width;
		this.height = height;
		this.noneCell = noneCell;
		
		int length = width * height;
		this.field = new ArrayList<>(length);
		
		for(int i = 0; i < length; i++)
		{
			this.field.add(noneCell);
		}
	}
	
	// アクセサ
	public Block2D<T> getDropBlock()
	{
		return this.block;
	}
	
	public int getDropBlockX()
	{
		return this.x;
	}

	public int getDropBlockY()
	{
		return this.y;
	}
	
	public void setBlockFixer(IBlockFixer<T> fixer)
	{
		this.fixer = (fixer != null) ? fixer : new DefaultFixer<T>();
	}
	
	/**
	 * 初期状態に戻します
	 *   ・落下ブロックを削除
	 *   ・全てのフィールド内のブロックを削除
	 */
	public void reset()
	{
		this.removeMoveBlock();

		for(int j = 0; j < this.getHeight(); j++)
		{
			for(int i = 0; i < this.getWidth(); i++)
			{
				this.setCellAt(i, j, this.noneCell);
			}
		}
	}
	
	/**
	 * 現在動いているブロック(ゴーストを含む)を削除します.
	 * @return 動いていたブロック
	 */
	public Block2D<T> removeMoveBlock()
	{
		for(int j = 0; j < this.getHeight(); j++)
		{
			for(int i = 0; i < this.getWidth(); i++)
			{
				BlockState state = this.getCellAt(i, j).getState();
				if((state == BlockState.MOVE) || (state == BlockState.GHOST))
				{
					this.setCellAt(i, j, noneCell);
				}
			}
		}
		
		Block2D<T> block = this.block;
		this.block = null;
		
		return block;
	}
	
	/**
	 * 現在動いているブロックを固定し、ゴーストを削除します.
	 * @param tag 固定後の状態を示す値
	 */
	public void fixMoveBlock()
	{
		for(int j = 0; j < this.getHeight(); j++)
		{
			for(int i = 0; i < this.getWidth(); i++)
			{
				if(this.getCellAt(i, j).getState() == BlockState.MOVE)
				{
					this.setCellAt(i, j, this.fixer.fixBlock(this.getCellAt(i, j)));
				}
			}
		}
		
		this.removeMoveBlock();	// Ghost削除
	}
	
	/**
	 * 位置(x,y)にblockが設置可能かどうかを判定します.
	 * @param x ブロックの左上のX座標
	 * @param y ブロックの左上のY座標
	 * @param block 設置するブロック
	 * @return ブロックが設置可能な場合はtrueを返し, そうでない場合はfalseを返します.
	 * @throws NullPointerException blockがnullの場合
	 */
	public boolean canPutBlock(int x, int y, Block2D<T> block)
	{
		if(block == null)
			throw new NullPointerException();

		for(int j = 0; j < block.getHeight(); j++)
		{
			for(int i = 0; i < block.getWidth(); i++)
			{
				if(block.getCellAt(i, j).getState() == BlockState.NONE)
				{
					continue;
				}

				// ブロックを設置する領域がエリア内かつ既存ブロックと被らないか判定
				if((x + i < 0) || (x + i >= this.getWidth())
					|| (y + j < 0) || (y + j >= this.getHeight()))
				{
					return false;
				}
				else if(this.getCellAt(x + i, y + j).getState() == BlockState.FIXED)
				{
					return false;
				}
			}
		}

		return true;
	}
	
	/**
	 * 位置(x,y)にblockを設置します.
	 * @param x ブロックの左上のX座標
	 * @param y ブロックの左上のY座標
	 * @param block 設置するブロック
	 * @return ブロックの設置に成功した場合はtrueを返し, そうでない場合はfalseを返します.
	 * @throws NullPointerException blockがnullの場合
	 */
	public boolean putBlock(int x, int y, Block2D<T> block)
	{
		// 設置可能判定
		if(!this.canPutBlock(x, y, block))
			return false;
		
		// ブロック設置
		for(int j = 0; j < block.getHeight(); j++)
		{
			for(int i = 0; i < block.getWidth(); i++)
			{
				if(block.getCellAt(i, j).getState() != BlockState.NONE)
				{
					this.setCellAt(x + i, y + j, block.getCellAt(i, j));
				}
			}
		}

		// ブロックを登録
		this.x = x;
		this.y = y;
		this.block = block;
		
		for(int n = y + 1; n < this.getHeight() + block.getHeight(); n++)
		{
			if(this.canPutBlock(x, n, block))
			{
				continue;
			}
			
			n--;
			
			for(int j = 0; j < block.getHeight(); j++)
			{
				for(int i = 0; i < block.getWidth(); i++)
				{
					BlockCell<T> cell = block.getCellAt(i, j);
					
					if((cell.getState() != BlockState.NONE)
						&& (this.getCellAt(x + i, n + j).getState() == BlockState.NONE))
					{
						this.setCellAt(x + i, n + j,
							new BlockCell<T>(BlockState.GHOST, cell.getTag()));
					}
				}
			}
			
			break;
		}
		
		return true;
	}
	
	/**
	 * 現在動いているブロックを移動させます.
	 * @param offsetX X方向の移動量
	 * @param offsetY Y方向の移動量
	 * @return 移動に成功した場合はtrueを返し、そうでない場合はfalseを返します.
	 */
	public boolean move(int offsetX, int offsetY)
	{
		if(this.block == null)
			return false;
		
		if(!this.canPutBlock(this.x + offsetX, this.y + offsetY, this.block))
			return false;
		
		Block2D<T> block = this.removeMoveBlock();
		this.putBlock(this.x + offsetX, this.y + offsetY, block);
		
		return true;
	}
	
	public boolean rotateL()
	{
		if(this.block == null)
			return false;
		
		return this.rotation(this.block.rotateL(), 1, Direction.Left);
	}
	
	public boolean rotateR()
	{
		if(this.block == null)
			return false;
		
		return this.rotation(this.block.rotateR(), 1, Direction.Right);
	}
	
	/**
	 * 固定ブロックで埋まっているラインを全て削除します
	 * @return 削除したライン数を返します. 削除するラインがなかった場合は0を返します.
	 */
	public int clearFillLine()
	{
		int count = 0;
		
		for(int j = 0; j < this.getHeight(); j++)
		{
			if(this.isFillLine(j))
			{
				this.removeLine(j);
				count++;
			}
		}
		
		return count;
	}
	
	// 内部メソッド
	/**
	 * y行目のラインが全て固定ブロックで埋まっているかどうかを判定します.
	 * @param y 判定する行(Y座標)
	 * @return ラインが全て固定ブロックであればtrueを返し、そうでない場合はfalseを返します.
	 */
	private boolean isFillLine(int y)
	{
		if((y < 0) || (y > this.getHeight()))
			throw new IndexOutOfBoundsException();
		
		for(int i = 0; i < this.getWidth(); i++)
		{
			if(this.getCellAt(i, y).getState() != BlockState.FIXED)
				return false;
		}
		
		return true;
	}
	
	/**
	 * y行目のラインを削除します.
	 * @param y 削除するラインの行(Y座標)
	 */
	private void removeLine(int y)
	{
		if((y < 0) || (y > this.getHeight()))
			throw new IndexOutOfBoundsException();
		
		for(int j = y - 1; j >= 0; j--)
		{
			for(int i = 0; i < this.getWidth(); i++)
			{
				this.setCellAt(i, j + 1, this.getCellAt(i, j));
			}
		}
		
		for(int i = 0; i < this.getWidth(); i++)
		{
			this.setCellAt(i, 0, this.noneCell);
		}
	}
	
	private boolean rotation(Block2D<T> rotated, int range, Direction priority)
	{
		boolean canPut = true;
		int offsetX = 0;
		int offsetY = 0;
		
		// 設置可能になる位置を探索
		while(!this.canPutBlock(this.x + offsetX, this.y, rotated))
		{
			if(this.x + offsetX < 0)
			{
				offsetX++;
			}
			else if(this.x + rotated.getWidth() + offsetX >= this.getWidth())
			{
				offsetX--;
			}
			else
			{
				canPut = false;
				break;
			}
		}
		
		if(!canPut)
		{
			int dist;
			int minDist = 255;
			int x = 0, y = 0;
			range = Math.abs(range);
			
			for(int j = -range; j <= range; j++)
			{
				for(int i = -range; i <= range; i++)
				{
					dist = Math.abs(i) + Math.abs(j);
					
					// 優先方向重み付け
					switch(priority)
					{
					case Left:
						if(i > 0) dist++;
						break;
					case Right:
						if(i < 0) dist++;
						break;
					default:
						break;						
					}
					
					if((dist < minDist)
						&& this.canPutBlock(this.x + offsetX + i,
							this.y + offsetY + j, rotated))
					{
						canPut = true;
						
						minDist = dist;
						x = i;
						y = j;
					}
				}
			}
			
			if(canPut)
			{
				offsetX += x;
				offsetY += y;
			}
		}
		
		if(canPut)
		{
			this.removeMoveBlock();
			this.putBlock(this.x + offsetX, this.y + offsetY, rotated);
		}
		
		return canPut;
	}
	
	private enum Direction { Left, Right }

	@Override
	public int getWidth()
	{
		return this.width;
	}

	@Override
	public int getHeight()
	{
		return this.height;
	}

	@Override
	public BlockCell<T> getCellAt(int x, int y)
	{
		return this.field.get(y * this.width + x);
	}
	
	private void setCellAt(int x, int y, BlockCell<T> cell)
	{
		this.field.set(y * this.width + x, cell);
	}
	
	private static class DefaultFixer<T> implements IBlockFixer<T>
	{
		public DefaultFixer()
		{ }
		
		@Override
		public BlockCell<T> fixBlock(BlockCell<T> cell)
		{
			return new BlockCell<>(BlockState.FIXED, cell.getTag());
		}
	}
}
