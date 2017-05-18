package model.menu;

public class MenuController
{
	private final MenuList topMenu;
	private int index = 0;
	private MenuList currentMenu;
	private final boolean recursive;
	
	public MenuController(MenuList topMenu, boolean recursive)
	{
		this.topMenu = topMenu;
		this.recursive = recursive;
	}
	
	/**
	 * メニューの選択位置を一つ上に移動します.
	 * @return メニューの移動に成功した場合はtrueを返し、失敗した場合はfalseを返します.
	 */
	public boolean upCursor()
	{
		if(this.index == 0)
		{
			if(!recursive)
				return false;
			else
				this.index = this.currentMenu.getCount() - 1;
		}
		else
		{
			this.index--;
		}
		
		return true;
	}

	/**
	 * メニューの選択位置を一つ下に移動します.
	 * @return メニューの移動に成功した場合はtrueを返し、失敗した場合はfalseを返します.
	 */
	public boolean downCursor()
	{
		if(this.index == this.currentMenu.getCount() - 1)
		{
			if(!recursive)
				return false;
			else
				this.index = 0;
		}
		else
		{
			this.index++;
		}
		
		return true;
	}
	
	/** 現在選択されているメニューを実行します. */
	public void execute()
	{
		MenuItem item = this.currentMenu.getItemAt(this.index);
		MenuList next = item.menuSelected();
		
		this.currentMenu = (next != null) ? next : this.currentMenu;
	}
	
	/** メニューを前階層に戻します. */
	public void backMenu()
	{
		
	}
}
