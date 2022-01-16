package gameobjects.moving_entity;


import java.util.ArrayList;
import libraries.Keybinding.SpecialKeys;
import libraries.Vector2;
import resources.ImagePaths;

public abstract class Living_Creature {
	private Vector2 position;
	private Vector2 size;
	private Vector2 direction;

	private double redHeart;

	private double speed;
	private double tearRate;
	private double damage;
	private double range;
	private double shootSpeed;
	
	private String imagePath;
	private int reloadTime;
	private Vector2 orientation;
	private Vector2 shootOrientation;
	private int invincibility; //freezing time of monsters and hero invincibility time are differents
	
	//Be sure to safezy the projectiles owner from his own projectiles if adding enemy's proj
	private ArrayList<Projectile> tears;
	
	//Maybe create some less big constructor
	public Living_Creature(Vector2 position, Vector2 size, double redHeart,
						   double speed, double tearRate, double damage, double range, double shootSpeed,
						   String imagePath)
	{
		this.position = position;
		this.direction = new Vector2();
		this.orientation = new Vector2(0.1,0);
		this.size = size;
		
		this.redHeart = redHeart;
		this.speed = speed;
		this.tearRate = tearRate;
		this.damage = damage;
		this.range = range;
		this.shootSpeed = shootSpeed;
		
		this.imagePath = imagePath;
		
		this.reloadTime = 0;
		this.invincibility = 0;
		
			//TODO Change this magic number to something coherent
		this.tears = new ArrayList<Projectile> (10);
		this.shootOrientation = new Vector2();

	}
	
	/**
	 * Make the living entity process one step
	 */
	public void updateGameObject()
	{
		for (Projectile tear:tears) {
			tear.move();
		}
		isReloading();
	}
	
	protected void move()
	{
		Vector2 normalizedDirection = getNormalizedDirection();
		Vector2 positionAfterMoving = getPosition().addVector(normalizedDirection);
		setPosition(positionAfterMoving);
		this.setDirection(new Vector2());
	}
	

//--COMBAT CODE-------------------------------------------------
	
	public void shoot() {
		if(getReloadTime()==0) {
			tears.add(new Projectile(getPosition(),getOrientation(),new Vector2(getSize().getX()/2,getSize().getY()/2),getDamage(), getShootSpeed(),ImagePaths.TEAR));//TODO enlever la valeur magique
			double ticksToWait = 40/getTearRate();
			reload((int)ticksToWait);
			//CreaturesInfos.convertTearRateToTicks(getTearRate())

		}
	}
	
	public void shoot(SpecialKeys orientation)
	{
		//TODO Concider diagonals
		if(getReloadTime()==0) {
			if(orientation == SpecialKeys.UP)
				shootOrientation = new Vector2(0.0, 0.1); 
			if(orientation == SpecialKeys.DOWN)
				shootOrientation = new Vector2(0.0, -0.1);
			if(orientation == SpecialKeys.LEFT)
				shootOrientation = new Vector2(-0.1, 0.0);
			if(orientation == SpecialKeys.RIGHT)
				shootOrientation = new Vector2(0.1, 0.0);
			
			tears.add(new Projectile(getPosition(),shootOrientation,new Vector2(getSize().getX()/2,getSize().getY()/2),getDamage(), getShootSpeed(), ImagePaths.TEAR));//TODO enlever la valeur magique
			double ticksToWait = 40/getTearRate();
			reload((int)ticksToWait);
			//CreaturesInfos.convertTearRateToTicks(getTearRate())
		}
	}
	
	//ITEM TEAR EFFECT : antigravitationnal tears
//	public void shootUP(SpecialKeys orientation)
//	{
//		if(getReloadTime()==0) {
//			if(orientation == SpecialKeys.UP)
//				shootOrientation = new Vector2(0.1, 0); 
//			if(orientation == SpecialKeys.DOWN)
//				shootOrientation = new Vector2(-0.1, 0);
//			if(orientation == SpecialKeys.LEFT)
//				shootOrientation = new Vector2(0, -0.1);
//			if(orientation == SpecialKeys.UP)
//				shootOrientation = new Vector2(0, 0.1);
//			
//			tears.add(new Projectile(getPosition(),shootOrientation,new Vector2(getSize().getX()/2,getSize().getY()/2),getDamage(), getShootSpeed(), getImagePath()));
//			double ticksToWait = 40/getTearRate();
//			reload((int)ticksToWait);
//			//CreaturesInfos.convertTearRateToTicks(getTearRate())
//		}
//	}
	
	
	public void removeProjectile(ArrayList<Projectile> toRemove) {
		deleteProjectile(toRemove);
	}
	
	private void deleteProjectile(ArrayList<Projectile> toRemove) {
		if(tears.containsAll(toRemove))
			tears.removeAll(toRemove);
	}
	
	public void updateProjectile(ArrayList<Projectile> projectileUpdated) {
		tears = projectileUpdated;
	}
	
	public ArrayList<Projectile> getProjectile() {
		return tears;
	}
		
	
//--HITTED---------------------------------------------------------------
	
	public void getHitted(double damage) {
		
	}
	
	public void addInvincibilityFrames(int InvincibilityFrames) {
		if (invincibility==0)
			invincibility += (InvincibilityFrames);
	}
	
	public void decreaseInvincibilityFrames() {
		invincibility -= 1;
	}
	
//--RELOAD---------------------------------------------------------------
	
	public void reload(int timeToReload) {//on peut prendre la meme valeur pour tous mais je nous laisse la liberter d'en mettre des != en fonction des personages
		this.reloadTime += timeToReload;
	}
	
	public void isReloading() {
		if (reloadTime>0) //Attention au sortie (si on retire plus de ticks, on pert du tearRate
		this.reloadTime--;
	}
	
//--CONTROLS-------------------------------------------------------------

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

	private Vector2 getNormalizedDirection()
	{
		Vector2 normalizedVector = new Vector2(direction);
		normalizedVector.euclidianNormalize(speed);
		return normalizedVector;
	}
	
//--GETTERS/SETTERS-------------------------------------------------------

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


	public Vector2 getDirection() {
		return direction;
	}

	public void setDirection(Vector2 direction) {
		this.direction = direction;
	}
	
	public Vector2 getOrientation() {
		return orientation;
	}
	
//--STATS--------------------------------

	public double getRedHeart() {
		return redHeart;
	}

	public void setRedHeart(double redHeart) {
		this.redHeart = redHeart;
	}
	
	public double getSpeed() {
		return speed;
	}

	public void setSpeed(double speed) {
		this.speed = speed;
	}
	
	public double getTearRate() {
		return tearRate;
	}


	public void setTearRate(double tearRate) {
		this.tearRate = tearRate;
	}
	
	public double getDamage() {
		return damage;
	}

	public void setDamage(double damage) {
		this.damage = damage;
	}

	public double getRange() {
		return range;
	}

	public void setRange(double range) {
		this.range = range;
	}

	public double getShootSpeed() {
		return shootSpeed;
	}
	
	public void setShootSpeed(double shootSpeed) {
		this.shootSpeed = shootSpeed;
	}
	
	public String getImagePath() {
		return imagePath;
	}

	public void setImagePath(String imagePath) {
		this.imagePath = imagePath;
	}
	
	public int getReloadTime() {
		return reloadTime;
	}
	
	public int getInvincibilityFrames() {
		return invincibility;
	}
}
