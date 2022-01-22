package gameWorld.room;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import gameobjects.Door;
import gameobjects.moving_entity.Hero;
import gameobjects.moving_entity.Projectile;
import gameobjects.moving_entity.monsters.*;
import gameobjects.obstacles.Obstacle;
import gameobjects.pickup.*;
import gameobjects.stuff.Item;
import libraries.StdDraw;
import libraries.Vector2;

import resources.CreaturesInfos;
import resources.DisplaySettings;
import resources.DoorInfos;
import resources.ImagePaths;
import resources.ItemInfos;
import resources.PickUpInfos;
import resources.Random;
import resources.RoomInfos;

public abstract class Room
{
	private Hero hero;
	private ArrayList<Door> doors ;
	private ArrayList<Obstacle> obstacles;
	private ArrayList<Projectile> projectile;
	private LinkedList<Monsters> monsters;
	
	private LinkedList<PickUp> rewards; //TODO remove all @Deprecated methods
	private boolean isClear;
	
	private LinkedList<Item> items;
	
	private Vector2 freePosition; //TODO set the freePosition (@see in RoomC1 and RoomC2 with Tiles position methods)
	
	public Room(Hero hero, List<Door> doors)
	{
		this.hero = hero;
		this.doors = new ArrayList<Door>(4);
		this.obstacles = new ArrayList<Obstacle>();
		this.projectile = new ArrayList<Projectile>(10);//valeur random
		
		this.monsters = new LinkedList<Monsters>();
		
		obstacles.add(RoomInfos.WALL_DOWN);
		obstacles.add(RoomInfos.WALL_UP);
		obstacles.add(RoomInfos.WALL_LEFT);
		obstacles.add(RoomInfos.WALL_RIGHT);
		
		for (Door door : doors) {
			if (door != null)
				this.doors.add(door);
		}
		
		this.rewards = new LinkedList<PickUp>();  //initiate a empty list of rewards for every room created
		this.items = new LinkedList<Item>();
		
		this.isClear = false;
		
		System.out.println(Random.getRewardPool(ItemInfos.STRING_ITEM_POOL));
	}
	
	public abstract void initialise();
	
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
		checkCollision();
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
	 * Update the Room with the projectile in the Hero class, check if the
	 * projectile need to be deleted and then send the new list to Hero class
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

