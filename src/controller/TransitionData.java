package controller;

import java.util.*;

/**
 * ViewModelの遷移時に受け渡しするデータのコンテナ
 */
// package
final class TransitionData
{
	private final HashMap<Class<?>, Object> map = new HashMap<>();
	
	public TransitionData()
	{ }

	public <T> TransitionData(Class<T> type, T value)
	{
		this.addData(type, value);
	}
	
	/**
	 * データを追加します
	 * @param type 追加するデータの型
	 * @param value 追加するデータ
	 * @return 自身のインスタンス
	 */
	public final <T> TransitionData addData(Class<T> type, T value)
	{
		this.map.put(type, value);
		return this;
	}
	
	/**
	 * データを取得します
	 * @param type 取得するデータの型
	 * @return 指定された型のデータが存在する場合はデータを返し、存在しない場合はnullを返します
	 */
	public final <T> T getData(Class<T> type)
	{
		Object value = this.map.get(type);
		return (value != null) ? type.cast(value) : null;
	}
	
	/**
	 * 指定された型のデータが含まれているかどうか判定します
	 * @param type 判定する型
	 * @return データが含まれている場合はtrueを返し、含まれていない場合はfalseを返します
	 */
	public final <T> boolean contains(Class<T> type)
	{
		return this.map.containsKey(type);
	}
}
