package resources;

import libraries.Vector2;

public class PickUpInfos {
	public static final double HALF_HEART = 0.5;
	public static final double FULL_HEART = 1;
	public static final double DOUBLE_HEART = 2;
	
	public static final String RED_HEART = "red";
	public static final String BLUE_HEART = "blue";
	public static final String BLACK_HEART = "black";
	public static final String GOLDEN_HEART = "golden";
	
	public static final int BLUE_HEART_DROP = 30; //percenatge
	public static final int HALF_HEART_DROP = 25; //whatever its color
	
	public static final Vector2 SIZE = RoomInfos.TILE_SIZE.scalarMultiplication(0.7);
}
