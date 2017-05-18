package controller;

// package
interface IStateController extends IController
{
	/**
	 * ViewModelの処理を開始します
	 * @param data 他のViewModelから渡されたデータ
	 */
	// このメソッドが呼ばれない場合でもViewModelが機能するように実装する
	void start(TransitionData data);
	
	/**
	 * ViewModelの処理を終了し、次のViewModelへ渡すデータを取得します
	 * @return 次のViewModelに渡すデータ
	 */
	TransitionData finish();
}
