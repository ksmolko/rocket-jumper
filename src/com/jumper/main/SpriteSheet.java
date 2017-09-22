package com.jumper.main;

import java.awt.image.BufferedImage;

public class SpriteSheet {
	
	private BufferedImage sprite;
	
	public SpriteSheet(BufferedImage spriteSheet){
		this.sprite = spriteSheet;
	}
	
	public BufferedImage getImage(int x, int y, int width, int height){
		BufferedImage img = sprite.getSubimage(x, y, width, height);
		return img;
	}

}
