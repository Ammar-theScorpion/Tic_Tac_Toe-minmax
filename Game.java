package com.ammar.shoot.game;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;

import com.ammar.shoot.Display;

public class Game implements Runnable{
	private int WIDTH, HEIGHT;
	
	private Display display;
	private Thread thread;
	private BufferStrategy bfs;
	private Graphics g;
	private boolean running;
 
 	public static int xof;
 	public static int yof;
	public Game(int width, int height) {
 		display = new Display(width, height); 
		WIDTH = width;
		HEIGHT = height;
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
			e.printStackTrace();
		}	
	}
 
}