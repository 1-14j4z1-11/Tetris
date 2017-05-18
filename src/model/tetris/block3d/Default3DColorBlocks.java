package model.tetris.block3d;

import java.awt.Color;
import model.tetris.common.*;

public class Default3DColorBlocks
{
	private static final BlockCell<Color> CELL_J =		new BlockCell<>(BlockState.MOVE, new Color(0xFF_00_70_FF, true));
	private static final BlockCell<Color> CELL_L =		new BlockCell<>(BlockState.MOVE, new Color(0xFF_FF_A0_00, true));
	private static final BlockCell<Color> CELL_S =		new BlockCell<>(BlockState.MOVE, new Color(0xFF_70_FF_70, true));
	private static final BlockCell<Color> CELL_Z =		new BlockCell<>(BlockState.MOVE, new Color(0xFF_FF_70_70, true));
	private static final BlockCell<Color> CELL_I =		new BlockCell<>(BlockState.MOVE, new Color(0xFF_70_D0_FF, true));
	private static final BlockCell<Color> CELL_O =		new BlockCell<>(BlockState.MOVE, new Color(0xFF_D0_D0_30, true));
	private static final BlockCell<Color> CELL_T =		new BlockCell<>(BlockState.MOVE, new Color(0xFF_D0_70_FF, true));
	private static final BlockCell<Color> CELL_LL =		new BlockCell<>(BlockState.MOVE, new Color(0xFF_40_40_40, true));
	private static final BlockCell<Color> CELL_LS1 =	new BlockCell<>(BlockState.MOVE, new Color(0xFF_00_00_FF, true));
	private static final BlockCell<Color> CELL_LS2 =	new BlockCell<>(BlockState.MOVE, new Color(0xFF_80_20_80, true));
	private static final BlockCell<Color> CELL_NONE =	new BlockCell<>(BlockState.NONE, new Color(0xFF_00_00_00, true));
	
	private static final Block3D<Color> BLOCK_J = new Block3D.Builder<>(3, 3, 3, CELL_NONE)
		.setCell(1, 0, 1, CELL_J)
		.setCell(1, 1, 1, CELL_J)
		.setCell(1, 2, 1, CELL_J)
		.setCell(0, 2, 1, CELL_J)
		.build();
	
	private static final Block3D<Color> BLOCK_L = new Block3D.Builder<>(3, 3, 3, CELL_NONE)
		.setCell(1, 0, 1, CELL_L)
		.setCell(1, 1, 1, CELL_L)
		.setCell(1, 2, 1, CELL_L)
		.setCell(0, 0, 1, CELL_L)
		.build();
	
	private static final Block3D<Color> BLOCK_S = new Block3D.Builder<>(3, 3, 3, CELL_NONE)
		.setCell(1, 0, 1, CELL_S)
		.setCell(1, 1, 1, CELL_S)
		.setCell(2, 1, 1, CELL_S)
		.setCell(2, 2, 1, CELL_S)
		.build();
	
	private static final Block3D<Color> BLOCK_Z = new Block3D.Builder<>(3, 3, 3, CELL_NONE)
		.setCell(1, 0, 1, CELL_Z)
		.setCell(1, 1, 1, CELL_Z)
		.setCell(0, 1, 1, CELL_Z)
		.setCell(0, 2, 1, CELL_Z)
		.build();
	
	private static final Block3D<Color> BLOCK_I = new Block3D.Builder<>(4, 4, 4, CELL_NONE)
		.setCell(2, 0, 1, CELL_I)
		.setCell(2, 1, 1, CELL_I)
		.setCell(2, 2, 1, CELL_I)
		.setCell(2, 3, 1, CELL_I)
		.build();
	
	private static final Block3D<Color> BLOCK_O = new Block3D.Builder<>(2, 2, 2, CELL_NONE)
		.setCell(0, 0, 1, CELL_O)
		.setCell(0, 1, 1, CELL_O)
		.setCell(1, 0, 1, CELL_O)
		.setCell(1, 1, 1, CELL_O)
		.build();
	
	private static final Block3D<Color> BLOCK_T = new Block3D.Builder<>(3, 3, 3, CELL_NONE)
		.setCell(0, 1, 1, CELL_T)
		.setCell(1, 1, 1, CELL_T)
		.setCell(2, 1, 1, CELL_T)
		.setCell(1, 0, 1, CELL_T)
		.build();
	
	private static final Block3D<Color> BLOCK_LL = new Block3D.Builder<>(2, 2, 2, CELL_NONE)
		.setCell(0, 0, 0, CELL_LL)
		.setCell(0, 1, 0, CELL_LL)
		.setCell(1, 0, 0, CELL_LL)
		.setCell(0, 0, 1, CELL_LL)
		.build();
	
	private static final Block3D<Color> BLOCK_LS1 = new Block3D.Builder<>(2, 2, 2, CELL_NONE)
		.setCell(0, 0, 0, CELL_LS1)
		.setCell(0, 1, 0, CELL_LS1)
		.setCell(1, 0, 0, CELL_LS1)
		.setCell(0, 1, 1, CELL_LS1)
		.build();

	private static final Block3D<Color> BLOCK_LS2 = new Block3D.Builder<>(2, 2, 2, CELL_NONE)
		.setCell(0, 0, 1, CELL_LS2)
		.setCell(0, 1, 1, CELL_LS2)
		.setCell(1, 0, 1, CELL_LS2)
		.setCell(0, 1, 0, CELL_LS2)
		.build();
	
	private Default3DColorBlocks() { }
	
	public static Block3D<Color> J()
	{
		return BLOCK_J;
	}
	
	public static Block3D<Color> L()
	{
		return BLOCK_L;
	}
	
	public static Block3D<Color> S()
	{
		return BLOCK_S;
	}
	
	public static Block3D<Color> Z()
	{
		return BLOCK_Z;
	}
	
	public static Block3D<Color> I()
	{
		return BLOCK_I;
	}
	
	public static Block3D<Color> O()
	{
		return BLOCK_O;
	}
	
	public static Block3D<Color> T()
	{
		return BLOCK_T;
	}
	
	public static Block3D<Color> LL()
	{
		return BLOCK_LL;
	}
	
	public static Block3D<Color> LS1()
	{
		return BLOCK_LS1;
	}
	
	public static Block3D<Color> LS2()
	{
		return BLOCK_LS2;
	}
}
