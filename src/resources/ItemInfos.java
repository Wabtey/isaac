package resources;

import java.util.LinkedList;

import gameobjects.Item;
import libraries.Vector2;

public class ItemInfos {

	// --Export ITEM Stats-------------------------------
	/**
	 * 
	 * @return allStats in a array
	 */
	private double[] convertAllStatsIntoList() {

		double[] allStats = new double[ItemInfos.STATS_A_LENGTH];

//			allStats[0]=getHeartContainer();
//			allStats[1]=getRedHeart();
//			allStats[2]=getBlueHeart();
//			allStats[3]=getSpeed();
//			allStats[4]=getTearRate();
//			allStats[5]=getDamage();
//			allStats[6]=getRange();
//			allStats[7]=getShootSpeed();
//			allStats[8]=getLuck();
//			allStats[9]=getDevilDeal();
//			allStats[10]=getAngelRoom();
//			allStats[11]=getGold();
//			allStats[12]=getBomb();
//			allStats[13]=getKey();
//			allStats[14]=getheroSize();

		return allStats;
	}

	public static final Vector2 ITEM_SIZE = RoomInfos.TILE_SIZE.scalarMultiplication(0.35 * DisplaySettings.SCALE);
	public static final int STATS_A_LENGTH = 16;
	
	//--NORMAL POOL-------------------------
	public static LinkedList<Item> generateNormalPool(){
		
		LinkedList<Item> NORMAL_POOL = new LinkedList<Item>();
		NORMAL_POOL.add(PENTAGRAM);
		NORMAL_POOL.add(MAGIC_MUSHROOM);
		NORMAL_POOL.add(STIGMATA);
		NORMAL_POOL.add(LUNCH);
		NORMAL_POOL.add(HP_UP);
		
		return NORMAL_POOL;
	}
	
	public static final LinkedList<Item> NORMAL_POOL = generateNormalPool();
	
	//--DEVIL POOL--------------------------

	public static LinkedList<Item> generateDevilPool(){
		
		LinkedList<Item> DEVIL_POOL = new LinkedList<Item>();
		DEVIL_POOL.add(PENTAGRAM);
		
		return DEVIL_POOL;
	}
	
	public static final LinkedList<Item> DEVIL_POOL = generateDevilPool();
	
//--DAMAGE----------------------
	// --PENTAGRAM--
	
	public static final double PENTAGRAM_SPEED = 0;
	public static final double PENTAGRAM_TEAR_RATE = 0;
	public static final double PENTAGRAM_DAMAGE = 2.0;
	public static final double PENTAGRAM_RANGE = 0;
	public static final double PENTAGRAM_SHOOTSPEED = 0;
	public static final double PENTAGRAM_LUCK = 0;

	// TODO find a way to give a proper position when created (using freePosition)
	public static final Item PENTAGRAM = new Item(PENTAGRAM_SPEED, PENTAGRAM_TEAR_RATE, PENTAGRAM_DAMAGE,
			PENTAGRAM_RANGE, PENTAGRAM_SHOOTSPEED, PENTAGRAM_LUCK, RoomInfos.POSITION_CENTER_OF_ROOM, ITEM_SIZE,
			ImagePaths.PENTAGRAM);

	public static final Item MAGIC_MUSHROOM = new Item(PENTAGRAM_SPEED, PENTAGRAM_TEAR_RATE, PENTAGRAM_DAMAGE,
			PENTAGRAM_RANGE, PENTAGRAM_SHOOTSPEED, PENTAGRAM_LUCK, RoomInfos.POSITION_CENTER_OF_ROOM, ITEM_SIZE,
			ImagePaths.PENTAGRAM);
	
//--HP UP----------------------
	
	public static final Item STIGMATA = new Item(PENTAGRAM_SPEED, PENTAGRAM_TEAR_RATE, PENTAGRAM_DAMAGE,
			PENTAGRAM_RANGE, PENTAGRAM_SHOOTSPEED, PENTAGRAM_LUCK, RoomInfos.POSITION_CENTER_OF_ROOM, ITEM_SIZE,
			ImagePaths.PENTAGRAM);
	
	public static final Item LUNCH = new Item(PENTAGRAM_SPEED, PENTAGRAM_TEAR_RATE, PENTAGRAM_DAMAGE,
			PENTAGRAM_RANGE, PENTAGRAM_SHOOTSPEED, PENTAGRAM_LUCK, RoomInfos.POSITION_CENTER_OF_ROOM, ITEM_SIZE,
			ImagePaths.PENTAGRAM);
	
	public static final Item HP_UP = new Item(PENTAGRAM_SPEED, PENTAGRAM_TEAR_RATE, PENTAGRAM_DAMAGE,
			PENTAGRAM_RANGE, PENTAGRAM_SHOOTSPEED, PENTAGRAM_LUCK, RoomInfos.POSITION_CENTER_OF_ROOM, ITEM_SIZE,
			ImagePaths.PENTAGRAM);

}
