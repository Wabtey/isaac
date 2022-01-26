package resources;

import java.util.Collections;
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
	
//--DAMAGE----------------------
	// --PENTAGRAM--
	
	public static final double PENTAGRAM_DMG_MULT = 0;
	public static final double PENTAGRAM_SPEED = 0;
	public static final double PENTAGRAM_TEAR_RATE = 0;
	public static final double PENTAGRAM_DAMAGE = 1.0;
	public static final double PENTAGRAM_RANGE = 0;
	public static final double PENTAGRAM_SHOOTSPEED = 0;
	public static final double PENTAGRAM_LUCK = 0;
	
	public static final String PENTAGRAM_PICKUP_PHRASE = "DMG UP";

	// TODO find a way to give a proper position when created (using freePosition)
	public static final Item PENTAGRAM = new Item(PENTAGRAM_DMG_MULT, PENTAGRAM_SPEED, PENTAGRAM_TEAR_RATE, PENTAGRAM_DAMAGE,
			PENTAGRAM_RANGE, PENTAGRAM_SHOOTSPEED, PENTAGRAM_LUCK, RoomInfos.POSITION_CENTER_OF_ROOM, ITEM_SIZE,
			ImagePaths.PENTAGRAM);
	
	// --MAGIC MUSHROOM--
	public static final int MAGIC_MUSHROOM_HEART_CONTAINER = 1;
	public static final double MAGIC_MUSHROOM_RED_HEART = 12; //restores all health
	public static final double MAGIC_MUSHROOM_BLUE_HEART = 0;
	
	public static final double MAGIC_MUSHROOM_DMG_MULT = 1.5;
	public static final double MAGIC_MUSHROOM_SPEED = 0.01; //TODO convert into real speed 0.3
	public static final double MAGIC_MUSHROOM_TEAR_RATE = 0;
	public static final double MAGIC_MUSHROOM_DAMAGE = 0.3;
	public static final double MAGIC_MUSHROOM_RANGE = 1.5; //TODO convert into real statistics
	public static final double MAGIC_MUSHROOM_SHOOTSPEED = 0;
	public static final double MAGIC_MUSHROOM_LUCK = 0;
	
	public static final double MAGIC_MUSHROOM_DEVIL_DEAL = 0;
	public static final double MAGIC_MUSHROOM_ANGEL_ROOM = 0;
	
	public static final int MAGIC_MUSHROOM_GOLD = 0;
	public static final int MAGIC_MUSHROOM_BOMB = 0;
	public static final int MAGIC_MUSHROOM_KEY = 0;
	
	public static final double MAGIC_MUSHROOM_HERO_SIZE = 0.02; //TODO verify it


	
	public static final String MAGIC_MUSHROOM_PICKUP_PHRASE = "DMG up";

	public static final Item MAGIC_MUSHROOM = new Item(MAGIC_MUSHROOM_HEART_CONTAINER, MAGIC_MUSHROOM_RED_HEART, MAGIC_MUSHROOM_BLUE_HEART, MAGIC_MUSHROOM_DMG_MULT, MAGIC_MUSHROOM_SPEED, MAGIC_MUSHROOM_TEAR_RATE,
			MAGIC_MUSHROOM_DAMAGE,MAGIC_MUSHROOM_RANGE, MAGIC_MUSHROOM_SHOOTSPEED,
			MAGIC_MUSHROOM_LUCK, MAGIC_MUSHROOM_DEVIL_DEAL, MAGIC_MUSHROOM_ANGEL_ROOM, MAGIC_MUSHROOM_GOLD, MAGIC_MUSHROOM_BOMB, MAGIC_MUSHROOM_KEY, MAGIC_MUSHROOM_HERO_SIZE, RoomInfos.POSITION_CENTER_OF_ROOM, ITEM_SIZE, ImagePaths.MAGIC_MUSHROOM);
	
	//--BLOOD_OF_THE_MARTYR--
	//TODO turn isaac's tears into blood tear
	
	public static final double BLOOD_OF_THE_MARTYR_DMG_MULT = 0;
	public static final double BLOOD_OF_THE_MARTYR_SPEED = 0;
	public static final double BLOOD_OF_THE_MARTYR_TEAR_RATE = 0;
	public static final double BLOOD_OF_THE_MARTYR_DAMAGE = 1.0;
	public static final double BLOOD_OF_THE_MARTYR_RANGE = 0;
	public static final double BLOOD_OF_THE_MARTYR_SHOOTSPEED = 0;
	public static final double BLOOD_OF_THE_MARTYR_LUCK = 0;
	
	public static final String BLOOD_OF_THE_MARTYR_PICKUP_PHRASE = "DMG up";
	
	public static final Item BLOOD_OF_THE_MARTYR = new Item(BLOOD_OF_THE_MARTYR_DMG_MULT, BLOOD_OF_THE_MARTYR_SPEED, BLOOD_OF_THE_MARTYR_TEAR_RATE, BLOOD_OF_THE_MARTYR_DAMAGE,
			BLOOD_OF_THE_MARTYR_RANGE, BLOOD_OF_THE_MARTYR_SHOOTSPEED, BLOOD_OF_THE_MARTYR_LUCK, RoomInfos.POSITION_CENTER_OF_ROOM, ITEM_SIZE,
			ImagePaths.BLOOD_OF_THE_MARTYR);
	
	//--CRICKETS_HEAD--

	public static final double CRICKETS_HEAD_DMG_MULT = 1.5;
	public static final double CRICKETS_HEAD_SPEED = 0;
	public static final double CRICKETS_HEAD_TEAR_RATE = 0;
	public static final double CRICKETS_HEAD_DAMAGE = 0.5;
	public static final double CRICKETS_HEAD_RANGE = 0;
	public static final double CRICKETS_HEAD_SHOOTSPEED = 0;
	public static final double CRICKETS_HEAD_LUCK = 0;
	
	public static final String CRICKETS_HEAD_PICKUP_PHRASE = "DMG up";
	
	public static final Item CRICKETS_HEAD = new Item(CRICKETS_HEAD_DMG_MULT, CRICKETS_HEAD_SPEED, CRICKETS_HEAD_TEAR_RATE, CRICKETS_HEAD_DAMAGE,
			CRICKETS_HEAD_RANGE, CRICKETS_HEAD_SHOOTSPEED, CRICKETS_HEAD_LUCK, RoomInfos.POSITION_CENTER_OF_ROOM, ITEM_SIZE,
			ImagePaths.CRICKETS_HEAD);
	
	// --JESUS_JUICE--
	//TODO jesus Juice : +0.5 tear height. 
	
	public static final double JESUS_JUICE_DMG_MULT = 0;
	public static final double JESUS_JUICE_SPEED = 0;
	public static final double JESUS_JUICE_TEAR_RATE = 0;
	public static final double JESUS_JUICE_DAMAGE = 0.5;
	public static final double JESUS_JUICE_RANGE = 0.25;
	public static final double JESUS_JUICE_SHOOTSPEED = 0;
	public static final double JESUS_JUICE_LUCK = 0;
		
	public static final String JESUS_JUICE_PICKUP_PHRASE = "Damage + range up";
	
	// TODO find a way to give a proper position when created (using freePosition)
	public static final Item JESUS_JUICE = new Item(JESUS_JUICE_DMG_MULT, JESUS_JUICE_SPEED, JESUS_JUICE_TEAR_RATE, JESUS_JUICE_DAMAGE,
				JESUS_JUICE_RANGE, JESUS_JUICE_SHOOTSPEED, JESUS_JUICE_LUCK, RoomInfos.POSITION_CENTER_OF_ROOM, ITEM_SIZE,
				ImagePaths.JESUS_JUICE);
	
