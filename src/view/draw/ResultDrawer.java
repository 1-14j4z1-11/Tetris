package view.draw;

import java.awt.*;
import view.utils.*;
import model.score.*;

public class ResultDrawer implements IDrawer
{
	private static final Color BG_COLOR = new Color(0xFF202020, true);
	private static final Color ITEM_BG_COLOR = new Color(0xFF404040, true);
	private static final int DRAW_MARGIN = 10;	// •`‰æ
	private static final int ITEM_COUNT = 3;	// 
	
	private IScore score;
	private final TextImages textimage;
	private final NumberImages numberImage;
	
	public ResultDrawer(TextImages textImage, NumberImages numberImage)
	{
		this.textimage = textImage;
		this.numberImage = numberImage;
	}
	
	public void setScore(IScore score)
	{
		this.score = score;
	}
	
	@Override
	public void draw(Graphics g, Rectangle area)
	{
		if(this.score == null)
			return;
		
		g.setColor(BG_COLOR);
		g.fill3DRect(area.x, area.y, area.width, area.height, true);
		
		Image title = this.textimage.createStringImage("Result");
		
		g.drawImage(title, area.x + (area.width - title.getWidth(null)) / 2, area.y + DRAW_MARGIN, null);
		
		area = new Rectangle(area.x, area.y + title.getHeight(null) + DRAW_MARGIN,
			area.width, area.height - title.getHeight(null) - DRAW_MARGIN);
		
		this.drawItem(g, area, this.textimage.createStringImage("Score "), this.numberImage.createNumberImage(this.score.getScore()), 0);
		this.drawItem(g, area, this.textimage.createStringImage("Line  "), this.numberImage.createNumberImage(this.score.getLine()), 1);
		this.drawItem(g, area, this.textimage.createStringImage("Tetris"), this.numberImage.createNumberImage(this.score.getTetris()), 2);
	}
	
	private void drawItem(Graphics g, Rectangle area, Image labelImage, Image valueImage, int itemIndex)
	{
		assert (itemIndex < ITEM_COUNT);
		assert (labelImage != null);
		assert (valueImage != null);
		
		int itemHeight = labelImage.getHeight(null) + valueImage.getHeight(null);
		int heightOffsest = (itemHeight + DRAW_MARGIN * 3) * itemIndex + area.height / 10;
		area = new Rectangle(area.x + DRAW_MARGIN, area.y + heightOffsest + DRAW_MARGIN,
			area.width - DRAW_MARGIN * 2, itemHeight + DRAW_MARGIN * 2);
		
		g.setColor(ITEM_BG_COLOR);
		g.fill3DRect(area.x, area.y, area.width, area.height, false);

		area.x += DRAW_MARGIN;
		area.y += DRAW_MARGIN;
		area.width -= DRAW_MARGIN * 2;
		area.height -= DRAW_MARGIN * 2;
		
		g.drawImage(labelImage, area.x, area.y, null);
		g.drawImage(valueImage, area.x + area.width - valueImage.getWidth(null), area.y + labelImage.getHeight(null), null);
	}
}
