package model.tetris.common;

import java.util.*;

/**
 * �����ǂ���Ƀu���b�N�𐶐�����N���X
 */
public class OrderedBlockCreator<T> implements IBlockCreator<T>
{
	private final List<T> blocks;
	private int index = 0;

	/**
	 * �C���X�^���X�����������܂�
	 * @param blocks ���������u���b�N�̔z��
	 * @exception NullPointerException ������null�̏ꍇ
	 * @exception IllegalArgumentException �z��̗v�f����0�̏ꍇ
	 */
	public OrderedBlockCreator(T[] blocks)
	{
		if(blocks == null)
			throw new NullPointerException();
		
		if(blocks.length == 0)
			throw new IllegalArgumentException("arg:\"blocks\" size must be positive.");
		
		this.blocks = Arrays.asList(blocks);
	}
	
	/**
	 * �C���X�^���X�����������܂�
	 * @param blocks ���������u���b�N�̃��X�g
	 * @exception NullPointerException ������null�̏ꍇ
	 * @exception IllegalArgumentException List�̗v�f����0�̏ꍇ
	 */
	public OrderedBlockCreator(List<T> blocks)
	{
		if(blocks == null)
			throw new NullPointerException();
		
		if(blocks.size() == 0)
			throw new IllegalArgumentException("arg:\"blocks\" size must be positive.");
		
		this.blocks = new ArrayList<>(blocks);
	}
	
	@Override
	public T nextBlock()
	{
		T block = this.blocks.get(this.index);
		this.index = (this.index + 1) % this.blocks.size();
		
		return block;
	}
}
