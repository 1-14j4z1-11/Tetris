package controller;

import java.awt.Color;
import java.util.*;
import model.tetris.block3d.*;

/**
 * 3D�e�g���X�̃f��������L�ڂ����N���X
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
	
	// �u���b�N/����̎菇����
	private static final List<Block3D<Color>> BLOCKS = new ArrayList<>();
	private static final List<TetrisCommand> COMMANDS = new ArrayList<>();
	
	// �u���b�N�̏����ݒ�
	static
	{
		BLOCKS.add(Default3DColorBlocks.LL());	// �_�~�[
		
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
	
	// �u���b�N����1������
	static
	{
		// �u���b�N1��
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
		
		// �u���b�N2��
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
		
		// �u���b�N3��
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
		
		// �u���b�N4��
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
		
		// �u���b�N5��
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
		
		// �u���b�N6��
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

		// �u���b�N7��
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
		
		// �u���b�N8��
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
	
	// �u���b�N����2������
	static
	{
		// �u���b�N1��
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
		
		// �u���b�N2��
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
		
		// �u���b�N3��
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
		
		// �u���b�N4��
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
		
		// �u���b�N5��
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
		
		// �u���b�N6��
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

		// �u���b�N7��
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
		
		// �u���b�N8��
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
	
	// �u���b�N����3������
	static
	{
		// �u���b�N1��
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
		
		// �u���b�N2��
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
		
		// �u���b�N3��
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
		
		// �u���b�N4��
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
		
		// �u���b�N5��
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
		
		// �u���b�N6��
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

		// �u���b�N7��
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
		
		// �u���b�N8��
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
	
	// �u���b�N����4������
	static
	{
		// �u���b�N1��
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
		
		// �u���b�N2��
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
		
		// �u���b�N3��
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
		
		// �u���b�N4��
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
		
		// �u���b�N5��
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
		
		// �u���b�N6��
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

		// �u���b�N7��
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
		
		// �u���b�N8��
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
	 * TetrisCommand�����������{�����Enum
	 */
	private static enum Commands implements TetrisCommand
	{
		NULL
		{
			@Override
			public void execute(Tetris3D<Color> tetris)
			{
				// �������Ȃ�
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
