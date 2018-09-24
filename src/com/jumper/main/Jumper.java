package com.jumper.main;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

/*
 * This is the MAIN class
 */

public class Jumper extends Canvas implements Runnable{

	private static final long serialVersionUID = -4430027627711981421L;

	public static final int WIDTH = 640;
	public static final int HEIGHT = 480;

	private Thread thread;
	private boolean isRunning = false;

	private Handler handler;
	private HUD hud;
	private Spawner spawner;
	public Player player;
	private Menu menu;
	private Instructions instructions;
	public BufferedImage player_sprites;
	public BufferedImage basicCoin_sprite;
	public BufferedImage movingCoin_sprite;
	public BufferedImage knockerCoin_sprite;
	public BufferedImage menu_sprite;
	public BufferedImage grass_sprite;
	public File highScoreFile;
	public Scanner fin;
	public PrintWriter fout;

	public STATE gameState = STATE.Menu;

	public Jumper() throws IOException{

		BufferedImageLoader loader = new BufferedImageLoader();
		player_sprites = loader.loadImage("/rocket/spritesheet.png");
		basicCoin_sprite = loader.loadImage("/coin/coin.png");
		movingCoin_sprite = loader.loadImage("/moving_coin/moving_coin.png");
		knockerCoin_sprite = loader.loadImage("/knocker_coin/knocker_coin.png");
		menu_sprite = loader.loadImage("/menu/menu_spritesheet.png");
		grass_sprite = loader.loadImage("/grass/grass.png");

		handler = new Handler();
		menu = new Menu(this, handler);
		instructions = new Instructions(this);

		this.highScoreFile = new File("High_Score.txt");

		this.addKeyListener(new KeyInput(handler, this));
		this.addMouseListener(menu);
		this.addMouseListener(instructions);
		new GameWindow(WIDTH, HEIGHT, "Jumper", this);

		this.fin = new Scanner(highScoreFile);
		hud = new HUD(this);
		this.fin.close();
		spawner = new Spawner(handler, hud, this);
	}

	public synchronized void start(){
		thread = new Thread(this);
		thread.start();
		isRunning = true;
	}

	public synchronized void stop(){
		try{
			thread.join();
			isRunning = false;
		}catch(Exception e){
			e.printStackTrace();
		}
	}

	public void run(){
		this.requestFocus();
		long lastTime = System.nanoTime();
		double amountOfTicks = 60.0;
		double ns = 1000000000 / amountOfTicks;
		double delta = 0;
		long timer = System.currentTimeMillis();
		int frames = 0;
		while(isRunning){
			long now = System.nanoTime();
			delta += (now - lastTime) / ns;
			lastTime = now;
			while(delta >= 1){
				tick();
				delta--;
			}
			if(isRunning)
				render();
			frames++;

			if(System.currentTimeMillis() - timer > 1000){
				timer += 1000;
				System.out.println("FPS: " + frames);
				frames = 0;
			}
		}
		stop();
	}

	private void tick(){
		handler.tick();
		if(gameState == STATE.Game){
			hud.tick();
			spawner.tick();
		}
		if(gameState == STATE.Menu){
			menu.tick();
		}
		if(gameState == STATE.GameOver){
			// Insert Game Over code here
			player.setVelY(10);
		}
		if(gameState == STATE.Instructions){
			instructions.tick();
		}
	}

	private void render(){
		BufferStrategy bs = this.getBufferStrategy();
		if(bs == null){
			this.createBufferStrategy(3);
			return;
		}
		Graphics2D graphics = (Graphics2D)bs.getDrawGraphics();

		graphics.setColor(Color.black);
		graphics.fillRect(0, 0, WIDTH, HEIGHT);

		handler.render(graphics);

		if(gameState == STATE.Game || gameState == STATE.PreGame){
			hud.render(graphics);
		}
		if(gameState == STATE.Menu || gameState == STATE.PostGame){
			menu.render(graphics);
		}
		if(gameState == STATE.Instructions){
			instructions.render(graphics);
		}

		graphics.dispose();
		bs.show();
	}

	public static float clamp(float var, float min, float max){
		if(var >= max)
			return var = max;
		else if(var <= min)
			return var = min;
		else return var;
	}

	public static void main(String[] args) throws IOException {
		new Jumper();
	}

}
