package view.draw;

import java.awt.*;
import java.util.*;

/**
 * デジタル時計を描画するクラス
 */
public class ClockDrawer extends Block2DDrawer
{
	private final int clockY;
	
	/**
	 * インスタンスを初期化します
	 * @param clockY 時計の描画位置(ブロック基準)
	 * @param nextDrawer このインスタンスの描画後に呼び出すDrawer
	 */
	public ClockDrawer(int clockY, Block2DDrawer nextDrawer)
	{
		super(nextDrawer);
		
		this.clockY = clockY;
	}
	
	@Override
	protected void doDraw(Graphics g, Rectangle area)
	{
		if(this.getBlock() == null)
			return;
		
		int blockSize = Math.min(area.width / this.getBlock().getWidth(), area.height / this.getBlock().getHeight());
		
		Calendar date = Calendar.getInstance();
		String timeString = String.format("%02d:%02d:%02d", date.get(Calendar.HOUR_OF_DAY), date.get(Calendar.MINUTE), date.get(Calendar.SECOND));
		
		int fontSize = blockSize * 5 / 2;
		
		g.setColor(new Color(0xFFFFFFFF, true));
		g.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, fontSize));
		
		g.setXORMode(new Color(0x007F7F7F, true));
		
		g.drawString(timeString,
			(area.width - this.getBlock().getWidth() * blockSize) / 2  + area.x + blockSize / 4,
			this.clockY * blockSize + blockSize / 2);
		
		g.setPaintMode();
	}
}
