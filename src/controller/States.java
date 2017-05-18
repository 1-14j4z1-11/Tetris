package controller;

// メソッドはpackage
public enum States
{
	MENU
	{
		@Override
		IStateController createState(MainController parentState)
		{
			return new MenuState(parentState);
		}
	},
	
	TETRIS_2D
	{
		@Override
		IStateController createState(MainController parentState)
		{
			return new Tetris2DState(parentState);
		}
	},
	
	TETRIS_3D
	{
		@Override
		IStateController createState(MainController parentState)
		{
			return new Tetris3DState(parentState);
		}
	},

	TETRIS_3D_DEMO
	{
		@Override
		IStateController createState(MainController parentState)
		{
			return new Tetris3DDemoState(parentState);
		}
	},
	
	RESULT
	{
		@Override
		IStateController createState(MainController parentState)
		{
			return new ResultState(parentState);
		}
	};
	
	/**
	 * 状態に対応したViewModelを作成します
	 * @param parentVM 親となるViewModel
	 * @return 状態に関連付いたViewModel
	 */
	abstract IStateController createState(MainController parentState);
}
