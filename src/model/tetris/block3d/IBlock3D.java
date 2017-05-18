package model.tetris.block3d;

import model.tetris.common.*;

public interface IBlock3D<T>
{
	/** ブロックの幅を取得します. */
	int getWidth();

	/** ブロックの高さを取得します. */
	int getHeight();

	/** ブロックの深さを取得します. */
	int getDepth();

	/** (x,y)の位置のブロックセルを取得します. */
	BlockCell<T> getCellAt(int x, int y, int z);
}
