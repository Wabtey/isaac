package gameobjects;

import gameWorld.GameWorld;
import gameWorld.room.roomPattern.Room;
import libraries.Vector2;

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
	
}
