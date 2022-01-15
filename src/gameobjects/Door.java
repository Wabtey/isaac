package gameobjects;

import gameWorld.GameWorld;
import gameWorld.room.Room;
import libraries.StdDraw;
import libraries.Vector2;
import resources.ImagePaths;

public class Door {
	private Vector2 coordonnees;
	private GameWorld nextRoom;
	private boolean open;
	
	public Door(Vector2 coordonees, GameWorld nextRoom) {
		this.coordonnees = coordonees;
		this.nextRoom = nextRoom;
		this.open = false;
	}

	public Vector2 getCoordonnees() {
		return coordonnees;
	}

	public GameWorld getNextRoom() {
		if (open == true)
			return nextRoom;
		return null;
	}
	
	public GameWorld getNextRoomForce() {
		return nextRoom;
	}
	
	public void openDoor() {
		this.open = true;
	}
	
	public void closeDoor() {
		this.open = false;
	}
	
	public boolean getDoorState() {
		return open;
	}
	
	public void drawGameObject() {
		StdDraw.picture(getCoordonnees().getX(), getCoordonnees().getY(), (open==true)?ImagePaths.OPENED_DOOR:ImagePaths.CLOSED_DOOR,
				0.1, 0.1, 0);
		
	}
	
}
