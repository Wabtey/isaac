package gameWorld.room.specialsRoom;

import java.util.List;

import gameWorld.room.Room;
import gameobjects.Door;
import gameobjects.moving_entity.Hero;

public class Shop extends Room {

	public Shop(Hero hero, List<Door> doors) {
		super(hero, doors);
	}

}