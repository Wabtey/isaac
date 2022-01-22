package gameWorld;

import java.util.LinkedList;
import java.util.List;

import gameWorld.room.Room;
import gameWorld.room.roomPattern.*;
import gameWorld.room.specialsRoom.*;
import gameobjects.Door;
import gameobjects.moving_entity.Hero;
import libraries.Keybinding.SpecialKeys;
import libraries.StdDraw;
import libraries.Vector2;
import resources.*;
import test.Cardinal_Points;

public class GameWorld
{
	private Cardinal_Points neighbors;
	private Room currentRoom;
	private List<Door> doors;
	private Hero hero; 
	
	private int tempo;

	// A world needs a hero
	public GameWorld(Hero hero)
	{
		this.hero = hero;
		this.currentRoom = null;
		this.neighbors= null;
		this.doors = null;
	}
	
	public void initalise() {
		createDoors();
		createRoom();
	}

	public void processUserInput()
	{
		if(tempo>0) //Prevent double activation of cheat code or any activable keys
			tempo--;
		processKeysForMovement();
	}

	public boolean gameOver()
	{
		if(hero.getRedHeart()<=0 && hero.getblueHeart()<=0) { //<= and not == cause of -1dmg when having only one half heart
			return true;
		}else 
			return false;

	}

	public void updateGameObjects()
	{
		currentRoom.updateRoom();

	}
	
	
	public GameWorld checkDoorGW() {
		Door temp =currentRoom.inDoor() ;
		if (temp!=null) {
			hero.setPosition(repositionHero(temp.getCoordonnees()));
			return temp.getNextRoom();
		}
		return null;
	}
	
	private void createDoors() {
		List <Door> doors = new LinkedList<Door>(); 
		if(neighbors.getNorth()!=null) {
			doors.add(new Door(DoorInfos.NORTH,neighbors.getNorth()));
		}
		if(neighbors.getSouth()!=null) {
			doors.add(new Door(DoorInfos.SOUTH,neighbors.getSouth()));
		}
		if(neighbors.getEast()!=null) {
			doors.add(new Door(DoorInfos.EAST,neighbors.getEast()));
		}
		if(neighbors.getWeast()!=null) {
			doors.add(new Door(DoorInfos.WEAST,neighbors.getWeast()));
		}
		this.doors = doors;
	}
	
	private void createRoom() {
		int wichRoom = (int) Math.round((Math.random()*2)*10)/10;
		switch (wichRoom){
		case 0:this.currentRoom = new RoomC1(hero,doors);break;
		case 1: this.currentRoom = new RoomC2(hero,doors); break;
		case 2: this.currentRoom = new RoomC1(hero,doors); break;
		}
		currentRoom.initialise();
	}
	
	public void changeTypeOfRoom(String type) {
		switch (type) {
		case "spawn" : currentRoom = new Spawn(hero, doors);break;
		case "boss" : currentRoom = new BossRoom(hero, doors);break;
		case "shop" : currentRoom = new Shop(hero, doors);break;
		default : currentRoom = new RoomC1(hero,doors);
		}
	}

	public void drawGameObjects()
	{
		
		currentRoom.drawRoom();
		if(gameOver()) //TODO MAKE A PROPER DEATH with item picked, the killer, etc
			StdDraw.picture(RoomInfos.POSITION_CENTER_OF_ROOM.getX(), RoomInfos.POSITION_CENTER_OF_ROOM.getY(), ImagePaths.LOSE_SCREEN);
	}

	//TODO if select is pressed show the map bigger
	
	private void processKeysForMovement()
	{
		if (StdDraw.isKeyPressed(Controls.goUp))
			hero.goUpNext();
		
		if (StdDraw.isKeyPressed(Controls.goDown))
			hero.goDownNext();
		
		if (StdDraw.isKeyPressed(Controls.goRight))
			hero.goRightNext();
		
		if (StdDraw.isKeyPressed(Controls.goLeft))
			hero.goLeftNext();
		
		if (StdDraw.isKeyPressed(Controls.up))
			hero.shoot(SpecialKeys.UP); //param can be Controls.up or a simple String
		
		if (StdDraw.isKeyPressed(Controls.down))
			hero.shoot(SpecialKeys.DOWN);
		
		if (StdDraw.isKeyPressed(Controls.left))
			hero.shoot(SpecialKeys.LEFT);
		
		if (StdDraw.isKeyPressed(Controls.right))
			hero.shoot(SpecialKeys.RIGHT);
		
		if(StdDraw.isKeyPressed(Controls.hud)) {
			if(this.tempo==0) {
				hero.changeHUD();
				this.tempo=5;
			}
		}
		
		if(StdDraw.isKeyPressed(Controls.beInvincible)) {
			if(this.tempo==0) {
				hero.changeInvincibility();
				this.tempo=5;
			}
		}
	
		if(StdDraw.isKeyPressed(Controls.beSuperFast)) {
			if(this.tempo==0) {
				hero.changeUltraSpeed();
				this.tempo=5;
			}
			
		}
		
		if(StdDraw.isKeyPressed(Controls.killEveryMonster))
			currentRoom.getMonsters().removeAll(currentRoom.getMonsters());
		
		if(StdDraw.isKeyPressed(Controls.bePowerfull)) {
			if(this.tempo==0) {
				hero.changePowerful();
				this.tempo=5;
			}
		}
		
		if(StdDraw.isKeyPressed(Controls.beRich))
			hero.setGold(hero.getGold()+10);
		
	}
	
	public Vector2 repositionHero(Vector2 door) {
		Vector2 temp = new Vector2(RoomInfos.POSITION_CENTER_OF_ROOM);
		if (door.equals(DoorInfos.NORTH)) {
			temp.setX(DoorInfos.SOUTH.getX());
			temp.setY(DoorInfos.SOUTH.getY());
			temp.addY(0.1);
		}
		if (door.equals(DoorInfos.SOUTH)) {
			temp.setX(DoorInfos.NORTH.getX());
			temp.setY(DoorInfos.NORTH.getY());
			temp.addY(-0.1);
		}
		if (door.equals(DoorInfos.EAST)) {
			temp.setX(DoorInfos.WEAST.getX());
			temp.setY(DoorInfos.WEAST.getY());
			temp.addX(+0.1);
		}
		if (door.equals(DoorInfos.WEAST)) {
			temp.setX(DoorInfos.EAST.getX());
			temp.setY(DoorInfos.EAST.getY());
			temp.addX(-0.1);
		}
		return temp;
	}
	public void setCurrentRoom(Room room) {
		this.currentRoom = room;
	}


	public Cardinal_Points getNeighbors() {
		return neighbors;
	}
	
	public void setNeighbors(Cardinal_Points neighbors) {
		this.neighbors = neighbors;
	}
	
	public boolean isASpecialRoom() {
		return (currentRoom instanceof Spawn || currentRoom instanceof BossRoom ||currentRoom instanceof Shop || currentRoom instanceof ItemRoom);
	}
	
	public Room getCurrentRoom() {
		return this.currentRoom;
	}
	
}
