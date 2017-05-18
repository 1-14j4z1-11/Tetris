package model.tetris.common;

import java.util.*;

/**
 * 順序どおりにブロックを生成するクラス
 */
public class OrderedBlockCreator<T> implements IBlockCreator<T>
{
	private final List<T> blocks;
	private int index = 0;

	/**
	 * インスタンスを初期化します
	 * @param blocks 生成されるブロックの配列
	 * @exception NullPointerException 引数がnullの場合
	 * @exception IllegalArgumentException 配列の要素数が0の場合
	 */
	public OrderedBlockCreator(T[] blocks)
	{
		if(blocks == null)
			throw new NullPointerException();
		
		if(blocks.length == 0)
			throw new IllegalArgumentException("arg:\"blocks\" size must be positive.");
		
		this.blocks = Arrays.asList(blocks);
	}
	
	/**
	 * インスタンスを初期化します
	 * @param blocks 生成されるブロックのリスト
	 * @exception NullPointerException 引数がnullの場合
	 * @exception IllegalArgumentException Listの要素数が0の場合
	 */
	public OrderedBlockCreator(List<T> blocks)
	{
		if(blocks == null)
			throw new NullPointerException();
		
		if(blocks.size() == 0)
			throw new IllegalArgumentException("arg:\"blocks\" size must be positive.");
		
		this.blocks = new ArrayList<>(blocks);
	}
	
	@Override
	public T nextBlock()
	{
		T block = this.blocks.get(this.index);
		this.index = (this.index + 1) % this.blocks.size();
		
		return block;
	}
}
