package com.ammar.display;
 
 
import java.awt.Dimension;

import com.ammar.game.Game;


public class Lancher {
	public static void main(String[] args)
	{
		Dimension s = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
		System.err.println(s.getHeight());
		new Game((int) 1000, 800).start();
	}

}
