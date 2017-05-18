package controller;

import java.awt.*;
import java.awt.event.*;
import view.draw.*;
import view.utils.*;
import model.score.*;
import model.tetris.block2d.*;
import model.tetris.common.*;

/**
 * リザルト画面のVM
 *  - 受け取るデータ ： IScore/NumberImages
 *  - 次へ渡すデータ ： なし
 */
// package
class ResultState implements IStateController
{
	private final MainController parentVM;
	private ResultDrawer mainDrawer = new ResultDrawer(TextImages.PLAIN, NumberImages.PLAIN);
	private final Block2DDrawer subDrawer = new ImageBlock2DDrawer(BlockImages.PLAIN, null, null);
	private final ScoreDrawer scoreDrawer = new ScoreDrawer(TextImages.PLAIN, NumberImages.PLAIN);
	private final ImageDrawer manualDrawer = new ImageDrawer().setImage(BasicImages.MANUAL_RESULT.getImage());
	
	private long counter = 0;
	private int index = -1;
	private final BlockCell<Color> cellOff = new BlockCell<>(BlockState.MOVE, new Color(0xFF404040, true));
	private final BlockCell<Color> cellOn = new BlockCell<>(BlockState.MOVE, new Color(0xFFFFFFFF, true));
	private final MutableBlock2D<Color> block = new MutableBlock2D<>(5, 5, this.cellOff);
	
	// Next/Holdに描画するブロックのフロー
	private static final Point[][] BLOCK_POINTS_FLOW = new Point[][]
	{
		new Point[] { },
		
		new Point[] { new Point(0, 0), new Point(2, 0), new Point(4, 0), },
		
		new Point[] { new Point(0, 0), new Point(2, 0), new Point(4, 0),
			new Point(1, 1), new Point(3, 1), },
			
		new Point[] { new Point(0, 0), new Point(2, 0), new Point(4, 0),
			new Point(1, 1), new Point(3, 1),
			new Point(0, 2), new Point(2, 2), new Point(4, 2), },
			
		new Point[] { new Point(0, 0), new Point(2, 0), new Point(4, 0),
			new Point(1, 1), new Point(3, 1),
			new Point(0, 2), new Point(2, 2), new Point(4, 2),
			new Point(1, 3), new Point(3, 3), },
			
		new Point[] { new Point(0, 0), new Point(2, 0), new Point(4, 0),
			new Point(1, 1), new Point(3, 1),
			new Point(0, 2), new Point(2, 2), new Point(4, 2),
			new Point(1, 3), new Point(3, 3),
			new Point(0, 4), new Point(2, 4), new Point(4, 4), },
		
		new Point[] { new Point(1, 1), new Point(3, 1),
			new Point(0, 2), new Point(2, 2), new Point(4, 2),
			new Point(1, 3), new Point(3, 3),
			new Point(0, 4), new Point(2, 4), new Point(4, 4), },
		
		new Point[] { new Point(0, 2), new Point(2, 2), new Point(4, 2),
			new Point(1, 3), new Point(3, 3),
			new Point(0, 4), new Point(2, 4), new Point(4, 4), },
		
		new Point[] { new Point(1, 3), new Point(3, 3),
			new Point(0, 4), new Point(2, 4), new Point(4, 4), },
		
		new Point[] { new Point(0, 4), new Point(2, 4), new Point(4, 4), },
	};
	
	public ResultState(MainController parentVM)
	{
		this.parentVM = parentVM;
		this.subDrawer.setBlock(this.block);
	}
	
	@Override
	public void keyPressed(KeyEvent e)
	{
		switch(e.getKeyCode())
		{
			case KeyEvent.VK_SPACE:
				this.parentVM.changeState(States.MENU);
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
		return this.subDrawer;
	}
	
	@Override
	public IDrawer getHoldDrawer()
	{
		return this.subDrawer;
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
		if((counter++ % 10) != 0)
			return;
		
		this.index = (this.index + 1) % BLOCK_POINTS_FLOW.length;
		this.block.clear();
		
		for(Point point : BLOCK_POINTS_FLOW[index])
		{
			this.block.setCellAt(point.x, point.y, this.cellOn);
		}
	}

	@Override
	public void start(TransitionData data)
	{
		if(data != null)
		{
			if(data.contains(NumberImages.class))
			{
				this.mainDrawer = new ResultDrawer(TextImages.PLAIN, data.getData(NumberImages.class));
			}
			if(data.contains(IScore.class))
			{
				this.mainDrawer.setScore(data.getData(IScore.class));
			}
		}
	}

	@Override
	public TransitionData finish()
	{
		return new TransitionData();
	}

	@Override
	public boolean allowFastFrame()
	{
		return false;
	}
}