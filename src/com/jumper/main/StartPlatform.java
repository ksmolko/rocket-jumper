package com.jumper.main;

import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;

public class StartPlatform extends GameObject {

	private BufferedImage grass_image;

	public StartPlatform(float x, float y, float width, float height, ID id, Handler handler, Jumper jumper) {
		super(x, y, width, height, id, handler, jumper);

		SpriteSheet spritesheet = new SpriteSheet(jumper.grass_sprite);

		grass_image = spritesheet.getImage(0, 0, 640, 60);
	}
	
	@Override
	public Rectangle2D getBounds() {
		return new Rectangle2D.Float(x, y, width, height);
	}

	@Override
	public void tick() {
		x += velX;
		y += velY;
		velX += accelX;
		velY += accelY;
		
		if(jumper.gameState == STATE.Game){
			velY = 5;
		}
		if(y >= Jumper.HEIGHT - 64 + 128){
			handler.removeObject(this);
		}
	}

	@Override
	public void render(Graphics2D graphics) {
		graphics.drawImage(grass_image, (int)x, (int)y, null);
	}
	
}
