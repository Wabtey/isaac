package gameWorld;

import gameobjects.moving_entity.Hero;
import libraries.Keybinding.SpecialKeys;
import libraries.StdDraw;
import libraries.Vector2;
import resources.Controls;
import resources.RoomInfos;

public class GameWorld
{
	private GameWorld Nord;
	private GameWorld South;
	private GameWorld East;
	private GameWorld Weast;
	private Room currentRoom;
	private Hero hero;
	private int NumberOfRooms;

	// A world needs a hero
	public GameWorld(Hero hero)
	{
		this.hero = hero;
		this.currentRoom = new Spawn(hero);
		this.Nord = this.East = this.Weast = this.South = null;
	}
	

	public void processUserInput()
	{
		processKeysForMovement();
	}

	public boolean gameOver()
	{
		double hx = (double)Math.round(hero.getPosition().getX()*10)/10;
		double hy = (double)Math.round(hero.getPosition().getY()*10)/10;
		if (currentRoom.getClass().getName() == "gameWorld.Boss" && hx==0.5 && hy==0.9) {
			System.out.println("game over");
			return true;
		}
		else if(hero.getredHeart()==0) {
			System.out.println("Vous �tes mort.");
			return true;
		}
		else {
			return false;
		}
	}

	public void updateGameObjects()
	{
		currentRoom.updateRoom();
		checkDoor();
	}
	//TODO a reparer (pourquoi j'ai fais ca?)
	private void checkDoor() {
		if (currentRoom.inDoor()!=null) {
			currentRoom = currentRoom.inDoor().getNextRoom();
			hero.setPosition(new Vector2(RoomInfos.POSITION_CENTER_OF_ROOM.getX(),RoomInfos.WALL_DOWN[0]));
		}
	}

	public void drawGameObjects()
	{
		currentRoom.drawRoom();
	}
	
	/*public void IsAWall() {
		double hx = (double)Math.round(hero.getPosition().getX()*10)/10;
		double hy = (double)Math.round(hero.getPosition().getY()*10)/10;	
		for (Door door : currentRoom.getDoors()) {
			double dx = (double)Math.round(door.getCoordonnees().getX()*10)/10;
			double dy = (double)Math.round(door.getCoordonnees().getY()*10)/10;
			if (hx == dx && hy == dy) {
				this.currentRoom = door.getNextRoom();
				hero.setPosition(new Vector2(0.5,0.1));
			}
		}
	}*/

	/*
	 * Keys processing
	 */

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


	public GameWorld getNord() {
		return Nord;
	}


	public void setNord(GameWorld nord) {
		Nord = nord;
	}


	public GameWorld getSouth() {
		return South;
	}


	public void setSouth(GameWorld south) {
		South = south;
	}


	public GameWorld getEast() {
		return East;
	}


	public void setEast(GameWorld east) {
		East = east;
	}


	public GameWorld getWeast() {
		return Weast;
	}


	public void setWeast(GameWorld weast) {
		Weast = weast;
	}
	
	public int getNumberOfRooms() {
		return NumberOfRooms;
	}
	
	public void decreaseRoomsNumber() {
		if (NumberOfRooms>0)
			NumberOfRooms --;
	}
}
