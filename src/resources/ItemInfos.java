package resources;

import java.util.LinkedList;

import gameobjects.stuff.Item;
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
	
	//--POOL----------------------------------
	
	public static final String STRING_ITEM_POOL = "ItemPool";
	public static final String STRING_DEVIL_POOL = "DevilPool";
	public static final String STRING_BOSS_POOL = "BossPool";

	
	public static final LinkedList<Item> ITEM_POOL = generateNormalPool();
	public static final LinkedList<Item> DEVIL_POOL = generateDevilPool();
	public static final LinkedList<Item> BOSS_POOL = generateBossPool();
	public static final LinkedList<Item> HP_UP_POOL = generateHPupPool();


	
	//--GENERATE POOL--------------------------

	public static LinkedList<Item> generateDevilPool(){
		
		LinkedList<Item> devilPool = new LinkedList<Item>();
		devilPool.add(PENTAGRAM);
		
		return devilPool;
	}
	
	public static LinkedList<Item> generateNormalPool(){
		
		LinkedList<Item> normalPool = new LinkedList<Item>();
		//bossPool.addList(HP_UP_POOL); //TODO create this List adder
		normalPool.add(PENTAGRAM);
		normalPool.add(MAGIC_MUSHROOM);
		normalPool.add(STIGMATA);
		normalPool.add(LUNCH);
		normalPool.add(HP_UP);
		
		return normalPool;
	}
	
	public static LinkedList<Item> generateBossPool(){
		
		LinkedList<Item> bossPool = new LinkedList<Item>();
		//bossPool.addList(NORMAL_POOL); //TODO create this List adder
		bossPool.add(PENTAGRAM);
		bossPool.add(MAGIC_MUSHROOM);
		bossPool.add(STIGMATA);
		bossPool.add(LUNCH);
		bossPool.add(HP_UP);

		
		return bossPool;
	}
	
	/**
	 * simple hpUP pool
	 * @return
	 */
	public static LinkedList<Item> generateHPupPool()
	{
		LinkedList<Item> hpUPPool = new LinkedList<Item>();
		hpUPPool.add(LUNCH);
		hpUPPool.add(HP_UP);

		return hpUPPool;
	}
	
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
