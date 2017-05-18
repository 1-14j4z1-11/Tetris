package controller;

import java.awt.Color;
import java.util.*;
import model.tetris.block3d.*;

/**
 * 3Dテトリスのデモ動作を記載したクラス
 */
class Tetris3DDemoStory
{
	public static interface TetrisCommand
	{
		void execute(Tetris3D<Color> tetris);
	}
	
	public static List<Block3D<Color>> blockList()
	{
		return BLOCKS;
	}
	
	public static List<TetrisCommand> commandList()
	{
		return COMMANDS;
	}
	
	// ブロック/操作の手順生成
	private static final List<Block3D<Color>> BLOCKS = new ArrayList<>();
	private static final List<TetrisCommand> COMMANDS = new ArrayList<>();
	
	// ブロックの順序設定
	static
	{
		BLOCKS.add(Default3DColorBlocks.LL());	// ダミー
		
		BLOCKS.add(Default3DColorBlocks.J());
		BLOCKS.add(Default3DColorBlocks.S());
		BLOCKS.add(Default3DColorBlocks.T());
		BLOCKS.add(Default3DColorBlocks.O());
		BLOCKS.add(Default3DColorBlocks.LS1());
		BLOCKS.add(Default3DColorBlocks.LS2());
		BLOCKS.add(Default3DColorBlocks.I());
		BLOCKS.add(Default3DColorBlocks.LL());
		
		BLOCKS.add(Default3DColorBlocks.LS1());
		BLOCKS.add(Default3DColorBlocks.I());
		BLOCKS.add(Default3DColorBlocks.S());
		BLOCKS.add(Default3DColorBlocks.LL());
		BLOCKS.add(Default3DColorBlocks.LS2());
		BLOCKS.add(Default3DColorBlocks.J());
		BLOCKS.add(Default3DColorBlocks.O());
		BLOCKS.add(Default3DColorBlocks.T());

		BLOCKS.add(Default3DColorBlocks.LL());
		BLOCKS.add(Default3DColorBlocks.J());
		BLOCKS.add(Default3DColorBlocks.LS2());
		BLOCKS.add(Default3DColorBlocks.I());
		BLOCKS.add(Default3DColorBlocks.T());
		BLOCKS.add(Default3DColorBlocks.O());
		BLOCKS.add(Default3DColorBlocks.LS1());
		BLOCKS.add(Default3DColorBlocks.S());
		
		BLOCKS.add(Default3DColorBlocks.T());
		BLOCKS.add(Default3DColorBlocks.O());
		BLOCKS.add(Default3DColorBlocks.LS1());
		BLOCKS.add(Default3DColorBlocks.S());
		BLOCKS.add(Default3DColorBlocks.J());
		BLOCKS.add(Default3DColorBlocks.LS2());
		BLOCKS.add(Default3DColorBlocks.I());
	}
	
