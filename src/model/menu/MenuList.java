package model.menu;

import java.util.*;

public class MenuList implements Iterable<MenuItem>
{
	private final MenuItem[] items;

	/**
	 * �C���X�^���X�����������܂�.
	 * @param items ���j���[�A�C�e���̔z��
	 */
	public MenuList(MenuItem[] items)
	{
		this.items = items.clone();
	}
	
	/**
	 * �C���X�^���X�����������܂�.
	 * @param items ���j���[�A�C�e����List
	 */
	public MenuList(List<MenuItem> items)
	{
		this.items = items.toArray(new MenuItem[] { });
	}
	
	/** ���j���[�����擾���܂�. */
	public int getCount()
	{
		return this.items.length;
	}
	
	/**
	 * ���j���[�A�C�e�����擾���܂�.
	 * @param index �擾���郁�j���[�A�C�e����Index
	 * @return ���j���[�A�C�e��
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
	 * MenuList�N���X��Iterator
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
