package model.tetris.block3d;

import model.tetris.common.*;

public interface IBlock3D<T>
{
	/** �u���b�N�̕����擾���܂�. */
	int getWidth();

	/** �u���b�N�̍������擾���܂�. */
	int getHeight();

	/** �u���b�N�̐[�����擾���܂�. */
	int getDepth();

	/** (x,y)�̈ʒu�̃u���b�N�Z�����擾���܂�. */
	BlockCell<T> getCellAt(int x, int y, int z);
}
