package gameWorld.room;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import gameobjects.Door;
import gameobjects.moving_entity.Hero;
import gameobjects.moving_entity.Projectile;
import gameobjects.moving_entity.monsters.Fly;
import gameobjects.moving_entity.monsters.Monsters;
import gameobjects.moving_entity.monsters.Moter;
import gameobjects.moving_entity.monsters.Spider;
import gameobjects.obstacles.Obstacle;
import libraries.StdDraw;
import libraries.Vector2;
import resources.CreaturesInfos;
import resources.DisplaySettings;
import resources.DoorInfos;
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

		this.monsters.add(new Spider(new Vector2(0.3, 0.3), hero.getPosition())); // CreaturesInfos.SPIDER
		this.monsters.add(new Fly(new Vector2(0.3, 0.3), hero.getPosition()));
		//this.monsters.add(new Spider(new Vector2(0.6, 0.6), hero.getPosition()));
		this.monsters.add(new Moter(new Vector2(0.6, 0.6), hero.getPosition()));

		// carefull about scaling
		obstacles.add(new Obstacle(new Vector2(0.5, 0), RoomInfos.WALL_DOWN[1], RoomInfos.WALL_DOWN[0])); // BAS
		obstacles.add(new Obstacle(new Vector2(0.5, 1), RoomInfos.WALL_UP[1], RoomInfos.WALL_UP[0])); // HAUT
		obstacles.add(new Obstacle(new Vector2(0, 0.5), RoomInfos.WALL_LEFTnRIGHT[1], RoomInfos.WALL_LEFTnRIGHT[0]));// GAUCHE
		obstacles.add(new Obstacle(new Vector2(1, 0.5), RoomInfos.WALL_LEFTnRIGHT[1], RoomInfos.WALL_LEFTnRIGHT[0]));// DROIT
		for (Door door : doors) {
			if (door != null) {
				this.doors.add(door);
			}
		}
	}
	
	
	/*
	 * Make every entity that compose a room process one step
	 */
	public void updateRoom()
	{
		checkMonstersHP();
		checkDoorState();
		makeHeroPlay();
		makeMonstersPlay();
		updateProjectile();
		checkCollision(); //TODO Only the first Mnster on the list can deal dmg to hero
	}

//--HERO--------------------------------------------------------
	
	protected void makeHeroPlay()
	{
		Vector2 lastPosition = hero.getPosition();
		hero.updateGameObject();
		if (inAnObstacle(hero.getPosition()))
			hero.setPosition(lastPosition);
	}
	
//--PROJECTILES-------------------------------------------------
	
	/**
	 * Update the Room with the projectile in the Hero class, check if the projectile
	 * need to be deleted and then send the new list to Hero class
	 */
	protected void updateProjectile() {
		updateHeroProjectiles();
		updateMonstersProjectiles();
	}
	
	private void updateMonstersProjectiles() {
		ArrayList<Projectile> projectile_delete = new ArrayList<Projectile>(projectile.size());
		for (Monsters monster : monsters) {
			ArrayList<Projectile> projectiles = monster.getProjectile();
			for (Projectile mp : projectiles) {
				if (inAnObstacle(mp.getProjPosition())) {
					projectile_delete.add(mp);
				}
			}
			monster.removeProjectile(projectile_delete);
		}
	}
	
	private void updateHeroProjectiles(){
		ArrayList<Projectile> projectile_delete = new ArrayList<Projectile>(projectile.size());
		ArrayList<Projectile> projectiles = hero.getProjectile();
		for (Projectile p : projectiles) {
			if (inAnObstacle(p.getProjPosition())) {
				projectile_delete.add(p);
			}
		}
		hero.removeProjectile(projectile_delete);
	}

	
//--COMBAT CODE-------------------------------------------------
	
	private void makeMonstersPlay() {			
		for (Monsters monster : monsters) {
			Vector2 lastPosition = monster.getPosition();
			monster.updateGameObject(hero); // DO NOT MOVE you monster
			if (inAnObstacle(monster.getPosition()))
				monster.setPosition(lastPosition);
		}
	}
	
	//TODO implementer tout type de collision (cac et projectile)
	private void checkCollision(){
		checkRangeCollision();
		checkCloseCollision();
	}
	
	private void checkCloseCollision() {
		for (Monsters monster : monsters) {
		if (collisionWithMonster(getHero().getPosition(), getHero().getSize(),monster.getPosition(),monster.getSize())) {
			Monsters contactMonster = monster;
			//contactMonster.addFreezeTime(20);
			getHero().getHitted(contactMonster.getDamage());
			getHero().addInvincibilityFrames(CreaturesInfos.HERO_INVINCIBILITY);
		}
		}
	}
	
	
	private void checkRangeCollision() {
		ArrayList<Projectile> projectile_delete = new ArrayList<Projectile>(projectile.size());
		ArrayList<Projectile> projectiles = new ArrayList<Projectile>(projectile.size());
		projectiles.addAll(getHero().getProjectile());
		if (!projectiles.isEmpty()) {
			for (Projectile projectile : projectiles) {
				for (Monsters monster : monsters) {
					if (collisionWithMonster(monster.getPosition(), monster.getSize(), projectile.getProjPosition(),
							projectile.getProjSize())) {
						monster.takeDamage(projectile.getProjDegat());
						projectile_delete.add(projectile);
					}
				}
			}
		}
		getHero().removeProjectile(projectile_delete);
	}
	
	
	/**
	 * @param Hero's coordonates
	 * @param Hero's size
	 * @return Monster which is in collision with the Hero
	 */
	private boolean collisionWithMonster(Vector2 coordonnees, Vector2 size, Vector2 coordonnees2, Vector2 size2) {
		double posX0 = coordonnees.getX() - (size.getX() / 2);
		double posX1 = coordonnees.getX() + (size.getX() / 2);
		double posY0 = coordonnees.getY() - (size.getY() / 2);
		double posY1 = coordonnees.getY() + (size.getY() / 2);
		double monX0 = coordonnees2.getX() - (size2.getX() / 2);
		double monX1 = coordonnees2.getX() + (size2.getX() / 2);
		double monY0 = coordonnees2.getY() - (size2.getY() / 2);
		double monY1 = coordonnees2.getY() + (size2.getY() / 2);
		if (!(posX0 > monX1 || posX1 <= monX0 || posY0 >= monY1 || posY1 <= monY0))
			return true;
		return false;
	}
	
	private void checkMonstersHP() {
		for (Monsters monster: monsters) {
			if (monster.getRedHeart()==0)
				monsters.remove(monster);
		}
	}

