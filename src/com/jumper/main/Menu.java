package com.jumper.main;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;

public class Menu extends MouseAdapter {
	
	private Jumper jumper;
	private Handler handler;
	private BufferedImage menu_play;
	private BufferedImage menu_instructions;
	private BufferedImage menu_quit;
	private BufferedImage menu_playAgain;
	private BufferedImage menu_playDown;
	private BufferedImage menu_instructionsDown;
	private BufferedImage menu_quitDown;
	private BufferedImage menu_playAgainDown;
	private boolean mouseOverPlay;
	private boolean mouseOverInstructions;
	private boolean mouseOverQuit;
	private boolean mouseOverPlayAgain;
	
	public Menu(Jumper jumper, Handler handler){
		this.jumper = jumper;
		this.handler = handler;
		
		this.mouseOverPlay = false;
		this.mouseOverInstructions = false;
		this.mouseOverQuit = false;
		this.mouseOverPlayAgain = false;
		
		SpriteSheet spritesheet = new SpriteSheet(jumper.menu_sprite);
		
		menu_play = spritesheet.getImage(0, 0, 256, 64);
		menu_instructions = spritesheet.getImage(0, 64, 256, 64);
		menu_quit = spritesheet.getImage(0, 128, 256, 64);
		menu_playAgain = spritesheet.getImage(0, 256, 256, 64);
		menu_playDown = spritesheet.getImage(0, 320, 256, 64);
		menu_instructionsDown = spritesheet.getImage(0, 384, 256, 64);
		menu_quitDown = spritesheet.getImage(0, 448, 256, 64);
		menu_playAgainDown = spritesheet.getImage(0, 576, 256, 64);
	}
	
	public void mousePressed(MouseEvent event){
		int mousex = event.getX();
		int mousey = event.getY();
		
		if(jumper.gameState == STATE.Menu){
			if(isMouseOver(mousex, mousey, Jumper.WIDTH / 2 - 256 / 2, 200 - 64, 256, 64))
				this.mouseOverPlay = true;
			else if(isMouseOver(mousex, mousey, Jumper.WIDTH / 2 - 256 / 2, 200 + 64 + 12 * 2, 256, 64))
				this.mouseOverQuit = true;
			else if(isMouseOver(mousex, mousey, Jumper.WIDTH / 2 - 256 / 2, 200 + 12, 256, 64))
				this.mouseOverInstructions = true;
		}else if(jumper.gameState == STATE.PostGame){
			if(isMouseOver(mousex, mousey, Jumper.WIDTH / 2 - 256 / 2, 200 + 12, 256, 64))
				this.mouseOverPlayAgain = true;
			else if(isMouseOver(mousex, mousey, Jumper.WIDTH / 2 - 256 / 2, 200 + 64 + 12 * 2, 256, 64))
				this.mouseOverQuit = true;
		}
		
	}
	
