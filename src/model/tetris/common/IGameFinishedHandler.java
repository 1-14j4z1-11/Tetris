package model.tetris.common;

/**
 * ゲーム終了時に呼び出されるリスナ
 */
public interface IGameFinishedHandler
{
	/**
	 * ゲーム終了時に呼び出されます.
	 */
	void execute();
}
