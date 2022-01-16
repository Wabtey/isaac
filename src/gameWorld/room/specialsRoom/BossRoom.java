package gameWorld.room.specialsRoom;

import java.util.ArrayList;
import java.util.List;

import gameWorld.room.Room;
import gameobjects.Door;
import gameobjects.moving_entity.Hero;
import gameobjects.moving_entity.monsters.Boss;
import gameobjects.obstacles.Obstacle;
import gameobjects.pickup.PickUp;

public class BossRoom extends Room {

	public BossRoom(Hero hero, List<Door> doors) {
		super(hero, doors);
	}

	@Override
	public void initialise() {
		createMonsters();
		
	}

	public void createMonsters() {
		getMonsters().add(new Boss());
	}
	
	//TODO change or implement an another methods to item reward when defeting the boss
	@Override
	public PickUp generateReward() {
		return null;
	}
	
	

}
