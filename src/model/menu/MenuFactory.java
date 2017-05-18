package model.menu;

public class MenuFactory
{
	private MenuFactory() { }
	
	public static MenuList createMenu(MenuController controller)
	{
		MenuItem configMenu = new MenuItem(controller, "Config", null);
		
		MenuItem[] topMenu = new MenuItem[]
		{
			new MenuItem(controller, "Game Start", new IMenuItemHandler()
			{
				@Override
				public void execute(MenuController controller)
				{
					// TODO ÉQÅ[ÉÄäJén
				}
			}),
			
			configMenu,
			
			new MenuItem(controller, "Exit", new IMenuItemHandler()
			{
				@Override
				public void execute(MenuController controller)
				{
					System.exit(0);
				}
			}),
		};
		
		return new MenuList(topMenu);
	}
}
