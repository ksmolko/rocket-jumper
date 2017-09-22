package com.jumper.main;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class HUD {
	
	public static int points = 0;
	public static int highScore;
	private Jumper jumper;
	
	public HUD(Jumper jumper){
		this.jumper = jumper;
		try{
			highScore = jumper.fin.nextInt();
		}catch(NoSuchElementException e){
			highScore = 0;
			try {
				jumper.fout = new PrintWriter(jumper.highScoreFile);
				jumper.fout.println(highScore);
				jumper.fout.close();
			} catch (FileNotFoundException e1) {
				e1.printStackTrace();
			}
		}
		jumper.fin.close();
	}
	public void tick(){
		try {
			jumper.fin = new Scanner(jumper.highScoreFile);
			if(jumper.fin.nextInt() < points){
				highScore = points;
				jumper.fout = new PrintWriter(jumper.highScoreFile);
				jumper.fout.println(HUD.points);
				jumper.fout.close();
			}
			jumper.fin.close();
		}catch (FileNotFoundException e) {
				e.printStackTrace();
		}
	}

	public void render(Graphics graphics){
		graphics.setFont(new Font("Helvetica", Font.BOLD, 20));
		graphics.setColor(Color.WHITE);
		graphics.drawString("POINTS: " + points, Jumper.WIDTH - 256, 48);
		
		graphics.setFont(new Font("Helvetica", 1, 10));
		graphics.setColor(Color.WHITE);
		graphics.drawString("HIGH SCORE: " + highScore, Jumper.WIDTH - 256, 64);
	}

	public static int getPoints() {
		return points;
	}

	public static void setPoints(int points) {
		HUD.points = points;
	}
}
