package com.jumper.main;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;

public class Instructions extends MouseAdapter{
	
	private Jumper jumper;
	private BufferedImage menu_back;
	private BufferedImage menu_backDown;
	private boolean mouseOverBack;
	
	public Instructions(Jumper jumper){
		this.jumper = jumper;
		
		this.mouseOverBack = false;
		
		SpriteSheet spritesheet = new SpriteSheet(jumper.menu_sprite);
		
		menu_back = spritesheet.getImage(0, 192, 256, 64);
		menu_backDown = spritesheet.getImage(0, 512, 256, 64);
	}
	
	public void mousePressed(MouseEvent event){
		
		if(jumper.gameState == STATE.Instructions){
			int mousex = event.getX();
			int mousey = event.getY();
			if(isMouseOver(mousex, mousey, Jumper.WIDTH / 2 - 256 / 2, 350, 256, 64))
				this.mouseOverBack = true;
		}
	}
	
	public void mouseReleased(MouseEvent event){
		
		this.mouseOverBack = false;
		
		if(jumper.gameState == STATE.Instructions){
			int mousex = event.getX();
			int mousey = event.getY();
			
			if(isMouseOver(mousex, mousey, Jumper.WIDTH / 2 - 256 / 2, 350, 256, 64)){
				jumper.gameState = STATE.Menu;
			}
		}

	}
	
	private boolean isMouseOver(int mousex, int mousey, int x, int y, int width, int height){
		if(mousex > x && mousex < x + width){
			if(mousey > y && mousey < y + height){
				return true;
			}else return false;
		}else return false;
	}

	public void tick(){
		
	}
	
	public void render(Graphics2D graphics){
		
		Font font = new Font("Helvetica", 1, 20);
		
		if(!this.mouseOverBack)
			graphics.drawImage(menu_back, Jumper.WIDTH / 2 - 256 / 2, 350, null);
		else if(this.mouseOverBack)
			graphics.drawImage(menu_backDown, Jumper.WIDTH / 2 - 256 / 2, 350, null);
		
		graphics.setFont(font);
		graphics.setColor(Color.white);
		graphics.drawString("- Welcome to the \"Jumper\" game", 64, 64);
		graphics.setFont(new Font("Helvetica", 1, 16));
		graphics.drawString("- Use the left and right arrow keys to move the rocket left and right", 64, 64 + 24);
		graphics.setFont(font);
		graphics.drawString("- Press the space bar to start the game after hitting play", 64, 64 + 24*2);
		graphics.drawString("- Hit the coins to propel yourself upward and get points", 64, 64 + 24*3);
		graphics.drawString("- Green coins are moving coins", 64, 64 + 24*4);
		graphics.drawString("- Red coins knock you over to the side when hit", 64, 64 + 24*5);
		graphics.setFont(new Font("Helvetica", 1, 12));
		graphics.setFont(font);
		graphics.drawString("Good Luck!", 64, 64 + 24*11);
	}
	
}
