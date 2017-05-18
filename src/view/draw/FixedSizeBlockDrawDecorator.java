package view.draw;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import model.tetris.block2d.*;
import model.tetris.common.*;

/**
 * ブロックサイズを実サイズに依存せず固定サイズで描画するためのDecoratorクラス
 */
public class FixedSizeBlockDrawDecorator extends Block2DDrawer
{
	private final Block2DDrawer baseDrawer;
	private final WrappedBlock wrappedBlock;
	
	/**
	 * インスタンスを初期化します
	 * @param baseDrawer 描画ブロックを固定サイズ化する対象のDrawer
	 * @param nextDrawer 次に描画するDrawer
	 * @param blockSize ブロックのサイズ
	 * @param noneCell サイズを埋めるためのブロックセル
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
		// baseDrawer.setBlock()はこのクラスで明示的に呼び出す必要がある
		this.baseDrawer.setBlock(this.wrappedBlock.setBlock(this.getBlock()));
		this.baseDrawer.doDraw(g, area);
	}
	
	/**
	 * IBlockインスタンスをラップして固定サイズのIBlockインスタンス化する内部クラス
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
		 * ラップするブロックを設定します
		 * @param block 設定するブロック
		 * @return このインスタンス自身
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
