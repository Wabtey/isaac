package gameobjects.moving_entity.monsters;

import javax.swing.text.Position;

import gameobjects.moving_entity.Hero;
import gameobjects.moving_entity.Living_Creature;
import gameobjects.obstacles.Obstacle;
import libraries.StdDraw;
import libraries.Vector2;

public abstract class Monsters extends Living_Creature {
	private Vector2 destination;
	private int freeze; //freezing time and hero invincibility time are different
	
	public Monsters(Vector2 position, Vector2 size, Vector2 destination,
			double hp, double speed, double tearRate, double damage, double range, double shootSpeed,
			String imagePath) //parameter delete : Vector2 direction
	{
		super(position, size, hp, speed, tearRate, damage, range, shootSpeed, imagePath);
		this.destination = destination;
		this.freeze = 5; //Tempo pour rentrer dans une salle
		
	}
	
	public void updateGameObject(Hero hero)
	{
		super.updateGameObject();
	}
	
	public void drawGameObject()
	{
		//Monster Spite
		StdDraw.picture(getPosition().getX(), getPosition().getY(), getImagePath(),
						getSize().getX(), getSize().getY(), 0);
		
	}
	
	
	// donne des coordonée au hasard 
	private Vector2 chooseRandomPoint() {
		double x = (Math.random()*(0.6))+0.2;//TODO changer les valeurs magiques
		double y = (Math.random()*(0.6))+0.2;	
		double rpx = (double)Math.round(x*10)/10;
		double rpy = (double) Math.round(y*10)/10;
		while (rpx==0||rpy==0||rpx==1|rpy==1 ||(rpx==0.5&&rpy==0.9)) {
			x = (Math.random()+0.1)*0.9;
			y = (Math.random()+0.1)*0.9;	
			rpx = (double)Math.round(x*10)/10;
			rpy = (double) Math.round(y*10)/10;
		}
		return new Vector2(rpx, rpy);
	}
		
	abstract void moveTo(Vector2 cible);
<<<<<<< HEAD
=======
		
>>>>>>> branch 'master' of https://github.com/WaBtey/isaac.git

	public void goUpNext()
	{
		getDirection().addY(1);
	}

	public void goDownNext()
	{
		getDirection().addY(-1);
	}

	public void goLeftNext()
	{
		getDirection().addX(-1);
	}

	public void goRightNext()
	{
		getDirection().addX(1);
	}
	
	public void addFreezeTime(int freezeTime) {
		if (freezeTime == 0)
			freeze += freezeTime;
	}
	
	public void decreaseFreezeTime() {
		this.freeze --; 
	}
	

	private Vector2 getNormalizedDirection()
	{
		Vector2 normalizedVector = new Vector2(getDirection());
		normalizedVector.euclidianNormalize(getSpeed());
		return normalizedVector;
	}

	public Vector2 getDestination() {
		return destination;
	}

	public void setDestination(Vector2 destination) {
		this.destination = destination;
	}
	
	public int getFreezeTime(){
		return freeze;
	}
	

	
	
}
