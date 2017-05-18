package view.draw;

import java.awt.*;
import model.tetris.block2d.IBlock2D;

public abstract class Block2DDrawer implements IDrawer
{
	private Block2DDrawer nextDrawer;
	private IBlock2D<Color> block;
	
	/**
	 * インスタンスを初期化します
	 * @param nextDrawer 次に描画するDrawer
	 */
	protected Block2DDrawer(Block2DDrawer nextDrawer)
	{
		this.nextDrawer = nextDrawer;
	}
	
	/** 描画するブロックを設定します */
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
	 * このインスタンスが行う描画処理のみを実装します
	 * @param g 描画対象のグラフィックス
	 * @param area 描画領域
	 */
	protected abstract void doDraw(Graphics g, Rectangle area);
}
