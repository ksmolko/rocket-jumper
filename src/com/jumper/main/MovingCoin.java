package com.jumper.main;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

public class MovingCoin extends Coin{
	
	private BufferedImage movingCoin_image;

	public MovingCoin(float x, float y, float width, float height, ID id, Handler handler, Jumper jumper) {
		super(x, y, width, height, id, handler, jumper);
		int random = (int)Math.round(Math.random());
		if(random == 0){
			this.velX = 5;
		}else if (random == 1){
			this.velX = -5;
		}
		SpriteSheet spritesheet = new SpriteSheet(jumper.movingCoin_sprite);
		
		movingCoin_image = spritesheet.getImage(0, 0, 32, 32);
	}
	
	@Override
	public void tick() {
		x += velX;
		y += velY;
		velX += accelX;
		velY += accelY;

		if(jumper.gameState == STATE.PreGame){
			velY = 0;
		}
		if(getLowestCoinY() < -256  || Spawner.numberOfCoins == 0){
			handler.removeObject(this);
			Spawner.numberOfCoins = 0;
			jumper.gameState = STATE.GameOver;
			HUD.points = 0;
		}
		if(coinState == COIN_STATE.advancing){
			Coin.velY = 20.5f;
			coinState = COIN_STATE.falling;
		}
		accelY = -0.1f;
		
		if(this.y > Jumper.HEIGHT + 128){
			handler.removeObject(this);
			Spawner.numberOfCoins--;
		}
		if(x >= Jumper.WIDTH - 32){
			this.velX = -5;
		}else if(x <= 0){
			this.velX = 5;
		}

	}

	@Override
	public void render(Graphics2D graphics) {
		graphics.drawImage(movingCoin_image, (int)x, (int)y, null);
	}

}
