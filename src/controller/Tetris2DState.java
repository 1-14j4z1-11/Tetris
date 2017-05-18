package controller;

import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.List;
import view.draw.*;
import view.utils.*;
import model.menu.*;
import model.score.*;
import model.tetris.block2d.*;
import model.tetris.common.*;

/**
 * 2DテトリスのVM
 *  - 受け取るデータ ： Config
 *  - 次へ渡すデータ ： IScore
 */
// package
class Tetris2DState implements IStateController
{
	private static final int DEF_WIDTH = 10;
	private static final int DEF_HEIGHT = 20;
	private static final int BASE_SCORE = 1000;
	private static final int BASE_QUOTA = 5;
	private static final double BASE_GRAVITY = 0.05;
	
	private static final List<Block2D<Color>> BLOCKS = new ArrayList<>();
	private static final BlockCell<Color> NONE_CELL = new BlockCell<>(BlockState.NONE, new Color(0xFF000000, true));
	
	// 生成されるブロックの設定
	static
	{
		BLOCKS.add(Default2DColorBlocks.J());
		BLOCKS.add(Default2DColorBlocks.L());
		BLOCKS.add(Default2DColorBlocks.S());
		BLOCKS.add(Default2DColorBlocks.Z());
		BLOCKS.add(Default2DColorBlocks.I());
		BLOCKS.add(Default2DColorBlocks.O());
		BLOCKS.add(Default2DColorBlocks.T());
	}
	
	private final IBlockCreator<Block2D<Color>> blockCreator = new RandomBlockCreator<>(BLOCKS);
	private final IScoreCalculator scoreCalculator = new SimpleScoreCalculator(BASE_SCORE, BASE_QUOTA, BASE_GRAVITY);
	
	private MainController parentVM;
	private Tetris2D<Color> tetris;
	private Config config = new Config().setDifficulty(Config.Difficulty.NORMAL).setInitLevel(1);
	
	private Block2DDrawer mainDrawer;
	private final Block2DDrawer mainDrawerNormal;
	private final Block2DDrawer mainDrawerHard;
	private final Block2DDrawer nextDrawer;
	private final Block2DDrawer holdDrawer;
	private final ScoreDrawer scoreDrawer = new ScoreDrawer(TextImages.PLAIN, NumberImages.PLAIN);
	private final ImageDrawer manualDrawer = new ImageDrawer().setImage(BasicImages.MANUAL_2D.getImage());
	
	private boolean skip = false;
	
	public Tetris2DState(MainController parentVM)
	{
		this.parentVM = parentVM;
		
		// Drawerの生成
		BlockImages blockImage = BlockImages.PLAIN;
		Block2DDrawer blockDrawer = new ImageBlock2DDrawer(blockImage, new BlockState[] { BlockState.NONE, BlockState.GHOST }, null);
		Block2DDrawer clockDrawer = new ClockDrawer(12, blockDrawer);
		
		this.mainDrawerNormal = new ImageBlock2DDrawer(blockImage, new BlockState[] { BlockState.MOVE, BlockState.FIXED }, clockDrawer);
		this.mainDrawerHard = new ColorBlock2DDrawer(new BlockState[] { BlockState.FIXED }, new Color(0xFF000000),
				new ImageBlock2DDrawer(blockImage, new BlockState[] { BlockState.MOVE }, clockDrawer));
		
		this.nextDrawer = new FixedSizeBlockDrawDecorator(new ImageBlock2DDrawer(BlockImages.PLAIN, null, null), null, 5, NONE_CELL);
		this.holdDrawer = new FixedSizeBlockDrawDecorator(new ImageBlock2DDrawer(BlockImages.PLAIN, null, null), null, 5, NONE_CELL);
		
		this.mainDrawer = this.mainDrawerNormal;
		
		// Tetrisインスタンスの初期化
		this.initTetris(DEF_WIDTH, DEF_HEIGHT);
	}
	
	@Override
	public void keyPressed(KeyEvent e)
	{
		switch(e.getKeyCode())
		{
			case KeyEvent.VK_LEFT:
				this.tetris.move(-1, 0);
				break;
			
			case KeyEvent.VK_RIGHT:
				this.tetris.move(1, 0);
				break;
				
			case KeyEvent.VK_DOWN:
				this.tetris.move(0, 1);
				break;
				
			case KeyEvent.VK_UP:
				this.tetris.hardDrop();
				break;
				
			case KeyEvent.VK_Z:
				this.tetris.rotateL();
				break;

			case KeyEvent.VK_X:
				this.tetris.rotateR();
				break;
				
			case KeyEvent.VK_SHIFT:
				this.tetris.holdBlock();
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
		if(!skip)
		{
			this.tetris.timeTicked();
		}

		this.mainDrawerNormal.setBlock(this.tetris.getFiled());
		this.mainDrawerHard.setBlock(this.tetris.getFiled());
		this.nextDrawer.setBlock(this.tetris.getNextBlock());
		this.holdDrawer.setBlock(this.tetris.getHoldBlock());
	}
	
	@Override
	public void start(TransitionData data)
	{
		if((data != null) && data.contains(Config.class))
		{
			Config config = data.getData(Config.class);
			this.initTetris(config.getWidth2D(), config.getHeight2D());
		}
		else
		{
			this.initTetris(DEF_HEIGHT, DEF_HEIGHT);
		}
	}
	
	@Override
	public TransitionData finish()
	{
		return new TransitionData()
			.addData(IScore.class, this.tetris.getScore())
			.addData(NumberImages.class, NumberImages.PLAIN);
	}
	
	private void initTetris(int width, int height)
	{
		// ゲーム状態の設定
		this.tetris = new Tetris2D<>(width, height, NONE_CELL, this.blockCreator, this.scoreCalculator);
		this.scoreDrawer.setScore(this.tetris.getScore());
		
		// ブロック固定時の処理
		this.tetris.setBlockFixedListener(new IBlockFixedHandler()
		{
			private int lineCount;
			
			@Override
			public void execute(IScore score)
			{
				if(this.lineCount > 0)
					this.lineCount--;
				
				this.lineCount += score.getLine();
				
				synchronized(Tetris2DState.this)
				{
					skip = true;
					
					if(config.getDifficulty() == Config.Difficulty.HARD)
						mainDrawer = (lineCount > 0) ? mainDrawerNormal : mainDrawerHard;
					else
						mainDrawer = mainDrawerNormal;
					
					skip = false;
				}
			}
		});
		
		// ゲーム終了時の処理
		this.tetris.setGameFinishedListener(new IGameFinishedHandler()
		{
			@Override
			public void execute()
			{
				Tetris2DState.this.parentVM.changeState(States.RESULT);
			}
		});
	}

	@Override
	public boolean allowFastFrame()
	{
		return false;
	}
}