	// ブロック操作1周期目
	static
	{
		// ブロック1個目
		COMMANDS.add(Commands.NULL);
		COMMANDS.add(Commands.NULL);
		COMMANDS.add(Commands.NULL);
		COMMANDS.add(Commands.NULL);
		COMMANDS.add(Commands.NULL);
		COMMANDS.add(Commands.NULL);
		COMMANDS.add(Commands.ROTATE_X);
		COMMANDS.add(Commands.ROTATE_X);
		COMMANDS.add(Commands.ROTATE_X);
		COMMANDS.add(Commands.MOVE_Y_MINUS);
		COMMANDS.add(Commands.MOVE_Y_MINUS);
		COMMANDS.add(Commands.MOVE_X_PLUS);
		COMMANDS.add(Commands.MOVE_X_PLUS);
		COMMANDS.add(Commands.MOVE_Z_PLUS);
		COMMANDS.add(Commands.MOVE_Z_PLUS);
		COMMANDS.add(Commands.MOVE_Z_PLUS);
		COMMANDS.add(Commands.MOVE_Z_PLUS);
		COMMANDS.add(Commands.MOVE_Z_PLUS);
		COMMANDS.add(Commands.MOVE_Z_PLUS);
		COMMANDS.add(Commands.MOVE_Z_PLUS);
		COMMANDS.add(Commands.MOVE_Z_PLUS);
		COMMANDS.add(Commands.MOVE_Z_PLUS);
		
		// ブロック2個目
		COMMANDS.add(Commands.NULL);
		COMMANDS.add(Commands.NULL);
		COMMANDS.add(Commands.NULL);
		COMMANDS.add(Commands.NULL);
		COMMANDS.add(Commands.NULL);
		COMMANDS.add(Commands.NULL);
		COMMANDS.add(Commands.ROTATE_Y);
		COMMANDS.add(Commands.ROTATE_Z);
		COMMANDS.add(Commands.ROTATE_Z);
		COMMANDS.add(Commands.ROTATE_Z);
		COMMANDS.add(Commands.MOVE_Y_MINUS);
		COMMANDS.add(Commands.MOVE_Y_MINUS);
		COMMANDS.add(Commands.MOVE_Z_PLUS);
		COMMANDS.add(Commands.MOVE_Z_PLUS);
		COMMANDS.add(Commands.MOVE_Z_PLUS);
		COMMANDS.add(Commands.MOVE_Z_PLUS);
		COMMANDS.add(Commands.MOVE_Z_PLUS);
		COMMANDS.add(Commands.MOVE_Z_PLUS);
		COMMANDS.add(Commands.MOVE_Z_PLUS);
		COMMANDS.add(Commands.MOVE_Z_PLUS);
		COMMANDS.add(Commands.MOVE_Z_PLUS);
		COMMANDS.add(Commands.MOVE_Z_PLUS);
		
		// ブロック3個目
		COMMANDS.add(Commands.NULL);
		COMMANDS.add(Commands.NULL);
		COMMANDS.add(Commands.NULL);
		COMMANDS.add(Commands.NULL);
		COMMANDS.add(Commands.NULL);
		COMMANDS.add(Commands.NULL);
		COMMANDS.add(Commands.ROTATE_X);
		COMMANDS.add(Commands.ROTATE_Y);
		COMMANDS.add(Commands.ROTATE_Z);
		COMMANDS.add(Commands.ROTATE_Z);
		COMMANDS.add(Commands.MOVE_Y_MINUS);
		COMMANDS.add(Commands.MOVE_Y_MINUS);
		COMMANDS.add(Commands.MOVE_X_MINUS);
		COMMANDS.add(Commands.MOVE_X_MINUS);
		COMMANDS.add(Commands.MOVE_Z_PLUS);
		COMMANDS.add(Commands.MOVE_Z_PLUS);
		COMMANDS.add(Commands.MOVE_Z_PLUS);
		COMMANDS.add(Commands.MOVE_Z_PLUS);
		COMMANDS.add(Commands.MOVE_Z_PLUS);
		COMMANDS.add(Commands.MOVE_Z_PLUS);
		COMMANDS.add(Commands.MOVE_Z_PLUS);
		COMMANDS.add(Commands.MOVE_Z_PLUS);
		
		// ブロック4個目
		COMMANDS.add(Commands.NULL);
		COMMANDS.add(Commands.NULL);
		COMMANDS.add(Commands.NULL);
		COMMANDS.add(Commands.NULL);
		COMMANDS.add(Commands.NULL);
		COMMANDS.add(Commands.NULL);
		COMMANDS.add(Commands.MOVE_X_PLUS);
		COMMANDS.add(Commands.MOVE_X_PLUS);
		COMMANDS.add(Commands.MOVE_Z_PLUS);
		COMMANDS.add(Commands.MOVE_Z_PLUS);
		COMMANDS.add(Commands.MOVE_Z_PLUS);
		COMMANDS.add(Commands.MOVE_Z_PLUS);
		COMMANDS.add(Commands.MOVE_Z_PLUS);
		COMMANDS.add(Commands.MOVE_Z_PLUS);
		COMMANDS.add(Commands.MOVE_Z_PLUS);
		COMMANDS.add(Commands.MOVE_Z_PLUS);
		COMMANDS.add(Commands.MOVE_Z_PLUS);
		COMMANDS.add(Commands.MOVE_Z_PLUS);
		COMMANDS.add(Commands.MOVE_Z_PLUS);
		
		// ブロック5個目
		COMMANDS.add(Commands.NULL);
		COMMANDS.add(Commands.NULL);
		COMMANDS.add(Commands.NULL);
		COMMANDS.add(Commands.NULL);
		COMMANDS.add(Commands.NULL);
		COMMANDS.add(Commands.NULL);
		COMMANDS.add(Commands.ROTATE_Z);
		COMMANDS.add(Commands.ROTATE_Z);
		COMMANDS.add(Commands.ROTATE_Z);
		COMMANDS.add(Commands.MOVE_X_PLUS);
		COMMANDS.add(Commands.MOVE_Z_PLUS);
		COMMANDS.add(Commands.MOVE_Z_PLUS);
		COMMANDS.add(Commands.MOVE_Z_PLUS);
		COMMANDS.add(Commands.MOVE_Z_PLUS);
		COMMANDS.add(Commands.MOVE_Z_PLUS);
		COMMANDS.add(Commands.MOVE_Z_PLUS);
		COMMANDS.add(Commands.MOVE_Z_PLUS);
		COMMANDS.add(Commands.MOVE_Z_PLUS);
		COMMANDS.add(Commands.MOVE_Z_PLUS);
		COMMANDS.add(Commands.MOVE_Z_PLUS);
		COMMANDS.add(Commands.MOVE_Z_PLUS);
		
		// ブロック6個目
		COMMANDS.add(Commands.NULL);
		COMMANDS.add(Commands.NULL);
		COMMANDS.add(Commands.NULL);
		COMMANDS.add(Commands.NULL);
		COMMANDS.add(Commands.NULL);
		COMMANDS.add(Commands.NULL);
		COMMANDS.add(Commands.ROTATE_Y);
		COMMANDS.add(Commands.ROTATE_Z);
		COMMANDS.add(Commands.ROTATE_Z);
		COMMANDS.add(Commands.ROTATE_Z);
		COMMANDS.add(Commands.MOVE_X_PLUS);
		COMMANDS.add(Commands.MOVE_X_PLUS);
		COMMANDS.add(Commands.MOVE_Z_PLUS);
		COMMANDS.add(Commands.MOVE_Z_PLUS);
		COMMANDS.add(Commands.MOVE_Z_PLUS);
		COMMANDS.add(Commands.MOVE_Z_PLUS);
		COMMANDS.add(Commands.MOVE_Z_PLUS);
		COMMANDS.add(Commands.MOVE_Z_PLUS);
		COMMANDS.add(Commands.MOVE_Z_PLUS);
		COMMANDS.add(Commands.MOVE_Z_PLUS);
		COMMANDS.add(Commands.MOVE_Z_PLUS);
		COMMANDS.add(Commands.MOVE_Z_PLUS);

		// ブロック7個目
		COMMANDS.add(Commands.NULL);
		COMMANDS.add(Commands.NULL);
		COMMANDS.add(Commands.NULL);
		COMMANDS.add(Commands.NULL);
		COMMANDS.add(Commands.NULL);
		COMMANDS.add(Commands.NULL);
		COMMANDS.add(Commands.ROTATE_Z);
		COMMANDS.add(Commands.MOVE_X_MINUS);
		COMMANDS.add(Commands.MOVE_Y_PLUS);
		COMMANDS.add(Commands.MOVE_Y_PLUS);
		COMMANDS.add(Commands.MOVE_Z_PLUS);
		COMMANDS.add(Commands.MOVE_Z_PLUS);
		COMMANDS.add(Commands.MOVE_Z_PLUS);
		COMMANDS.add(Commands.MOVE_Z_PLUS);
		COMMANDS.add(Commands.MOVE_Z_PLUS);
		COMMANDS.add(Commands.MOVE_Z_PLUS);
		COMMANDS.add(Commands.MOVE_Z_PLUS);
		COMMANDS.add(Commands.MOVE_Z_PLUS);
		COMMANDS.add(Commands.MOVE_Z_PLUS);
		COMMANDS.add(Commands.MOVE_Z_PLUS);
		COMMANDS.add(Commands.MOVE_Z_PLUS);
		
		// ブロック8個目
		COMMANDS.add(Commands.NULL);
		COMMANDS.add(Commands.NULL);
		COMMANDS.add(Commands.NULL);
		COMMANDS.add(Commands.NULL);
		COMMANDS.add(Commands.NULL);
		COMMANDS.add(Commands.NULL);
		COMMANDS.add(Commands.ROTATE_Z);
		COMMANDS.add(Commands.ROTATE_Z);
		COMMANDS.add(Commands.MOVE_X_PLUS);
		COMMANDS.add(Commands.MOVE_X_PLUS);
		COMMANDS.add(Commands.MOVE_Y_PLUS);
		COMMANDS.add(Commands.MOVE_Z_PLUS);
		COMMANDS.add(Commands.MOVE_Z_PLUS);
		COMMANDS.add(Commands.MOVE_Z_PLUS);
		COMMANDS.add(Commands.MOVE_Z_PLUS);
		COMMANDS.add(Commands.MOVE_Z_PLUS);
		COMMANDS.add(Commands.MOVE_Z_PLUS);
		COMMANDS.add(Commands.MOVE_Z_PLUS);
		COMMANDS.add(Commands.MOVE_Z_PLUS);
		COMMANDS.add(Commands.MOVE_Z_PLUS);
		COMMANDS.add(Commands.MOVE_Z_PLUS);
		COMMANDS.add(Commands.MOVE_Z_PLUS);
	}
	
