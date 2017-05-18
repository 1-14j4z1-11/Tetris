package model.tetris.block2d;

import java.awt.Color;
import model.tetris.common.*;

public class Default2DColorBlocks
{
	private static final BlockCell<Color> CELL_J =		new BlockCell<>(BlockState.MOVE, new Color(0xFF_00_70_FF, true));
	private static final BlockCell<Color> CELL_L =		new BlockCell<>(BlockState.MOVE, new Color(0xFF_FF_A0_00, true));
	private static final BlockCell<Color> CELL_S =		new BlockCell<>(BlockState.MOVE, new Color(0xFF_70_FF_70, true));
	private static final BlockCell<Color> CELL_Z =		new BlockCell<>(BlockState.MOVE, new Color(0xFF_FF_70_70, true));
	private static final BlockCell<Color> CELL_I =		new BlockCell<>(BlockState.MOVE, new Color(0xFF_70_D0_FF, true));
	private static final BlockCell<Color> CELL_O =		new BlockCell<>(BlockState.MOVE, new Color(0xFF_FF_FF_30, true));
	private static final BlockCell<Color> CELL_T =		new BlockCell<>(BlockState.MOVE, new Color(0xFF_D0_70_FF, true));
	private static final BlockCell<Color> CELL_NONE =	new BlockCell<>(BlockState.NONE, new Color(0xFF_00_00_00, true));
	
	private static final Block2D<Color> BLOCK_J = new Block2D.Builder<>(3, 3, CELL_NONE)
		.setCell(1, 0, CELL_J)
		.setCell(1, 1, CELL_J)
		.setCell(1, 2, CELL_J)
		.setCell(0, 2, CELL_J)
		.build();

	private static final Block2D<Color> BLOCK_L = new Block2D.Builder<>(3, 3, CELL_NONE)
		.setCell(1, 0, CELL_L)
		.setCell(1, 1, CELL_L)
		.setCell(1, 2, CELL_L)
		.setCell(0, 0, CELL_L)
		.build();

	private static final Block2D<Color> BLOCK_S = new Block2D.Builder<>(3, 3, CELL_NONE)
		.setCell(1, 0, CELL_S)
		.setCell(1, 1, CELL_S)
		.setCell(2, 1, CELL_S)
		.setCell(2, 2, CELL_S)
		.build();

	private static final Block2D<Color> BLOCK_Z = new Block2D.Builder<>(3, 3, CELL_NONE)
		.setCell(1, 0, CELL_Z)
		.setCell(1, 1, CELL_Z)
		.setCell(0, 1, CELL_Z)
		.setCell(0, 2, CELL_Z)
		.build();

	private static final Block2D<Color> BLOCK_I = new Block2D.Builder<>(4, 4, CELL_NONE)
		.setCell(2, 0, CELL_I)
		.setCell(2, 1, CELL_I)
		.setCell(2, 2, CELL_I)
		.setCell(2, 3, CELL_I)
		.build();

	private static final Block2D<Color> BLOCK_O = new Block2D.Builder<>(2, 2, CELL_NONE)
		.setCell(0, 0, CELL_O)
		.setCell(0, 1, CELL_O)
		.setCell(1, 0, CELL_O)
		.setCell(1, 1, CELL_O)
		.build();

	private static final Block2D<Color> BLOCK_T = new Block2D.Builder<>(3, 3, CELL_NONE)
		.setCell(0, 1, CELL_T)
		.setCell(1, 1, CELL_T)
		.setCell(2, 1, CELL_T)
		.setCell(1, 0, CELL_T)
		.build();
	
	private Default2DColorBlocks() { }
	
	public static Block2D<Color> J()
	{
		return BLOCK_J;
	}
	
	public static Block2D<Color> L()
	{
		return BLOCK_L;
	}

	public static Block2D<Color> S()
	{
		return BLOCK_S;
	}

	public static Block2D<Color> Z()
	{
		return BLOCK_Z;
	}

	public static Block2D<Color> I()
	{
		return BLOCK_I;
	}

	public static Block2D<Color> O()
	{
		return BLOCK_O;
	}

	public static Block2D<Color> T()
	{
		return BLOCK_T;
	}
}
