package model.tetris.common;

import java.util.*;

/**
 * �����_���Ƀu���b�N�𐶐�����N���X
 */
public class RandomBlockCreator<T> implements IBlockCreator<T>
{
	private final List<T> blocks;
	private final boolean[] created;
	private int createdCount = 0;
	private Random random = new Random();

	/**
	 * �C���X�^���X�����������܂�
	 * @param blocks ���������u���b�N�̔z��
	 * @exception NullPointerException ������null�̏ꍇ
	 * @exception IllegalArgumentException �z��̗v�f����0�̏ꍇ
	 */
	public RandomBlockCreator(T[] blocks)
	{
		if(blocks == null)
			throw new NullPointerException();
		
		if(blocks.length == 0)
			throw new IllegalArgumentException("arg:\"blocks\" size must be positive.");
		
		this.blocks = Arrays.asList(blocks);
		this.created = new boolean[this.blocks.size()];
	}
	
	/**
	 * �C���X�^���X�����������܂�
	 * @param blocks ���������u���b�N�̃��X�g
	 * @exception NullPointerException ������null�̏ꍇ
	 * @exception IllegalArgumentException List�̗v�f����0�̏ꍇ
	 */
	public RandomBlockCreator(List<T> blocks)
	{
		if(blocks == null)
			throw new NullPointerException();
		
		if(blocks.size() == 0)
			throw new IllegalArgumentException("arg:\"blocks\" size must be positive.");
		
		this.blocks = new ArrayList<>(blocks);
		this.created = new boolean[this.blocks.size()];
	}
	
	@Override
	public T nextBlock()
	{
		int total = this.blocks.size();
		int index = random.nextInt(total - this.createdCount);
		
		for(int i = 0; i <= index; i++)
		{
			if(this.created[i])
				index++;
		}
		
		T block = this.blocks.get(index);
		this.created[index] = true;

		if(++this.createdCount == total)
		{
			this.createdCount = 0;
			
			for(int i = 0; i < this.created.length; i++)
			{
				this.created[i] = false;
			}
		}
		
		return block;
	}
}
