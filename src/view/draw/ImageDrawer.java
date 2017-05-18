package view.draw;

import java.awt.*;
import view.utils.ImageUtils;

public class ImageDrawer implements IDrawer
{
	private Image image;
	
	/**
	 * �C���X�^���X�����������܂�
	 */
	public ImageDrawer()
	{ }
	
	/**
	 * �`�悷��摜��ݒ肵�܂�
	 * @param image �`�悷��摜
	 * @return ���g�̃C���X�^���X
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
