package model.tetris.block3d;

import java.util.*;
import model.tetris.common.*;

/**
 * ブロック(複数個のセルの集合)のクラス
 * @param <T> ブロックセルの保持する追加データの型
 */
public class Block3D<T> implements IBlock3D<T>
{
	private final int width;
	private final int height;
	private final int depth;
	private final ArrayList<BlockCell<T>> cells;
	
	/**
	 * ブロックを初期化します.
	 * @param width ブロックの幅(セル数)
	 * @param height ブロックの高さ(セル数)
	 * @param depth ブロックの深さ(セル数)
	 * @param blocks ブロックのセルの初期値
	 */
	protected Block3D(int width, int height, int depth, List<BlockCell<T>> blocks)
	{
		this.width = width;
		this.height = height;
		this.depth = depth;
		this.cells = new ArrayList<>(blocks);
	}
	
	@Override
	public final int getWidth()
	{
		return this.width;
	}

	@Override
	public final int getHeight()
	{
		return this.height;
	}

	@Override
	public final int getDepth()
	{
		return this.depth;
	}

	@Override
	public final BlockCell<T> getCellAt(int x, int y, int z)
	{
		if((x < 0) || (x >= this.width)
			|| (y < 0) || (y >= this.height)
			|| (z < 0) || (z >= this.depth))
			throw new IndexOutOfBoundsException("arg:(" + x + "," + y + "," + z + ") size:(" + this.width + "," + this.height + "," + this.depth + ")");
		
		return this.cells.get((z * this.height + y) * this.width + x);
	}

	/**
	 * このブロックをX軸回転したブロックを取得します.
	 * @return X軸回転したブロック
	 */
	public final Block3D<T> rotateX()
	{
		List<BlockCell<T>> cells = new ArrayList<>(this.width * this.height * this.depth);
		
		for(int k = 0; k < this.depth; k++)
		{
			for(int j = 0; j < this.height; j++)
			{
				for(int i = 0; i < this.width; i++)
				{
					cells.add(this.getCellAt(i, this.depth - k - 1, j));
				}
			}
		}
		
		return new Block3D<T>(this.width, this.depth, this.height, cells);
	}

	/**
	 * このブロックをY軸回転したブロックを取得します.
	 * @return Y軸回転したブロック
	 */
	public final Block3D<T> rotateY()
	{
		List<BlockCell<T>> cells = new ArrayList<>(this.width * this.height * this.depth);

		for(int k = 0; k < this.depth; k++)
		{
			for(int j = 0; j < this.height; j++)
			{
				for(int i = 0; i < this.width; i++)
				{
					cells.add(this.getCellAt(k, j, this.width - i - 1));
				}
			}
		}
		
		return new Block3D<T>(this.depth, this.height, this.width, cells);
	}

	/**
	 * このブロックをZ軸回転したブロックを取得します.
	 * @return Z軸回転したブロック
	 */
	public final Block3D<T> rotateZ()
	{
		List<BlockCell<T>> cells = new ArrayList<>(this.width * this.height * this.depth);

		for(int k = 0; k < this.depth; k++)
		{
			for(int j = 0; j < this.height; j++)
			{
				for(int i = 0; i < this.width; i++)
				{
					cells.add(this.getCellAt(this.height - j - 1, i, k));
				}
			}
		}
		
		return new Block3D<T>(this.height, this.width, this.depth, cells);
	}

	/**
	 * (x,y)の位置のブロックセルを設定します
	 * @param x 設定するブロックセルのX座標
	 * @param y 設定するブロックセルのY座標
	 * @param z 設定するブロックセルのZ座標
	 * @param cell 設定するブロックセル
	 * @throws IndexOutOfBoundsException 座標がブロックの範囲外
	 */
	protected void setCellAt(int x, int y, int z, BlockCell<T> cell)
	{
		if((x < 0) || (x >= this.width)
			|| (y < 0) || (y >= this.height)
			|| (z < 0) || (z >= this.depth))
			throw new IndexOutOfBoundsException();
		
		this.cells.set((z * this.height + y) * this.width + x, cell);
	}
	
	/**
	 * Blockを生成するためのBuilder
	 * @param <T> ブロックセルの保持する追加データの型
	 */
	public static class Builder<T>
	{
		private final int width;
		private final int height;
		private final int depth;
		private final ArrayList<BlockCell<T>> blocks;
		
		/**
		 * インスタンスを初期化します.
		 * @param width 生成するブロックの幅
		 * @param height 生成するブロックの高さ
		 * @param depth 生成するブロックの深さ
		 * @param defaultCell 生成するブロックの各セルの初期値
		 */
		public Builder(int width, int height, int depth, BlockCell<T> defaultCell)
		{
			this.width = width;
			this.height = height;
			this.depth = depth;
			
			int length = width * height * depth;
			this.blocks = new ArrayList<BlockCell<T>>(length);
			
			for(int i = 0; i < length; i++)
			{
				this.blocks.add(defaultCell);
			}
		}
		
		/**
		 * 生成するBlockの(x,y)の位置のBlockCellを設定します.
		 * @param x 設定するBlockCell位置のX座標
		 * @param y 設定するBlockCell位置のY座標
		 * @param z 設定するBlockCell位置のZ座標
		 * @param cell 設定するBlockCell
		 * @return 自身のインスタンス
		 */
		public Builder<T> setCell(int x, int y, int z, BlockCell<T> cell)
		{
			this.blocks.set((z * this.height + y) * this.width + x, cell);
			return this;
		}
		
		/**
		 * Blockを生成します.
		 * @return Blockインスタンス
		 */
		public Block3D<T> build()
		{
			return new Block3D<>(this.width, this.height, this.depth, this.blocks);
		}
	}
}
