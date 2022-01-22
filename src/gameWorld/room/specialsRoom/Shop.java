package gameWorld.room.specialsRoom;

import java.util.List;

import gameWorld.room.Room;
import gameobjects.Door;
import gameobjects.moving_entity.Hero;
import gameobjects.obstacles.Machine;
import gameobjects.stuff.pickup.PickUp;

public class Shop extends Room {

	private int level;
	private Machine machine;
	
	public Shop(Hero hero, List<Door> doors) {
		super(hero, doors);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void initialise() {
		// TODO Auto-generated method stub
		
	}
	
	//Keep the specials room without reward
	@Override
	public PickUp generateReward() {
		return null;
	}
	

}
