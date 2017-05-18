package model.tetris.block3d;

import java.util.*;
import model.tetris.common.*;

/**
 * �u���b�N(�����̃Z���̏W��)�̃N���X
 * @param <T> �u���b�N�Z���̕ێ�����ǉ��f�[�^�̌^
 */
public class Block3D<T> implements IBlock3D<T>
{
	private final int width;
	private final int height;
	private final int depth;
	private final ArrayList<BlockCell<T>> cells;
	
	/**
	 * �u���b�N�����������܂�.
	 * @param width �u���b�N�̕�(�Z����)
	 * @param height �u���b�N�̍���(�Z����)
	 * @param depth �u���b�N�̐[��(�Z����)
	 * @param blocks �u���b�N�̃Z���̏����l
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
	 * ���̃u���b�N��X����]�����u���b�N���擾���܂�.
	 * @return X����]�����u���b�N
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
	 * ���̃u���b�N��Y����]�����u���b�N���擾���܂�.
	 * @return Y����]�����u���b�N
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
	 * ���̃u���b�N��Z����]�����u���b�N���擾���܂�.
	 * @return Z����]�����u���b�N
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
	 * (x,y)�̈ʒu�̃u���b�N�Z����ݒ肵�܂�
	 * @param x �ݒ肷��u���b�N�Z����X���W
	 * @param y �ݒ肷��u���b�N�Z����Y���W
	 * @param z �ݒ肷��u���b�N�Z����Z���W
	 * @param cell �ݒ肷��u���b�N�Z��
	 * @throws IndexOutOfBoundsException ���W���u���b�N�͈̔͊O
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
	 * Block�𐶐����邽�߂�Builder
	 * @param <T> �u���b�N�Z���̕ێ�����ǉ��f�[�^�̌^
	 */
	public static class Builder<T>
	{
		private final int width;
		private final int height;
		private final int depth;
		private final ArrayList<BlockCell<T>> blocks;
		
		/**
		 * �C���X�^���X�����������܂�.
		 * @param width ��������u���b�N�̕�
		 * @param height ��������u���b�N�̍���
		 * @param depth ��������u���b�N�̐[��
		 * @param defaultCell ��������u���b�N�̊e�Z���̏����l
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
		 * ��������Block��(x,y)�̈ʒu��BlockCell��ݒ肵�܂�.
		 * @param x �ݒ肷��BlockCell�ʒu��X���W
		 * @param y �ݒ肷��BlockCell�ʒu��Y���W
		 * @param z �ݒ肷��BlockCell�ʒu��Z���W
		 * @param cell �ݒ肷��BlockCell
		 * @return ���g�̃C���X�^���X
		 */
		public Builder<T> setCell(int x, int y, int z, BlockCell<T> cell)
		{
			this.blocks.set((z * this.height + y) * this.width + x, cell);
			return this;
		}
		
		/**
		 * Block�𐶐����܂�.
		 * @return Block�C���X�^���X
		 */
		public Block3D<T> build()
		{
			return new Block3D<>(this.width, this.height, this.depth, this.blocks);
		}
	}
}
