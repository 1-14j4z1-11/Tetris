package view.draw;

import java.awt.*;
import view.utils.*;
import model.score.*;

/**
 * �X�R�A��`�悷��N���X
 */
public class ScoreDrawer implements IDrawer
{
	private static final int ITEM_MARGIN = 5;	// ���ڊԂ̊Ԋu(��f��)
	private static final int ITEM_COUNT = 3;	// �`�悷�鍀�ڐ�
	private static final int DRAW_OFFSET = 5;	// �`�捀�ڂ̃I�t�Z�b�g
	private static final Color BG_COLOR = new Color(0xFF606060, true);
	
	private IScore score;
	private final TextImages textImage;
	private final NumberImages numberImage;
	
	public ScoreDrawer(TextImages textImage, NumberImages numberImage)
	{
		this.textImage = textImage;
		this.numberImage = numberImage;
	}
	
	@Override
	public void draw(Graphics g, Rectangle area)
	{
		Image label_Level = this.textImage.createStringImage("Lv");
		Image label_Quota = this.textImage.createStringImage("Next");
		Image label_Score = this.textImage.createStringImage("Score");
		
		Image value_Level = (this.score != null) ? this.numberImage.createNumberImage(this.score.getLevel()) : null;
		Image value_Quota = (this.score != null) ? this.numberImage.createNumberImage(this.score.getQuota()) : null;
		Image value_Score = (this.score != null) ? this.numberImage.createNumberImage(this.score.getScore()) : null;
		
		this.drawItem(g, area, label_Level, value_Level, 0, 0.4, 0.65);
		this.drawItem(g, area, label_Quota, value_Quota, 1, 0.4, 0.65);
		this.drawItem(g, area, label_Score, value_Score, 2, 0.4, 0.65);
	}
	
	/**
	 * �`�悷��X�R�A��ݒ肵�܂�
	 * @param score �`��Ώۂ̃X�R�A
	 */
	public void setScore(IScore score)
	{
		this.score = score;
	}
	
	private void drawItem(Graphics g, Rectangle area, Image labelImage, Image valueImage, int itemIndex, double labelHeightRatio, double valueHeightRatio)
	{
		assert (itemIndex < ITEM_COUNT);
		
		int width = area.width;
		int height = (area.height - ITEM_MARGIN * (ITEM_COUNT - 1)) / ITEM_COUNT;
		int x = area.x;
		int y = area.y + ITEM_MARGIN * itemIndex + height * itemIndex;
		
		// �w�i�`��
		g.setColor(BG_COLOR);
		g.fill3DRect(x, y, width, height, true);
		
		// ���ڕ`��̈���̗]���������Z
		x += DRAW_OFFSET;
		y += DRAW_OFFSET;
		width -= DRAW_OFFSET * 2;
		height -= DRAW_OFFSET * 2;
		
		// ���x���̕`��
		if(labelImage != null)
		{
			Image image = ImageUtils.resizeFixedRatioImage(labelImage, width, (int)(height * labelHeightRatio));
			g.drawImage(image, x, y, null);
		}
		
		// �l�̕`��
		if(valueImage != null)
		{
			Image image = ImageUtils.resizeFixedRatioImage(valueImage, width, (int)(height * valueHeightRatio));
			g.drawImage(image, x + width - image.getWidth(null), y + height - image.getHeight(null), null);
		}
	}
}