	public void mouseReleased(MouseEvent event){
		int mousex = event.getX();
		int mousey = event.getY();
		
		this.mouseOverPlay = false;
		this.mouseOverInstructions = false;
		this.mouseOverQuit = false;
		this.mouseOverPlayAgain = false;
		
		if(jumper.gameState == STATE.Menu){
			
			// Play Button
			if(isMouseOver(mousex, mousey, Jumper.WIDTH / 2 - 256 / 2, 200 - 64, 256, 64)){
				jumper.gameState = STATE.PreGame;
				jumper.player = new Player(Jumper.WIDTH / 2 - 32, Jumper.HEIGHT - 128 + 32, 32, 64, ID.Player, handler, jumper);
				handler.addObject(new StartPlatform(0, Jumper.HEIGHT - 84, Jumper.WIDTH, 60, ID.StartPlatform, handler, jumper));
				handler.addObject(jumper.player);
			}
			
			// Quit Button
			else if(isMouseOver(mousex, mousey, Jumper.WIDTH / 2 - 256 / 2, 200 + 64 + 12 * 2, 256, 64)){
				System.exit(0);
			}
			
			// Instructions Button
			else if(isMouseOver(mousex, mousey, Jumper.WIDTH / 2 - 256 / 2, 200 + 12, 256, 64)){
				jumper.gameState = STATE.Instructions;
			}
		}else if(jumper.gameState == STATE.PostGame){
			
			// Play Again Button
			if(isMouseOver(mousex, mousey, Jumper.WIDTH / 2 - 256 / 2, 200 + 12, 256, 64)){
				jumper.gameState = STATE.PreGame;
				for(int i = 0; i < handler.object.size(); i++){
					GameObject tempObject = handler.object.get(i);
					
					if(tempObject.getId() == ID.BasicCoin ||
						tempObject.getId() == ID.MovingCoin ||
						tempObject.getId() == ID.KnockerCoin ||
						tempObject.getId() == ID.Player){
							handler.removeObject(tempObject);
							System.out.println("Object removed");
					}
				}
				jumper.player = new Player(Jumper.WIDTH / 2 - 32, Jumper.HEIGHT - 128 + 32, 32, 64, ID.Player, handler, jumper);
				handler.addObject(new StartPlatform(0, Jumper.HEIGHT - 84, Jumper.WIDTH, 60, ID.StartPlatform, handler, jumper));
				handler.addObject(jumper.player);
			}
			
			//Quit Button
			else if(isMouseOver(mousex, mousey, Jumper.WIDTH / 2 - 256 / 2, 200 + 64 + 12 * 2, 256, 64)){
				System.exit(0);
			}
			
		}
		
	}
	
	public void tick(){
		
	}
	
	private boolean isMouseOver(int mousex, int mousey, int x, int y, int width, int height){
		if(mousex > x && mousex < x + width){
			if(mousey > y && mousey < y + height){
				return true;
			}else return false;
		}else return false;
	}
	
	public void render(Graphics2D graphics){
		
		Font font = new Font("TimesRoman", 1, 50);
		
		if(jumper.gameState == STATE.Menu){
			graphics.setFont(font);
			graphics.setColor(Color.white);
			graphics.drawString("Menu", Jumper.WIDTH / 2 - 64, 70);
			
			// Play Button
			if(!this.mouseOverPlay)
				graphics.drawImage(menu_play, Jumper.WIDTH / 2 - 256 / 2, 200 - 64, null);
			else if (this.mouseOverPlay)
				graphics.drawImage(menu_playDown, Jumper.WIDTH / 2 - 256 / 2, 200 - 64, null);
			
			// Instructions Button
			if(!this.mouseOverInstructions)
				graphics.drawImage(menu_instructions, Jumper.WIDTH / 2 - 256 / 2, 200 + 12, null);
			else if(this.mouseOverInstructions)
				graphics.drawImage(menu_instructionsDown, Jumper.WIDTH / 2 - 256 / 2, 200 + 12, null);
			
			// Quit Button
			if(!this.mouseOverQuit)
				graphics.drawImage(menu_quit, Jumper.WIDTH / 2 - 256 / 2, 200 + 64 + 12 * 2, null);
			else if(this.mouseOverQuit)
				graphics.drawImage(menu_quitDown, Jumper.WIDTH / 2 - 256 / 2, 200 + 64 + 12 * 2, null);
				
		}else if(jumper.gameState == STATE.PostGame){
			graphics.setFont(font);
			graphics.setColor(Color.white);
			graphics.drawString("Game Over", Jumper.WIDTH / 2 - 128, 70);
			
			// Play Again Button
			if(!this.mouseOverPlayAgain)
				graphics.drawImage(menu_playAgain, Jumper.WIDTH / 2 - 256 / 2, 200 + 12, null);
			else if(this.mouseOverPlayAgain)
				graphics.drawImage(menu_playAgainDown, Jumper.WIDTH / 2 - 256 / 2, 200 + 12, null);
			
			// Quit Button
			if(!this.mouseOverQuit)
				graphics.drawImage(menu_quit, Jumper.WIDTH / 2 - 256 / 2, 200 + 64 + 12 * 2, null);
			else if(this.mouseOverQuit)
				graphics.drawImage(menu_quitDown, Jumper.WIDTH / 2 - 256 / 2, 200 + 64 + 12 * 2, null);
		}

	}
	
}
