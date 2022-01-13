package test;

import gameWorld.GameWorld;

public class Neighbors {
	private GameWorld North;
	private GameWorld South;
	private GameWorld East;
	private GameWorld Weast;
	
	
	public Neighbors(GameWorld north, GameWorld south, GameWorld east, GameWorld weast) {
		North = north;
		South = south;
		East = east;
		Weast = weast;
	}
	
	public GameWorld getNorth() {
		return North;
	}
	public void setNorth(GameWorld north) {
		North = north;
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

}