//--HP UP----------------------
	
	//--STIGMATA--

	public static final int STIGMATA_HEART_CONTAINER = 1;
	public static final double STIGMATA_RED_HEART = 1;
	public static final double STIGMATA_DAMAGE = 0.5;

	
	public static final String STIGMATA_PICKUP_PHRASE = "DMG + HP up";
	
	public static final Item STIGMATA = new Item(STIGMATA_HEART_CONTAINER, STIGMATA_RED_HEART, 0, 0, 0, 0, STIGMATA_DAMAGE, 0, 0,
			0, 0, 0, 0, 0, 0, 0, RoomInfos.POSITION_CENTER_OF_ROOM, ITEM_SIZE, ImagePaths.STIGMATA);
	
	//--LUNCH--

	public static final int LUNCH_HEART_CONTAINER = 1;
	public static final double LUNCH_RED_HEART = 2;
	
	public static final String LUNCH_PICKUP_PHRASE = "HP up";
	
	public static final Item LUNCH = new Item(LUNCH_HEART_CONTAINER, LUNCH_RED_HEART, 0, RoomInfos.POSITION_CENTER_OF_ROOM, ITEM_SIZE,
			ImagePaths.LUNCH);
	
	//--HP_UP--

	public static final int HP_UP_HEART_CONTAINER = 1;
	public static final double HP_UP_RED_HEART = 2;
	
	public static final String HP_UP_PICKUP_PHRASE = "HP up";
		
	public static final Item HP_UP = new Item(HP_UP_HEART_CONTAINER, HP_UP_RED_HEART, 0, RoomInfos.POSITION_CENTER_OF_ROOM, ITEM_SIZE,
				ImagePaths.HP_UP);
	
	
	//--GENERATE POOL--------------------------
	
		/**
		 * suffle all the final Item Pool list
		 * The suffle is done one time (when called) to not perturb,
		 * at each time a item spawned, the order of the list (like the actual game) 
		 */
		public static final void generatePool() { 
			Collections.shuffle(ITEM_POOL);
			Collections.shuffle(DEVIL_POOL);
			Collections.shuffle(BOSS_POOL);
			Collections.shuffle(SHOP_POOL);
			Collections.shuffle(HP_UP_POOL);

//			generateDevilPool();
//			generateNormalPool();
//			generateShopPool();
//			generateBossPool();
//			generateHPupPool();
		}

		public static final LinkedList<Item> generateDevilPool(){
			
			LinkedList<Item> devilPool = new LinkedList<Item>();
			devilPool.add(PENTAGRAM);
			devilPool.add(BLOOD_OF_THE_MARTYR);
			
			//Collections.shuffle(devilPool);
			
			return devilPool;
		}
		
		public static final LinkedList<Item> generateNormalPool(){
			
			LinkedList<Item> normalPool = new LinkedList<Item>();
			//bossPool.addList(HP_UP_POOL); //TODO create this List adder
			normalPool.add(JESUS_JUICE);
			normalPool.add(CRICKETS_HEAD);
			System.out.println(normalPool);
			System.out.println("size : "+ normalPool.size());
			normalPool.add(MAGIC_MUSHROOM);
			normalPool.add(STIGMATA);
			normalPool.add(LUNCH);
			normalPool.add(HP_UP);
			System.out.println(normalPool.get(0));
			
			//Collections.shuffle(normalPool);
			
			return normalPool;
		}
		
		public static final LinkedList<Item> generateShopPool(){
			
			LinkedList<Item> shopPool = ITEM_POOL;
			
			//Collections.shuffle(shopPool);
			
			return shopPool;
		}
		
		public static final LinkedList<Item> generateBossPool(){
			
			LinkedList<Item> bossPool = new LinkedList<Item>();
			//bossPool.addList(NORMAL_POOL); //TODO create this List adder
			bossPool.add(JESUS_JUICE);
			bossPool.add(PENTAGRAM);
			bossPool.add(BLOOD_OF_THE_MARTYR);
			bossPool.add(MAGIC_MUSHROOM);
			bossPool.add(CRICKETS_HEAD);
			bossPool.add(STIGMATA);
			bossPool.add(LUNCH);
			bossPool.add(HP_UP);

			//Collections.shuffle(bossPool);
			
			return bossPool;
		}
		
		/**
		 * simple hpUP pool
		 * @return
		 */
		public static final LinkedList<Item> generateHPupPool()
		{
			LinkedList<Item> hpUPPool = new LinkedList<Item>();
			hpUPPool.add(STIGMATA);
			hpUPPool.add(LUNCH);
			hpUPPool.add(HP_UP);
			
			//Collections.shuffle(hpUPPool);

			return hpUPPool;
		}
		
		//--POOL---------------------------------- 
		
		public static final String STRING_ITEM_POOL = "ItemPool";
		public static final String STRING_DEVIL_POOL = "DevilPool";
		public static final String STRING_BOSS_POOL = "BossPool";
		public static final String STRING_SHOP_POOL = "ShopPool";

		
		public static LinkedList<Item> ITEM_POOL = generateNormalPool();
		public static LinkedList<Item> DEVIL_POOL = generateDevilPool();
		public static LinkedList<Item> BOSS_POOL = generateBossPool();
		public static LinkedList<Item> SHOP_POOL = generateShopPool();
		public static LinkedList<Item> HP_UP_POOL = generateHPupPool();


}