//--OBSTACLES--------------------------------------------------

	public boolean inAnObstacle(Vector2 coordonnees) {
		return checkObstacle(coordonnees);
	}

	// if (checkposition.getX()>(RoomInfos.ROOM_WIDTH[0]) &&
	// checkposition.getX()<(RoomInfos.ROOM_WIDTH[1]) &&
	// checkposition.getY()>(RoomInfos.ROOM_HEIGHT[0]) &&
	// checkposition.getY()<(RoomInfos.ROOM_HEIGHT[1])) {
	private boolean checkObstacle(Vector2 coordonnees) {
		double posX = coordonnees.getX();
		double posY = coordonnees.getY();
		for (Obstacle obstacle : obstacles) {
			double obsX1 = (obstacle.getPosition().getX()) - (obstacle.getWidth());
			double obsX2 = (obstacle.getPosition().getX()) + (obstacle.getWidth());
			double obsY1 = (obstacle.getPosition().getY()) - (obstacle.getHeight());
			double obsY2 = (obstacle.getPosition().getY()) + (obstacle.getHeight());
			if (posX > obsX1 && posX < obsX2 && posY > obsY1 && posY < obsY2) {
				return true;
			}
		}
		return false;
	}
	
//--EXIT-----------------------------------------
	
	/**
	 * Permit to change room
	 * @return Door touching Hero
	 */
	public Door inDoor() {
		if (checkDoor()!=null && checkDoor().getDoorState()) {
			return checkDoor();
		}
		return null;
	}
	
	private void checkDoorState() {
		if (monsters.isEmpty()) {
			for (Door door:doors) {
				door.openDoor();
			}
		}
	}
	
	private Door checkDoor() {
		double posX = Math.round(hero.getPosition().getX() * 100);
		double posY = Math.round(hero.getPosition().getY() * 100);
		for (Door door : doors) {
			double doorX = door.getCoordonnees().getX() * 100;
			double doorY = door.getCoordonnees().getY() * 100;
			if ((posX + DoorInfos.DOOR_RADIUS) > doorX && (posX - DoorInfos.DOOR_RADIUS) < doorX && (posY + DoorInfos.DOOR_RADIUS) > doorY && (posY - DoorInfos.DOOR_RADIUS) < doorY)
				return door;
		}
		return null;
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
		StdDraw.picture(position.getX(), position.getY(), ImagePaths.FLOOR, scaling, scaling);
		StdDraw.picture(position.getX(), position.getY(), ImagePaths.WALL, scaling, scaling);

		hero.drawGameObject();
		ArrayList<Projectile> tears = hero.getProjectile();
		for(Projectile tear:tears) {
			tear.drawGameObject();
		}
		for(Monsters monster: monsters) {
			ArrayList<Projectile> pewpew = monster.getProjectile();
			for(Projectile pew:pewpew) {
				pew.drawGameObject();
			}
		}
		for(Monsters monster:monsters) {
			monster.drawGameObject();
			//monster.drawImage(animation.getSprite(), x, y, null);
		}
		for(Door door: doors) {
			door.drawGameObject();
		}
		
//		//--HITBOX DREW---------------------
//		double posX0 = this.getHero().getPosition().getX() - (this.getHero().getSize().getX() / 2); //TODO a supp
//		double posX1 = this.getHero().getPosition().getX() + (this.getHero().getSize().getX() / 2);
//		double posY0 = this.getHero().getPosition().getY() - (this.getHero().getSize().getY() / 2);
//		double posY1 = this.getHero().getPosition().getY() + (this.getHero().getSize().getY() / 2);
//		StdDraw.setPenColor(StdDraw.BLUE);
//		StdDraw.filledCircle(posX1, posY0, 0.01);
//		StdDraw.filledCircle(posX1, posY1, 0.01);
//		StdDraw.filledCircle(posX0, posY0, 0.01);
//		StdDraw.filledCircle(posX0, posY1, 0.01);
//		StdDraw.filledCircle(0.5, 0.5, 0.02);
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
	
//--GETTERS/SETTERS-----------------------------------
	
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
