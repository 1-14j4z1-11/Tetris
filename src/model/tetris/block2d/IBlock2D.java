package model.tetris.block2d;

import model.tetris.common.BlockCell;


public interface IBlock2D<T>
{
	/** ブロックの幅を取得します. */
	int getWidth();
	
	/** ブロックの高さを取得します. */
	int getHeight();
	
	/** (x,y)の位置のブロックセルを取得します. */
	BlockCell<T> getCellAt(int x, int y);
}
