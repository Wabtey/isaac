package gameWorld.room.specialsRoom;

import java.util.List;

import gameWorld.room.Room;
import gameobjects.Door;
import gameobjects.moving_entity.Hero;
import gameobjects.pickup.PickUp;
import resources.PickUpInfos;
import resources.Random;

public class Boss extends Room {

	public Boss(Hero hero, List<Door> doors) {
		super(hero, doors);
		//GameWorld.
	}

	@Override
	public void initialise() {
		// TODO Auto-generated method stub
		
	}
	
	
	//TODO change or implement an another methods to item reward when defeting the boss
	@Override
	public PickUp generateReward() {
		return null;
	}
	
	

}
