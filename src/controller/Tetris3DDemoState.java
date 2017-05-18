package controller;

import java.awt.Color;
import java.awt.event.*;
import java.util.List;
import view.draw.*;
import view.utils.*;
import model.score.*;
import model.tetris.block3d.*;
import model.tetris.common.*;

/**
 * 3DテトリスのデモのVM
 *  - 受け取るデータ ： なし
 *  - 次へ渡すデータ ： なし
 */
// package
class Tetris3DDemoState implements IStateController
{
	private static final int DEF_WIDTH = 5;
	private static final int DEF_HEIGHT = 5;
	private static final int DEF_DEPTH = 15;
	private static final int BASE_SCORE = 10000;
	private static final int BASE_QUOTA = 5;
	private static final double BASE_GRAVITY = 0.05;
	
	private static final BlockCell<Color> NONE_CELL = new BlockCell<>(BlockState.NONE, new Color(0xFF000000, true));
	
	private final IBlockCreator<Block3D<Color>> blockCreator = new OrderedBlockCreator<>(Tetris3DDemoStory.blockList());
	private final List<Tetris3DDemoStory.TetrisCommand> commands = Tetris3DDemoStory.commandList();
	private final IScoreCalculator scoreCalculator = new SimpleScoreCalculator(BASE_SCORE, BASE_QUOTA, BASE_GRAVITY);
	private int counter;
	private final int interval = 5;
	
	private MainController parentVM;
	private Tetris3D<Color> tetris;
	
	private final ColorBlock3DDrawer mainDrawer;
	private final ColorBlock3DDrawer nextDrawer;
	private final ColorBlock3DDrawer holdDrawer;
	private final ScoreDrawer scoreDrawer = new ScoreDrawer(TextImages.PLAIN, NumberImages.PLAIN);
	private final ImageDrawer manualDrawer = new ImageDrawer().setImage(BasicImages.MANUAL_3D_DEMO.getImage());
	
	public Tetris3DDemoState(MainController parentVM)
	{
		// Drawerの生成
		this.mainDrawer = new ColorBlock3DDrawer(true, false);
		this.nextDrawer = new ColorBlock3DDrawer(false, false);
		this.holdDrawer = new ColorBlock3DDrawer(false, false);
		
		this.parentVM = parentVM;
		
		this.initTetris(DEF_WIDTH, DEF_HEIGHT, DEF_DEPTH);
	}
	
	@Override
	public void keyPressed(KeyEvent e)
	{
		switch(e.getKeyCode())
		{
			case KeyEvent.VK_SPACE:
				this.tetris.finish();
				break;
				
			default:
				break;
		}
	}
	
	@Override
	public IDrawer getMainDrawer()
	{
		return this.mainDrawer;
	}
	
	@Override
	public IDrawer getNextDrawer()
	{
		return this.nextDrawer;
	}
	
	@Override
	public IDrawer getHoldDrawer()
	{
		return this.holdDrawer;
	}

	@Override
	public IDrawer getScoreDrawer()
	{
		return this.scoreDrawer;
	}
	
	@Override
	public IDrawer getManualDrawer()
	{
		return this.manualDrawer;
	}
	
	@Override
	public void timeTicked()
	{
		this.tetris.timeTicked();
		
		if((this.counter % this.interval) == 0)
		{
			this.commands.get(this.counter / this.interval).execute(this.tetris);
		}
		this.counter = (this.counter + 1) % (this.commands.size() * this.interval);
		
		this.mainDrawer.setBlock3D(this.tetris.getFiled());
		this.nextDrawer.setBlock3D(this.tetris.getNextBlock());
		this.holdDrawer.setBlock3D(this.tetris.getHoldBlock());
	}
	
	@Override
	public void start(TransitionData data)
	{
		this.initTetris(DEF_WIDTH, DEF_HEIGHT, DEF_DEPTH);
	}
	
	@Override
	public TransitionData finish()
	{
		return new TransitionData(IScore.class, this.tetris.getScore());
	}
	
	private void initTetris(int width, int height, int depth)
	{
		// ゲーム状態の設定
		this.tetris = new Tetris3D<>(width, height, depth, NONE_CELL, this.blockCreator, this.scoreCalculator);
		this.scoreDrawer.setScore(null);
		this.counter = 0;
		
		// ブロック固定時の処理
		this.tetris.setBlockFixedListener(null);
		
		// ゲーム終了時の処理
		this.tetris.setGameFinishedListener(new IGameFinishedHandler()
		{
			@Override
			public void execute()
			{
				Tetris3DDemoState.this.parentVM.changeState(States.MENU);
			}
		});
	}

	@Override
	public boolean allowFastFrame()
	{
		return true;
	}
}