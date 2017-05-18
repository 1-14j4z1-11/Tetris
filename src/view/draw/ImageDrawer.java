package view.draw;

import java.awt.*;
import view.utils.ImageUtils;

public class ImageDrawer implements IDrawer
{
	private Image image;
	
	/**
	 * インスタンスを初期化します
	 */
	public ImageDrawer()
	{ }
	
	/**
	 * 描画する画像を設定します
	 * @param image 描画する画像
	 * @return 自身のインスタンス
	 */
	public ImageDrawer setImage(Image image)
	{
		this.image = image;
		return this;
	}
	
	@Override
	public void draw(Graphics g, Rectangle area)
	{
		if(this.image == null)
			return;
		
		g.drawImage(ImageUtils.resizeFixedRatioImage(this.image, area.width, area.height), area.x, area.y, null);
	}
}
