package gameWorld;

import gameobjects.moving_entity.Hero;
import libraries.StdDraw;
import libraries.Vector2;
import resources.Controls;
import resources.RoomInfos;

public class GameWorld
{
	private Room[] rooms;
	private Room currentRoom;
	private Hero hero;

	// A world needs a hero
	public GameWorld(Hero hero)
	{
		this.hero = hero;
		this.currentRoom = new Spawn(hero);
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
		else if(hero.getHitPoint()==0) {
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
		{
			hero.goUpNext();
		}
		if (StdDraw.isKeyPressed(Controls.goDown))
		{
			hero.goDownNext();
		}
		if (StdDraw.isKeyPressed(Controls.goRight))
		{
			hero.goRightNext();
		}
		if (StdDraw.isKeyPressed(Controls.goLeft))
		{
			hero.goLeftNext();
		}
		if (StdDraw.isKeyPressed(Controls.shoot)) {
			hero.shoot();
		}
	}
	
	public void setCurrentRoom(Room room) {
		this.currentRoom = room;
	}
}
