package controller;

import java.awt.event.KeyEvent;
import view.draw.IDrawer;

/**
 * ゲームの各状態での状態変化に関する処理を提供するインターフェース
 */
public interface IController
{
	/** メインのDrawerを取得します. */
	IDrawer getMainDrawer();
	
	/** NextBlockのDrawerを取得します. */
	IDrawer getNextDrawer();
	
	/** HoldBlockのDrawerを取得します. */
	IDrawer getHoldDrawer();
	
	/** ScoreのDrawerを取得します. */
	IDrawer getScoreDrawer();
	
	/** ManualのDrawerを取得します. */
	IDrawer getManualDrawer();
	
	/** キーイベントを受け取り、Modelの処理を実行します. */
	void keyPressed(KeyEvent e);
	
	/** フレームを進めます. */
	void timeTicked();
	
	/** フレーム速度の高速化を許可するかどうかを判定します */
	boolean allowFastFrame();
}
