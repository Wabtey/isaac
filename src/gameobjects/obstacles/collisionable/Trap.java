package gameobjects.obstacles.collisionable;

import libraries.StdDraw;
import libraries.Vector2;
import resources.DisplaySettings;
import resources.ImagePaths;
import resources.RoomInfos;

public class Trap {

	private Vector2 position;
	private String image;
	private Vector2 size = RoomInfos.TILE_SIZE.scalarMultiplication(0.6*DisplaySettings.SCALE);
	
	private boolean open; //TODO create a fall security
	
	public Trap(Vector2 position) {
		this.position = position;
		this.image = ImagePaths.OPEN_TRAP;
		this.open = true;

	}

	
	public void drawGameObject() {
		StdDraw.picture(getPosition().getX(), getPosition().getY(), getImage(),
				getSize().getX(), getSize().getY());
	}
	
//--GETTERS/SETTERS--------------------------------------
	
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

	public boolean isOpen() {
		return open;
	}

	public void setOpen(boolean open) {
		this.open = open;
	}




	public Vector2 getSize() {
		return size;
	}
	
	
}
