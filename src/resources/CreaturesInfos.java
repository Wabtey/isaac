package resources;

import gameobjects.moving_entity.Hero;
import gameobjects.moving_entity.monsters.Spider;
import libraries.Vector2;


public class CreaturesInfos
{

//--ISAAC-------------------------
	public static final int HERO_INVINCIBILITY = 20; //can change with a trinket (supp final when implementing)
	//--STARTER-------------------
	public static Vector2 ISAAC_SIZE = RoomInfos.TILE_SIZE.scalarMultiplication(0.7);
	public static final double ISAAC_REDHEART = 4;
	public static final double ISAAC_BLUEHEART = 3;
	public static final double ISAAC_SPEED = 0.01;
	public static final double ISAAC_TEARRATE = 2.5;
	public static final double ISAAC_DAMAGE = 3.5;
	public static final double ISAAC_RANGE = 0.5;
	public static final double ISAAC_SHOTSPEED = 0.03;
	
	public static Hero ISAAC = new Hero(new Vector2(0.5,0.5), ISAAC_SIZE,
										ISAAC_REDHEART, ISAAC_BLUEHEART, ISAAC_SPEED,
										ISAAC_TEARRATE, ISAAC_DAMAGE, ISAAC_RANGE, ISAAC_SHOTSPEED,
		    							ImagePaths.ISAAC);
	
//--SPIDER---------------------------
	public static Vector2 SPIDER_SIZE = RoomInfos.TILE_SIZE.scalarMultiplication(0.3);
	public static final double SPIDER_HEALTH = 6.5;
	public static final double SPIDER_SPEED = 0.02;
	public static final double SPIDER_TEARRATE = 0;
	public static final double SPIDER_DAMAGE = 0.5;
	public static final double SPIDER_RANGE = 0;
	public static final double SPIDER_SHOOTSPEED = 0;
	public static final Vector2 SPIDER_VISION = new Vector2(0.1,0.15);
	
//--FLY---------------------------
	public static Vector2 FLY_SIZE = RoomInfos.TILE_SIZE.scalarMultiplication(0.3);	
	public static final double FLY_HEALTH = 5;
	public static final double FLY_SPEED = 0.00;
	public static final double FLY_TEARRATE = 0;
	public static final double FLY_DAMAGE = 0.5;
	public static final double FLY_RANGE = 0;
	public static final double FLY_SHOOTSPEED = 0;

	
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
