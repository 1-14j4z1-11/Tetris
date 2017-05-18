package model.tetris.common;

/**
 * ブロックセル(ブロック1個)のクラス
 * @param <T> ブロックセルが保持する追加データのクラス
 */
public class BlockCell<T>
{
	private final BlockState state;
	private final T tag;
	
	/**
	 * ブロックセルを初期化します
	 * @param state ブロックの状態
	 * @param tag 追加データ
	 */
	public BlockCell(BlockState state, T tag)
	{
		this.state = state;
		this.tag = tag;
	}
	
	/**
	 * ブロックセルの状態を取得します
	 */
	public final BlockState getState()
	{
		return this.state;
	}
	
	/**
	 * ブロックセルの追加データを取得します
	 */
	public final T getTag()
	{
		return this.tag;
	}
}