	// ブロック操作2周期目
	static
	{
		// ブロック1個目
		COMMANDS.add(Commands.NULL);
		COMMANDS.add(Commands.NULL);
		COMMANDS.add(Commands.NULL);
		COMMANDS.add(Commands.NULL);
		COMMANDS.add(Commands.NULL);
		COMMANDS.add(Commands.NULL);
		COMMANDS.add(Commands.ROTATE_Z);
		COMMANDS.add(Commands.ROTATE_Z);
		COMMANDS.add(Commands.ROTATE_Z);
		COMMANDS.add(Commands.MOVE_Y_MINUS);
		COMMANDS.add(Commands.MOVE_Y_MINUS);
		COMMANDS.add(Commands.MOVE_X_PLUS);
		COMMANDS.add(Commands.MOVE_X_PLUS);
		COMMANDS.add(Commands.MOVE_Z_PLUS);
		COMMANDS.add(Commands.MOVE_Z_PLUS);
		COMMANDS.add(Commands.MOVE_Z_PLUS);
		COMMANDS.add(Commands.MOVE_Z_PLUS);
		COMMANDS.add(Commands.MOVE_Z_PLUS);
		COMMANDS.add(Commands.MOVE_Z_PLUS);
		COMMANDS.add(Commands.MOVE_Z_PLUS);
		COMMANDS.add(Commands.MOVE_Z_PLUS);
		
		// ブロック2個目
		COMMANDS.add(Commands.NULL);
		COMMANDS.add(Commands.NULL);
		COMMANDS.add(Commands.NULL);
		COMMANDS.add(Commands.NULL);
		COMMANDS.add(Commands.NULL);
		COMMANDS.add(Commands.NULL);
		COMMANDS.add(Commands.ROTATE_Z);
		COMMANDS.add(Commands.MOVE_X_MINUS);
		COMMANDS.add(Commands.MOVE_Y_PLUS);
		COMMANDS.add(Commands.MOVE_Y_PLUS);
		COMMANDS.add(Commands.MOVE_Y_PLUS);
		COMMANDS.add(Commands.MOVE_Z_PLUS);
		COMMANDS.add(Commands.MOVE_Z_PLUS);
		COMMANDS.add(Commands.MOVE_Z_PLUS);
		COMMANDS.add(Commands.MOVE_Z_PLUS);
		COMMANDS.add(Commands.MOVE_Z_PLUS);
		COMMANDS.add(Commands.MOVE_Z_PLUS);
		COMMANDS.add(Commands.MOVE_Z_PLUS);
		COMMANDS.add(Commands.MOVE_Z_PLUS);
		COMMANDS.add(Commands.MOVE_Z_PLUS);
		COMMANDS.add(Commands.MOVE_Z_PLUS);
		
		// ブロック3個目
		COMMANDS.add(Commands.NULL);
		COMMANDS.add(Commands.NULL);
		COMMANDS.add(Commands.NULL);
		COMMANDS.add(Commands.NULL);
		COMMANDS.add(Commands.NULL);
		COMMANDS.add(Commands.NULL);
		COMMANDS.add(Commands.ROTATE_Z);
		COMMANDS.add(Commands.MOVE_X_MINUS);
		COMMANDS.add(Commands.MOVE_X_MINUS);
		COMMANDS.add(Commands.ROTATE_Y);
		COMMANDS.add(Commands.ROTATE_Y);
		COMMANDS.add(Commands.MOVE_Z_PLUS);
		COMMANDS.add(Commands.MOVE_Z_PLUS);
		COMMANDS.add(Commands.MOVE_Z_PLUS);
		COMMANDS.add(Commands.MOVE_Z_PLUS);
		COMMANDS.add(Commands.MOVE_Z_PLUS);
		COMMANDS.add(Commands.MOVE_Z_PLUS);
		COMMANDS.add(Commands.MOVE_Z_PLUS);
		COMMANDS.add(Commands.MOVE_Z_PLUS);
		COMMANDS.add(Commands.MOVE_Z_PLUS);
		COMMANDS.add(Commands.MOVE_Z_PLUS);
		
		// ブロック4個目
		COMMANDS.add(Commands.NULL);
		COMMANDS.add(Commands.NULL);
		COMMANDS.add(Commands.NULL);
		COMMANDS.add(Commands.NULL);
		COMMANDS.add(Commands.NULL);
		COMMANDS.add(Commands.NULL);
		COMMANDS.add(Commands.ROTATE_Z);
		COMMANDS.add(Commands.MOVE_X_MINUS);
		COMMANDS.add(Commands.MOVE_Z_PLUS);
		COMMANDS.add(Commands.MOVE_Z_PLUS);
		COMMANDS.add(Commands.MOVE_Z_PLUS);
		COMMANDS.add(Commands.MOVE_Z_PLUS);
		COMMANDS.add(Commands.MOVE_Z_PLUS);
		COMMANDS.add(Commands.MOVE_Z_PLUS);
		COMMANDS.add(Commands.MOVE_Z_PLUS);
		COMMANDS.add(Commands.MOVE_Z_PLUS);
		COMMANDS.add(Commands.MOVE_Z_PLUS);
		COMMANDS.add(Commands.MOVE_Z_PLUS);
		COMMANDS.add(Commands.MOVE_Z_PLUS);
		
		// ブロック5個目
		COMMANDS.add(Commands.NULL);
		COMMANDS.add(Commands.NULL);
		COMMANDS.add(Commands.NULL);
		COMMANDS.add(Commands.NULL);
		COMMANDS.add(Commands.NULL);
		COMMANDS.add(Commands.NULL);
		COMMANDS.add(Commands.ROTATE_X);
		COMMANDS.add(Commands.ROTATE_Y);
		COMMANDS.add(Commands.ROTATE_Z);
		COMMANDS.add(Commands.ROTATE_Z);
		COMMANDS.add(Commands.MOVE_X_MINUS);
		COMMANDS.add(Commands.MOVE_X_MINUS);
		COMMANDS.add(Commands.MOVE_Z_PLUS);
		COMMANDS.add(Commands.MOVE_Z_PLUS);
		COMMANDS.add(Commands.MOVE_Z_PLUS);
		COMMANDS.add(Commands.MOVE_Z_PLUS);
		COMMANDS.add(Commands.MOVE_Z_PLUS);
		COMMANDS.add(Commands.MOVE_Z_PLUS);
		COMMANDS.add(Commands.MOVE_Z_PLUS);
		COMMANDS.add(Commands.MOVE_Z_PLUS);
		COMMANDS.add(Commands.MOVE_Z_PLUS);
		COMMANDS.add(Commands.MOVE_Z_PLUS);
		
		// ブロック6個目
		COMMANDS.add(Commands.NULL);
		COMMANDS.add(Commands.NULL);
		COMMANDS.add(Commands.NULL);
		COMMANDS.add(Commands.NULL);
		COMMANDS.add(Commands.NULL);
		COMMANDS.add(Commands.NULL);
		COMMANDS.add(Commands.ROTATE_Y);
		COMMANDS.add(Commands.ROTATE_Z);
		COMMANDS.add(Commands.ROTATE_X);
		COMMANDS.add(Commands.MOVE_X_MINUS);
		COMMANDS.add(Commands.MOVE_X_MINUS);
		COMMANDS.add(Commands.MOVE_Y_PLUS);
		COMMANDS.add(Commands.MOVE_Z_PLUS);
		COMMANDS.add(Commands.MOVE_Z_PLUS);
		COMMANDS.add(Commands.MOVE_Z_PLUS);
		COMMANDS.add(Commands.MOVE_Z_PLUS);
		COMMANDS.add(Commands.MOVE_Z_PLUS);
		COMMANDS.add(Commands.MOVE_Z_PLUS);
		COMMANDS.add(Commands.MOVE_Z_PLUS);
		COMMANDS.add(Commands.MOVE_Z_PLUS);
		COMMANDS.add(Commands.MOVE_Z_PLUS);

		// ブロック7個目
		COMMANDS.add(Commands.NULL);
		COMMANDS.add(Commands.NULL);
		COMMANDS.add(Commands.NULL);
		COMMANDS.add(Commands.NULL);
		COMMANDS.add(Commands.NULL);
		COMMANDS.add(Commands.NULL);
		COMMANDS.add(Commands.ROTATE_X);
		COMMANDS.add(Commands.MOVE_Y_MINUS);
		COMMANDS.add(Commands.MOVE_Y_MINUS);
		COMMANDS.add(Commands.MOVE_Z_PLUS);
		COMMANDS.add(Commands.MOVE_Z_PLUS);
		COMMANDS.add(Commands.MOVE_Z_PLUS);
		COMMANDS.add(Commands.MOVE_Z_PLUS);
		COMMANDS.add(Commands.MOVE_Z_PLUS);
		COMMANDS.add(Commands.MOVE_Z_PLUS);
		COMMANDS.add(Commands.MOVE_Z_PLUS);
		COMMANDS.add(Commands.MOVE_Z_PLUS);
		COMMANDS.add(Commands.MOVE_Z_PLUS);
		
		// ブロック8個目
		COMMANDS.add(Commands.NULL);
		COMMANDS.add(Commands.NULL);
		COMMANDS.add(Commands.NULL);
		COMMANDS.add(Commands.NULL);
		COMMANDS.add(Commands.NULL);
		COMMANDS.add(Commands.NULL);
		COMMANDS.add(Commands.MOVE_X_MINUS);
		COMMANDS.add(Commands.MOVE_Y_PLUS);
		COMMANDS.add(Commands.MOVE_Z_PLUS);
		COMMANDS.add(Commands.MOVE_Z_PLUS);
		COMMANDS.add(Commands.MOVE_Z_PLUS);
		COMMANDS.add(Commands.MOVE_Z_PLUS);
		COMMANDS.add(Commands.MOVE_Z_PLUS);
		COMMANDS.add(Commands.MOVE_Z_PLUS);
		COMMANDS.add(Commands.MOVE_Z_PLUS);
		COMMANDS.add(Commands.MOVE_Z_PLUS);
		COMMANDS.add(Commands.MOVE_Z_PLUS);
		COMMANDS.add(Commands.MOVE_Z_PLUS);
		COMMANDS.add(Commands.MOVE_Z_PLUS);
	}
	
