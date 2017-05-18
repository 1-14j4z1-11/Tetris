package view.utils;

import java.awt.*;
import java.io.*;

public enum BasicImages
{
	MAIN_BG("/img/MainBG.png"),
	SUB_BG("/img/SubBG.png"),
	
	MANUAL_MENU("/img/Manual_Menu.png"),
	MANUAL_2D("/img/Manual_2D.png"),
	MANUAL_3D("/img/Manual_3D.png"),
	MANUAL_3D_DEMO("/img/Manual_3D_Demo.png"),
	MANUAL_RESULT("/img/Manual_Result.png"),
	
	LABEL_NEXT("/img/Label_Next.png"),
	LABEL_HOLD("/img/Label_Hold.png"),
	
	AXES("/img/Axes.png");
	
	private final String path;
	private Image image;
	
	private BasicImages(String path)
	{
		this.path = path;
	}
	
	public Image getImage()
	{
		if(this.image == null)
		{
			if(!this.readImage())
			{
				System.err.printf("Image file could not be opened : %s%n", this.path);
				return null;
			}
		}
		
		return this.image;
	}
	
	private boolean readImage()
	{
		if(this.image != null)
			return true;
		
		try
		{
			this.image = ImageUtils.readIntegratedImage(this.path, 1, 1)[0];
		}
		catch(IOException | IndexOutOfBoundsException e)
		{
			return false;
		}

		return true;
	}
}
