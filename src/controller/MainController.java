package controller;

import java.awt.event.KeyEvent;
import view.draw.*;
import view.frame.*;

public class MainController implements IController
{
	private final MainFrame frame;
	private IStateController currentState;
	
	public MainController(String title, States initState)
	{
		this.currentState = initState.createState(this);
		this.frame = new MainFrame(this, title);	
	}
	
	/**
	 * ゲームを開始します.
	 */
	public void start()
	{
		this.frame.setVisible(true);
		this.frame.updateViews();
	}
	
	/**
	 * ゲームの状態を変更します.
	 * @param state
	 */
	public void changeState(States state)
	{
		TransitionData data = (this.currentState != null) ? this.currentState.finish() : null;
		
		this.currentState = state.createState(this);
		this.currentState.start(data);
		
		this.frame.updateViews();
	}
	
	@Override
	public void keyPressed(KeyEvent e)
	{
		this.currentState.keyPressed(e);
	}
	
	@Override
	public IDrawer getMainDrawer()
	{
		return this.currentState.getMainDrawer();
	}
	
	@Override
	public IDrawer getNextDrawer()
	{
		return this.currentState.getNextDrawer();
	}
	
	@Override
	public IDrawer getHoldDrawer()
	{
		return this.currentState.getHoldDrawer();
	}
	
	@Override
	public IDrawer getScoreDrawer()
	{
		return this.currentState.getScoreDrawer();
	}
	
	@Override
	public IDrawer getManualDrawer()
	{
		return this.currentState.getManualDrawer();
	}
	
	@Override
	public synchronized void timeTicked()
	{
		this.currentState.timeTicked();
	}

	@Override
	public boolean allowFastFrame()
	{
		return this.currentState.allowFastFrame();
	}
}
