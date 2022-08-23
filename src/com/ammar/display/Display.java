package com.ammar.display;


import java.awt.Canvas;
import java.awt.Dimension;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;

import javax.swing.JFrame;

public class Display implements MouseMotionListener{

	private JFrame frame;
	private Canvas canvas;
	public static int atX;
	public static int atY;
	public Display(int width, int height)
	{
		frame = new JFrame();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		canvas = new Canvas();
		canvas.setPreferredSize(new Dimension(width, height));
		canvas.setMaximumSize(new Dimension(width, height));
		canvas.setMinimumSize(new Dimension(width, height));
		canvas.setFocusable(false);
		frame.add(canvas);
		frame.pack();
		frame.setResizable(true);
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
		frame.addMouseMotionListener(this);
		canvas.addMouseMotionListener(this);
	}
	public JFrame getFrame()
	{
		return frame;
	}
	public Canvas getCanvas()
	{
		return canvas;
	}
		
	@Override
	public void mouseDragged(MouseEvent e) {
		
	}
	@Override
	public void mouseMoved(MouseEvent e) {
		atX = e.getX();	
		atY = e.getY();	
		
	}
	
}
