package com.jumper.main;

import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;

/*
 * Abstract class to define all game objects and store them in a LinkedList
 */

public abstract class GameObject {
	
	protected float x, y;
	protected ID id;
	protected float velX, velY;
	protected float accelX, accelY;
	protected float height, width;
	protected Handler handler;
	protected Jumper jumper;
	
	public GameObject(float x, float y, float width, float height, ID id, Handler handler, Jumper jumper){
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		this.id = id;
		this.handler = handler;
		this.jumper = jumper;
	}
	
	public abstract void tick();
	public abstract void render(Graphics2D graphics);
	public abstract Rectangle2D getBounds();

	public float getX() {
		return x;
	}

	public void setX(float x) {
		this.x = x;
	}

	public float getY() {
		return y;
	}

	public void setY(float y) {
		this.y = y;
	}

	public ID getId() {
		return id;
	}

	public void setId(ID id) {
		this.id = id;
	}

	public float getVelX() {
		return velX;
	}

	public void setVelX(float velX) {
		this.velX = velX;
	}

	public float getVelY() {
		return velY;
	}

	public void setVelY(float velY) {
		this.velY = velY;
	}

	public float getAccelX() {
		return accelX;
	}

	public void setAccelX(float accelX) {
		this.accelX = accelX;
	}

	public float getAccelY() {
		return accelY;
	}

	public void setAccelY(float accelY) {
		this.accelY = accelY;
	}

	public float getHeight() {
		return height;
	}

	public void setHeight(float height) {
		this.height = height;
	}

	public float getWidth() {
		return width;
	}

	public void setWidth(float width) {
		this.width = width;
	}
	
	

}
