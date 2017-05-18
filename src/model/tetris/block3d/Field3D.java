package model.tetris.block3d;

import java.util.*;
import model.tetris.common.*;

public class Field3D<T> implements IBlock3D<T>
{
	// フィールド
	private final int width;
	private final int height;
	private final int depth;
	private final List<BlockCell<T>> field;
	private final BlockCell<T> noneCell;
	
	private IBlockFixer<T> fixer = new DefaultFixer<T>();
	
	private Block3D<T> block;
	private int x = -1;
	private int y = -1;
	private int z = -1;
	
	// コンストラクタ
	public Field3D(int width, int height, int depth, BlockCell<T> noneCell)
	{
		this.width = width;
		this.height = height;
		this.depth = depth;
		this.noneCell = noneCell;
		
		int length = width * height * depth;
		this.field = new ArrayList<>(length);
		
		for(int i = 0; i < length; i++)
		{
			this.field.add(noneCell);
		}
	}
	
	// アクセサ
	public Block3D<T> getDropBlock()
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
	
	public int getDropBlockZ()
	{
		return this.z;
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
		
		for(int k = 0; k < this.getDepth(); k++)
		{
			for(int j = 0; j < this.getHeight(); j++)
			{
				for(int i = 0; i < this.getWidth(); i++)
				{
					this.setCellAt(i, j, k, this.noneCell);
				}
			}
		}
	}
	
	/**
	 * 現在動いているブロック(ゴーストを含む)を削除します.
	 * @return 動いていたブロック
	 */
	public Block3D<T> removeMoveBlock()
	{
		for(int k = 0; k < this.getDepth(); k++)
		{
			for(int j = 0; j < this.getHeight(); j++)
			{
				for(int i = 0; i < this.getWidth(); i++)
				{
					BlockState state = this.getCellAt(i, j, k).getState();
					if((state == BlockState.MOVE) || (state == BlockState.GHOST))
					{
						this.setCellAt(i, j, k, noneCell);
					}
				}
			}
		}
		
		Block3D<T> block = this.block;
		this.block = null;
		
		return block;
	}
	
