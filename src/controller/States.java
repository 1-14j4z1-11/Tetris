package controller;

// ���\�b�h��package
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
	 * ��ԂɑΉ�����ViewModel���쐬���܂�
	 * @param parentVM �e�ƂȂ�ViewModel
	 * @return ��ԂɊ֘A�t����ViewModel
	 */
	abstract IStateController createState(MainController parentState);
}
