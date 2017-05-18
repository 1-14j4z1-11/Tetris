package model.tetris.block2d;

import model.tetris.common.BlockCell;


public interface IBlock2D<T>
{
	/** �u���b�N�̕����擾���܂�. */
	int getWidth();
	
	/** �u���b�N�̍������擾���܂�. */
	int getHeight();
	
	/** (x,y)�̈ʒu�̃u���b�N�Z�����擾���܂�. */
	BlockCell<T> getCellAt(int x, int y);
}
