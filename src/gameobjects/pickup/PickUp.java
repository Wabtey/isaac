package gameobjects.pickup;

import libraries.StdDraw;
import libraries.Vector2;
import resources.PickUpInfos;

public abstract class PickUp {

	private Vector2 position;
	
	public PickUp(Vector2 position) {
		this.position = position;
	}

	
	public void drawGameObject() {

	}
	
	
//--GETTERS/SETTERS-------------------------------
	public Vector2 getPosition() {
		return position;
	}

	public void setPosition(Vector2 position) {
		this.position = position;
	}
	
	//Override by downstream class
	public Vector2 getSize() { 
		return PickUpInfos.SIZE;
	}
}
