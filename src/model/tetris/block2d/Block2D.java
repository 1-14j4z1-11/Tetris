package model.tetris.block2d;

import java.util.*;
import model.tetris.common.BlockCell;

/**
 * ブロック(複数個のセルの集合)のクラス
 * @param <T> ブロックセルの保持する追加データの型
 */
public class Block2D<T> implements IBlock2D<T>
{
	private final int width;
	private final int height;
	private final ArrayList<BlockCell<T>> cells;
	
	/**
	 * ブロックを初期化します.
	 * @param width ブロックの幅(セル数)
	 * @param height ブロックの高さ(セル数)
	 * @param defaultCell ブロックのセルの初期値
	 */
	protected Block2D(int width, int height, List<BlockCell<T>> blocks)
	{
		this.width = width;
		this.height = height;
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
	public final BlockCell<T> getCellAt(int x, int y)
	{
		if((x < 0) || (x >= this.width) || (y < 0) || (y >= this.height))
			throw new IndexOutOfBoundsException("arg:(" + x + "," + y + ") size:(" + this.width + "," + this.height + ")");
		
		return this.cells.get(y * this.width + x);
	}
	
	/**
	 * このブロックを左回転したブロックを取得します.
	 * @return 左回転したブロック
	 */
	public final Block2D<T> rotateL()
	{
		List<BlockCell<T>> cells = new ArrayList<>(this.width * this.height);
		
		for(int j = this.height - 1; j >= 0 ; j--)
		{
			for(int i = 0; i < this.width; i++)
			{
				cells.add(this.cells.get(i * this.height + j));
			}
		}
		
		return new Block2D<T>(this.height, this.width, cells);
	}

	/**
	 * このブロックを右回転したブロックを取得します.
	 * @return 右回転したブロック
	 */
	public Block2D<T> rotateR()
	{
		List<BlockCell<T>> cells = new ArrayList<>(this.width * this.height);
		
		for(int j = 0; j < this.height; j++)
		{
			for(int i = this.width - 1; i >= 0; i--)
			{
				cells.add(this.cells.get(i * this.height + j));
			}
		}
		
		return new Block2D<T>(this.height, this.width, cells);
	}
	
	/**
	 * (x,y)の位置のブロックセルを設定します
	 * @param x 設定するブロックセルのX座標
	 * @param y 設定するブロックセルのY座標
	 * @param cell 設定するブロックセル
	 * @throws IndexOutOfBoundsException 座標がブロックの範囲外
	 */
	protected void setCellAt(int x, int y, BlockCell<T> cell)
	{
		if((x < 0) || (x >= this.width) || (y < 0) || (y >= this.height))
			throw new IndexOutOfBoundsException();
		
		this.cells.set(y * this.width + x, cell);
	}
	
	/**
	 * Blockを生成するためのBuilder
	 * @param <T> ブロックセルの保持する追加データの型
	 */
	public static class Builder<T>
	{
		private final int width;
		private final int height;
		private final ArrayList<BlockCell<T>> blocks;
		
		/**
		 * インスタンスを初期化します.
		 * @param width 生成するブロックの幅
		 * @param height 生成するブロックの高さ
		 * @param defaultCell 生成するブロックの各セルの初期値
		 */
		public Builder(int width, int height, BlockCell<T> defaultCell)
		{
			this.width = width;
			this.height = height;
			
			int length = width * height;
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
		 * @param cell 設定するBlockCell
		 * @return 自身のインスタンス
		 */
		public Builder<T> setCell(int x, int y, BlockCell<T> cell)
		{
			this.blocks.set(y * this.width + x, cell);
			return this;
		}
		
		/**
		 * Blockを生成します.
		 * @return Blockインスタンス
		 */
		public Block2D<T> build()
		{
			return new Block2D<>(this.width, this.height, this.blocks);
		}
	}
}
