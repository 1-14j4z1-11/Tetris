package view.frame;

import java.awt.*;
import javax.swing.*;
import view.draw.*;

/**
 * IDrawerを受け取ることで描画するPanelクラス
 */
public class DrawablePanel extends JPanel
{
	/**
	 * 余白領域を扱うための構造体
	 */
	private static class Margin
	{
		public int top;		// 余白領域の上
		public int left;	// 余白領域の左
		public int width;	// 余白領域の幅の合計
		public int height;	// 余白領域の高さの合計
	}
	
	private static final long serialVersionUID = 1L;
	private IDrawer drawer;
	private final Margin margin = new Margin();

	/**
	 * インスタンスを初期化します
	 */
	public DrawablePanel()
	{
		this.setLayout(null);
		this.setDrawer(null);
	}

	/**
	 * 描画処理インスタンスを設定します(描画しない場合はnullを設定)
	 * @param drawer 設定する描画処理インスタンス
	 */
	public final void setDrawer(IDrawer drawer)
	{
		this.drawer = (drawer == null) ? DefaultDrawer.getInstance() : drawer;
		this.repaint();
	}
	
	/**
	 * 描画領域の余白領域を設定します
	 * @param top 上余白領域
	 * @param left 左余白領域
	 * @param bottom 下余白領域
	 * @param right 右余白領域
	 */
	public final void setMargin(int top, int left, int bottom, int right)
	{
		this.margin.top = top;
		this.margin.left = left;
		this.margin.width = left + right;
		this.margin.height = top + bottom;
		this.repaint();
	}

	@Override
	public synchronized void paint(Graphics g)
	{
		super.paint(g);
		
		// 背景領域の描画
		g.setColor(this.getBackground());
		g.fillRect(0, 0, this.getWidth(), this.getHeight());
		
		this.drawer.draw(g,
			new Rectangle(this.margin.left, this.margin.top, this.getWidth() - this.margin.width, this.getHeight() - this.margin.height));
	}

	/**
	 * IDrawerのNullObject
	 */
	private static final class DefaultDrawer implements IDrawer
	{
		private static final DefaultDrawer INSTANCE = new DefaultDrawer();

		public static DefaultDrawer getInstance()
		{
			return INSTANCE;
		}

		private DefaultDrawer() { }

		@Override
		public void draw(Graphics g, Rectangle area)
		{
			// 描画しない
		}
	}
}