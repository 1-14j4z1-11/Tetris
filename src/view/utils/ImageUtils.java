package view.utils;

import java.awt.*;
import java.awt.image.*;
import java.io.*;
import java.net.URL;
import javax.imageio.*;

/**
 * �`��p�摜��Util�N���X
 */
public class ImageUtils
{
	// �C���X�^���X���s��
	private ImageUtils() { }
	
	/**
	 * ��f�l��ύX�����摜�𐶐����܂�.</br>
	 * �ύX��̊e�`���l���̉�f�l��(color.channel * �ύX�O�̉�f�l / 255)�ɂȂ�܂�.
	 * @param image �ύX��K�p����摜
	 * @param color �ύX��̉�f�l
	 * @return ��f�l��ύX�����摜
	 */
	public static Image changeColor(Image image, Color color)
	{
		BufferedImage bImage = new BufferedImage(image.getWidth(null), image.getHeight(null), BufferedImage.TYPE_INT_ARGB);
		
		Graphics graphics = bImage.getGraphics();
		graphics.drawImage(image, 0, 0, null);
		graphics.dispose();
		
		int coefR = color.getRed();
		int coefG = color.getGreen();
		int coefB = color.getBlue();
		
		for(int i = 0; i < bImage.getWidth(); i++)
		{
			for(int j = 0; j < bImage.getHeight(); j++)
			{
				int a = bImage.getRGB(i, j) & 0xFF000000;
				int r = (bImage.getRGB(i, j) & 0x00FF0000) >> 16;
				int g = (bImage.getRGB(i, j) & 0x0000FF00) >> 8;
				int b = (bImage.getRGB(i, j) & 0x000000FF);
				
				int dest = ((r * coefR / 255) << 16) | ((g * coefG / 255) << 8) | (b * coefB / 255) | a;
				
				bImage.setRGB(i, j, dest);
			}
		}
		
		return bImage;
	}
	
	/**
	 * �A�X�y�N�g����ێ�������Ԃŉ摜�T�C�Y���w��T�C�Y�Ɏ��܂�悤�Ƀ��T�C�Y���܂�
	 * @param image ���摜
	 * @param width ���T�C�Y��̉摜�̍ő啝
	 * @param height ���T�C�Y��̉摜�̍ő卂��
	 * @return ���T�C�Y�����摜
	 */
	public static Image resizeFixedRatioImage(Image image, int width, int height)
	{
		double ratio = Math.min((double)width / image.getWidth(null), (double)height / image.getHeight(null));
		int dstWidth = (int)(image.getWidth(null) * ratio);
		int dstHeight = (int)(image.getHeight(null) * ratio);
		
		BufferedImage resizedImage = new BufferedImage(dstWidth, dstHeight, BufferedImage.TYPE_INT_ARGB);
		Graphics graphics = resizedImage.getGraphics();
		graphics.drawImage(image, 0, 0, dstWidth, dstHeight, 0, 0, image.getWidth(null), image.getHeight(null), null);
		graphics.dispose();
		
		return resizedImage;
	}
	
	/**
	 * �����̉摜���܂Ƃ܂����摜�f�[�^�𕪊����ēǂݍ��݂܂�
	 * @param path �摜�t�@�C���̃p�X
	 * @param partitionH �������̕�����
	 * @param partitionV �c�����̕�����
	 * @return ���������摜�z��
	 * @throws IOException �t�@�C���̓ǂݍ��݂Ɏ��s�����ꍇ
	 */
	// package
	static Image[] readIntegratedImage(String path, int partitionH, int partitionV) throws IOException
	{
		URL url = ImageUtils.class.getResource(path);
		
		BufferedImage rawImage = null;
		rawImage = ImageIO.read(url);
		
		int imageCount = partitionH * partitionV;
		int unitWidth = rawImage.getWidth() / partitionH;
		int unitHeight = rawImage.getHeight() / partitionV;
		Image[] images = new Image[imageCount];
		
		for(int i = 0; i < imageCount; i++)
		{
			images[i] = rawImage.getSubimage(unitWidth * (i % partitionH), unitHeight * (i / partitionH), unitWidth, unitHeight);
		}
		
		return images;
	}
}
