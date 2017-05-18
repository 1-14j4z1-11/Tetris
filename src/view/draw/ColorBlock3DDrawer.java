package view.draw;

import java.awt.*;
import java.awt.image.*;
import java.util.*;
import view.utils.*;
import model.tetris.block3d.*;
import model.tetris.common.*;

/**
 * 3D�u���b�N��`�悷��N���X
 * ����Drawer�Ə������قȂ�̂Œ���
 */
public class ColorBlock3DDrawer implements IDrawer
{
	private static final double DEPTH_RATIO = 0.6;
	private static final Color FIELD_LINE_COLOR = new Color(0xFFFFFFFF, true);
	private static final Color FILED_BACK_COLOR = new Color(0xFF000000, true);
	
	private IBlock3D<Color> block;
	private boolean drawField;
	private boolean drawAxes;
	private boolean opaquePlane = true;
	
	/**
	 * �C���X�^���X�����������܂�
	 * @param drawField �t�B�[���h��`�悷��ꍇ��true�A���Ȃ��ꍇ��false
	 * @param drawAxes ���W����\������ꍇ��true�A���Ȃ��ꍇ��false
	 */
	public ColorBlock3DDrawer(boolean drawField, boolean drawAxes)
	{
		this.drawField = drawField;
		this.drawAxes = drawAxes;
	}
	
	/**
	 * �`�悷��u���b�N��ݒ肵�܂�
	 */
	public void setBlock3D(IBlock3D<Color> block)
	{
		this.block = block;
	}
	
	/**
	 * �u���b�N�̖ʂ�s�����`�悷�邩�ǂ����̒l���擾���܂�
	 */
	public boolean isOpaquePlane()
	{
		return this.opaquePlane;
	}
	
	/**
	 * �u���b�N�̖ʂ�s�����ŕ`�悷�邩�ǂ����̒l��ݒ肵�܂�
	 * @param opaquePlane �s�����ŕ`�悷��ꍇ��true�A�������ŕ`�悷��ꍇ��false
	 * @return ���g�̃C���X�^���X
	 */
	public ColorBlock3DDrawer setOpaquePlane(boolean opaquePlane)
	{
		this.opaquePlane = opaquePlane;
		return this;
	}
	
	@Override
	public void draw(Graphics g, Rectangle area)
	{
		// �w�i�`��
		g.setColor(FILED_BACK_COLOR);
		g.fillRect(area.x, area.y, area.width, area.height);
		
		if(this.block == null)
			return;
		
		// ���W���\��
		if(this.drawAxes)
		{
			Image image = BasicImages.AXES.getImage();
			int dstLength = Math.min(Math.max(area.width, area.height) / 8, image.getWidth(null));
			
			g.drawImage(image,
				area.x, area.y + area.height - dstLength, area.x + dstLength, area.y + area.height,
				0, 0, image.getWidth(null), image.getHeight(null), null);
		}
		
		// �t�B�[���h�`��
		if(this.drawField)
		{
			this.drawField(g, area);
		}
		
		// �u���b�N�`��
		for(int k = this.block.getDepth() - 1; k >= 0 ; k--)
		{
			for(int j = 0; j < this.block.getHeight(); j++)
			{
				for(int i = this.block.getWidth() - 1; i >= 0; i--)
				{
					BlockCell<Color> cell = this.block.getCellAt(i, j , k);
					
					if((cell.getState() != BlockState.FIXED) && (cell.getState() != BlockState.MOVE))
						continue;
					
					this.drawBlock(g, area, i, j, k, cell.getTag());
				}
			}
		}
	}
	
	/**
	 * 3������Ԃ�`�悵�܂�
	 * @param g �`��Ώۂ�Graphics
	 * @param area �`��̈�
	 * @param width ��Ԃ̕�
	 * @param height ��Ԃ̍���
	 * @param depth ��Ԃ̐[��
	 * @exception NullPointerException ������null���܂܂��ꍇ
	 */
	private void drawField(Graphics g, Rectangle area)
	{
		if((g == null) || (area == null))
			throw new NullPointerException();
		
		int width = this.block.getWidth();
		int height = this.block.getHeight();
		int depth = this.block.getDepth();
		
		int blockSize = (int)Math.min(area.width / (width + height * DEPTH_RATIO), area.height / (depth + height * DEPTH_RATIO));		
		int blockSizeZ = (int)(blockSize * DEPTH_RATIO);
		int offsetX = area.x + (area.width - blockSize * width - blockSizeZ * height) / 2;
		int offsetY = area.y + (area.height - blockSize * depth - blockSizeZ * height) / 2;
		
		g.setColor(FIELD_LINE_COLOR);
		
		int lineX = blockSize * width;
		int lineY = blockSize * depth;
		int lineZ = blockSizeZ * height;
		
		for(int i = 0; i <= width; i++)
		{
			g.drawLine(
				offsetX + blockSize * i,
				offsetY,
				offsetX + blockSize * i,
				offsetY + lineY);
			
			g.drawLine(
				offsetX + blockSize * i,
				offsetY + lineY,
				offsetX + lineZ + blockSize * i,
				offsetY + lineZ + lineY);
		}
		for(int j = 0; j <= depth; j++)
		{
			g.drawLine(
				offsetX,
				offsetY + blockSize * j,
				offsetX + lineX,
				offsetY + blockSize * j);
			
			g.drawLine(
				offsetX + lineX,
				offsetY + blockSize * j,
				offsetX + lineX + lineZ,
				offsetY + lineZ + blockSize * j);
		}
		for(int k = 0; k <= height; k++)
		{
			g.drawLine(
				offsetX + lineX + blockSizeZ * k,
				offsetY + blockSizeZ * k,
				offsetX + lineX + blockSizeZ * k,
				offsetY + lineY + blockSizeZ * k);
			
			g.drawLine(
				offsetX + blockSizeZ * k,
				offsetY + lineY + blockSizeZ * k,
				offsetX + lineX + blockSizeZ * k,
				offsetY + lineY + blockSizeZ * k);
		}
	}
	
