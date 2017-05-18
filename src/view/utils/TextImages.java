package view.utils;

import java.awt.*;
import java.awt.image.*;
import java.io.*;

public enum TextImages
{
	/*
	 * [文字画像の追加方法]
	 *  1.等幅なA-Zの文字が横4行*縦7列に並んだ画像をresources/img/下に設置.
	 *    - 27番目は空白/28番目は未使用.
	 *  2.Enum定数を定義し、引数に/img/以下の画像パスを指定.
	 */
	
	PLAIN("/img/Alphabet_Plain.png");
	
	private static final int PARTITION_H = 7;
	private static final int PARTITION_V = 4;
	
	private final String path;
	private Dimension imageSize;
	private Image[] images;
	
	private TextImages(String path)
	{
		this.path = path;
	}

	/**
	 * 文字列を描画した画像を生成します.</br>
	 * 文字列に含まれる文字は[A-Za-z _]に限定されます.</br>
	 * '_'は' 'に変換されます.
	 * @param text 生成する画像に描画する文字列
	 * @return 生成された文字列画像
	 */
	public Image createStringImage(String text)
	{
		if(!text.matches("[A-Za-z _]*"))
			throw new IllegalArgumentException();
		
		if(this.images == null)
		{
			if(!this.readAlphabetImage())
			{
				System.err.printf("Image file could not be opened : %s%n", this.path);
				return null;
			}
		}
		
		text = text.toUpperCase();
		int length = text.length();
		
		BufferedImage image = new BufferedImage(this.imageSize.width * length, this.imageSize.height, BufferedImage.TYPE_INT_ARGB);
		Graphics g = image.getGraphics();
		
		for(int w = 0, i = 0; i < length; w += this.imageSize.width, i++)
		{
			char c = text.charAt(i);
			int index;
			
			if((c >= 'A') && (c <= 'Z'))
				index = c - 'A';

			else if((c >= 'a') && (c <= 'z'))
				index = c - 'a';
			
			else if((c == ' ') || (c == '_'))
				index = 26;
			
			else
				throw new AssertionError();
			
			g.drawImage(this.images[index], w, 0, null);
		}
		
		g.dispose();
		
		return image;
	}
	
	/**
	 * アルファベット画像を読み込みます.
	 * @param path 画像ファイルのパス
	 * @return 読み込みに成功した場合または既に読み込んでいる場合はtrueを返し、読み込みに失敗した場合はfalseを返します.
	 */
	private synchronized boolean readAlphabetImage()
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
