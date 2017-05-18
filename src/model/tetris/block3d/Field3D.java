package model.tetris.block3d;

import java.util.*;
import model.tetris.common.*;

public class Field3D<T> implements IBlock3D<T>
{
	// �t�B�[���h
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
	
	// �R���X�g���N�^
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
	
	// �A�N�Z�T
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
	 * ������Ԃɖ߂��܂�
	 *   �E�����u���b�N���폜
	 *   �E�S�Ẵt�B�[���h���̃u���b�N���폜
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
	 * ���ݓ����Ă���u���b�N(�S�[�X�g���܂�)���폜���܂�.
	 * @return �����Ă����u���b�N
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
	 * ���ݓ����Ă���u���b�N���Œ肵�A�S�[�X�g���폜���܂�.
	 * @param tag �Œ��̏�Ԃ������l
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
		
		this.removeMoveBlock();	// Ghost�폜
	}
	
	/**
	 * �ʒu(x,y)��block���ݒu�\���ǂ����𔻒肵�܂�.
	 * @param x �u���b�N�̍����X���W
	 * @param y �u���b�N�̍����Y���W
	 * @param block �ݒu����u���b�N
	 * @return �u���b�N���ݒu�\�ȏꍇ��true��Ԃ�, �����łȂ��ꍇ��false��Ԃ��܂�.
	 * @throws NullPointerException block��null�̏ꍇ
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
						
					// �u���b�N��ݒu����̈悪�G���A���������u���b�N�Ɣ��Ȃ�������
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
	 * �ʒu(x,y)��block��ݒu���܂�.
	 * @param x �u���b�N�̍����X���W
	 * @param y �u���b�N�̍����Y���W
	 * @param block �ݒu����u���b�N
	 * @return �u���b�N�̐ݒu�ɐ��������ꍇ��true��Ԃ�, �����łȂ��ꍇ��false��Ԃ��܂�.
	 * @throws NullPointerException block��null�̏ꍇ
	 */
	public boolean putBlock(int x, int y, int z, Block3D<T> block)
	{
		// �ݒu�\����
		if(!this.canPutBlock(x, y, z, block))
			return false;
		
		// �u���b�N�ݒu
		
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
		
		// �u���b�N��o�^
		this.x = x;
		this.y = y;
		this.z = z;
		this.block = block;
		
		return true;
	}
	
	/**
	 * ���ݓ����Ă���u���b�N���ړ������܂�.
	 * @param offsetX X�����̈ړ���
	 * @param offsetY Y�����̈ړ���
	 * @return �ړ��ɐ��������ꍇ��true��Ԃ��A�����łȂ��ꍇ��false��Ԃ��܂�.
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
	 * �Œ�u���b�N�Ŗ��܂��Ă��郉�C����S�č폜���܂�
	 * @return �폜�������C������Ԃ��܂�. �폜���郉�C�����Ȃ������ꍇ��0��Ԃ��܂�.
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
	
	// �������\�b�h
	/**
	 * y�s�ڂ̃��C�����S�ČŒ�u���b�N�Ŗ��܂��Ă��邩�ǂ����𔻒肵�܂�.
	 * @param y ���肷��s(Y���W)
	 * @return ���C�����S�ČŒ�u���b�N�ł����true��Ԃ��A�����łȂ��ꍇ��false��Ԃ��܂�.
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
	 * y�s�ڂ̃��C�����폜���܂�.
	 * @param y �폜���郉�C���̍s(Y���W)
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
		
		// �ݒu�\�ɂȂ�ʒu��T��
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
