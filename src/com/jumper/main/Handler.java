package com.jumper.main;

import java.awt.Graphics2D;
import java.util.LinkedList;

public class Handler {
	
	LinkedList<GameObject> object = new LinkedList<GameObject>();
	
	public void tick(){
		for(int i = 0; i < object.size(); i++){
			GameObject tempObject = object.get(i);
			
			tempObject.tick();
		}
	}
	
	public void render(Graphics2D graphics){
		for(int i = 0; i < object.size(); i++){
			GameObject tempObject = object.get(i);
			
			tempObject.render(graphics);
		}
	}
	
	public void addObject(GameObject object){
		this.object.add(object);
	}
	
	public void removeObject(GameObject object){
		this.object.remove(object);
	}

}
