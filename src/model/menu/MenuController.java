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
	 * ���j���[�̑I���ʒu�����Ɉړ����܂�.
	 * @return ���j���[�̈ړ��ɐ��������ꍇ��true��Ԃ��A���s�����ꍇ��false��Ԃ��܂�.
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
	 * ���j���[�̑I���ʒu������Ɉړ����܂�.
	 * @return ���j���[�̈ړ��ɐ��������ꍇ��true��Ԃ��A���s�����ꍇ��false��Ԃ��܂�.
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
	
	/** ���ݑI������Ă��郁�j���[�����s���܂�. */
	public void execute()
	{
		MenuItem item = this.currentMenu.getItemAt(this.index);
		MenuList next = item.menuSelected();
		
		this.currentMenu = (next != null) ? next : this.currentMenu;
	}
	
	/** ���j���[��O�K�w�ɖ߂��܂�. */
	public void backMenu()
	{
		
	}
}
