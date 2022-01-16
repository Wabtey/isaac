package gameobjects.moving_entity;

import libraries.StdDraw;
import libraries.Vector2;

public class Projectile {
	private Vector2 proj_position;
	private Vector2 proj_direction;
	private Vector2 proj_size;
	private double proj_speed;
	private double proj_degat;
	private String proj_imagePath;
	private boolean isAHeroTear;
	
	public Projectile(Vector2 proj_position ,Vector2 proj_direction ,Vector2 proj_size, double proj_degat, double proj_speed,
			String proj_imagePath, boolean isAHeroTear) {
		this.proj_position = proj_position;
		this.proj_size = proj_size;
		this.proj_speed = proj_speed;
		this.proj_direction = proj_direction;
		this.proj_degat = proj_degat;
		this.proj_imagePath = proj_imagePath;
		this.isAHeroTear = isAHeroTear;
	}
	
	
	protected void move()
	{
		Vector2 normalizedDirection = getNormalizedDirection();
		Vector2 positionAfterMoving = getProjPosition().addVector(normalizedDirection);
		setProjPosition(positionAfterMoving);
	}
	
	
	public Vector2 getNormalizedDirection()
	{
		Vector2 normalizedVector = new Vector2(proj_direction);
		normalizedVector.euclidianNormalize(proj_speed);
		return normalizedVector;
	}
	
	public void drawGameObject()
	{
		StdDraw.picture(getProjPosition().getX(),
						getProjPosition().getY(),
						getProjImagePath(),
						getProjSize().getX(),
						getProjSize().getY());
	}

	
	

	public Vector2 getProjPosition() {
		return proj_position;
	}
	
	public void setProjPosition(Vector2 proj_position) {
		this.proj_position = proj_position;
	}
	
	public Vector2 getProjDirection() {
		return proj_direction;
	}
	
	public void setProjDirection(Vector2 proj_direction) {
		this.proj_direction = proj_direction;
	}
	
	public Vector2 getProjSize() {
		return proj_size;
	}

	public double getProjSpeed() {
		return proj_speed;
	}

	public double getProjDegat() {
		return proj_degat;
	}
	
	public String getProjImagePath() {
		return proj_imagePath;
	}
	
	public boolean getIsAHeroTear() {
		return isAHeroTear;
	}
		
	
}
