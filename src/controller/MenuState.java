package controller;

import java.awt.event.*;
import model.menu.*;
import view.draw.*;
import view.utils.*;

/**
 * メニュー画面のVM
 *  - 受け取るデータ ： なし
 *  - 次へ渡すデータ ： Config
 */
// package
class MenuState implements IStateController
{
	private MainController parentVM;
	private Config config = new Config();
	
	private IDrawer mainDrawer;
	private ScoreDrawer scoreDrawer = new ScoreDrawer(TextImages.PLAIN, NumberImages.PLAIN);
	private final ImageDrawer manualDrawer = new ImageDrawer().setImage(BasicImages.MANUAL_MENU.getImage());
	
	public MenuState(MainController parentVM)
	{
		this.parentVM = parentVM;
		this.initConfig();
	}
	
	@Override
	public void keyPressed(KeyEvent e)
	{
		switch(e.getKeyCode())
		{
			case KeyEvent.VK_SPACE:
				if(e.isControlDown())
					this.parentVM.changeState(States.TETRIS_3D);
				else
					this.parentVM.changeState(States.TETRIS_2D);
				break;
				
			case KeyEvent.VK_ENTER:
				if(e.isControlDown())
					this.parentVM.changeState(States.TETRIS_3D_DEMO);
				break;
				
			default:
				break;
		}
	}
	
	private void initConfig()
	{
		this.config = new Config();
		this.mainDrawer = new MenuDrawer(this.config);
	}
	
	@Override
	public IDrawer getMainDrawer()
	{
		return this.mainDrawer;
	}

	@Override
	public IDrawer getNextDrawer()
	{
		return null;
	}
	
	@Override
	public IDrawer getHoldDrawer()
	{
		return null;
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
		// 何もしない
	}
	
	@Override
	public void start(TransitionData data)
	{
		this.initConfig();
	}

	@Override
	public TransitionData finish()
	{
		return new TransitionData(Config.class, this.config);
	}

	@Override
	public boolean allowFastFrame()
	{
		return false;
	}
}