	// ブロック操作3周期目
	static
	{
		// ブロック1個目
		COMMANDS.add(Commands.NULL);
		COMMANDS.add(Commands.NULL);
		COMMANDS.add(Commands.NULL);
		COMMANDS.add(Commands.NULL);
		COMMANDS.add(Commands.NULL);
		COMMANDS.add(Commands.NULL);
		COMMANDS.add(Commands.ROTATE_Z);
		COMMANDS.add(Commands.NULL);
		COMMANDS.add(Commands.ROTATE_Z);
		COMMANDS.add(Commands.NULL);
		COMMANDS.add(Commands.ROTATE_X);
		COMMANDS.add(Commands.NULL);
		COMMANDS.add(Commands.ROTATE_X);
		COMMANDS.add(Commands.NULL);
		COMMANDS.add(Commands.ROTATE_X);
		COMMANDS.add(Commands.MOVE_X_PLUS);
		COMMANDS.add(Commands.MOVE_X_PLUS);
		COMMANDS.add(Commands.MOVE_Y_PLUS);
		COMMANDS.add(Commands.MOVE_Z_PLUS);
		COMMANDS.add(Commands.MOVE_Z_PLUS);
		COMMANDS.add(Commands.MOVE_Z_PLUS);
		COMMANDS.add(Commands.MOVE_Z_PLUS);
		COMMANDS.add(Commands.MOVE_Z_PLUS);
		COMMANDS.add(Commands.MOVE_Z_PLUS);
		COMMANDS.add(Commands.MOVE_Z_PLUS);
		COMMANDS.add(Commands.MOVE_Z_PLUS);
		
		// ブロック2個目
		COMMANDS.add(Commands.NULL);
		COMMANDS.add(Commands.NULL);
		COMMANDS.add(Commands.NULL);
		COMMANDS.add(Commands.NULL);
		COMMANDS.add(Commands.NULL);
		COMMANDS.add(Commands.NULL);
		COMMANDS.add(Commands.ROTATE_X);
		COMMANDS.add(Commands.NULL);
		COMMANDS.add(Commands.ROTATE_Y);
		COMMANDS.add(Commands.NULL);
		COMMANDS.add(Commands.ROTATE_X);
		COMMANDS.add(Commands.NULL);
		COMMANDS.add(Commands.ROTATE_X);
		COMMANDS.add(Commands.NULL);
		COMMANDS.add(Commands.ROTATE_Z);
		COMMANDS.add(Commands.NULL);
		COMMANDS.add(Commands.ROTATE_Z);
		COMMANDS.add(Commands.MOVE_X_PLUS);
		COMMANDS.add(Commands.MOVE_Z_PLUS);
		COMMANDS.add(Commands.MOVE_Z_PLUS);
		COMMANDS.add(Commands.MOVE_Z_PLUS);
		COMMANDS.add(Commands.MOVE_Z_PLUS);
		COMMANDS.add(Commands.MOVE_Z_PLUS);
		COMMANDS.add(Commands.MOVE_Z_PLUS);
		
		// ブロック3個目
		COMMANDS.add(Commands.NULL);
		COMMANDS.add(Commands.NULL);
		COMMANDS.add(Commands.NULL);
		COMMANDS.add(Commands.NULL);
		COMMANDS.add(Commands.NULL);
		COMMANDS.add(Commands.NULL);
		COMMANDS.add(Commands.ROTATE_Z);
		COMMANDS.add(Commands.ROTATE_Y);
		COMMANDS.add(Commands.ROTATE_Y);
		COMMANDS.add(Commands.MOVE_X_PLUS);
		COMMANDS.add(Commands.MOVE_X_PLUS);
		COMMANDS.add(Commands.MOVE_Y_PLUS);
		COMMANDS.add(Commands.MOVE_Z_PLUS);
		COMMANDS.add(Commands.MOVE_Z_PLUS);
		COMMANDS.add(Commands.MOVE_Z_PLUS);
		COMMANDS.add(Commands.MOVE_Z_PLUS);
		COMMANDS.add(Commands.MOVE_Z_PLUS);
		COMMANDS.add(Commands.MOVE_Z_PLUS);
		COMMANDS.add(Commands.MOVE_Z_PLUS);
		
		// ブロック4個目
		COMMANDS.add(Commands.NULL);
		COMMANDS.add(Commands.NULL);
		COMMANDS.add(Commands.NULL);
		COMMANDS.add(Commands.NULL);
		COMMANDS.add(Commands.NULL);
		COMMANDS.add(Commands.NULL);
		COMMANDS.add(Commands.ROTATE_Z);
		COMMANDS.add(Commands.MOVE_Y_PLUS);
		COMMANDS.add(Commands.MOVE_Y_PLUS);
		COMMANDS.add(Commands.MOVE_Y_PLUS);
		COMMANDS.add(Commands.MOVE_Z_PLUS);
		COMMANDS.add(Commands.MOVE_Z_PLUS);
		COMMANDS.add(Commands.MOVE_Z_PLUS);
		COMMANDS.add(Commands.MOVE_Z_PLUS);
		COMMANDS.add(Commands.MOVE_Z_PLUS);
		COMMANDS.add(Commands.MOVE_Z_PLUS);
		COMMANDS.add(Commands.MOVE_Z_PLUS);
		COMMANDS.add(Commands.MOVE_Z_PLUS);
		COMMANDS.add(Commands.MOVE_Z_PLUS);
		COMMANDS.add(Commands.MOVE_Z_PLUS);
		
		// ブロック5個目
		COMMANDS.add(Commands.NULL);
		COMMANDS.add(Commands.NULL);
		COMMANDS.add(Commands.NULL);
		COMMANDS.add(Commands.NULL);
		COMMANDS.add(Commands.NULL);
		COMMANDS.add(Commands.NULL);
		COMMANDS.add(Commands.ROTATE_X);
		COMMANDS.add(Commands.MOVE_Y_MINUS);
		COMMANDS.add(Commands.MOVE_Z_PLUS);
		COMMANDS.add(Commands.MOVE_Z_PLUS);
		COMMANDS.add(Commands.MOVE_Z_PLUS);
		COMMANDS.add(Commands.MOVE_Z_PLUS);
		COMMANDS.add(Commands.MOVE_Z_PLUS);
		COMMANDS.add(Commands.MOVE_Z_PLUS);
		COMMANDS.add(Commands.MOVE_Z_PLUS);
		COMMANDS.add(Commands.MOVE_Z_PLUS);
		COMMANDS.add(Commands.MOVE_Z_PLUS);
		
		// ブロック6個目
		COMMANDS.add(Commands.NULL);
		COMMANDS.add(Commands.NULL);
		COMMANDS.add(Commands.NULL);
		COMMANDS.add(Commands.NULL);
		COMMANDS.add(Commands.NULL);
		COMMANDS.add(Commands.NULL);
		COMMANDS.add(Commands.MOVE_X_MINUS);
		COMMANDS.add(Commands.MOVE_Y_PLUS);
		COMMANDS.add(Commands.MOVE_Z_PLUS);
		COMMANDS.add(Commands.MOVE_Z_PLUS);
		COMMANDS.add(Commands.MOVE_Z_PLUS);
		COMMANDS.add(Commands.MOVE_Z_PLUS);
		COMMANDS.add(Commands.MOVE_Z_PLUS);
		COMMANDS.add(Commands.MOVE_Z_PLUS);
		COMMANDS.add(Commands.MOVE_Z_PLUS);
		COMMANDS.add(Commands.MOVE_Z_PLUS);
		COMMANDS.add(Commands.MOVE_Z_PLUS);

		// ブロック7個目
		COMMANDS.add(Commands.NULL);
		COMMANDS.add(Commands.NULL);
		COMMANDS.add(Commands.NULL);
		COMMANDS.add(Commands.NULL);
		COMMANDS.add(Commands.NULL);
		COMMANDS.add(Commands.NULL);
		COMMANDS.add(Commands.ROTATE_X);
		COMMANDS.add(Commands.ROTATE_Z);
		COMMANDS.add(Commands.MOVE_X_MINUS);
		COMMANDS.add(Commands.MOVE_Y_MINUS);
		COMMANDS.add(Commands.MOVE_Z_PLUS);
		COMMANDS.add(Commands.MOVE_Z_PLUS);
		COMMANDS.add(Commands.MOVE_Z_PLUS);
		COMMANDS.add(Commands.MOVE_Z_PLUS);
		COMMANDS.add(Commands.MOVE_Z_PLUS);
		COMMANDS.add(Commands.MOVE_Z_PLUS);
		COMMANDS.add(Commands.MOVE_Z_PLUS);
		COMMANDS.add(Commands.MOVE_Z_PLUS);
		COMMANDS.add(Commands.MOVE_Z_PLUS);
		
		// ブロック8個目
		COMMANDS.add(Commands.NULL);
		COMMANDS.add(Commands.NULL);
		COMMANDS.add(Commands.NULL);
		COMMANDS.add(Commands.NULL);
		COMMANDS.add(Commands.NULL);
		COMMANDS.add(Commands.NULL);
		COMMANDS.add(Commands.ROTATE_X);
		COMMANDS.add(Commands.ROTATE_Z);
		COMMANDS.add(Commands.MOVE_Y_PLUS);
		COMMANDS.add(Commands.MOVE_Z_PLUS);
		COMMANDS.add(Commands.MOVE_Z_PLUS);
		COMMANDS.add(Commands.MOVE_Z_PLUS);
		COMMANDS.add(Commands.MOVE_Z_PLUS);
		COMMANDS.add(Commands.MOVE_Z_PLUS);
		COMMANDS.add(Commands.MOVE_Z_PLUS);
		COMMANDS.add(Commands.MOVE_Z_PLUS);
		COMMANDS.add(Commands.MOVE_Z_PLUS);
	}
	
