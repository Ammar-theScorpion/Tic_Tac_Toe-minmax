package com.ammar.game;

import java.awt.Color;
import java.awt.Graphics;
 

class data{
	int x; int y; char c=' ';
}
public class Tic_Tac_Toe {

	private int width;
	private int height;
	public data[][] board;
	public Tic_Tac_Toe(int width, int height) {
		this.width = width;
		this.height = height;
		board = new data[3][3];
		for (int i = 0; i < board.length; i++)
			for (int j = 0; j < board.length; j++)
				board[i][j] = new data();
	}
	public void render(Graphics g) {
		g.setColor(Color.WHITE);
		
		g.fillRect((int)(width*0.35), 40, 2, height-60);
		g.fillRect((int)(width*0.70), 40, 2, height-60);
		g.fillRect(40, (int)(height*0.35), width-80, 2);
		g.fillRect(40, (int)(height*0.70), width-80, 2);
		
		for(data[] d:board) {
			for(data t:d) {
				if(t.c=='x')
					renderX(g, t.x, t.y);
				else if (t.c=='o')
					renderO(g, t.x, t.y);
			}
		}
	}
	private void renderO(Graphics g, int x, int y) {
		g.drawOval(x+(int)(width*0.10), y+(int)(height*0.05), ((int)(width*0.15)), ((int)(width*0.15)));
	}
	private void renderX(Graphics g, int x, int y) {
		g.drawLine(x+(int)(width*0.05), y+(int)(height*0.05), x+((int)(width*0.35)), y+(int)(height*0.35));
		g.drawLine(x+((int)(width*0.35)), y+(int)(height*0.05), x+(int)(width*0.05), y+(int)(height*0.35));		
		
	}
	
}
