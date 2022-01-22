package gameobjects.obstacles;

import libraries.Vector2;

public class Machine {

	/*
	 * Machine
	 *      DON 
	 *           SANG
	 *           ARGENT
	 *      GAMBLE
	 *           VOYANT
	 *           CASINO
	 *      REROLL
	 */
	private Vector2 position;
	private Vector2 size;
	
	public Machine(Vector2 position, Vector2 size) {
		this.position = position;
		this.size = size;
	}
	
	public void drawGameObject() {
		//TODO picture of Machine and build collision to add money in it
	}
	
	
}
