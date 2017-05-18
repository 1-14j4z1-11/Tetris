package model.tetris.block2d;

import java.util.*;
import model.tetris.common.BlockCell;
import model.tetris.common.BlockState;
import model.tetris.common.IBlockFixer;

public class Field2D<T> implements IBlock2D<T>
{
	// �t�B�[���h
	private final int width;
	private final int height;
	private final List<BlockCell<T>> field;
	private final BlockCell<T> noneCell;
	
	private IBlockFixer<T> fixer = new DefaultFixer<T>();
	
	private Block2D<T> block;
	private int x = -1;
	private int y = -1;
	
	// �R���X�g���N�^
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
	
	// �A�N�Z�T
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
	 * ������Ԃɖ߂��܂�
	 *   �E�����u���b�N���폜
	 *   �E�S�Ẵt�B�[���h���̃u���b�N���폜
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
	 * ���ݓ����Ă���u���b�N(�S�[�X�g���܂�)���폜���܂�.
	 * @return �����Ă����u���b�N
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
	 * ���ݓ����Ă���u���b�N���Œ肵�A�S�[�X�g���폜���܂�.
	 * @param tag �Œ��̏�Ԃ������l
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

				// �u���b�N��ݒu����̈悪�G���A���������u���b�N�Ɣ��Ȃ�������
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
	 * �ʒu(x,y)��block��ݒu���܂�.
	 * @param x �u���b�N�̍����X���W
	 * @param y �u���b�N�̍����Y���W
	 * @param block �ݒu����u���b�N
	 * @return �u���b�N�̐ݒu�ɐ��������ꍇ��true��Ԃ�, �����łȂ��ꍇ��false��Ԃ��܂�.
	 * @throws NullPointerException block��null�̏ꍇ
	 */
	public boolean putBlock(int x, int y, Block2D<T> block)
	{
		// �ݒu�\����
		if(!this.canPutBlock(x, y, block))
			return false;
		
		// �u���b�N�ݒu
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

		// �u���b�N��o�^
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
	 * ���ݓ����Ă���u���b�N���ړ������܂�.
	 * @param offsetX X�����̈ړ���
	 * @param offsetY Y�����̈ړ���
	 * @return �ړ��ɐ��������ꍇ��true��Ԃ��A�����łȂ��ꍇ��false��Ԃ��܂�.
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
	 * �Œ�u���b�N�Ŗ��܂��Ă��郉�C����S�č폜���܂�
	 * @return �폜�������C������Ԃ��܂�. �폜���郉�C�����Ȃ������ꍇ��0��Ԃ��܂�.
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
	
	// �������\�b�h
	/**
	 * y�s�ڂ̃��C�����S�ČŒ�u���b�N�Ŗ��܂��Ă��邩�ǂ����𔻒肵�܂�.
	 * @param y ���肷��s(Y���W)
	 * @return ���C�����S�ČŒ�u���b�N�ł����true��Ԃ��A�����łȂ��ꍇ��false��Ԃ��܂�.
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
	 * y�s�ڂ̃��C�����폜���܂�.
	 * @param y �폜���郉�C���̍s(Y���W)
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
		
		// �ݒu�\�ɂȂ�ʒu��T��
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
					
					// �D������d�ݕt��
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
