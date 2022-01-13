package test;

import gameWorld.GameWorld;

public class Cardinal_Points {
	private GameWorld north;
	private GameWorld south;
	private GameWorld east;
	private GameWorld weast;
	
	public Cardinal_Points() {
		this.north = this.south = this.east = this.weast = null;
	}
	
	public Cardinal_Points(GameWorld north, GameWorld south, GameWorld east, GameWorld weast) {
		this.north = north;
		this.south = south;
		this.east = east;
		this.weast = weast;
	}
	
	public boolean isEmpty() {
		return (this.north == null && this.south == null && this.east == null && this.weast == null);
	}
	public GameWorld getNorth() {
		return north;
	}
	public void setNorth(GameWorld north) {
		this.north = north;
	}
	public GameWorld getSouth() {
		return south;
	}
	public void setSouth(GameWorld south) {
		this.south = south;
	}
	public GameWorld getEast() {
		return east;
	}
	public void setEast(GameWorld east) {
		this.east = east;
	}
	public GameWorld getWeast() {
		return weast;
	}
	public void setWeast(GameWorld weast) {
		this.weast = weast;
	}
	
	

}
