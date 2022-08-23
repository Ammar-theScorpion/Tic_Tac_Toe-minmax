package com.ammar.game;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;

import com.ammar.display.Display;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

// name age data
   
public class Game implements Runnable, MouseListener{
	private int WIDTH, HEIGHT;
	
	private Display display;
	private Thread thread;
	private BufferStrategy bfs;
	private Graphics g;
	private boolean running;
	private Tic_Tac_Toe tic;
	private char c;
 
 	public static int xof;
 	public static int yof;
	public Game(int width, int height) {
 
		display = new Display(width, height);
		tic = new Tic_Tac_Toe(width, height);
		display.getCanvas().addMouseListener(this);
		display.getFrame().addMouseListener(this);
		WIDTH = width;
		HEIGHT = height;
		c='x';
//		AI();
	}
	@Override
	public void run() {
		while(running) {
			render();
			tick();
		}
		stop();
	}
	private void render() {
		bfs = display.getCanvas().getBufferStrategy();
		if(bfs == null) {
			display.getCanvas().createBufferStrategy(3);
			return;
		}
		g = bfs.getDrawGraphics();
		g.clearRect(0, 0, WIDTH, HEIGHT);
		g.setColor(Color.BLACK);
		g.fillRect(0, 0, WIDTH, HEIGHT);
		tic.render(g);
 
		bfs.show();
		g.dispose();
	}
	private void tick() {
	 
	}
	public synchronized void start() {
		running = true;
		thread = new Thread(this);
		thread.start();
 
	}
	
	private synchronized void stop() {
		running = false;
		try {
			thread.join();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
	}
	@Override
	public void mouseClicked(MouseEvent e) {
		int t = check();
		if(t==0) {
			System.out.println("TIE");
			return;
		}
		if(t==1) {
			System.out.println("O wins");
			return; 
		}
		if(t==-1) {
			System.out.println("X wins");
			return;
		}
		int x =(int)(e.getX()/(WIDTH*0.35));
		int y =(int)(e.getY()/(HEIGHT*0.35));
		if(tic.board[x][y].c!=' ')return;
		tic.board[x][y].c = 'x';		
		tic.board[x][y].x = (int)(x*(WIDTH*0.35));
		tic.board[x][y].y = (int)(y*(HEIGHT*0.35));		
		AI();
	}
	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	private void AI() {
		int bestScore =	Integer.MIN_VALUE;
		int movex=0;
		int movey=0;
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				if(tic.board[i][j].c==' ') {
					tic.board[i][j].c='o';
					int score = minmax(0);
					tic.board[i][j].c=' ';
					if(score>bestScore) {
						bestScore = score;
						movex=i;
						movey=j;
					}
				}
			}
		}
		tic.board[movex][movey].c='o';
		tic.board[movex][movey].x = (int)(movex*(WIDTH*0.35));
		tic.board[movex][movey].y = (int)(movey*(HEIGHT*0.35));		
	}
 
	private int minmax(int turn) {
		int ch = check();
		if(ch!=-2)
			return ch;
		if(turn==1) {
			int bestScore =	Integer.MIN_VALUE;
 
			for (int i = 0; i < 3; i++) {
				for (int j = 0; j < 3; j++) {
					if(tic.board[i][j].c==' ') {
						tic.board[i][j].c='o';
						int score = minmax(0);
						tic.board[i][j].c=' ';
						if(score>bestScore) {
							bestScore = score;
						}
					}
				}
			}
			return bestScore;
		}else {
			int bestScore =	Integer.MAX_VALUE;
 
			for (int i = 0; i < 3; i++) {
				for (int j = 0; j < 3; j++) {
					if(tic.board[i][j].c==' ') {
						tic.board[i][j].c='x';
						int score = minmax(1);
						tic.board[i][j].c=' ';
						if(score<bestScore) {
							bestScore = score;
						}
					}
				}
			}
			return bestScore;
		}
	}
	private int check() {
		char c=' ';
		for (int i = 0; i < 3; i++) 
				if(tic.board[i][0].c!=' '&&tic.board[i][0].c==tic.board[i][1].c&&tic.board[i][1].c==tic.board[i][2].c) 
					c=tic.board[i][0].c;
		for (int i = 0; i < 3; i++) 
			if(tic.board[0][i].c!=' '&&tic.board[0][i].c==tic.board[1][i].c&&tic.board[1][i].c==tic.board[2][i].c) 
				c=tic.board[0][i].c;
			 
		
		 if(tic.board[1][1].c!=' '&&tic.board[0][0].c == tic.board[1][1].c&&tic.board[1][1].c == tic.board[2][2].c)	
			 c=tic.board[0][0].c;
		 if(tic.board[1][1].c!=' '&&tic.board[0][2].c == tic.board[1][1].c&&tic.board[1][1].c == tic.board[2][0].c)	
			 c=tic.board[0][2].c;
		 
		 int emp=0;
		 for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				if(tic.board[i][j].c==' ')
					emp++;
			}
		}
		 if(c==' '&&emp==0)
			 return 0;
		 if(c=='o')
			 return 1;
		 else if(c=='x')
			 return -1;
		 return -2;
	 
	}
}
