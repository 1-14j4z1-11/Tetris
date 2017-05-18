package view.draw;

import java.util.Arrays;
import java.util.List;
import java.awt.*;
import model.tetris.block2d.*;
import model.tetris.common.*;

/**
 * �u���b�N�Z�����ێ�����Color�C���X�^���X�̐F�Ńu���b�N��`�悷��N���X</br>
 * �w�肵����Ԃ̃u���b�N�Z���݂̂�`��
 */
public class ColorBlock2DDrawer extends Block2DDrawer
{
	private final List<BlockState> targets;
	private final Color overrideColor;
	
	/**
	 * �C���X�^���X�����������܂�.
	 * @param targets �`��Ώۂ�BlockState��List
	 * @param nextDrawer ���̃C���X�^���X�̕`���ɌĂяo��Drawer
	 */
	public ColorBlock2DDrawer(BlockState[] targets, Block2DDrawer nextDrawer)
	{
		this(targets, null, nextDrawer);
	}
	
	/**
	 * �C���X�^���X�����������܂�.
	 * @param parentDrawer ���̃C���X�^���X�̕`��̌�Ɏ��s����Drawer
	 * @param targets �`��Ώۂ�BlockState��List
	 * @param overrideColor �`�悷��u���b�N�̐F(BlockCell�̏��𖳎����ĕ`��A�ݒ肵�Ȃ��ꍇ��null)
	 */
	public ColorBlock2DDrawer(BlockState[] targets, Color overrideColor, Block2DDrawer nextDrawer)
	{
		super(nextDrawer);
		
		this.targets = (targets != null) ? Arrays.asList(targets) : null;
		this.overrideColor = overrideColor;
	}
	
	@Override
	protected void doDraw(Graphics g, Rectangle area)
	{
		if(this.getBlock() == null)
			return;
		
		IBlock2D<Color> block = this.getBlock();
		
		int blockSize = Math.min(area.width / block.getWidth(), area.height / block.getHeight());

		int x = (area.width - block.getWidth() * blockSize) / 2  + area.x;
		int y = (area.height - block.getHeight() * blockSize) / 2  + area.y;
		
		for(int i = 0; i < block.getWidth(); i++)
		{
			for(int j = 0; j < block.getHeight(); j++)
			{
				BlockState state = block.getCellAt(i, j).getState();
				
				if((this.targets != null) && !this.targets.contains(state))
					continue;
				
				if(this.overrideColor != null)
					g.setColor(this.overrideColor);
				else
					g.setColor(block.getCellAt(i, j).getTag());
				
				if(state == BlockState.GHOST)
					g.draw3DRect(x + i * blockSize + 2, y + j * blockSize + 2, blockSize, blockSize, true);
				else
					g.fill3DRect(x + i * blockSize + 2, y + j * blockSize + 2, blockSize, blockSize, true);
			}
		}
	}
}
