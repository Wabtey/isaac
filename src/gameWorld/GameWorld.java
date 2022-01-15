package gameWorld;

import java.util.LinkedList;
import java.util.List;

import gameWorld.room.Room;
import gameWorld.room.specialsRoom.Boss;
import gameWorld.room.specialsRoom.Shop;
import gameWorld.room.specialsRoom.Spawn;
import gameobjects.Door;
import gameobjects.moving_entity.Hero;
import libraries.Keybinding.SpecialKeys;
import libraries.StdDraw;
import libraries.Vector2;
import resources.Controls;
import resources.DoorInfos;
import resources.RoomInfos;
import test.Cardinal_Points;

public class GameWorld
{
	private Cardinal_Points neighbors;
	private Room currentRoom;
	private List<Door> doors;
	private Hero hero; 
	private int NumberOfRooms;

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
		return false;
	}

	public void updateGameObjects()
	{
		currentRoom.updateRoom();
		System.out.println(currentRoom.getClass());
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
		this.currentRoom = new Room(hero,doors);
	}
	
	public void changeTypeOfRoom(String type) {
		switch (type) {
		case "spawn" : currentRoom = new Spawn(hero, doors);break;
		case "boss" : currentRoom = new Boss(hero, doors);break;
		case "shop" : currentRoom = new Shop(hero, doors);break;
		default : currentRoom = new Room(hero,doors);
		}
	}

	public void drawGameObjects()
	{
		currentRoom.drawRoom();
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
		
		if (StdDraw.isKeyPressed(Controls.shoot))
			hero.shoot();
		
		if (StdDraw.isKeyPressed(Controls.up))
			hero.shootUP(SpecialKeys.UP); //param can be Controls.up or a simple String
		
		if (StdDraw.isKeyPressed(Controls.down))
			hero.shootUP(SpecialKeys.DOWN);
		
		if (StdDraw.isKeyPressed(Controls.left))
			hero.shootUP(SpecialKeys.LEFT);
		
		if (StdDraw.isKeyPressed(Controls.right))
			hero.shootUP(SpecialKeys.RIGHT);
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
	
	public int getNumberOfRooms() {
		return NumberOfRooms;
	}
	
	public void decreaseRoomsNumber() {
		if (NumberOfRooms>0)
			NumberOfRooms --;
	}
	
	public void setNeighbors(Cardinal_Points neighbors) {
		this.neighbors = neighbors;
	}
	
	public boolean isASpecialRoom() {
		return (currentRoom instanceof Spawn || currentRoom instanceof Boss ||currentRoom instanceof Shop );
	}
	
	public Room getCurrentRoom() {
		return this.currentRoom;
	}
	
}
