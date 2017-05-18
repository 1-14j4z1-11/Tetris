package controller;

import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.List;
import view.draw.*;
import view.utils.*;
import model.menu.*;
import model.score.*;
import model.tetris.block3d.*;
import model.tetris.common.*;

/**
 * 3DテトリスのVM
 *  - 受け取るデータ ： Config
 *  - 次へ渡すデータ ： IScore
 */
// package
class Tetris3DState implements IStateController
{
	private static final int DEF_WIDTH = 5;
	private static final int DEF_HEIGHT = 5;
	private static final int DEF_DEPTH = 15;
	private static final int BASE_SCORE = 20000;
	private static final int BASE_QUOTA = 5;
	private static final double BASE_GRAVITY = 0.025;
	
	private static final List<Block3D<Color>> BLOCKS = new ArrayList<>();
	private static final BlockCell<Color> NONE_CELL = new BlockCell<>(BlockState.NONE, new Color(0xFF000000, true));
	
	// 生成されるブロックの設定
	static
	{
		BLOCKS.add(Default3DColorBlocks.J());
		//blocks.add(DefaultColorBlocks.L());	// 3次元ではJと同じ
		BLOCKS.add(Default3DColorBlocks.S());
		//blocks.add(DefaultColorBlocks.Z());	// 3次元ではSと同じ
		BLOCKS.add(Default3DColorBlocks.I());
		BLOCKS.add(Default3DColorBlocks.O());
		BLOCKS.add(Default3DColorBlocks.T());
		BLOCKS.add(Default3DColorBlocks.LL());
		BLOCKS.add(Default3DColorBlocks.LS1());
		BLOCKS.add(Default3DColorBlocks.LS2());
	}
	
	private final IBlockCreator<Block3D<Color>> blockCreator = new RandomBlockCreator<>(BLOCKS);
	private final IScoreCalculator scoreCalculator = new SimpleScoreCalculator(BASE_SCORE, BASE_QUOTA, BASE_GRAVITY);
	
	private MainController parentVM;
	private Tetris3D<Color> tetris;
	
	private final ColorBlock3DDrawer mainDrawer;
	private final ColorBlock3DDrawer nextDrawer;
	private final ColorBlock3DDrawer holdDrawer;
	private final ScoreDrawer scoreDrawer = new ScoreDrawer(TextImages.PLAIN, NumberImages.PLAIN);
	private final ImageDrawer manualDrawer = new ImageDrawer().setImage(BasicImages.MANUAL_3D.getImage());
	
	public Tetris3DState(MainController parentVM)
	{
		// Drawerの生成
		this.mainDrawer = new ColorBlock3DDrawer(true, true).setOpaquePlane(false);
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
			case KeyEvent.VK_LEFT:
				this.tetris.move(-1, 0, 0);
				break;
			
			case KeyEvent.VK_RIGHT:
				this.tetris.move(1, 0, 0);
				break;
				
			case KeyEvent.VK_DOWN:
				this.tetris.move(0, 1, 0);
				break;
				
			case KeyEvent.VK_UP:
				this.tetris.move(0, -1, 0);
				break;

			case KeyEvent.VK_SPACE:
				if(e.isControlDown())
					this.tetris.hardDrop();
				else
					this.tetris.move(0, 0, 1);
				break;
				
			case KeyEvent.VK_Z:
				this.tetris.rotateZ();
				break;
				
			case KeyEvent.VK_X:
				this.tetris.rotateX();
				break;

			case KeyEvent.VK_C:
				this.tetris.rotateY();
				break;

			case KeyEvent.VK_A:
				this.mainDrawer.setOpaquePlane(!this.mainDrawer.isOpaquePlane());
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
		this.tetris.timeTicked();
		
		this.mainDrawer.setBlock3D(this.tetris.getFiled());
		this.nextDrawer.setBlock3D(this.tetris.getNextBlock());
		this.holdDrawer.setBlock3D(this.tetris.getHoldBlock());
	}
	
	@Override
	public void start(TransitionData data)
	{
		if((data != null) && data.contains(Config.class))
		{
			Config config = data.getData(Config.class);
			this.initTetris(config.getWidth3D(), config.getHeight3D(), config.getDepth3D());
		}
		else
		{
			this.initTetris(DEF_WIDTH, DEF_HEIGHT, DEF_DEPTH);
		}
	}
	
	@Override
	public TransitionData finish()
	{
		return new TransitionData()
			.addData(IScore.class, this.tetris.getScore())
			.addData(NumberImages.class, NumberImages.PLAIN);
	}
	
	private void initTetris(int width, int height, int depth)
	{
		// ゲーム状態の設定
		this.tetris = new Tetris3D<>(width, height, depth, NONE_CELL, this.blockCreator, this.scoreCalculator);
		this.scoreDrawer.setScore(this.tetris.getScore());
		
		// ブロック固定時の処理
		this.tetris.setBlockFixedListener(null);
		
		// ゲーム終了時の処理
		this.tetris.setGameFinishedListener(new IGameFinishedHandler()
		{
			@Override
			public void execute()
			{
				Tetris3DState.this.parentVM.changeState(States.RESULT);
			}
		});
	}
	
	@Override
	public boolean allowFastFrame()
	{
		return false;
	}
}