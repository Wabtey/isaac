package gameWorld;

import java.util.ArrayList;

import gameobjects.Hero;
import libraries.StdDraw;
import libraries.Vector2;

public class Monster extends Room {

	public Monster(Hero hero) {
		super(hero);
		this.doors.add(new Door(new Vector2(0.5,0.9), new Boss(hero)));
		this.doors.add(new Door(new Vector2(0.9,0.5), new Shop(hero)));
	}
	
	public void drawRoom() {
	super.drawRoom();
	StdDraw.setPenColor(StdDraw.BLUE);
	StdDraw.filledCircle(0.5, 0.5, 0.02);
	}
	
	
}
