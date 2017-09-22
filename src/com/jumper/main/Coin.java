package com.jumper.main;

import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;

public abstract class Coin extends GameObject{
	
	public static COIN_STATE coinState = COIN_STATE.falling;
	protected float velX;
	protected static float velY;
	protected static float accelX;
	protected static float accelY;
	
	public enum COIN_STATE{
		advancing,
		falling;
	}

	public Coin(float x, float y, float width, float height, ID id, Handler handler, Jumper jumper) {
		super(x, y, width, height, id, handler, jumper);
	}
	
	@Override
	public Rectangle2D getBounds() {
		return new Rectangle2D.Float(x, y, width, height);
	}
	
	public float getLowestCoinY(){
		float lowestCoinY = -1000;
		for(int i = 0; i < handler.object.size(); i++){
			GameObject tempObject = handler.object.get(i);
			
			if((tempObject.id == ID.BasicCoin || 
				tempObject.id == ID.MovingCoin || 
				tempObject.id == ID.KnockerCoin) && tempObject.getY() > lowestCoinY){
				lowestCoinY = tempObject.getY();
			}
		}
		if(lowestCoinY != -1000){
			return lowestCoinY;
		}
		else{
			return -256;
		}
	}

	@Override
	public abstract void tick();

	@Override
	public abstract void render(Graphics2D graphics);

}
