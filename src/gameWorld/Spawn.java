package gameWorld;

import java.util.ArrayList;

import gameobjects.Hero;
import libraries.StdDraw;
import libraries.Vector2;

public class Spawn extends Room {

	public Spawn(Hero hero) {
		super(hero);
		this.doors.add(new Door(new Vector2(0.5,0.9), new Monster(hero)));
	}
	
	
	public void drawRoom() {
		super.drawRoom();
		StdDraw.setPenColor(StdDraw.BLUE);
		StdDraw.filledSquare(0.5, 0.2, 0.05);
	}
	
}
