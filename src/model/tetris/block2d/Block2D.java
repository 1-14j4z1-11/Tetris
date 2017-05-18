package model.tetris.block2d;

import java.util.*;
import model.tetris.common.BlockCell;

/**
 * �u���b�N(�����̃Z���̏W��)�̃N���X
 * @param <T> �u���b�N�Z���̕ێ�����ǉ��f�[�^�̌^
 */
public class Block2D<T> implements IBlock2D<T>
{
	private final int width;
	private final int height;
	private final ArrayList<BlockCell<T>> cells;
	
	/**
	 * �u���b�N�����������܂�.
	 * @param width �u���b�N�̕�(�Z����)
	 * @param height �u���b�N�̍���(�Z����)
	 * @param defaultCell �u���b�N�̃Z���̏����l
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
	 * ���̃u���b�N������]�����u���b�N���擾���܂�.
	 * @return ����]�����u���b�N
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
	 * ���̃u���b�N���E��]�����u���b�N���擾���܂�.
	 * @return �E��]�����u���b�N
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
	 * (x,y)�̈ʒu�̃u���b�N�Z����ݒ肵�܂�
	 * @param x �ݒ肷��u���b�N�Z����X���W
	 * @param y �ݒ肷��u���b�N�Z����Y���W
	 * @param cell �ݒ肷��u���b�N�Z��
	 * @throws IndexOutOfBoundsException ���W���u���b�N�͈̔͊O
	 */
	protected void setCellAt(int x, int y, BlockCell<T> cell)
	{
		if((x < 0) || (x >= this.width) || (y < 0) || (y >= this.height))
			throw new IndexOutOfBoundsException();
		
		this.cells.set(y * this.width + x, cell);
	}
	
	/**
	 * Block�𐶐����邽�߂�Builder
	 * @param <T> �u���b�N�Z���̕ێ�����ǉ��f�[�^�̌^
	 */
	public static class Builder<T>
	{
		private final int width;
		private final int height;
		private final ArrayList<BlockCell<T>> blocks;
		
		/**
		 * �C���X�^���X�����������܂�.
		 * @param width ��������u���b�N�̕�
		 * @param height ��������u���b�N�̍���
		 * @param defaultCell ��������u���b�N�̊e�Z���̏����l
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
		 * ��������Block��(x,y)�̈ʒu��BlockCell��ݒ肵�܂�.
		 * @param x �ݒ肷��BlockCell�ʒu��X���W
		 * @param y �ݒ肷��BlockCell�ʒu��Y���W
		 * @param cell �ݒ肷��BlockCell
		 * @return ���g�̃C���X�^���X
		 */
		public Builder<T> setCell(int x, int y, BlockCell<T> cell)
		{
			this.blocks.set(y * this.width + x, cell);
			return this;
		}
		
		/**
		 * Block�𐶐����܂�.
		 * @return Block�C���X�^���X
		 */
		public Block2D<T> build()
		{
			return new Block2D<>(this.width, this.height, this.blocks);
		}
	}
}
