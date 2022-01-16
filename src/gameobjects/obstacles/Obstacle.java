package gameobjects.obstacles;

import libraries.Vector2;

public class Obstacle {
	private Vector2 position;
	private double width;
	private double height;
	
	public Obstacle(Vector2 position, double width, double height) {
		this.position = position;
		this.width = width;
		this.height = height;
	}

	public boolean inObstacle(Vector2 positioncheck) {
		if (positioncheck.getX() > (position.getX()-width) && positioncheck.getX() < (position.getX()+width) &&
			positioncheck.getY() > (position.getY()-height) && positioncheck.getY() < (position.getY()+height)){
				return true;
			}
		return false;
	}
	
	public void drawGameObject() {
		
	}
	
	
	//GETTERS SETTERS
	public Vector2 getPosition() {
		return position;
	}

	public void setPosition(Vector2 position) {
		this.position = position;
	}

	public double getWidth() {
		return width;
	}

	public void setWidth(double width) {
		this.width = width;
	}

	public double getHeight() {
		return height;
	}

	public void setHeight(double height) {
		this.height = height;
	}
	
	
	
	
}
