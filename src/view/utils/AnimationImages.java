package view.utils;

import java.awt.*;
import java.io.*;

public enum AnimationImages
{
	CURSOR("/img/Cursor.png", 12);
	
	private final String path;
	private final int imageCount;
	private Image[] images;
	
	private AnimationImages(String path, int imageCount)
	{
		this.path = path;
		this.imageCount = imageCount;
	}
	
	public int getImageCount()
	{
		return this.imageCount;
	}
	
	public Image getImageAt(int index)
	{
		if(this.images == null)
		{
			if(!this.readImage())
			{
				System.err.printf("Image file could not be opened : %s%n", this.path);
				return null;
			}
		}
		
		Image image = this.images[index];
		return image.getScaledInstance(image.getWidth(null), image.getHeight(null), Image.SCALE_FAST);
	}
	
	private boolean readImage()
	{
		if(this.images != null)
			return true;
		
		try
		{
			this.images = ImageUtils.readIntegratedImage(this.path, this.imageCount, 1);
		}
		catch(IOException e)
		{
			return false;
		}

		return true;
	}
}
