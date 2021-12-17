package gameWorld;

import gameobjects.Hero;
import libraries.StdDraw;
import libraries.Vector2;
import resources.Controls;

public class GameWorld
{
	private Room currentRoom;
	private Hero hero;

	// A world needs a hero
	public GameWorld(Hero hero)
	{
		this.hero = hero;
		currentRoom = new Spawn(hero);
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
		} else {
			return false;
		}
	}

	public void updateGameObjects()
	{
		System.out.println(currentRoom.getClass().getName() == "gameWorld.Boss");
		System.out.println(hero.getPosition());
		nextDoor();
		currentRoom.updateRoom();
	}

	public void drawGameObjects()
	{
		currentRoom.drawRoom();
	}
	
	public void nextDoor() {
		double hx = (double)Math.round(hero.getPosition().getX()*10)/10;
		double hy = (double)Math.round(hero.getPosition().getY()*10)/10;	
		for (Door door : currentRoom.getDoor()) {
			double dx = (double)Math.round(door.getCoordonnees().getX()*10)/10;
			double dy = (double)Math.round(door.getCoordonnees().getY()*10)/10;
			if (hx == dx && hy == dy) {
				this.currentRoom = door.getNextRoom();
				hero.setPosition(new Vector2(0.5,0.1));
			}
		}
	}

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
	}
}
