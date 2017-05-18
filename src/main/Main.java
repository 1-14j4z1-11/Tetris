package main;

import controller.*;

public class Main
{
	public static void main(String[] args)
	{
		MainController controller = new MainController("Tetris", States.MENU);
		controller.start();
	}
}
