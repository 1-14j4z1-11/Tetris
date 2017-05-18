package view.frame;

import java.awt.*;
import javax.swing.*;

public class ImageLabel extends JLabel
{
	public static enum ScaleType
	{
		CENTER
		{
			@Override
			protected final Rectangle calcDrawArea(Dimension componentSize, Dimension imageSize)
			{
				return new Rectangle((componentSize.width - imageSize.width) / 2,
					(componentSize.height - imageSize.height) / 2,
					imageSize.width,
					imageSize.height);
			}
		},
		
		FIT
		{
			@Override
			protected final Rectangle calcDrawArea(Dimension componentSize, Dimension imageSize)
			{
				return new Rectangle(0, 0, componentSize.width, componentSize.height);
			}
		};
		
		protected abstract Rectangle calcDrawArea(Dimension componentSize, Dimension imageSize);
	}
	
	private static final long serialVersionUID = 1L;
	
	private Image image;
	private ScaleType scaleType = ScaleType.FIT;
	
	public ImageLabel()
	{ }
	
	public void setImage(Image image)
	{
		this.image = image;
	}
	
	public void setScaleType(ScaleType type)
	{
		if(type == null)
			throw new NullPointerException();
		
		this.scaleType = type;
	}
	
	@Override
	public void paint(Graphics g)
	{
		super.paint(g);
		
		g.setColor(this.getBackground());
		g.fillRect(0, 0, this.getWidth(), this.getHeight());
		
		if(this.image == null)
			return;
		
		Rectangle drawArea = this.scaleType.calcDrawArea(this.getSize(), new Dimension(this.image.getWidth(null), this.image.getHeight(null)));
		
		g.drawImage(this.image, drawArea.x, drawArea.y, (int)drawArea.getMaxX(), (int)drawArea.getMaxY(),
			0, 0, this.image.getWidth(null), this.image.getHeight(null), null);
	}
}
