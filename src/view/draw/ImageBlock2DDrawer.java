package view.draw;

import java.util.Arrays;
import java.util.List;
import java.awt.*;
import view.utils.*;
import model.tetris.block2d.*;
import model.tetris.common.*;

/**
 * �u���b�N�Z�����ێ�����Color�C���X�^���X�̐F�Ńu���b�N��`�悷��N���X</br>
 * �w�肵����Ԃ̃u���b�N�Z���݂̂�`��
 */
public class ImageBlock2DDrawer extends Block2DDrawer
{
	private static final Color BG_COLOR = new Color(0xFF000000, true);
	
	private final BlockImages images;
	private final List<BlockState> targets;
	
	/**
	 * �C���X�^���X�����������܂�.
	 * @param images �`��Ŏg�p����BlockImages
	 * @param targets �`��Ώۂ�BlockState��List
	 * @param nextDrawer ���̃C���X�^���X�̕`���ɌĂяo��Drawer
	 */
	public ImageBlock2DDrawer(BlockImages images, BlockState[] targets, Block2DDrawer nextDrawer)
	{
		super(nextDrawer);
		
		this.images = images;
		this.targets = (targets != null) ? Arrays.asList(targets) : null;
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
		
		g.setColor(BG_COLOR);
		
		for(int i = 0; i < block.getWidth(); i++)
		{
			for(int j = 0; j < block.getHeight(); j++)
			{
				BlockState state = block.getCellAt(i, j).getState();
				
				if((this.targets != null) && !this.targets.contains(state))
					continue;
				
				int sX = x + i * blockSize + 2;
				int sY = y + j * blockSize + 2;
				
				//�@��x�w�i��h��Ԃ�(���߉摜�`��̂���)
				g.fillRect(sX, sY, blockSize, blockSize);
				
				Image image = this.images.createBlockImage(block.getCellAt(i, j));
				g.drawImage(image, sX, sY, sX + blockSize, sY + blockSize,
					0, 0, image.getWidth(null), image.getHeight(null), null);
			}
		}
	}
}
