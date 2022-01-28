package resources;

import gameobjects.obstacles.Obstacle;
import libraries.Vector2;

public class RoomInfos
{
	public static final int NB_TILES = 9/**DisplaySettings.SCALE*/;
	public static final double TILE_WIDTH = 1.0*DisplaySettings.SCALE / NB_TILES*DisplaySettings.SCALE;
	public static final double TILE_HEIGHT = 1.0*DisplaySettings.SCALE / NB_TILES*DisplaySettings.SCALE;
	public static final Vector2 TILE_SIZE = new Vector2(TILE_WIDTH, TILE_HEIGHT);
	public static final Vector2 HALF_TILE_SIZE = new Vector2(TILE_WIDTH, TILE_HEIGHT).scalarMultiplication(0.5);
	
	public static final Vector2 POSITION_CENTER_OF_ROOM = new Vector2(0.5*DisplaySettings.SCALE, 0.5*DisplaySettings.SCALE);
	public static final Vector2 POSITION_START_OF_ROOM  = new Vector2(0*DisplaySettings.SCALE, 0*DisplaySettings.SCALE);
	public static final Vector2 POSITION_END_OF_ROOM    = new Vector2(1*DisplaySettings.SCALE, 1*DisplaySettings.SCALE);
	
	public static final Vector2 POSITION_LEFT_BOTTOM_OF_ROOM = new Vector2(0.15, 0.216);
	public static final Vector2 POSITION_DIAG_1 = POSITION_LEFT_BOTTOM_OF_ROOM;
	public static final Vector2 POSITION_DIAG_2 = new Vector2(0.2165, 0.282);
	public static final Vector2 POSITION_DIAG_3 = new Vector2(0.283, 0.348); //0.0665, 0.066
	public static final Vector2 POSITION_DIAG_4 = new Vector2(0.35, 0.415); //0.067, 0.067
	public static final Vector2 POSITION_DIAG_5 = new Vector2(0.417, 0.482); //0.067, 0.067
	public static final Vector2 POSITION_DIAG_6 = new Vector2(0.484, 0.549); //0.067, 0.067
	public static final Vector2 POSITION_DIAG_7 = new Vector2(0.551, 0.616); //0.067, 0.067
	public static final Vector2 POSITION_DIAG_8 = new Vector2(0.617, 0.682); //0.066, 0.066
	public static final Vector2 POSITION_DIAG_9 = new Vector2(0.684, 0.749); //0.067, 0.066
	public static final Vector2 POSITION_DIAG_10 = new Vector2(0.751, 0.816); //0.067, 0.067
	public static final Vector2 POSITION_DIAG_11 = new Vector2(0.818, 0.883); //0.067, 0.067
	public static final Vector2 POSITION_DIAG_12 = new Vector2(0.885, 0.95); //0.067, 0.067








									  			   //= new Vector2(width, heigth);
	public static final Vector2 SIZE_WALL_UP         = new Vector2(1*DisplaySettings.SCALE, 0.21*DisplaySettings.SCALE);
	public static final Vector2 SIZE_WALL_DOWN       = new Vector2(1*DisplaySettings.SCALE, 0.14*DisplaySettings.SCALE);
	public static final Vector2 SIZE_WALL_LEFTnRIGHT = new Vector2(0.13*DisplaySettings.SCALE, 1*DisplaySettings.SCALE);
	
	
	public static final Obstacle WALL_UP    = new Obstacle(new Vector2(POSITION_CENTER_OF_ROOM.getX(), POSITION_START_OF_ROOM.getY()),
														   RoomInfos.SIZE_WALL_UP.getX(),
														   RoomInfos.SIZE_WALL_UP.getY());

	public static final Obstacle WALL_DOWN  = new Obstacle(new Vector2(POSITION_CENTER_OF_ROOM.getX(), POSITION_END_OF_ROOM.getY()),
													 	   RoomInfos.SIZE_WALL_DOWN.getX(),
													 	   RoomInfos.SIZE_WALL_DOWN.getY());
	
	public static final Obstacle WALL_LEFT  = new Obstacle(new Vector2(POSITION_START_OF_ROOM.getX(), POSITION_CENTER_OF_ROOM.getY()),
		 	 											   RoomInfos.SIZE_WALL_LEFTnRIGHT.getX(),
		 	 											   RoomInfos.SIZE_WALL_LEFTnRIGHT.getY());
	
	public static final Obstacle WALL_RIGHT = new Obstacle(new Vector2(POSITION_END_OF_ROOM.getX(), POSITION_CENTER_OF_ROOM.getY()),
														   RoomInfos.SIZE_WALL_LEFTnRIGHT.getX(),
														   RoomInfos.SIZE_WALL_LEFTnRIGHT.getY());
	
}
