package com.jumper.main;

import java.awt.Canvas;
import java.awt.Dimension;

import javax.swing.JFrame;

public class GameWindow extends Canvas{
	
	private static final long serialVersionUID = -8299377127128265267L;
	
	public GameWindow(int width, int height, String title, Jumper game){
		JFrame window = new JFrame(title);

		window.setPreferredSize(new Dimension(width, height));
		window.setMaximumSize(new Dimension(width, height));
		window.setMinimumSize(new Dimension(width, height));
		window.setResizable(false);
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.setLocationRelativeTo(null);
		window.setFocusable(true);
		window.add(game);
		window.setVisible(true);
		game.start();
	}
}
