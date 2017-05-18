package model.tetris.block2d;

import java.util.*;
import model.tetris.common.BlockCell;

public class MutableBlock2D<T> implements IBlock2D<T>
{
	private final int width;
	private final int height;
	private final List<BlockCell<T>> cells;
	private final BlockCell<T> defaultCell;
	
	public MutableBlock2D(int width, int height, BlockCell<T> defaultCell)
	{
		this.width = width;
		this.height = height;
		this.defaultCell = defaultCell;
		
		int length = width * height;
		this.cells = new ArrayList<>(length);
		
		for(int i = 0; i < length; i++)
		{
			this.cells.add(defaultCell);
		}
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
	public BlockCell<T> getCellAt(int x, int y)
	{
		return this.cells.get(y * this.width + x);
	}
	
	/**
	 * (x,y)のブロックセルを設定します.
	 * @param x ブロックセルを設定する位置のX座標
	 * @param y ブロックセルを設定する位置のY座標
	 * @param cell 設置するセル
	 * @return このインスタンス自身
	 */
	public MutableBlock2D<T> setCellAt(int x, int y, BlockCell<T> cell)
	{
		this.cells.set(y * this.width + x, cell);
		return this;
	}
	
	/**
	 * ブロックセルを全て削除します
	 * @return このインスタンス自身
	 */
	public MutableBlock2D<T> clear()
	{
		for(int i = 0; i < this.width * this.height; i++)
		{
			this.cells.set(i, this.defaultCell);
		}
		return this;
	}
}
