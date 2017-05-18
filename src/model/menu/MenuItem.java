package model.menu;

import java.util.*;

public class MenuItem 
{
	private final Config config;
	private final IMenuItemHandler handler;
	private List<MenuItem> nextItems = new ArrayList<>();
	
	/**
	 * インスタンスを初期化します
	 * @param config 設定インスタンス
	 * @param handler メニューに関するの処理のハンドラ(処理をしない場合はnullを設定)
	 */
	public MenuItem(Config config, IMenuItemHandler handler)
	{
		this.config = config;
		this.handler = handler;
	}
	
	/** メニューテキストを取得します. */
	public String getText()
	{
		return this.text;
	}
	
	/**
	 * このメニューの下階層のアイテムを追加します.
	 * @param item 追加するメニューアイテム
	 * @return このインスタンス自身
	 * @exception NullPointerException 引数がnullの場合
	 */
	public MenuItem addNextItem(MenuItem item)
	{
		if(item == null)
			throw new NullPointerException();
		
		this.nextItems.add(item);
		return this;
	}
	
	/**
	 * メニュー選択時の処理を実行します.
	 * @return このアイテムの下階層のメニューアイテムリスト
	 */
	public MenuList menuSelected()
	{
		if(this.handler != null)
			this.handler.execute();
			
		return new MenuList(this.nextItems);
	}
}