	private void updateHeroProjectiles() {
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

	private void checkCollision() {
		checkRangeCollisionWithHero();
		checkRangeCollisionWithMonster();
		checkCloseCollision();
	}

	//TODO : Separated PickUp and Item into different checkCloseCollison() methods ?
	private void checkCloseCollision() {
		for (Monsters monster : monsters) {
			if (collision(getHero().getPosition(), getHero().getSize(), monster.getPosition(), monster.getSize())) {
				Monsters contactMonster = monster;
				// contactMonster.addFreezeTime(20);
				getHero().getHitted(contactMonster.getDamage());
				getHero().addInvincibilityFrames(CreaturesInfos.HERO_INVINCIBILITY);
			}
		}
		
		//--REWARD--
		for (PickUp pickUp : rewards) {
			// TODO shrink hero collision to his feet about pick up the reward
			if (collision(getHero().getPosition(), getHero().getSize(),
						  pickUp.getPosition(), pickUp.getSize())) {
				PickUp contactPickup = pickUp;
				if (getHero().hasPickedUp(contactPickup)) {
					rewards.remove(contactPickup);
				}
			}
		}
		
		//--ITEM--
		for (Item item : items) {
			if (collision(getHero().getPosition(), getHero().getSize(),
						  item.getPosition(), item.getSize())) {
				Item contactItem = item;
				if (getHero().takeItem(contactItem)) {
					items.remove(contactItem);
				}
			}
		}

		
	}

	private void checkRangeCollisionWithMonster() {
		ArrayList<Projectile> projectile_delete = new ArrayList<Projectile>(projectile.size());
		ArrayList<Projectile> projectiles = new ArrayList<Projectile>(projectile.size());
		for (Monsters monster : monsters) {
			projectiles.addAll(monster.getProjectile());
			if (!projectiles.isEmpty())
				for (Projectile projectile : projectiles) {
					if (collision(hero.getPosition(), hero.getSize(), projectile.getProjPosition(),
							projectile.getProjSize()) && projectile.getIsAHeroTear() == false) {
						hero.getHitted(projectile.getProjDegat());
						projectile_delete.add(projectile);
					}
				}
			projectiles.removeAll(monster.getProjectile());
			monster.removeProjectile(projectile_delete);
		}
	}

	private void checkRangeCollisionWithHero() {
		ArrayList<Projectile> projectile_delete = new ArrayList<Projectile>(projectile.size());
		ArrayList<Projectile> projectiles = new ArrayList<Projectile>(projectile.size());
		projectiles.addAll(getHero().getProjectile());
		for (Projectile projectile : projectiles) {
			for (Monsters monster : monsters) {
				if (collision(monster.getPosition(), monster.getSize(), projectile.getProjPosition(),
						projectile.getProjSize()) && projectile.getIsAHeroTear() == true) {
					monster.takeDamage(projectile.getProjDegat());
					projectile_delete.add(projectile);
				}
			}
		}
		getHero().getProjectile().removeAll(projectile_delete);
	}

	/**
	 * @param Hero's coordonates
	 * @param Hero's size
	 * @return true if the two objects are in collision, false otherwise
	 */
	private boolean collision(Vector2 coordonnees, Vector2 size, Vector2 coordonnees2, Vector2 size2) {
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
		ArrayList<Monsters> toDelete = new ArrayList<Monsters>(monsters.size());
		for (Monsters monster : monsters) {
			if (monster.getRedHeart() == 0)
				toDelete.add(monster);
		}
		monsters.removeAll(toDelete);
	}
	
//--REWARD-----------------------------------------------------
	

	/**
	 * this method is override by the special Room to avoid them to have reward
	 * 
	 * That percent number is then used to determine the reward given after clearing the room, using the following index:
	 * 
     * Nothing (< 0.22, base 22% chance)
     * A coin (0.22 - 0.37, base 15% chance)
     * A heart (0.37 - 0.52, base 15% chance)
     * A key (0.52 - 0.72, base 20% chance)
     * A bomb (0.72 - 0.87, base 15% chance)
     * A chest (>0.87) (for now nothing)
     *
	 * @return PickUp droped or null
	 */
	public PickUp generateReward() {
		double percent = Random.roomRewardPercentage(getHero().getLuck());
		PickUp drop = null;
		if(percent>=0.22 || percent < 0.87) {
			if (percent<0.37) {
				drop = new Coin(freePosition); //Generate a Coin, Nickel or Dime 
			}else if (percent <0.52) {
				drop = new Heart(freePosition); //Generate a redHeart, half one, BlueHeart or a half one
			}else if (percent < 0.72) {
				drop = new Key(freePosition);
			}else if (percent < 0.87) {
				drop = new Bomb(freePosition);
			}
		}		
		return drop;

	}
	
	/**
	 * Make all pickUp in the Room disappear
	 * Careful about this methods
	 */
	@Deprecated
	public void removeAllPickUp() {
		setRewards(null);
	}
	public void addItems(Item stuff) {
		getItems().add(stuff);
	}
	
	/**
	 * give a position to every single item which need to spawn
	 */
	public void spawnItem(Item coolStuff) {
		//TODO spawn item method
		coolStuff.setPosition(new Vector2(0.3, 0.3));
		items.add(coolStuff);

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
	 * 
	 * @return Door touching Hero
	 */
	public Door inDoor() {
		if (checkDoor() != null && checkDoor().getDoorState()) {
			return checkDoor();
		}
		return null;
	}

	private void checkDoorState() {
		if (monsters.isEmpty()) {
			for (Door door : doors) {
				door.openDoor();
				generateRoomReward();
			}
		}
	}
	
	private void generateRoomReward() {
		if(!isClear()) {
			PickUp RoomPickUp = generateReward();
			if(RoomPickUp!=null)
				getRewards().add(RoomPickUp);
			setIsClear(true);
		}
	}

	private Door checkDoor() {
		double posX = Math.round(hero.getPosition().getX() * 100);
		double posY = Math.round(hero.getPosition().getY() * 100);
		for (Door door : doors) {
			double doorX = door.getCoordonnees().getX() * 100;
			double doorY = door.getCoordonnees().getY() * 100;
			if ((posX + DoorInfos.DOOR_RADIUS) > doorX && (posX - DoorInfos.DOOR_RADIUS) < doorX
					&& (posY + DoorInfos.DOOR_RADIUS) > doorY && (posY - DoorInfos.DOOR_RADIUS) < doorY)
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
		//double scaling = DisplaySettings.SCALE;

		Vector2 position = RoomInfos.POSITION_CENTER_OF_ROOM;
		//Make a room rectangular
//		StdDraw.picture(position.getX(), position.getY(), ImagePaths.FLOOR);
//		StdDraw.picture(position.getX(), position.getY(), ImagePaths.WALL);
		
		//TODO removing the display of wall and floor make the game faster: learn why
		StdDraw.picture(position.getX(), position.getY(), ImagePaths.FLOOR, DisplaySettings.SCALE, DisplaySettings.SCALE);
		StdDraw.picture(position.getX(), position.getY(), ImagePaths.WALL, DisplaySettings.SCALE, DisplaySettings.SCALE);


		for(Door door: doors) {
			door.drawGameObject();
		}
		
		for (Obstacle obstacle: obstacles) {
			obstacle.drawGameObject();
		}
		
		for(PickUp pickup: rewards) {
			pickup.drawGameObject();
		}
		
		for(Item item: items) {
			//item.setPosition(new Vector2(0.5, 0.4)); replace it by spawnItem() method
			item.drawGameObject();
		}
		
		//--ENTITY--
		
		hero.drawGameObject();
		
		//TODO make the pewpew stayed even if the shooter is dead
		for (Monsters monster : monsters) {
			ArrayList<Projectile> pewpew = monster.getProjectile();
			for (Projectile pew : pewpew) {
				pew.drawGameObject();
			}
		}
		
		for (Monsters monster : monsters) {
			monster.drawGameObject();
		}
		

		
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
	
	public LinkedList<Monsters> getMonsters(){
		return monsters;
	}
	
	public Boss getBoss() {
		for(Monsters monster :getMonsters()) {
			if(monster instanceof Boss) {
				return (Boss)monster; //TODO verify (Boss) cast (same on PickUp)
			}
		}
		return null;
	}
	
	//--PICKUPs--
	
	public LinkedList<PickUp> getRewards() {
		return rewards;
	}

	//carefull about this methods
	@Deprecated
	public void setRewards(LinkedList<PickUp> rewards) {
		this.rewards = rewards;
	}

	public LinkedList<Item> getItems() {
		return items;
	}

	@Deprecated
	public void setItems(LinkedList<Item> items) {
		this.items = items;
	}

	public boolean isClear() {
		return isClear;
	}

	public void setIsClear(boolean isClear) {
		this.isClear = isClear;
	}

	public Vector2 getFreePosition() {
		return freePosition;
	}

	public void setFreePosition(Vector2 freePosition) {
		this.freePosition = freePosition;
	}
	
	
	
}