	// ブロック操作4周期目
	static
	{
		// ブロック1個目
		COMMANDS.add(Commands.NULL);
		COMMANDS.add(Commands.NULL);
		COMMANDS.add(Commands.NULL);
		COMMANDS.add(Commands.NULL);
		COMMANDS.add(Commands.NULL);
		COMMANDS.add(Commands.NULL);
		COMMANDS.add(Commands.ROTATE_X);
		COMMANDS.add(Commands.ROTATE_X);
		COMMANDS.add(Commands.ROTATE_X);
		COMMANDS.add(Commands.MOVE_X_MINUS);
		COMMANDS.add(Commands.MOVE_Y_PLUS);
		COMMANDS.add(Commands.MOVE_Y_PLUS);
		COMMANDS.add(Commands.MOVE_Z_PLUS);
		COMMANDS.add(Commands.MOVE_Z_PLUS);
		COMMANDS.add(Commands.MOVE_Z_PLUS);
		COMMANDS.add(Commands.MOVE_Z_PLUS);
		COMMANDS.add(Commands.MOVE_Z_PLUS);
		COMMANDS.add(Commands.MOVE_Z_PLUS);
		COMMANDS.add(Commands.MOVE_Z_PLUS);
		COMMANDS.add(Commands.MOVE_Z_PLUS);
		COMMANDS.add(Commands.MOVE_Z_PLUS);
		
		// ブロック2個目
		COMMANDS.add(Commands.NULL);
		COMMANDS.add(Commands.NULL);
		COMMANDS.add(Commands.NULL);
		COMMANDS.add(Commands.NULL);
		COMMANDS.add(Commands.NULL);
		COMMANDS.add(Commands.NULL);
		COMMANDS.add(Commands.ROTATE_X);
		COMMANDS.add(Commands.MOVE_Y_MINUS);
		COMMANDS.add(Commands.MOVE_X_MINUS);
		COMMANDS.add(Commands.MOVE_Y_MINUS);
		COMMANDS.add(Commands.MOVE_Z_PLUS);
		COMMANDS.add(Commands.MOVE_Z_PLUS);
		COMMANDS.add(Commands.MOVE_Z_PLUS);
		COMMANDS.add(Commands.MOVE_Z_PLUS);
		COMMANDS.add(Commands.MOVE_Z_PLUS);
		COMMANDS.add(Commands.MOVE_Z_PLUS);
		COMMANDS.add(Commands.MOVE_Z_PLUS);
		COMMANDS.add(Commands.MOVE_Z_PLUS);
		
		// ブロック3個目
		COMMANDS.add(Commands.NULL);
		COMMANDS.add(Commands.NULL);
		COMMANDS.add(Commands.NULL);
		COMMANDS.add(Commands.NULL);
		COMMANDS.add(Commands.NULL);
		COMMANDS.add(Commands.NULL);
		COMMANDS.add(Commands.MOVE_X_MINUS);
		COMMANDS.add(Commands.MOVE_X_MINUS);
		COMMANDS.add(Commands.MOVE_Y_PLUS);
		COMMANDS.add(Commands.MOVE_Y_PLUS);
		COMMANDS.add(Commands.MOVE_Z_PLUS);
		COMMANDS.add(Commands.MOVE_Z_PLUS);
		COMMANDS.add(Commands.MOVE_Z_PLUS);
		COMMANDS.add(Commands.MOVE_Z_PLUS);
		COMMANDS.add(Commands.MOVE_Z_PLUS);
		COMMANDS.add(Commands.MOVE_Z_PLUS);
		COMMANDS.add(Commands.MOVE_Z_PLUS);
		
		// ブロック4個目
		COMMANDS.add(Commands.NULL);
		COMMANDS.add(Commands.NULL);
		COMMANDS.add(Commands.NULL);
		COMMANDS.add(Commands.NULL);
		COMMANDS.add(Commands.NULL);
		COMMANDS.add(Commands.NULL);
		COMMANDS.add(Commands.ROTATE_X);
		COMMANDS.add(Commands.ROTATE_Z);
		COMMANDS.add(Commands.ROTATE_Z);
		COMMANDS.add(Commands.MOVE_X_PLUS);
		COMMANDS.add(Commands.MOVE_Y_PLUS);
		COMMANDS.add(Commands.MOVE_Y_PLUS);
		COMMANDS.add(Commands.MOVE_Z_PLUS);
		COMMANDS.add(Commands.MOVE_Z_PLUS);
		COMMANDS.add(Commands.MOVE_Z_PLUS);
		COMMANDS.add(Commands.MOVE_Z_PLUS);
		COMMANDS.add(Commands.MOVE_Z_PLUS);
		COMMANDS.add(Commands.MOVE_Z_PLUS);
		COMMANDS.add(Commands.MOVE_Z_PLUS);
		COMMANDS.add(Commands.MOVE_Z_PLUS);
		
		// ブロック5個目
		COMMANDS.add(Commands.NULL);
		COMMANDS.add(Commands.NULL);
		COMMANDS.add(Commands.NULL);
		COMMANDS.add(Commands.NULL);
		COMMANDS.add(Commands.NULL);
		COMMANDS.add(Commands.NULL);
		COMMANDS.add(Commands.ROTATE_Z);
		COMMANDS.add(Commands.MOVE_X_PLUS);
		COMMANDS.add(Commands.MOVE_Y_MINUS);
		COMMANDS.add(Commands.MOVE_Y_MINUS);
		COMMANDS.add(Commands.MOVE_Z_PLUS);
		COMMANDS.add(Commands.MOVE_Z_PLUS);
		COMMANDS.add(Commands.MOVE_Z_PLUS);
		COMMANDS.add(Commands.MOVE_Z_PLUS);
		COMMANDS.add(Commands.MOVE_Z_PLUS);
		COMMANDS.add(Commands.MOVE_Z_PLUS);
		COMMANDS.add(Commands.MOVE_Z_PLUS);
		COMMANDS.add(Commands.MOVE_Z_PLUS);
		COMMANDS.add(Commands.MOVE_Z_PLUS);
		
		// ブロック6個目
		COMMANDS.add(Commands.NULL);
		COMMANDS.add(Commands.NULL);
		COMMANDS.add(Commands.NULL);
		COMMANDS.add(Commands.NULL);
		COMMANDS.add(Commands.NULL);
		COMMANDS.add(Commands.NULL);
		COMMANDS.add(Commands.ROTATE_Z);
		COMMANDS.add(Commands.ROTATE_Z);
		COMMANDS.add(Commands.ROTATE_Z);
		COMMANDS.add(Commands.MOVE_X_PLUS);
		COMMANDS.add(Commands.MOVE_Z_PLUS);
		COMMANDS.add(Commands.MOVE_Z_PLUS);
		COMMANDS.add(Commands.MOVE_Z_PLUS);
		COMMANDS.add(Commands.MOVE_Z_PLUS);
		COMMANDS.add(Commands.MOVE_Z_PLUS);
		COMMANDS.add(Commands.MOVE_Z_PLUS);
		COMMANDS.add(Commands.MOVE_Z_PLUS);

		// ブロック7個目
		COMMANDS.add(Commands.NULL);
		COMMANDS.add(Commands.NULL);
		COMMANDS.add(Commands.NULL);
		COMMANDS.add(Commands.NULL);
		COMMANDS.add(Commands.NULL);
		COMMANDS.add(Commands.NULL);
		COMMANDS.add(Commands.ROTATE_X);
		COMMANDS.add(Commands.MOVE_X_PLUS);
		COMMANDS.add(Commands.MOVE_X_PLUS);
		COMMANDS.add(Commands.MOVE_Y_PLUS);
		COMMANDS.add(Commands.MOVE_Y_PLUS);
		COMMANDS.add(Commands.MOVE_Y_PLUS);
		COMMANDS.add(Commands.MOVE_Z_PLUS);
		COMMANDS.add(Commands.MOVE_Z_PLUS);
		COMMANDS.add(Commands.MOVE_Z_PLUS);
		COMMANDS.add(Commands.MOVE_Z_PLUS);
		COMMANDS.add(Commands.MOVE_Z_PLUS);
		COMMANDS.add(Commands.MOVE_Z_PLUS);
		COMMANDS.add(Commands.MOVE_Z_PLUS);
		COMMANDS.add(Commands.MOVE_Z_PLUS);
		COMMANDS.add(Commands.MOVE_Z_PLUS);
		
		// ブロック8個目
		COMMANDS.add(Commands.NULL);
		COMMANDS.add(Commands.NULL);
		COMMANDS.add(Commands.NULL);
		COMMANDS.add(Commands.NULL);
		COMMANDS.add(Commands.NULL);
		COMMANDS.add(Commands.NULL);
		COMMANDS.add(Commands.ROTATE_X);
		COMMANDS.add(Commands.ROTATE_Z);
		COMMANDS.add(Commands.ROTATE_Z);
		COMMANDS.add(Commands.MOVE_X_PLUS);
		COMMANDS.add(Commands.MOVE_X_PLUS);
		COMMANDS.add(Commands.MOVE_Y_MINUS);
		COMMANDS.add(Commands.MOVE_Y_MINUS);
		COMMANDS.add(Commands.MOVE_Z_PLUS);
		COMMANDS.add(Commands.MOVE_Z_PLUS);
		COMMANDS.add(Commands.MOVE_Z_PLUS);
		COMMANDS.add(Commands.MOVE_Z_PLUS);
		COMMANDS.add(Commands.MOVE_Z_PLUS);
		COMMANDS.add(Commands.MOVE_Z_PLUS);
		COMMANDS.add(Commands.MOVE_Z_PLUS);
		COMMANDS.add(Commands.MOVE_Z_PLUS);
	}
	
