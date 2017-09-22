package com.jumper.main;

public class Spawner {
	
	private Handler handler;
	private HUD hud;
	private Jumper jumper;
	public static int numberOfCoins = 0;
	private static int count = 0;
	
	public Spawner(Handler handler, HUD hud, Jumper jumper){
		this.handler = handler;
		this.hud = hud;
		this.jumper = jumper;
	}
	
	public void tick(){
		if(numberOfCoins < 4 && jumper.gameState == STATE.Game){
			if(count < 5){
				handler.addObject(new BasicCoin((float)Math.random() * Jumper.WIDTH - 32, -224, 32, 32, ID.BasicCoin, handler, jumper));
				numberOfCoins += 1;
				count++;
			}else if(count == 5){
				int random = (int)Math.round(Math.random());
				if(random == 0){
					handler.addObject(new MovingCoin((float)Math.random() * Jumper.WIDTH - 32, -224, 32, 32, ID.MovingCoin, handler, jumper));
					numberOfCoins += 1;
					count = 0;
				}else if(random == 1){
					handler.addObject(new KnockerCoin((float)Math.random() * Jumper.WIDTH - 32, -224, 32, 32, ID.KnockerCoin, handler, jumper));
					numberOfCoins += 1;
					count = 0;
				}

			}
			
		}
	}

}
