package gameWorld;

import java.util.ArrayList;
import gameobjects.Door;
import gameobjects.moving_entity.Hero;
import gameobjects.moving_entity.Projectile;
import gameobjects.obstacles.Obstacle;
import libraries.StdDraw;
import libraries.Vector2;
import resources.DisplaySettings;
import resources.ImagePaths;
import resources.RoomInfos;

public class Room
{
	private Hero hero;
	private Room previousRoom;
	private ArrayList<Door> doors ;
	private ArrayList<Obstacle> obstacles;
	private ArrayList<Projectile> projectile;
	
	public Room(Hero hero)
	{
		this.hero = hero;
		this.previousRoom = null;
		this.doors = new ArrayList<Door>(4);
		this.obstacles = new ArrayList<Obstacle>(4);
		this.projectile = new ArrayList<Projectile>(10);//valeur random
		//carefull about scaling
		obstacles.add(new Obstacle(new Vector2(0.5,0), RoomInfos.WALL_DOWN[1], RoomInfos.WALL_DOWN[0])); 			//BAS
		obstacles.add(new Obstacle(new Vector2(0.5,1), RoomInfos.WALL_UP[1], RoomInfos.WALL_UP[0])); 				//HAUT
		obstacles.add(new Obstacle(new Vector2(0,0.5), RoomInfos.WALL_LEFTnRIGHT[1], RoomInfos.WALL_LEFTnRIGHT[0]));//GAUCHE
		obstacles.add(new Obstacle(new Vector2(1,0.5), RoomInfos.WALL_LEFTnRIGHT[1], RoomInfos.WALL_LEFTnRIGHT[0]));//DROIT
	}
	
	public Room(Hero hero, Room previousRoom)
	{
		this.hero = hero;
		this.previousRoom = previousRoom;
		this.doors = new ArrayList<Door>(4);
		this.obstacles = new ArrayList<Obstacle>(4);
		this.projectile = new ArrayList<Projectile>(10);//valeur random
		//carefull about scaling
		obstacles.add(new Obstacle(new Vector2(0.5,0), RoomInfos.WALL_DOWN[1], RoomInfos.WALL_DOWN[0])); 			//BAS
		obstacles.add(new Obstacle(new Vector2(0.5,1), RoomInfos.WALL_UP[1], RoomInfos.WALL_UP[0])); 				//HAUT
		obstacles.add(new Obstacle(new Vector2(0,0.5), RoomInfos.WALL_LEFTnRIGHT[1], RoomInfos.WALL_LEFTnRIGHT[0]));//GAUCHE
		obstacles.add(new Obstacle(new Vector2(1,0.5), RoomInfos.WALL_LEFTnRIGHT[1], RoomInfos.WALL_LEFTnRIGHT[0]));//DROIT
	}
	
	
	/*
	 * Make every entity that compose a room process one step
	 */
	public void updateRoom()
	{
		makeHeroPlay();
		updateProjectile();
	}

	public boolean inAnObstacle(Vector2 coordonnees) {
		return checkObstacle(coordonnees);
	}
	
	
//	if (checkposition.getX()>(RoomInfos.ROOM_WIDTH[0]) && checkposition.getX()<(RoomInfos.ROOM_WIDTH[1]) &&
//			checkposition.getY()>(RoomInfos.ROOM_HEIGHT[0]) && checkposition.getY()<(RoomInfos.ROOM_HEIGHT[1])) {
	private boolean checkObstacle(Vector2 coordonnees) {
		double posX = coordonnees.getX();
		double posY = coordonnees.getY();
		for (Obstacle obstacle:obstacles) {
			double obsX1 = (obstacle.getPosition().getX()) - (obstacle.getWidth());
			double obsX2 = (obstacle.getPosition().getX()) + (obstacle.getWidth());
			double obsY1 = (obstacle.getPosition().getY()) - (obstacle.getHeight());
			double obsY2 = (obstacle.getPosition().getY()) + (obstacle.getHeight());
			if (posX>obsX1 && posX<obsX2 && posY>obsY1 && posY<obsY2) {
				return true;
			}
		}
		return false;
	}
	
	public Door inDoor() {
		return checkDoor();
	}
	
