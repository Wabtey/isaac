package gameobjects.obstacles.collisionable;

import gameobjects.obstacles.Obstacle;
import libraries.StdDraw;
import libraries.Vector2;
import resources.ImagePaths;

public class Rock extends Obstacle {

	public Rock(Vector2 position, double width, double height) {
		super(position, width, height);
	}


	@Override
	public void drawGameObject() {
		StdDraw.picture(getPosition().getX(), getPosition().getY(),ImagePaths.ROCK, getWidth(), getHeight());
	}
 
}
