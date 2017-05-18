package view.utils;

import java.awt.*;
import java.awt.image.*;
import java.io.*;

public enum TextImages
{
	/*
	 * [�����摜�̒ǉ����@]
	 *  1.������A-Z�̕�������4�s*�c7��ɕ��񂾉摜��resources/img/���ɐݒu.
	 *    - 27�Ԗڂ͋�/28�Ԗڂ͖��g�p.
	 *  2.Enum�萔���`���A������/img/�ȉ��̉摜�p�X���w��.
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
	 * �������`�悵���摜�𐶐����܂�.</br>
	 * ������Ɋ܂܂�镶����[A-Za-z _]�Ɍ��肳��܂�.</br>
	 * '_'��' '�ɕϊ�����܂�.
	 * @param text ��������摜�ɕ`�悷�镶����
	 * @return �������ꂽ������摜
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
	 * �A���t�@�x�b�g�摜��ǂݍ��݂܂�.
	 * @param path �摜�t�@�C���̃p�X
	 * @return �ǂݍ��݂ɐ��������ꍇ�܂��͊��ɓǂݍ���ł���ꍇ��true��Ԃ��A�ǂݍ��݂Ɏ��s�����ꍇ��false��Ԃ��܂�.
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