	/**
	 * 現在動いているブロックを固定し、ゴーストを削除します.
	 * @param tag 固定後の状態を示す値
	 */
	public void fixMoveBlock()
	{
		for(int k = 0; k < this.getDepth(); k++)
		{
			for(int j = 0; j < this.getHeight(); j++)
			{
				for(int i = 0; i < this.getWidth(); i++)
				{
					if(this.getCellAt(i, j, k).getState() == BlockState.MOVE)
					{
						this.setCellAt(i, j, k, this.fixer.fixBlock(this.getCellAt(i, j, k)));
					}
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
	public boolean canPutBlock(int x, int y, int z, Block3D<T> block)
	{
		if(block == null)
			throw new NullPointerException();
		
		for(int k = 0; k < block.getDepth(); k++)
		{
			for(int j = 0; j < block.getHeight(); j++)
			{
				for(int i = 0; i < block.getWidth(); i++)
				{
					if(block.getCellAt(i, j, k).getState() == BlockState.NONE)
					{
						continue;
					}
						
					// ブロックを設置する領域がエリア内かつ既存ブロックと被らないか判定
					if((x + i < 0) || (x + i >= this.getWidth())
						|| (y + j < 0) || (y + j >= this.getHeight())
						|| (z + k < 0) || (z + k >= this.getDepth()))
					{
						return false;
					}
					else if(this.getCellAt(x + i, y + j, z + k).getState() == BlockState.FIXED)
					{
						return false;
					}
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
	public boolean putBlock(int x, int y, int z, Block3D<T> block)
	{
		// 設置可能判定
		if(!this.canPutBlock(x, y, z, block))
			return false;
		
		// ブロック設置
		
		for(int k = 0; k < block.getDepth(); k++)
		{
			for(int j = 0; j < block.getHeight(); j++)
			{
				for(int i = 0; i < block.getWidth(); i++)
				{
					if(block.getCellAt(i, j, k).getState() != BlockState.NONE)
					{
						this.setCellAt(x + i, y + j, z + k, block.getCellAt(i, j, k));
					}
				}
			}
		}
		
		// ブロックを登録
		this.x = x;
		this.y = y;
		this.z = z;
		this.block = block;
		
		return true;
	}
	
	/**
	 * 現在動いているブロックを移動させます.
	 * @param offsetX X方向の移動量
	 * @param offsetY Y方向の移動量
	 * @return 移動に成功した場合はtrueを返し、そうでない場合はfalseを返します.
	 */
	public boolean move(int offsetX, int offsetY, int offsetZ)
	{
		if(this.block == null)
			return false;
		
		if(!this.canPutBlock(this.x + offsetX, this.y + offsetY, this.z + offsetZ, this.block))
			return false;
		
		Block3D<T> block = this.removeMoveBlock();
		this.putBlock(this.x + offsetX, this.y + offsetY, this.z + offsetZ, block);
		
		return true;
	}
	
	public boolean rotateX()
	{
		if(this.block == null)
			return false;
		
		return this.rotation(this.block.rotateX(), 1);
	}
	
	public boolean rotateY()
	{
		if(this.block == null)
			return false;
		
		return this.rotation(this.block.rotateY(), 1);
	}
	
	public boolean rotateZ()
	{
		if(this.block == null)
			return false;
		
		return this.rotation(this.block.rotateZ(), 1);
	}
	
	/**
	 * 固定ブロックで埋まっているラインを全て削除します
	 * @return 削除したライン数を返します. 削除するラインがなかった場合は0を返します.
	 */
	public int clearFillPlane()
	{
		int count = 0;
		
		for(int k = 0; k < this.getDepth(); k++)
		{
			if(this.isFillPlane(k))
			{
				this.removePlane(k);
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
	private boolean isFillPlane(int z)
	{
		if((z < 0) || (z > this.getDepth()))
			throw new IndexOutOfBoundsException();
		
		for(int j = 0; j < this.getHeight(); j++)
		{
			for(int i = 0; i < this.getWidth(); i++)
			{
				if(this.getCellAt(i, j, z).getState() != BlockState.FIXED)
					return false;
			}
		}
		
		return true;
	}
	
	/**
	 * y行目のラインを削除します.
	 * @param y 削除するラインの行(Y座標)
	 */
	private void removePlane(int z)
	{
		if((z < 0) || (z > this.getDepth()))
			throw new IndexOutOfBoundsException();
		
		for(int k = z - 1; k >= 0; k--)
		{
			for(int j = 0; j < this.getHeight(); j++)
			{
				for(int i = 0; i < this.getWidth(); i++)
				{
					this.setCellAt(i, j, k + 1, this.getCellAt(i, j, k));
				}
			}
		}
		
		for(int j = 0; j < this.getHeight(); j++)
		{
			for(int i = 0; i < this.getWidth(); i++)
			{
				this.setCellAt(i, j, 0, this.noneCell);
			}
		}
	}
	
	private boolean rotation(Block3D<T> rotated, int range)
	{
		boolean canPut = true;
		int offsetX = 0;
		int offsetY = 0;
		int offsetZ = 0;
		
		// 設置可能になる位置を探索
		while(!this.canPutBlock(this.x + offsetX, this.y + offsetY, this.z, rotated))
		{
			if(this.x + offsetX < 0)
			{
				offsetX++;
			}
			else if(this.x + rotated.getWidth() + offsetX >= this.getWidth())
			{
				offsetX--;
			}
			else if(this.y + offsetY < 0)
			{
				offsetY++;
			}
			else if(this.y + rotated.getHeight() + offsetY >= this.getHeight())
			{
				offsetY--;
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
			int x = 0, y = 0, z = 0;
			range = Math.abs(range);

			for(int k = -range; k <= range; k++)
			{
				for(int j = -range; j <= range; j++)
				{
					for(int i = -range; i <= range; i++)
					{
						dist = Math.abs(i) + Math.abs(j);
						
						if((dist < minDist)
							&& this.canPutBlock(this.x + offsetX + i, this.y + offsetY + j, this.z + offsetZ + k, rotated))
						{
							canPut = true;
							
							minDist = dist;
							x = i;
							y = j;
							z = k;
						}
					}
				}
			}
			
			if(canPut)
			{
				offsetX += x;
				offsetY += y;
				offsetZ += z;
			}
		}
		
		if(canPut)
		{
			this.removeMoveBlock();
			this.putBlock(this.x + offsetX, this.y + offsetY, this.z + offsetZ, rotated);
		}
		
		return canPut;
	}
	
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
	public int getDepth()
	{
		return this.depth;
	}

	@Override
	public BlockCell<T> getCellAt(int x, int y, int z)
	{
		return this.field.get((z * this.height + y) * this.width + x);
	}
	
	private void setCellAt(int x, int y, int z, BlockCell<T> cell)
	{
		this.field.set((z * this.height + y) * this.width + x, cell);
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
