package view.draw;

import java.awt.*;
import model.tetris.block2d.IBlock2D;

public abstract class Block2DDrawer implements IDrawer
{
	private Block2DDrawer nextDrawer;
	private IBlock2D<Color> block;
	
	/**
	 * �C���X�^���X�����������܂�
	 * @param nextDrawer ���ɕ`�悷��Drawer
	 */
	protected Block2DDrawer(Block2DDrawer nextDrawer)
	{
		this.nextDrawer = nextDrawer;
	}
	
	/** �`�悷��u���b�N��ݒ肵�܂� */
	public final void setBlock(IBlock2D<Color> block)
	{
		this.block = block;
		
		if(this.nextDrawer != null)
			this.nextDrawer.setBlock(block);
	}
	
	protected final IBlock2D<Color> getBlock()
	{
		return this.block;
	}
	
	@Override
	public final void draw(Graphics g, Rectangle area)
	{
		this.doDraw(g, area);
		
		if(this.nextDrawer != null)
			this.nextDrawer.draw(g, area);
	}
	
	/**
	 * ���̃C���X�^���X���s���`�揈���݂̂��������܂�
	 * @param g �`��Ώۂ̃O���t�B�b�N�X
	 * @param area �`��̈�
	 */
	protected abstract void doDraw(Graphics g, Rectangle area);
}
