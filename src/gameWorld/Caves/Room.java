package gameWorld.Caves;

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
	private ArrayList<Door> doors ;
	private ArrayList<Obstacle> obstacles;
	private ArrayList<Projectile> projectile;
	
	public Room(Hero hero)
	{
		this.hero = hero;
		this.doors = new ArrayList<Door>(4);
		this.obstacles = new ArrayList<Obstacle>(4);		//value random between 0 and 8 (position precreated)
		this.projectile = new ArrayList<Projectile>(10);	//value random

		//Walls delimitation
		//BELOW
		obstacles.add(new Obstacle(
				new Vector2(RoomInfos.POSITION_CENTER_OF_ROOM.getX(), RoomInfos.START),
				RoomInfos.WALL_DOWN[0], RoomInfos.WALL_DOWN[1])); 	
		//ABOVE
		obstacles.add(new Obstacle(
				new Vector2(RoomInfos.POSITION_CENTER_OF_ROOM.getX(),RoomInfos.SIZE),
				RoomInfos.WALL_UP[0], RoomInfos.WALL_UP[1])); 			
		//LEFT
		obstacles.add(new Obstacle(
				new Vector2(RoomInfos.START,RoomInfos.POSITION_CENTER_OF_ROOM.getY()),
				RoomInfos.WALL_LEFTnRIGHT[0], RoomInfos.WALL_LEFTnRIGHT[1]));	
		//RIGHT
		obstacles.add(new Obstacle(
				new Vector2(RoomInfos.SIZE,RoomInfos.POSITION_CENTER_OF_ROOM.getY()),
				RoomInfos.WALL_LEFTnRIGHT[0], RoomInfos.WALL_LEFTnRIGHT[1]));	
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
		
		hero.drawGameObject();
		ArrayList<Projectile> tears = hero.getProjectile();
		for(Projectile tear:tears) {
			tear.drawGameObject();
		}
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
