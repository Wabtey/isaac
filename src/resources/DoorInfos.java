package resources;

import libraries.Vector2;

public class DoorInfos {
	public static final Vector2 NORTH = new Vector2(RoomInfos.POSITION_CENTER_OF_ROOM.getX(), 0.89*DisplaySettings.SCALE);
	public static final Vector2 SUD   = new Vector2(RoomInfos.POSITION_CENTER_OF_ROOM.getX(), 0.2*DisplaySettings.SCALE);
	public static final Vector2 EAST  = new Vector2(0.9*DisplaySettings.SCALE, RoomInfos.POSITION_CENTER_OF_ROOM.getY());
	public static final Vector2 WEAST = new Vector2(0.1*DisplaySettings.SCALE, RoomInfos.POSITION_CENTER_OF_ROOM.getY());
	public static final int DOOR_RADIUS = 5;
	
										//= new Vector2(width, heigth)
	public static final Vector2 DOOR_SIZE = new Vector2(0.1*DisplaySettings.SCALE, 0.1*DisplaySettings.SCALE);
	
	public static final Vector2 POSITION_DOOR_NORTH = new Vector2(NORTH.getX(), NORTH.getY()-0.02*DisplaySettings.SCALE);

}
