package gameWorld.room.specialsRoom;

import java.util.List;

import gameWorld.room.Room;
import gameobjects.Door;
import gameobjects.moving_entity.Hero;
import gameobjects.stuff.pickup.PickUp;
import libraries.StdDraw;
import libraries.Vector2;
import resources.DisplaySettings;
import resources.DoorInfos;
import resources.ImagePaths;
import resources.RoomInfos;

public class Spawn extends Room {

	public Spawn(Hero hero, List<Door> doors) {
		super(hero, doors);
		//getDoors().add(new Door(DoorInfos.NORTH, new Room(hero)));
		
		
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
	
	public void drawWallnFloor() {
		Vector2 center = RoomInfos.POSITION_CENTER_OF_ROOM;
		StdDraw.picture(center.getX(), center.getY(), ImagePaths.FLOOR, DisplaySettings.SCALE, DisplaySettings.SCALE);
		StdDraw.picture(center.getX(), center.getY(), ImagePaths.WALL, DisplaySettings.SCALE, DisplaySettings.SCALE);
		StdDraw.picture(center.getX(), center.getY(), ImagePaths.SPAWN_HELP);
		StdDraw.picture(0.85, 0.85, ImagePaths.POST_IT);
	}
	
	
	
}
