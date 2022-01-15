package gameWorld.room;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import gameobjects.Door;
import gameobjects.moving_entity.Hero;
import gameobjects.moving_entity.Projectile;
import gameobjects.moving_entity.monsters.Monsters;
import gameobjects.moving_entity.monsters.Spider;
import gameobjects.obstacles.Obstacle;
import libraries.StdDraw;
import libraries.Vector2;
import resources.CreaturesInfos;
import resources.DisplaySettings;
import resources.ImagePaths;
import resources.RoomInfos;

public class Room
{
	private Hero hero;
	private ArrayList<Door> doors ;
	private ArrayList<Obstacle> obstacles;
	private ArrayList<Projectile> projectile;
	private LinkedList<Monsters> monsters;
	
	public Room(Hero hero, List<Door> doors)
	{
		this.hero = hero;
		this.doors = new ArrayList<Door>(4);
		this.obstacles = new ArrayList<Obstacle>(4);
		this.projectile = new ArrayList<Projectile>(10);//valeur random
		
		this.monsters = new LinkedList<Monsters>();
		//The destination must be random (spider pattenr move)
				this.monsters.add(new Spider(new Vector2(0.3,0.3), hero.getPosition())); //CreaturesInfos.SPIDER
		
		//carefull about scaling
		obstacles.add(new Obstacle(new Vector2(0.5,0), RoomInfos.WALL_DOWN[1], RoomInfos.WALL_DOWN[0])); 			//BAS
		obstacles.add(new Obstacle(new Vector2(0.5,1), RoomInfos.WALL_UP[1], RoomInfos.WALL_UP[0])); 				//HAUT
		obstacles.add(new Obstacle(new Vector2(0,0.5), RoomInfos.WALL_LEFTnRIGHT[1], RoomInfos.WALL_LEFTnRIGHT[0]));//GAUCHE
		obstacles.add(new Obstacle(new Vector2(1,0.5), RoomInfos.WALL_LEFTnRIGHT[1], RoomInfos.WALL_LEFTnRIGHT[0]));//DROIT
		
		for (Door door:doors) {
			if (door!=null) {
				this.doors.add(door);
			}
		}
	}
	
	
	/*
	 * Make every entity that compose a room process one step
	 */
	public void updateRoom()
	{
		makeHeroPlay();
		updateProjectile();
		makeMonstersPlay();
		checkCollision();
	}
	
//--COMBAT CODE-------------------------------------------------
	private void makeMonstersPlay() {			
		for (Monsters monster : monsters) {
			Vector2 lastPosition = monster.getPosition();
			monster.updateGameObject(); //DO NOT MOVE you monster
			if (inAnObstacle(monster.getPosition()))
				monster.setPosition(lastPosition);
		}
	}
	
	private void checkCollision(){//implementer tout type de collision (cac et projectile)
		checkRangeCollision();
		checkCloseCollision();
	}
	
	private void checkCloseCollision() {
		if (collisionWithMonster(getHero().getPosition(), getHero().getSize())!=null) {
			Monsters contactMonster = collisionWithMonster(getHero().getPosition(), getHero().getSize());
			contactMonster.addFreezeTime(20);
			getHero().getHitted(contactMonster.getDamage());
			getHero().addInvincibilityFrames(CreaturesInfos.HERO_INVINCIBILITY);
		}
	}
	
	
	private void checkRangeCollision() {
		ArrayList<Monsters> monster_delete = new ArrayList<Monsters>();
		ArrayList<Projectile> projectile_delete = new ArrayList<Projectile>();
		ArrayList<Projectile> projectiles = new ArrayList<Projectile>();
		projectiles.addAll(getHero().getProjectile());
		if (!projectiles.isEmpty()) {
			for (Projectile projectile:projectiles) {
				if (collisionWithMonster(projectile.getProjPosition(), projectile.getProjSize())!=null) {
				monster_delete.add(collisionWithMonster(projectile.getProjPosition(), projectile.getProjSize()));
				projectile_delete.add(projectile);
				}
			
			}
		}
		monsters.removeAll(monster_delete);
		getHero().removeProjectile(projectile_delete);
	}
	
	private Monsters collisionWithMonster(Vector2 coordonnees, Vector2 size) {
		double posX0 = coordonnees.getX() - (size.getX() / 2);
		double posX1 = coordonnees.getX() + (size.getX() / 2);
		double posY0 = coordonnees.getY() - (size.getY() / 2);
		double posY1 = coordonnees.getY() + (size.getY() / 2);
		Monsters guilty = null;
		for (Monsters monster : monsters) {
			double monX0 = monster.getPosition().getX() - (monster.getSize().getX() / 2);
			double monX1 = monster.getPosition().getX() + (monster.getSize().getX() / 2);
			double monY0 = monster.getPosition().getY() - (monster.getSize().getY() / 2);
			double monY1 = monster.getPosition().getY() + (monster.getSize().getY() / 2);
			if (posX0>monX1 || posX1<=monX0 || posY0>=monY1 || posY1<=monY0)
				return null;//pas de collision
			guilty = monster;
		}
		return guilty;
	}

//--OBSTACLES--------------------------------------------------

	public boolean inAnObstacle(Vector2 coordonnees) {
		return checkObstacle(coordonnees);
	}
	
	
	//if (checkposition.getX()>(RoomInfos.ROOM_WIDTH[0]) && checkposition.getX()<(RoomInfos.ROOM_WIDTH[1]) &&
	//	  checkposition.getY()>(RoomInfos.ROOM_HEIGHT[0]) && checkposition.getY()<(RoomInfos.ROOM_HEIGHT[1])) {
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
		

//--INTERFACE-GRAPHIQUE------------------------------------------------------
	
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
		for(Monsters monster:monsters) {
			monster.drawGameObject();
		}
		for(Door door: doors) {
			door.drawGameObject();
		}
		
		//--HITBOX DREW---------------------
//		double posX0 = this.getHero().getPosition().getX() - (this.getHero().getSize().getX() / 2);//TODO a supp
//		double posX1 = this.getHero().getPosition().getX() + (this.getHero().getSize().getX() / 2);
//		double posY0 = this.getHero().getPosition().getY() - (this.getHero().getSize().getY() / 2);
//		double posY1 = this.getHero().getPosition().getY() + (this.getHero().getSize().getY() / 2);
//		StdDraw.setPenColor(StdDraw.BLUE);
//		StdDraw.filledCircle(posX1, posY0, 0.01);
//		StdDraw.filledCircle(posX1, posY1, 0.01);
//		StdDraw.filledCircle(posX0, posY0, 0.01);
//		StdDraw.filledCircle(posX0, posY1, 0.01);
//		StdDraw.filledCircle(0.5, 0.5, 0.02);
		
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
		
	}
	
	/**
	 * Convert a tile index to a 0-1 position.
	 * 
	 * @param indexX
	 * @param indexY
	 * @return
	 */
	@SuppressWarnings("unused")
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

	public ArrayList<Door> getDoors() {
		return doors;
	}
	
	
	
}