	private Door checkDoor() {
		double posX = Math.round(hero.getPosition().getX()*100);
		double posY = Math.round(hero.getPosition().getY()*100);
		for (Door door:doors) {
			double doorX = door.getCoordonnees().getX()*100;
			double doorY = door.getCoordonnees().getY()*100;
			if (posX==doorX && posY == doorY)
				return door;
		}
		return null;
	}

	
	/**
	 * Update the Room with the projectile in the Hero class, check if the projectile
	 * need to be deleted and then send the new list to Hero class
	 */
	protected void updateProjectile() {
		ArrayList<Projectile> projectile_delete = new ArrayList<Projectile>();
		projectile = hero.getProjectile();
			for (Projectile p:projectile) {
				System.out.println("les projos: " + hero.getProjectile());
				if (inAnObstacle(p.getProjPosition())) {
					projectile_delete.add(p);
				}
			}
		hero.removeProjectile(projectile_delete);
	}

	protected void makeHeroPlay()
	{
		Vector2 lastPosition = hero.getPosition();
		hero.updateGameObject();
		if (inAnObstacle(hero.getPosition())) 
			hero.setPosition(lastPosition);
	}
		
	
	/*
	 * Drawing
	 */
	public void drawRoom() {
		// For every tile, set background color.
		StdDraw.setPenColor(StdDraw.BLUE);
		double scaling = DisplaySettings.SCALE;
		
		Vector2 position = RoomInfos.POSITION_CENTER_OF_ROOM;
		StdDraw.picture(position.getX(), position.getY(), ImagePaths.FLOOR,
				scaling, scaling);
		StdDraw.picture(position.getX(), position.getY(), ImagePaths.WALL,
				scaling, scaling);
		
//		for (int i = 0; i < RoomInfos.NB_TILES; i++) {
//			for (int j = 0; j < RoomInfos.NB_TILES; j++) {
//				Vector2 position = positionFromTileIndex(i, j);
//				StdDraw.picture(position.getX(), position.getY(), "images/floor.jpg",
//						RoomInfos.HALF_TILE_SIZE.getX() * 2, RoomInfos.HALF_TILE_SIZE.getY() * 2);
//			}
//		}
//		StdDraw.setPenColor(88, 41, 0);// BROWN
//		for (int i = 0; i < RoomInfos.NB_TILES; i++) {
//			for (int j = 0; j < RoomInfos.NB_TILES; j++) {
//				Vector2 position = positionFromTileIndex(i, j);
//				StdDraw.picture(position.getX(), 0, ImagePaths.WALL, RoomInfos.HALF_TILE_SIZE.getX() * 2,
//						RoomInfos.HALF_TILE_SIZE.getY());
//				StdDraw.picture(position.getX(), 1, ImagePaths.WALL, RoomInfos.HALF_TILE_SIZE.getX() * 2,
//						RoomInfos.HALF_TILE_SIZE.getY());
//				StdDraw.picture(0, position.getY(), ImagePaths.WALL, RoomInfos.HALF_TILE_SIZE.getX(),
//						RoomInfos.HALF_TILE_SIZE.getY() * 2);
//				StdDraw.picture(1, position.getY(), ImagePaths.WALL, RoomInfos.HALF_TILE_SIZE.getX(),
//						RoomInfos.HALF_TILE_SIZE.getY() * 2);
//			}
//		}
		hero.drawGameObject();
		ArrayList<Projectile> tears = hero.getProjectile();
		for(Projectile tear:tears) {
			tear.drawGameObject();
		}
	}
	
	/*public void IsAWall() {
		double hx = (double)Math.round(hero.getPosition().getX()*10)/10;
		double hy = (double)Math.round(hero.getPosition().getY()*10)/10;	
		for (Door door : doors) {
			double dx = (double)Math.round(door.getCoordonnees().getX()*10)/10;
			double dy = (double)Math.round(door.getCoordonnees().getY()*10)/10;
			if (hx == dx && hy == dy) {
				GameWorld.setCurrentRoom(door.getNextRoom());
				hero.setPosition(new Vector2(0.5,0.1));
			}
		}
	}*/

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
	
	public ArrayList<Obstacle> getObstacles(){
		return this.obstacles;
	}
	
	protected Hero getHero() {
		return hero;
	}

	protected ArrayList<Door> getDoors() {
		return doors;
	}
	
}
