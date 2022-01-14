package gameWorld;

import java.util.LinkedList;
import java.util.List;

import gameWorld.room.Room;
import gameWorld.room.specialsRoom.Spawn;
import gameobjects.Door;
import gameobjects.moving_entity.Hero;
import libraries.Keybinding.SpecialKeys;
import libraries.StdDraw;
import libraries.Vector2;
import resources.Controls;
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
		for (int i = 0; i<=doors.size()-1 ;i++) {
			System.out.println(doors.get(i).getCoordonnees() + " : " + doors.get(i).getNextRoom());
		}
		System.out.println("coter room:");
		for (int i = 0; i<=currentRoom.getDoors().size()-1 ;i++) {
			System.out.println(currentRoom.getDoors().get(i).getCoordonnees() + " : " + currentRoom.getDoors().get(i).getNextRoom());
		}
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
	}
	
	
	public GameWorld checkDoor() {
		if (currentRoom.inDoor()!=null) {
			hero.setPosition(new Vector2(RoomInfos.POSITION_CENTER_OF_ROOM.getX(),RoomInfos.WALL_DOWN[0]));
			return currentRoom.inDoor().getNextRoom();
		}
		return null;
	}
	
	private void createDoors() {
		List <Door> doors = new LinkedList<Door>(); 
		if(neighbors.getNorth()!=null) {
			doors.add(new Door(new Vector2(0.5,0.8),neighbors.getNorth()));
		}
		if(neighbors.getSouth()!=null) {
			doors.add(new Door(new Vector2(0.5,0.3),neighbors.getSouth()));
		}
		if(neighbors.getEast()!=null) {
			doors.add(new Door(new Vector2(0.8,0.5),neighbors.getEast()));
		}
		if(neighbors.getWeast()!=null) {
			doors.add(new Door(new Vector2(0.2,0.5),neighbors.getWeast()));
		}
		this.doors = doors;
	}
	
	private void createRoom() {
		this.currentRoom = new Room(hero,doors);
	}

	public void drawGameObjects()
	{
		currentRoom.drawRoom();
	}

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
	
}
