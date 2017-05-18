package view.draw;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import model.tetris.block2d.*;
import model.tetris.common.*;

/**
 * �u���b�N�T�C�Y�����T�C�Y�Ɉˑ������Œ�T�C�Y�ŕ`�悷�邽�߂�Decorator�N���X
 */
public class FixedSizeBlockDrawDecorator extends Block2DDrawer
{
	private final Block2DDrawer baseDrawer;
	private final WrappedBlock wrappedBlock;
	
	/**
	 * �C���X�^���X�����������܂�
	 * @param baseDrawer �`��u���b�N���Œ�T�C�Y������Ώۂ�Drawer
	 * @param nextDrawer ���ɕ`�悷��Drawer
	 * @param blockSize �u���b�N�̃T�C�Y
	 * @param noneCell �T�C�Y�𖄂߂邽�߂̃u���b�N�Z��
	 */
	public FixedSizeBlockDrawDecorator(Block2DDrawer baseDrawer, Block2DDrawer nextDrawer, int blockSize, BlockCell<Color> noneCell)
	{
		super(nextDrawer);

		if(baseDrawer == null)
			throw new NullPointerException();
		
		this.baseDrawer = baseDrawer;
		this.wrappedBlock = new WrappedBlock(blockSize, noneCell);
	}
	
	@Override
	protected void doDraw(Graphics g, Rectangle area)
	{
		// baseDrawer.setBlock()�͂��̃N���X�Ŗ����I�ɌĂяo���K�v������
		this.baseDrawer.setBlock(this.wrappedBlock.setBlock(this.getBlock()));
		this.baseDrawer.doDraw(g, area);
	}
	
	/**
	 * IBlock�C���X�^���X�����b�v���ČŒ�T�C�Y��IBlock�C���X�^���X����������N���X
	 */
	private static class WrappedBlock implements IBlock2D<Color>
	{
		private IBlock2D<Color> block;
		private final int blockSize;
		private final BlockCell<Color> noneCell;
		
		public WrappedBlock(int blockSize, BlockCell<Color> noneCell)
		{
			this.blockSize = blockSize;
			this.noneCell = noneCell;
		}
		
		/**
		 * ���b�v����u���b�N��ݒ肵�܂�
		 * @param block �ݒ肷��u���b�N
		 * @return ���̃C���X�^���X���g
		 */
		public WrappedBlock setBlock(IBlock2D<Color> block)
		{
			this.block = block;
			return this;
		}
		
		@Override
		public int getWidth()
		{
			return this.blockSize;
		}
		
		@Override
		public int getHeight()
		{
			return this.blockSize;
		}
		
		@Override
		public BlockCell<Color> getCellAt(int x, int y)
		{
			if(this.block == null)
			{
				return this.noneCell;
			}
			
			int offsetX = (this.blockSize - this.block.getWidth()) / 2;
			int offsetY = (this.blockSize - this.block.getHeight()) / 2;
			
			if((x >= offsetX) && (x < offsetX + this.block.getWidth())
				&& (y >= offsetY) && (y < offsetY + this.block.getHeight()))
			{
				return this.block.getCellAt(x - offsetX, y - offsetY);
			}
			
			return this.noneCell;
		}
	}
}
