package gameWorld.room.specialsRoom;

import java.util.List;

import gameWorld.room.Room;
import gameobjects.Door;
import gameobjects.moving_entity.Hero;
import gameobjects.pickup.PickUp;

public class ItemRoom extends Room{

	public ItemRoom(Hero hero, List<Door> doors) {
		super(hero, doors);
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
