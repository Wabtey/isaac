package gameWorld.Caves;

import gameobjects.Door;
import gameobjects.moving_entity.Hero;
import libraries.StdDraw;
import libraries.Vector2;

public class Spawn extends Room {

	public Spawn(Hero hero) {
		super(hero);
		this.getDoors().add(new Door(new Vector2(0.5,0.8), new RoomMonster(hero)));
		getDoors().add(new Door(new Vector2(0.5,0.9), new RoomMonster(hero)));
		
	}
	
	
	public void drawRoom() {
		super.drawRoom();
		StdDraw.setPenColor(StdDraw.BLUE);
		StdDraw.filledSquare(0.5, 0.2, 0.05);
	}
	
}