	/**
	 * TetrisCommandを実現する基本操作のEnum
	 */
	private static enum Commands implements TetrisCommand
	{
		NULL
		{
			@Override
			public void execute(Tetris3D<Color> tetris)
			{
				// 何もしない
			}
		},
		
		FINISH
		{
			@Override
			public void execute(Tetris3D<Color> tetris)
			{
				tetris.finish();
			}
		},
		
		MOVE_X_PLUS
		{
			@Override
			public void execute(Tetris3D<Color> tetris)
			{
				tetris.move(1, 0, 0);
			}
		},
		
		MOVE_X_MINUS
		{
			@Override
			public void execute(Tetris3D<Color> tetris)
			{
				tetris.move(-1, 0, 0);
			}
		},
		
		MOVE_Y_PLUS
		{
			@Override
			public void execute(Tetris3D<Color> tetris)
			{
				tetris.move(0, 1, 0);
			}
		},
		
		MOVE_Y_MINUS
		{
			@Override
			public void execute(Tetris3D<Color> tetris)
			{
				tetris.move(0, -1, 0);
			}
		},

		MOVE_Z_PLUS
		{
			@Override
			public void execute(Tetris3D<Color> tetris)
			{
				tetris.move(0, 0, 1);
			}
		},

		ROTATE_X
		{
			@Override
			public void execute(Tetris3D<Color> tetris)
			{
				tetris.rotateX();
			}
		},

		ROTATE_Y
		{
			@Override
			public void execute(Tetris3D<Color> tetris)
			{
				tetris.rotateY();
			}
		},

		ROTATE_Z
		{
			@Override
			public void execute(Tetris3D<Color> tetris)
			{
				tetris.rotateZ();
			}
		};
	}
}
