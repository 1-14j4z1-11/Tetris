package view.utils;

import java.awt.*;
import java.awt.image.*;
import java.io.*;

public enum NumberImages
{
	/*
	 * [数値画像の追加方法]
	 *  1.等幅な0-9の数字が横1行に並んだ画像をresources/img/下に設置.
	 *  2.Enum定数を定義し、引数に/img/以下の画像パスを指定.
	 */
	
	PLAIN("/img/Number_Plain.png");
	
	private static final int PARTITION_H = 10;
	private static final int PARTITION_V = 1;
	
	private final String path;
	private Dimension imageSize;
	private Image[] images;
	
	private NumberImages(String path)
	{
		this.path = path;
	}

	/**
	 * 数値を描画した画像を生成します.
	 * @param number 生成する画像に描画する数値
	 * @return 生成された数値画像
	 */
	public Image createNumberImage(int number)
	{
		if(this.images == null)
		{
			if(!this.readNumberImage())
			{
				System.err.printf("Image file could not be opened : %s%n", this.path);
				return null;
			}
		}

		number = Math.abs(number);
		int length = (number == 0) ? 1 : (int)Math.log10(number) + 1;
		
		BufferedImage image = new BufferedImage(this.imageSize.width * length, this.imageSize.height, BufferedImage.TYPE_INT_ARGB);
		Graphics g = image.getGraphics();
		
		if(number == 0)
		{
			g.drawImage(this.images[0], this.imageSize.width * (length - 1), 0, null);
		}
		else
		{
			for(int w = this.imageSize.width * (length - 1);
				number != 0;
				number /= 10, w -= this.imageSize.width)
			{
				g.drawImage(this.images[number % 10], w, 0, null);
			}
		}
		
		g.dispose();
		
		return image;
	}

	/**
	 * 各Enumインスタンスのpathから数字画像を読み込みます.
	 * @return 読み込みに成功した場合または既に読み込んでいる場合はtrueを返し、読み込みに失敗した場合はfalseを返します.
	 */
	private synchronized boolean readNumberImage()
	{
		if(this.images != null)
			return true;
		
		try
		{
			this.images = ImageUtils.readIntegratedImage(this.path, PARTITION_H, PARTITION_V);
			this.imageSize = new Dimension(this.images[0].getWidth(null), this.images[0].getHeight(null));
		}
		catch(IOException e)
		{
			return false;
		}
		
		return true;
	}
}
