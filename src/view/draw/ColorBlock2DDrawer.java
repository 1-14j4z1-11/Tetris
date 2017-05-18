package view.draw;

import java.util.Arrays;
import java.util.List;
import java.awt.*;
import model.tetris.block2d.*;
import model.tetris.common.*;

/**
 * ブロックセルが保持するColorインスタンスの色でブロックを描画するクラス</br>
 * 指定した状態のブロックセルのみを描画
 */
public class ColorBlock2DDrawer extends Block2DDrawer
{
	private final List<BlockState> targets;
	private final Color overrideColor;
	
	/**
	 * インスタンスを初期化します.
	 * @param targets 描画対象のBlockStateのList
	 * @param nextDrawer このインスタンスの描画後に呼び出すDrawer
	 */
	public ColorBlock2DDrawer(BlockState[] targets, Block2DDrawer nextDrawer)
	{
		this(targets, null, nextDrawer);
	}
	
	/**
	 * インスタンスを初期化します.
	 * @param parentDrawer このインスタンスの描画の後に実行するDrawer
	 * @param targets 描画対象のBlockStateのList
	 * @param overrideColor 描画するブロックの色(BlockCellの情報を無視して描画、設定しない場合はnull)
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
