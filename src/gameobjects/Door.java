package gameobjects;

import gameWorld.GameWorld;
import gameWorld.room.Room;
import libraries.StdDraw;
import libraries.Vector2;
import resources.ImagePaths;

public class Door {
	private Vector2 coordonnees;
	private GameWorld nextRoom;
	
	public Door(Vector2 coordonees, GameWorld nextRoom) {
		this.coordonnees = coordonees;
		this.nextRoom = nextRoom;
	}

	public Vector2 getCoordonnees() {
		return coordonnees;
	}

	public GameWorld getNextRoom() {
		return nextRoom;
	}

	public void drawGameObject() {
		StdDraw.picture(getCoordonnees().getX(), getCoordonnees().getY(), ImagePaths.OPENED_DOOR,
				0.1, 0.1, 0);
		
	}
	
}
