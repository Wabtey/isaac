package resources;

import gameobjects.Item;
import libraries.Vector2;

public class ItemInfos {
	
	public static final Vector2 ITEM_SIZE = RoomInfos.TILE_SIZE.scalarMultiplication(0.35*DisplaySettings.SCALE);
	
	//--PENTAGRAM--
	public static final double PENTAGRAM_SPEED = 0;
	public static final double PENTAGRAM_TEAR_RATE = 0;
	public static final double PENTAGRAM_DAMAGE = 2.0;
	public static final double PENTAGRAM_RANGE = 0;
	public static final double PENTAGRAM_SHOOTSPEED = 0;
	public static final double PENTAGRAM_LUCK = 0;
	
	public static final Item PENTAGRAM = new Item(PENTAGRAM_DAMAGE, ITEM_SIZE, ImagePaths.PENTAGRAM);
	
//	public static final Item PENTAGRAM = new Item(PENTAGRAM_SPEED, PENTAGRAM_TEAR_RATE, PENTAGRAM_DAMAGE, PENTAGRAM_RANGE,
//											  PENTAGRAM_SHOOTSPEED, PENTAGRAM_LUCK, ITEM_SIZE, ImagePaths.PENTAGRAM);
	
	//--HP UP--
	
}
