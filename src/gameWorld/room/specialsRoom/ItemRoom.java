package gameWorld.room.specialsRoom;

import java.util.List;

import gameWorld.room.Room;
import gameobjects.Door;
import gameobjects.moving_entity.Hero;

public class ItemRoom extends Room{

	public ItemRoom(Hero hero, List<Door> doors) {
		super(hero, doors);
	}

}
