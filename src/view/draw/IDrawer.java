package view.draw;

import java.awt.*;

/**
 * 描画処理を実装していることを示すインターフェース
 */
public interface IDrawer
{
	/**
	 * Graphicsに描画します.
	 * @param g 描画対象のGraphics
	 * @param area 描画領域
	 */
	void draw(Graphics g, Rectangle area);
}
