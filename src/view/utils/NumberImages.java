package view.utils;

import java.awt.*;
import java.awt.image.*;
import java.io.*;

public enum NumberImages
{
	/*
	 * [���l�摜�̒ǉ����@]
	 *  1.������0-9�̐�������1�s�ɕ��񂾉摜��resources/img/���ɐݒu.
	 *  2.Enum�萔���`���A������/img/�ȉ��̉摜�p�X���w��.
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
	 * ���l��`�悵���摜�𐶐����܂�.
	 * @param number ��������摜�ɕ`�悷�鐔�l
	 * @return �������ꂽ���l�摜
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
	 * �eEnum�C���X�^���X��path���琔���摜��ǂݍ��݂܂�.
	 * @return �ǂݍ��݂ɐ��������ꍇ�܂��͊��ɓǂݍ���ł���ꍇ��true��Ԃ��A�ǂݍ��݂Ɏ��s�����ꍇ��false��Ԃ��܂�.
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
