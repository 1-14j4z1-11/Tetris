package view.draw;

import java.awt.*;
import view.utils.TextImages;
import model.menu.*;

public class MenuDrawer implements IDrawer
{
	private final Config config;
	private int counter = 0;
	
	public MenuDrawer(Config config)
	{
		this.config = config;
	}
	
	@Override
	public void draw(Graphics g, Rectangle area)
	{
		if((this.counter++ % 40) < 25)
		{
			Image image = TextImages.PLAIN.createStringImage("Press Space");
			g.drawImage(image,
				area.x + (area.width - image.getWidth(null)) / 2,
				area.y + (area.height - image.getHeight(null)) / 2,
				null);
		}
	}
}