	private void drawBlock(Graphics g, Rectangle area, int x, int y, int z, Color color)
	{
		if((g == null) || (area == null))
			throw new NullPointerException();

		int width = this.block.getWidth();
		int height = this.block.getHeight();
		int depth = this.block.getDepth();
		
		int blockSize = (int)Math.min(area.width / (width + height * DEPTH_RATIO), area.height / (depth + height * DEPTH_RATIO));		
		int blockSizeZ = (int)(blockSize * DEPTH_RATIO);
		int offsetX = area.x + (area.width - blockSize * width - blockSizeZ * height) / 2;
		int offsetY = area.y + (area.height - blockSize * depth - blockSizeZ * height) / 2;
		
		y++;
		
		g.setColor(this.opaquePlane ? color : new Color(color.getRGB() & 0xA0FFFFFF, true));
		
		int rootVertexX = offsetX + blockSize * x + blockSizeZ * y;
		int rootVertexY = offsetY + blockSize * z + blockSizeZ * y;
		
		g.fillRect(
			rootVertexX,
			rootVertexY,
			blockSize, blockSize);
		
		g.fillPolygon(
			new int[] { rootVertexX, rootVertexX + blockSize, rootVertexX + blockSize - blockSizeZ, rootVertexX - blockSizeZ },
			new int[] { rootVertexY, rootVertexY, rootVertexY - blockSizeZ, rootVertexY - blockSizeZ },
			4);
		
		g.fillPolygon(
			new int[] { rootVertexX, rootVertexX, rootVertexX - blockSizeZ, rootVertexX - blockSizeZ },
			new int[] { rootVertexY, rootVertexY + blockSize, rootVertexY + blockSize - blockSizeZ, rootVertexY - blockSizeZ },
			4);
		
		g.setColor(this.opaquePlane ? FIELD_LINE_COLOR : new Color(FIELD_LINE_COLOR.getRGB() & 0x3FFFFFFF, true));
		
		g.drawRect(
			rootVertexX,
			rootVertexY,
			blockSize, blockSize);

		g.drawPolygon(
			new int[] { rootVertexX, rootVertexX + blockSize, rootVertexX + blockSize - blockSizeZ, rootVertexX - blockSizeZ },
			new int[] { rootVertexY, rootVertexY, rootVertexY - blockSizeZ, rootVertexY - blockSizeZ },
			4);
		
		g.drawPolygon(
			new int[] { rootVertexX, rootVertexX, rootVertexX - blockSizeZ, rootVertexX - blockSizeZ },
			new int[] { rootVertexY, rootVertexY + blockSize, rootVertexY + blockSize - blockSizeZ, rootVertexY - blockSizeZ },
			4);
		
		// ���v�`��(������)
		int heightOffset = 11;
		if((z - heightOffset >= 0) && (z - heightOffset < width / 2));
		{
			for(int j = y; j < this.block.getHeight(); j++)
			{
				if((this.block.getCellAt(x, j, z).getState() == BlockState.FIXED)
					|| (this.block.getCellAt(x, j, z).getState() == BlockState.MOVE))
				{
					return;
				}
			}
			
			int cWidth = blockSize * width;
			int cHeight = cWidth * 2 / 5;
			
			Calendar calendar = Calendar.getInstance();
			String clockStr = String.format("%02d:%02d:%02d", calendar.get(Calendar.HOUR_OF_DAY), calendar.get(Calendar.MINUTE), calendar.get(Calendar.SECOND));
			
			BufferedImage clockImage = new BufferedImage(cWidth, cHeight, BufferedImage.TYPE_INT_ARGB);
			Graphics clockGraphics = clockImage.getGraphics();
			clockGraphics.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, (int)(blockSize * 1.25)));
			clockGraphics.setColor(new Color(0xFFFFFFFF, true));
			clockGraphics.drawString(clockStr, (int)(blockSize * 0.15), (int)(blockSize * 1.5));
			clockGraphics.dispose();
			
			g.setXORMode(new Color(0x007F7F7F, true));
			g.drawImage(clockImage, rootVertexX, rootVertexY, rootVertexX + blockSize, rootVertexY + blockSize,
					x * blockSize, (z - heightOffset) * blockSize, (x + 1) * blockSize, (z - heightOffset + 1) * blockSize, null);
			g.setPaintMode();
		}
		//
	}
}