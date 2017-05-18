package model.tetris.common;

import model.score.IScore;

/**
 * ブロックが固定されたときに呼び出されるハンドラ
 */
public interface IBlockFixedHandler
{
	/**
	 * ブロックが固定されたときに呼び出されます.
	 * @param score 固定時のスコア状態
	 */
	void execute(IScore score);
}
