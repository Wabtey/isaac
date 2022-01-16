package gameWorld;

import java.util.LinkedList;
import java.util.List;

import gameWorld.room.Room;
import gameWorld.room.roomPattern.RoomC1;
import gameWorld.room.roomPattern.RoomC2;
import gameWorld.room.specialsRoom.BossRoom;
import gameWorld.room.specialsRoom.Shop;
import gameWorld.room.specialsRoom.Spawn;
import gameobjects.Door;
import gameobjects.moving_entity.Hero;
import gameobjects.moving_entity.monsters.Monsters;
import libraries.Keybinding.SpecialKeys;
import libraries.StdDraw;
import libraries.Vector2;
import resources.Controls;
import resources.DoorInfos;
import resources.ImagePaths;
import resources.RoomInfos;
import test.Cardinal_Points;

public class GameWorld
{
	private Cardinal_Points neighbors;
	private Room currentRoom;
	private List<Door> doors;
	private Hero hero; 

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
		processKeysForMovement();
	}

	public boolean gameOver()
	{
		if(hero.getRedHeart()==0) {
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
			doors.add(new Door(DoorInfos.SUD,neighbors.getSouth()));
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
		
		if(StdDraw.isKeyPressed(Controls.beInvincible))
			hero.changeInvincibility();
	
		if(StdDraw.isKeyPressed(Controls.beSuperFast))
			hero.changeUltraSpeed();
		
		if(StdDraw.isKeyPressed(Controls.killEveryMonster))
			currentRoom.getMonsters().removeAll(currentRoom.getMonsters());
		
		if(StdDraw.isKeyPressed(Controls.bePowerfull))
			hero.changePowerfull();
		
		if(StdDraw.isKeyPressed(Controls.beRich))
			hero.setGold(hero.getGold()+10);
		
	}
	
	public Vector2 repositionHero(Vector2 door) {
		Vector2 temp = new Vector2(RoomInfos.POSITION_CENTER_OF_ROOM);
		if (door.equals(DoorInfos.NORTH)) {
			temp.setX(DoorInfos.SUD.getX());
			temp.setY(DoorInfos.SUD.getY());
			temp.addY(0.1);
		}
		if (door.equals(DoorInfos.SUD)) {
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
		return (currentRoom instanceof Spawn || currentRoom instanceof BossRoom ||currentRoom instanceof Shop );
	}
	
	public Room getCurrentRoom() {
		return this.currentRoom;
	}
	
}
