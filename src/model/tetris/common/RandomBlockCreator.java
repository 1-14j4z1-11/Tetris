package model.tetris.common;

import java.util.*;

/**
 * ランダムにブロックを生成するクラス
 */
public class RandomBlockCreator<T> implements IBlockCreator<T>
{
	private final List<T> blocks;
	private final boolean[] created;
	private int createdCount = 0;
	private Random random = new Random();

	/**
	 * インスタンスを初期化します
	 * @param blocks 生成されるブロックの配列
	 * @exception NullPointerException 引数がnullの場合
	 * @exception IllegalArgumentException 配列の要素数が0の場合
	 */
	public RandomBlockCreator(T[] blocks)
	{
		if(blocks == null)
			throw new NullPointerException();
		
		if(blocks.length == 0)
			throw new IllegalArgumentException("arg:\"blocks\" size must be positive.");
		
		this.blocks = Arrays.asList(blocks);
		this.created = new boolean[this.blocks.size()];
	}
	
	/**
	 * インスタンスを初期化します
	 * @param blocks 生成されるブロックのリスト
	 * @exception NullPointerException 引数がnullの場合
	 * @exception IllegalArgumentException Listの要素数が0の場合
	 */
	public RandomBlockCreator(List<T> blocks)
	{
		if(blocks == null)
			throw new NullPointerException();
		
		if(blocks.size() == 0)
			throw new IllegalArgumentException("arg:\"blocks\" size must be positive.");
		
		this.blocks = new ArrayList<>(blocks);
		this.created = new boolean[this.blocks.size()];
	}
	
	@Override
	public T nextBlock()
	{
		int total = this.blocks.size();
		int index = random.nextInt(total - this.createdCount);
		
		for(int i = 0; i <= index; i++)
		{
			if(this.created[i])
				index++;
		}
		
		T block = this.blocks.get(index);
		this.created[index] = true;

		if(++this.createdCount == total)
		{
			this.createdCount = 0;
			
			for(int i = 0; i < this.created.length; i++)
			{
				this.created[i] = false;
			}
		}
		
		return block;
	}
}
