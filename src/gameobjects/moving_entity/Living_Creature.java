package gameobjects.moving_entity;

import libraries.Vector2;

public abstract class Living_Creature {
	private Vector2 position;
	private Vector2 size;
	private double speed;
	private Vector2 direction;
	private int hitPoint;
	private int damage;
	private double tearRate;
	private String imagePath;
	private int reloadTime;
	private Vector2 orientation;
	private int invincibility; //freezing time of monsters and hero invincibility time are differents
	
	public Living_Creature(Vector2 position, Vector2 size, double speed, int hitPoint
			,int damage, double tearRate, String imagePath) {
		this.position = position;
		this.size = size;
		this.speed = speed;
		this.direction = new Vector2();
		this.hitPoint = 120;
		this.damage = damage;
		this.tearRate = tearRate;
		this.imagePath = imagePath;
		this.reloadTime = 0;
		this.orientation = new Vector2(0.1,0);
		this.invincibility = 0;
	}
	

	
	protected void move()
	{
		Vector2 normalizedDirection = getNormalizedDirection();
		Vector2 positionAfterMoving = getPosition().addVector(normalizedDirection);
		setPosition(positionAfterMoving);
		this.setDirection(new Vector2());
	}
	

	/*
	 * Moving from key inputs. Direction vector is later normalised.
	 */
	public void goUpNext()
	{
		getDirection().addY(1);
		orientation = new Vector2(0,0.1);
	}

	public void goDownNext()
	{
		getDirection().addY(-1);
		orientation = new Vector2(0,-0.1);
	}

	public void goLeftNext()
	{
		getDirection().addX(-1);
		orientation = new Vector2(-0.1,0);
	}

	public void goRightNext()
	{
		getDirection().addX(1);
		orientation = new Vector2(0.1,0);
	}

	public Vector2 getNormalizedDirection()
	{
		Vector2 normalizedVector = new Vector2(direction);
		normalizedVector.euclidianNormalize(speed);
		return normalizedVector;
	}

	public Vector2 getPosition() {
		return position;
	}

	public void setPosition(Vector2 position) {
		this.position = position;
	}

	public Vector2 getSize() {
		return size;
	}

	public void setSize(Vector2 size) {
		this.size = size;
	}

	public double getSpeed() {
		return speed;
	}

	public void setSpeed(double speed) {
		this.speed = speed;
	}

	public Vector2 getDirection() {
		return direction;
	}

	public void setDirection(Vector2 direction) {
		this.direction = direction;
	}

	public int getHitPoint() {
		return hitPoint;
	}

	public void setHitPoint(int hitPoint) {
		this.hitPoint = hitPoint;
	}

	public int getDamage() {
		return damage;
	}

	public void setDamage(int damage) {
		this.damage = damage;
	}

	public String getImagePath() {
		return imagePath;
	}

	public void setImagePath(String imagePath) {
		this.imagePath = imagePath;
	}
	
	public void reload(int timeToReload) {//on peut prendre la meme valeur pour tous mais je nous laisse la liberter d'en mettre des != en fonction des personages
		this.reloadTime += timeToReload;
	}
	
	public void isReloading() {
		if (reloadTime>0) 
		this.reloadTime--;
	}
	
	public int getReloadTime() {
		return reloadTime;
	}
	
	public Vector2 getOrientation() {
		return orientation;
	}
	
	public void getHitted(int damage) {
		if (invincibility==0) {
			if (damage>=hitPoint)
				hitPoint = 0;
			else 
				hitPoint -= damage;
		}
	}
	
	public void addInvincibilityFrames(int InvincibilityFrames) {
		if (invincibility==0)
			invincibility += (InvincibilityFrames);
	}
	
	public void decreaseInvincibilityFrames() {
		invincibility -= 1;
	}
	
	public int getInvincibilityFrames() {
		return invincibility;
	}
}
