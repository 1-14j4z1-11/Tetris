package view.frame;

import java.awt.*;
import javax.swing.*;
import view.draw.*;

/**
 * IDrawer���󂯎�邱�Ƃŕ`�悷��Panel�N���X
 */
public class DrawablePanel extends JPanel
{
	/**
	 * �]���̈���������߂̍\����
	 */
	private static class Margin
	{
		public int top;		// �]���̈�̏�
		public int left;	// �]���̈�̍�
		public int width;	// �]���̈�̕��̍��v
		public int height;	// �]���̈�̍����̍��v
	}
	
	private static final long serialVersionUID = 1L;
	private IDrawer drawer;
	private final Margin margin = new Margin();

	/**
	 * �C���X�^���X�����������܂�
	 */
	public DrawablePanel()
	{
		this.setLayout(null);
		this.setDrawer(null);
	}

	/**
	 * �`�揈���C���X�^���X��ݒ肵�܂�(�`�悵�Ȃ��ꍇ��null��ݒ�)
	 * @param drawer �ݒ肷��`�揈���C���X�^���X
	 */
	public final void setDrawer(IDrawer drawer)
	{
		this.drawer = (drawer == null) ? DefaultDrawer.getInstance() : drawer;
		this.repaint();
	}
	
	/**
	 * �`��̈�̗]���̈��ݒ肵�܂�
	 * @param top ��]���̈�
	 * @param left ���]���̈�
	 * @param bottom ���]���̈�
	 * @param right �E�]���̈�
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
		
		// �w�i�̈�̕`��
		g.setColor(this.getBackground());
		g.fillRect(0, 0, this.getWidth(), this.getHeight());
		
		this.drawer.draw(g,
			new Rectangle(this.margin.left, this.margin.top, this.getWidth() - this.margin.width, this.getHeight() - this.margin.height));
	}

	/**
	 * IDrawer��NullObject
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
			// �`�悵�Ȃ�
		}
	}
}