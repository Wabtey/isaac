package gameobjects;

import gameWorld.Caves.Room;
import libraries.Vector2;

public class Door {
	private Vector2 coordonnees;
	private Room nextRoom;
	
	public Door(Vector2 coordonees, Room nextRoom) {
		this.coordonnees = coordonees;
		this.nextRoom = nextRoom;
	}

	public Vector2 getCoordonnees() {
		return coordonnees;
	}

	public Room getNextRoom() {
		return nextRoom;
	}
	
}
