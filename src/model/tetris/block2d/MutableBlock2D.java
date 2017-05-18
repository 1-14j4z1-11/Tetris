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
	 * (x,y)�̃u���b�N�Z����ݒ肵�܂�.
	 * @param x �u���b�N�Z����ݒ肷��ʒu��X���W
	 * @param y �u���b�N�Z����ݒ肷��ʒu��Y���W
	 * @param cell �ݒu����Z��
	 * @return ���̃C���X�^���X���g
	 */
	public MutableBlock2D<T> setCellAt(int x, int y, BlockCell<T> cell)
	{
		this.cells.set(y * this.width + x, cell);
		return this;
	}
	
	/**
	 * �u���b�N�Z����S�č폜���܂�
	 * @return ���̃C���X�^���X���g
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
