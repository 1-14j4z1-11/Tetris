package model.tetris.common;

/**
 * �u���b�N�𐶐����鏈�����������邽�߂̃C���^�[�t�F�[�X
 * @param <T> �u���b�N�̌^
 */
public interface IBlockCreator<T>
{
	T nextBlock();
}
