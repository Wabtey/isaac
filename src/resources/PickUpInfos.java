package resources;

import gameobjects.pickup.*;
import libraries.Vector2;

public class PickUpInfos {
	
//--HEART INFOS--------------------------------------------
	public static final double HALF_HEART_VALUE = 0.5;
	public static final double FULL_HEART_VALUE = 1;
	public static final double DOUBLE_HEART_VALUE = 2;
	
	public static final String RED_HEART_COLOR = "red";
	public static final String BLUE_HEART_COLOR = "blue";
	public static final String BLACK_HEART_COLOR = "black";
	public static final String GOLDEN_HEART_COLOR = "golden";
	
	public static final int BLUE_HEART_DROP = 30; //percenatge
	public static final int HALF_HEART_DROP = 25; //whatever its color
	
	public static final Vector2 SIZE = RoomInfos.TILE_SIZE.scalarMultiplication(0.7*DisplaySettings.SCALE);
	
	//TODO have to set the position after using this final unless the position is still in the center of the room
	public static final Heart HALF_RED_HEART  = new Heart(RoomInfos.POSITION_CENTER_OF_ROOM, RED_HEART_COLOR,
														  HALF_HEART_VALUE, ImagePaths.HALF_HEART_PICKABLE);
	public static final Heart RED_HEART 	  = new Heart(RoomInfos.POSITION_CENTER_OF_ROOM, RED_HEART_COLOR,
														  FULL_HEART_VALUE, ImagePaths.HEART_PICKABLE);
	public static final Heart HALF_BLUE_HEART = new Heart(RoomInfos.POSITION_CENTER_OF_ROOM, BLUE_HEART_COLOR,
														  HALF_HEART_VALUE, ImagePaths.HALF_BLUE_HEART_PICKABLE);
	public static final Heart BLUE_HEART 	  = new Heart(RoomInfos.POSITION_CENTER_OF_ROOM, BLUE_HEART_COLOR,
														  FULL_HEART_VALUE, ImagePaths.BLUE_HEART_PICKABLE);
	
//--COIN INFOS---------------------------------------------
	public static final int COIN_VALUE = 1;
	public static final int NICKEL_VALUE = 5;
	public static final int DIME_VALUE = 10;
	
	public static final double NICKEL_DROP = 20;//25% mean 1/4 coin is a nickel
	public static final double DIME_DROP = 5;	//5% mean 1/20 coin is a dime
	
	//TODO have to set the position after using this final unless the position is still in the center of the room
	public static final Coin NICKEL = new Coin(RoomInfos.POSITION_CENTER_OF_ROOM, NICKEL_VALUE, ImagePaths.NICKEL);
	public static final Coin DIME   = new Coin(RoomInfos.POSITION_CENTER_OF_ROOM, DIME_VALUE, ImagePaths.DIME);
}
