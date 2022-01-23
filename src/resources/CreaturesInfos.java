package resources;

import java.util.LinkedList;

import gameobjects.moving_entity.Hero;
import gameobjects.moving_entity.monsters.Boss;
import gameobjects.moving_entity.monsters.Spider;
import gameobjects.stuff.Item;
import libraries.Vector2;


public class CreaturesInfos
{

//--TEAR--------------------------
	public static final Vector2 TEAR_SIZE = RoomInfos.TILE_SIZE.scalarMultiplication(0.35*DisplaySettings.SCALE);

//--INVINCIBILITY FRAME-----------
	public static final int HERO_INVINCIBILITY = 20; //can change with a trinket (supp final when implementing)

//--ISAAC-------------------------
	
	//--STARTER-------------------
	public static Vector2 ISAAC_SIZE = RoomInfos.TILE_SIZE.scalarMultiplication(0.7*DisplaySettings.SCALE);
	public static final double ISAAC_REDHEART = 3;
	public static final double ISAAC_BLUEHEART = 2;
	public static final double ISAAC_SPEED = 0.01*DisplaySettings.SCALE;
	public static final double ISAAC_TEARRATE = 3.5*DisplaySettings.SCALE;
	public static final double ISAAC_DAMAGE = 6;
	public static final double ISAAC_RANGE = 0.5*DisplaySettings.SCALE; //depend the room's width //TODO MAKE IT WORK
	public static final double ISAAC_SHOTSPEED = 0.03*DisplaySettings.SCALE;
	
	public static final LinkedList<Item> ISAAC_STARTER = new LinkedList<Item>();
	
	public static Hero ISAAC = new Hero(RoomInfos.POSITION_CENTER_OF_ROOM, ISAAC_SIZE,
										ISAAC_REDHEART, ISAAC_BLUEHEART, ISAAC_SPEED,
										ISAAC_TEARRATE, ISAAC_DAMAGE, ISAAC_RANGE, ISAAC_SHOTSPEED,
		    							ISAAC_STARTER, ImagePaths.ISAAC);

//--MAGDALENE-------------------------
	
	//--STARTER-------------------
	public static Vector2 MAGDALENE_SIZE = RoomInfos.TILE_SIZE.scalarMultiplication(0.7*DisplaySettings.SCALE);
	public static final double MAGDALENE_REDHEART = 5;
	public static final double MAGDALENE_BLUEHEART = 0;
	public static final double MAGDALENE_SPEED = 0.01*DisplaySettings.SCALE;
	public static final double MAGDALENE_TEARRATE = 3.5*DisplaySettings.SCALE;
	public static final double MAGDALENE_DAMAGE = 4;
	public static final double MAGDALENE_RANGE = 0.5*DisplaySettings.SCALE; //depend the room's width //TODO MAKE IT WORK
	public static final double MAGDALENE_SHOTSPEED = 0.03*DisplaySettings.SCALE;
		
	public static final LinkedList<Item> MAGDALENE_STARTER = new LinkedList<Item>(); //activable heal
		
	public static Hero MAGDALENE = new Hero(RoomInfos.POSITION_CENTER_OF_ROOM, MAGDALENE_SIZE,
											MAGDALENE_REDHEART, MAGDALENE_BLUEHEART, MAGDALENE_SPEED,
											MAGDALENE_TEARRATE, MAGDALENE_DAMAGE, MAGDALENE_RANGE, MAGDALENE_SHOTSPEED,
			    							MAGDALENE_STARTER, ImagePaths.MAGDALENE);
	//--CHEATING STATS------------
	public static final double BROKEN_DAMAGE = 100;
	public static final double BROKEN_SPEED = 0.05*DisplaySettings.SCALE; //TODO verify the placement of scale multiplier
	public static final double BROKEN_TEAR_RATE = 10;
	public static final double BROKEN_RANGE = 1*DisplaySettings.SCALE;


	
//--SPIDER-------------------------
	public static Vector2 SPIDER_SIZE = RoomInfos.TILE_SIZE.scalarMultiplication(0.3*DisplaySettings.SCALE);
	public static final int SPIDER_CELLS_NB = 6;
	public static final double SPIDER_HEALTH = 6.5;
	public static final double SPIDER_SPEED = 0.02;
	public static final double SPIDER_TEARRATE = 0;
	public static final double SPIDER_DAMAGE = 0.5;
	public static final double SPIDER_RANGE = 0;
	public static final double SPIDER_SHOOTSPEED = 0;
	public static final int SPIDERT_RELOADTIME = 80;
	public static final Vector2 SPIDER_VISION = new Vector2(0.1,0.15);
	
//--FLY---------------------------
	public static Vector2 FLY_SIZE = RoomInfos.TILE_SIZE.scalarMultiplication(0.3*DisplaySettings.SCALE);	
	public static final int FLY_CELLS_NB = 4;
	public static final double FLY_HEALTH = 5;
	public static final double FLY_SPEED = 0.01;
	public static final double FLY_TEARRATE = 0;
	public static final double FLY_DAMAGE = 0.5;
	public static final double FLY_RANGE = 0;
	public static final double FLY_SHOOTSPEED = 0.01;
	
//--MOTER-------------------------
	public static Vector2 MOTER_SIZE = RoomInfos.TILE_SIZE.scalarMultiplication(0.3*DisplaySettings.SCALE);
	public static final int MOTER_SRITE_SHEET_SIZE = 26; //each cell is 26px long, 11px high
	public static final int MOTER_CELLS_NB = 4;
	public static final double MOTER_HEALTH = 10;
	public static final double MOTER_SPEED = 0.008;
	public static final double MOTER_TEARRATE = 0;
	public static final double MOTER_DAMAGE = 0.5;
	public static final double MOTER_RANGE = 0;
	public static final double MOTER_SHOOTSPEED = 0;
	
//--BOSS-------------------------
		public static Vector2 BOSS_SIZE = RoomInfos.TILE_SIZE.scalarMultiplication(2*DisplaySettings.SCALE);
		public static final int BOSS_SRITE_SHEET_SIZE = 26; //each cell is 26px long, 11px high
		public static final int BOSS_CELLS_NB = 4;
		public static final double BOSS_HEALTH = 50;
		public static final double BOSS_SPEED = 0.006; //was 0.008 but the flies were stuck on his bodyhaving the same speed
		public static final double BOSS_TEARRATE = 0;
		public static final double BOSS_DAMAGE = 0.5;
		public static final double BOSS_RANGE = 0;
		public static final double BOSS_SHOOTSPEED = 0;

	public static final Boss SWARMER = new Boss(RoomInfos.POSITION_CENTER_OF_ROOM, CreaturesInfos.BOSS_SIZE, RoomInfos.POSITION_CENTER_OF_ROOM, CreaturesInfos.BOSS_HEALTH,
				CreaturesInfos.BOSS_SPEED, CreaturesInfos.BOSS_TEARRATE, CreaturesInfos.BOSS_DAMAGE,
				CreaturesInfos.BOSS_RANGE,  CreaturesInfos.BOSS_SHOOTSPEED, ImagePaths.BOSS);
	//public static Monsters SPIDER = new Spider(new Vector2(0.3,0.3), hero.getPosition());
	
	/**
	 * Convert the tearRate stat of a creature into a number of ticks.
	 * (Associated with FPS in (global number) DisplaySettings)
	 * @param double tearRate
	 * @return number of ticks the creature needs to wait before be able to reshoot
	 */
	public static int convertTearRateToTicks(double tearRate)
	{
		double ticks = tearRate*DisplaySettings.FPS;
		return (int)ticks;
	}
	
}
