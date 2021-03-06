package model.tetris.common;


public interface IBlockFixer<T>
{
	/** 固定後のBlockCellを生成します. */
	BlockCell<T> fixBlock(BlockCell<T> cell);
}
