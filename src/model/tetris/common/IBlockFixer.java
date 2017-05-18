package model.tetris.common;


public interface IBlockFixer<T>
{
	/** ŒÅ’èŒã‚ÌBlockCell‚ğ¶¬‚µ‚Ü‚·. */
	BlockCell<T> fixBlock(BlockCell<T> cell);
}
