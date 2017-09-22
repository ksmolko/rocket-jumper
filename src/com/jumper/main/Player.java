package com.jumper.main;

import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;

import com.jumper.main.Coin.COIN_STATE;

public class Player extends GameObject {
	
	private BufferedImage player_image;
	private BufferedImage player_image2;
	private BufferedImage player_image3;
	private static long spriteTimer;
	private int count;
	
	public Player(float x, float y, float width, float height, ID id, Handler handler, Jumper jumper) {
		super(x, y, width, height, id, handler, jumper);
		
		SpriteSheet spritesheet = new SpriteSheet(jumper.player_sprites);
		
		player_image = spritesheet.getImage(0, 0, 32, 64);
		player_image2 = spritesheet.getImage(32, 0, 32, 64);
		player_image3 = spritesheet.getImage(64, 0, 32, 64);
		spriteTimer = System.nanoTime();
		this.count = 0;
	}
	
	@Override
	public Rectangle2D getBounds() {
		return new Rectangle2D.Float(x, y, width, height);
	}
	
	private void collision(){
		for(int i = 0; i < handler.object.size(); i++){
			GameObject tempObject = handler.object.get(i);
			if(tempObject.getId() == ID.BasicCoin || tempObject.getId() == ID.MovingCoin){
				if(getBounds().intersects(tempObject.getBounds())){
					handler.removeObject(tempObject);
					Spawner.numberOfCoins -= 1;
					Coin.coinState = COIN_STATE.advancing;
					HUD.points++;
				}
			}else if(tempObject.getId() == ID.KnockerCoin){
				if(getBounds().intersects(tempObject.getBounds())){
					handler.removeObject(tempObject);
					Spawner.numberOfCoins -= 1;
					Coin.coinState = COIN_STATE.advancing;
					HUD.points++;
					if(this.velX >= 0) this.setVelX(-10);
					else if(this.velX < 0) this.setVelX(10);
				}
			}
		}
	}

	@Override
	public void tick() {
		x += velX;
		y += velY;
		velX += accelX;
		velY += accelY;
		
		if(KeyInput.keyIsReleased){
			if(velX > 0 || velX < 0)
				velX *= 0.9f;
			else if(velX == 0)
				velX = 0;
		}
		if(y <= Jumper.HEIGHT / 2){
			velY = 0;
			jumper.gameState = STATE.Game;
			if(this.count == 0){
				Coin.coinState = COIN_STATE.advancing;
				this.count++;
			}
		}
		if(y >= Jumper.HEIGHT + 128) {
			handler.removeObject(this);
			jumper.gameState = STATE.PostGame;
			HUD.points = 0;
		}
		
		x = Jumper.clamp(x, 0, Jumper.WIDTH - 38);
		
		collision();
	}

	@Override
	public void render(Graphics2D graphics) {
		if(jumper.gameState == STATE.PreGame){
			spriteTimer = System.nanoTime();
		}
		boolean change = false;
		if(spriteTimer - System.nanoTime() < -500000000){
			change = true;
			if(spriteTimer - System.nanoTime() < -1000000000){
				change = false;
				spriteTimer = System.nanoTime();
			}
		}
		if(jumper.gameState == STATE.Game && !change){
			graphics.drawImage(player_image2, (int)x, (int)y, null);
		}else if(jumper.gameState == STATE.Game && change){
			graphics.drawImage(player_image3, (int)x, (int)y, null);
		}else if(jumper.gameState == STATE.PreGame || jumper.gameState == STATE.GameOver){
			graphics.drawImage(player_image, (int)x, (int)y, null);
		}
	}
}
