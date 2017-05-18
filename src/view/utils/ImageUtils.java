package view.utils;

import java.awt.*;
import java.awt.image.*;
import java.io.*;
import java.net.URL;
import javax.imageio.*;

/**
 * 描画用画像のUtilクラス
 */
public class ImageUtils
{
	// インスタンス化不可
	private ImageUtils() { }
	
	/**
	 * 画素値を変更した画像を生成します.</br>
	 * 変更後の各チャネルの画素値は(color.channel * 変更前の画素値 / 255)になります.
	 * @param image 変更を適用する画像
	 * @param color 変更後の画素値
	 * @return 画素値を変更した画像
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
	 * アスペクト比を維持した状態で画像サイズが指定サイズに収まるようにリサイズします
	 * @param image 元画像
	 * @param width リサイズ後の画像の最大幅
	 * @param height リサイズ後の画像の最大高さ
	 * @return リサイズした画像
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
	 * 複数の画像がまとまった画像データを分割して読み込みます
	 * @param path 画像ファイルのパス
	 * @param partitionH 横方向の分割数
	 * @param partitionV 縦方向の分割数
	 * @return 分割した画像配列
	 * @throws IOException ファイルの読み込みに失敗した場合
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
