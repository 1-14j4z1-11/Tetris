package model.menu;

public interface IMenuItemHandler
{
	/**
	 * 選択されているメニュー項目を実行します.
	 * @param controller 
	 */
	void execute();
	
	/**
	 *メニューの 設定値を次の値に変更
	 */
	void changeNext();
	
	/**
	 *メニューの 設定値を前の値に変更
	 */
	void changePrevious();
}
