package model.tetris.common;

/**
 * �u���b�N�Z��(�u���b�N1��)�̃N���X
 * @param <T> �u���b�N�Z�����ێ�����ǉ��f�[�^�̃N���X
 */
public class BlockCell<T>
{
	private final BlockState state;
	private final T tag;
	
	/**
	 * �u���b�N�Z�������������܂�
	 * @param state �u���b�N�̏��
	 * @param tag �ǉ��f�[�^
	 */
	public BlockCell(BlockState state, T tag)
	{
		this.state = state;
		this.tag = tag;
	}
	
	/**
	 * �u���b�N�Z���̏�Ԃ��擾���܂�
	 */
	public final BlockState getState()
	{
		return this.state;
	}
	
	/**
	 * �u���b�N�Z���̒ǉ��f�[�^���擾���܂�
	 */
	public final T getTag()
	{
		return this.tag;
	}
}
