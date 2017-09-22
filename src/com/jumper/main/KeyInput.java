package com.jumper.main;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import com.jumper.main.Coin.COIN_STATE;

public class KeyInput extends KeyAdapter{
	
	private Handler handler;
	private Jumper jumper;
	public static boolean keyIsReleased;
	private boolean keyDown[];
	
	public KeyInput(Handler handler, Jumper jumper){
		this.handler = handler;
		this.jumper = jumper;
		this.keyDown = new boolean[2];
		keyDown[0] = false;
		keyDown[1] = false;
	}
	
	public void keyPressed(KeyEvent event){
		int key = event.getKeyCode();
		for(int i = 0; i < handler.object.size(); i++){
			GameObject tempObject = handler.object.get(i);
			
			if(tempObject.getId() == ID.Player){
				if(key == KeyEvent.VK_SPACE && jumper.gameState == STATE.PreGame){
					Coin.coinState = COIN_STATE.advancing;
					tempObject.setVelY(-5);
				}
				if(key == KeyEvent.VK_LEFT){
					keyIsReleased = false;
					keyDown[0] = true;
					
				}
				if(key == KeyEvent.VK_RIGHT){
					keyIsReleased = false;
					keyDown[1] = true;
				}
				
				// Left key
				if(keyDown[0]){
					if(tempObject.getVelX() >= -11)
						if(tempObject.getAccelX() >= 0)
							tempObject.setAccelX(-0.7f);
						else tempObject.setAccelX(-0.7f);
					else{
						tempObject.setAccelX(0);
						tempObject.setVelX(-11);
					}
				}
				
				// Right Key
				if(keyDown[1]){
					if(tempObject.getVelX() <= 11){
						if(tempObject.getAccelX() <= 0)
							tempObject.setAccelX(0.7f);
						else tempObject.setAccelX(0.7f);
					}else{
						tempObject.setAccelX(0);
						tempObject.setVelX(11);
					}
				}
			}
		}
		
		if(key == KeyEvent.VK_ESCAPE)
			System.exit(1);
	}
	
	public void keyReleased(KeyEvent event){
		int key = event.getKeyCode();
		for(int i = 0; i < handler.object.size(); i++){
			GameObject tempObject = handler.object.get(i);
			
			if(tempObject.getId() == ID.Player){
				if(key == KeyEvent.VK_LEFT){
					keyIsReleased = true;
					keyDown[0] = false;
				}
				if(key == KeyEvent.VK_RIGHT){
					keyIsReleased = true;
					keyDown[1] = false;
				}
				
				if(!keyDown[0]) tempObject.setAccelX(0); // Left Key
				if(!keyDown[1]) tempObject.setAccelX(0); // Right Key
			}
		}
	}
	
}
