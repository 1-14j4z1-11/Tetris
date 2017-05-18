package model.tetris.common;

/**
 * ブロックセルの状態を扱う列挙体
 */
public enum BlockState
{
	/**
	 * ブロックなし
	 */
	NONE,
	
	/**
	 * 落下ブロック
	 */
	MOVE,
	
	/**
	 * 固定ブロック
	 */
	FIXED,
	
	/**
	 * ゴースト
	 */
	GHOST,
}
