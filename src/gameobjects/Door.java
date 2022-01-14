package gameobjects;

import gameWorld.GameWorld;
import libraries.StdDraw;
import libraries.Vector2;

public class Door {
	private Vector2 coordonnees;
	private GameWorld nextRoom;
	
	public Door(Vector2 coordonees, GameWorld nextRoom) {
		this.coordonnees = coordonees;
		this.nextRoom = nextRoom;
	}
	
	public void drawGameObject() {
		StdDraw.picture(getCoordonnees().getX(), getCoordonnees().getY(), "images/opened_door.png",
				0.1, 0.1, 0);
	}
	public Vector2 getCoordonnees() {
		return coordonnees;
	}

	public GameWorld getNextRoom() {
		return nextRoom;
	}
	
}
