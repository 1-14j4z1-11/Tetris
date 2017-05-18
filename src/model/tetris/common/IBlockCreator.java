package model.tetris.common;

/**
 * ブロックを生成する処理を実現するためのインターフェース
 * @param <T> ブロックの型
 */
public interface IBlockCreator<T>
{
	T nextBlock();
}
