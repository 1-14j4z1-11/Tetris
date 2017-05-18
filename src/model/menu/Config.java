package model.menu;

import controller.States;

public class Config
{
	public static enum Difficulty
	{
		NORMAL,
		HARD;
	}

	public static enum GameMode
	{
		TETRIS_2D(States.TETRIS_2D),
		TETRIS_3D(States.TETRIS_3D);
		
		private final States state;
		
		private GameMode(States state)
		{
			this.state = state;
		}
		
		public States getState()
		{
			return this.state;
		}
	}
	
	private Difficulty difficulty = Difficulty.NORMAL;
	private GameMode gameMode = GameMode.TETRIS_2D;

	private int initLevel = 1;
	private int width2D = 10;
	private int height2D = 20;
	private int width3D = 5;
	private int height3D = 5;
	private int depth3D = 15;
	
	public Difficulty getDifficulty() { return this.difficulty; }
	public Config setDifficulty(Difficulty difficulty) { this.difficulty = difficulty; return this; }
	
	public GameMode getGameMode() { return this.gameMode; }
	public Config setGameMode(GameMode gameMode) { this.gameMode = gameMode; return this; }
	
	public int getInitLevel() { return this.initLevel; }
	public Config setInitLevel(int initLevel) { this.initLevel = initLevel; return this; }
	
	public int getWidth2D() { return this.width2D; }
	public Config setWidth2D(int width) { this.width2D = width; return this; }
	
	public int getHeight2D() { return this.height2D; }
	public Config setHeight2D(int height) { this.height2D = height; return this; }

	public int getWidth3D() { return this.width3D; }
	public Config setWidth3D(int width) { this.width3D = width; return this; }
	
	public int getHeight3D() { return this.height3D; }
	public Config setHeight3D(int height) { this.height3D = height; return this; }

	public int getDepth3D() { return this.depth3D; }
	public Config setDepth(int depth) { this.depth3D = depth; return this; }
}