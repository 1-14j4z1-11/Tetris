package model.tetris.common;


public interface IBlockFixer<T>
{
	/** �Œ���BlockCell�𐶐����܂�. */
	BlockCell<T> fixBlock(BlockCell<T> cell);
}
