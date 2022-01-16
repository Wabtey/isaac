package gameobjects.pickup;

import libraries.StdDraw;
import libraries.Vector2;
import resources.PickUpInfos;

public abstract class PickUp {

	private Vector2 position;
	private String image;
	
	public PickUp(Vector2 position) {
		this.position = position;
		this.image = getImage();
	}

	
	public void drawGameObject() {
		StdDraw.picture(getPosition().getX(), getPosition().getY(), getImage(),
				getSize().getX(), getSize().getY());
	}
	
//--GETTERS/SETTERS-------------------------------
	public Vector2 getPosition() {
		return position;
	}

	public void setPosition(Vector2 position) {
		this.position = position;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}
	
	//Override by downstream class
	public Vector2 getSize() { 
		return PickUpInfos.SIZE;
	}
}
