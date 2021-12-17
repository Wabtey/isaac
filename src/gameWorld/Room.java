package gameWorld;

import java.util.ArrayList;

import gameobjects.Hero;
import libraries.StdDraw;
import libraries.Vector2;
import resources.ImagePaths;
import resources.RoomInfos;

public class Room
{
	protected Hero hero;
	protected ArrayList<Door> doors ;


	public Room(Hero hero)
	{
		this.hero = hero;
		this.doors = new ArrayList<Door>(4);
	}
	
	public ArrayList<Door> getDoor() {
		return doors;
	}


	/*
	 * Make every entity that compose a room process one step
	 */
	public void updateRoom()
	{
		makeHeroPlay();
	}


	private void makeHeroPlay()
	{
		hero.updateGameObject();
	}
	
	

	/*
	 * Drawing
	 */
	public void drawRoom() {
		// For every tile, set background color.
		StdDraw.setPenColor(StdDraw.BLUE);
		for (int i = 0; i < RoomInfos.NB_TILES; i++) {
			for (int j = 0; j < RoomInfos.NB_TILES; j++) {
				Vector2 position = positionFromTileIndex(i, j);
				StdDraw.picture(position.getX(), position.getY(), "images/floor.jpg",
						RoomInfos.HALF_TILE_SIZE.getX() * 2, RoomInfos.HALF_TILE_SIZE.getY() * 2);
			}
		}
		StdDraw.setPenColor(88, 41, 0);// BROWN
		for (int i = 0; i < RoomInfos.NB_TILES; i++) {
			for (int j = 0; j < RoomInfos.NB_TILES; j++) {
				Vector2 position = positionFromTileIndex(i, j);
				StdDraw.picture(position.getX(), 0, ImagePaths.WALL, RoomInfos.HALF_TILE_SIZE.getX() * 2,
						RoomInfos.HALF_TILE_SIZE.getY());
				StdDraw.picture(position.getX(), 1, ImagePaths.WALL, RoomInfos.HALF_TILE_SIZE.getX() * 2,
						RoomInfos.HALF_TILE_SIZE.getY());
				StdDraw.picture(0, position.getY(), ImagePaths.WALL, RoomInfos.HALF_TILE_SIZE.getX(),
						RoomInfos.HALF_TILE_SIZE.getY() * 2);
				StdDraw.picture(1, position.getY(), ImagePaths.WALL, RoomInfos.HALF_TILE_SIZE.getX(),
						RoomInfos.HALF_TILE_SIZE.getY() * 2);
			}
		}
		hero.drawGameObject();
	}

	/**
	 * Convert a tile index to a 0-1 position.
	 * 
	 * @param indexX
	 * @param indexY
	 * @return
	 */
	private static Vector2 positionFromTileIndex(int indexX, int indexY)
	{
		return new Vector2(indexX * RoomInfos.TILE_WIDTH + RoomInfos.HALF_TILE_SIZE.getX(),
				indexY * RoomInfos.TILE_HEIGHT + RoomInfos.HALF_TILE_SIZE.getY());
	}
}