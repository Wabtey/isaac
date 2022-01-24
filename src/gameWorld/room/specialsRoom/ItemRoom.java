package gameWorld.room.specialsRoom;

import java.util.List;

import gameWorld.room.Room;
import gameobjects.Door;
import gameobjects.moving_entity.Hero;
import gameobjects.stuff.Item;
import gameobjects.stuff.pickup.PickUp;
import resources.ItemInfos;

public class ItemRoom extends Room{

	public ItemRoom(Hero hero, List<Door> doors) {
		super(hero, doors);
		
		initialise(); //Meant to be called in setSpecialRooms() (Dungeon) into changeTypeOfRoom(String type) (GameWorld)

	}

	@Override
	public void initialise() {
		spawnItem(generateItem(ItemInfos.STRING_ITEM_POOL));
		
	}
	
	//Keep the specials room without reward
	@Override
	public PickUp generateReward() {
		return null;
	}
	

}
