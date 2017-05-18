package model.menu;

import java.util.*;

public class MenuList implements Iterable<MenuItem>
{
	private final MenuItem[] items;

	/**
	 * インスタンスを初期化します.
	 * @param items メニューアイテムの配列
	 */
	public MenuList(MenuItem[] items)
	{
		this.items = items.clone();
	}
	
	/**
	 * インスタンスを初期化します.
	 * @param items メニューアイテムのList
	 */
	public MenuList(List<MenuItem> items)
	{
		this.items = items.toArray(new MenuItem[] { });
	}
	
	/** メニュー数を取得します. */
	public int getCount()
	{
		return this.items.length;
	}
	
	/**
	 * メニューアイテムを取得します.
	 * @param index 取得するメニューアイテムのIndex
	 * @return メニューアイテム
	 */
	public MenuItem getItemAt(int index)
	{
		return this.items[index];
	}
	
	public Iterator<MenuItem> iterator()
	{
		return new MenuItemIterator(this.items);
	}
	
	/**
	 * MenuListクラスのIterator
	 */
	private static class MenuItemIterator implements Iterator<MenuItem>
	{
		private final MenuItem[] items;
		private int index = 0;
		
		public MenuItemIterator(MenuItem[] items)
		{
			this.items = items.clone();
		}

		@Override
		public boolean hasNext()
		{
			return (this.index < this.items.length);
		}

		@Override
		public MenuItem next()
		{
			return this.items[this.index++];
		}

		@Override
		public void remove()
		{
			throw new UnsupportedOperationException();
		}
	}
}
