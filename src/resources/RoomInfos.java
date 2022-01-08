package resources;

import libraries.Vector2;

public class RoomInfos
{
	public static final int NB_TILES = 9*DisplaySettings.SCALE;
	public static final double TILE_WIDTH = 1.0 / NB_TILES;
	public static final double TILE_HEIGHT = 1.0 / NB_TILES;
	public static final Vector2 TILE_SIZE = new Vector2(TILE_WIDTH, TILE_HEIGHT);
	public static final Vector2 HALF_TILE_SIZE = new Vector2(TILE_WIDTH, TILE_HEIGHT).scalarMultiplication(0.5);
	
	public static final Vector2 POSITION_CENTER_OF_ROOM = new Vector2(0.5*DisplaySettings.SCALE, 0.5*DisplaySettings.SCALE);
	public static final double SIZE = 1*DisplaySettings.SCALE;
	public static final double START = 0*DisplaySettings.SCALE; //of the window, change it when we will center the game in the screen
	//TODO MAKE it understandable 
	public static final double[] WALL_UP = {1*DisplaySettings.SCALE, 0.15*DisplaySettings.SCALE};
	public static final double[] WALL_DOWN = {1*DisplaySettings.SCALE, 0.21*DisplaySettings.SCALE};
	public static final double[] WALL_LEFTnRIGHT = {0.13*DisplaySettings.SCALE, 1*DisplaySettings.SCALE};
	
}
