package gameobjects.moving_entity;

import libraries.StdDraw;
import libraries.Vector2;
import resources.ImagePaths;

public class Hero extends Living_Creature
{
	private int heartContainer;
	private double blueHeart;
	private double luck;
	private double devilDeal;
	private double angelRoom;

	
	public Hero(Vector2 position, Vector2 size,
			    double redHeart, double blueHeart, 
			    double speed, double damage, double tearRate, double range, double shootSpeed,
			    String imagePath) 
	{
		//using the first constructor (for hero)
		super(position, size, redHeart, speed, tearRate, damage, range, shootSpeed, imagePath);
		this.heartContainer = (int)redHeart;
		this.blueHeart = blueHeart;

	}
	
	public void updateGameObject() {
		super.updateGameObject();
		move();
		if (getInvincibilityFrames()>0)
			decreaseInvincibilityFrames(); ;
	}
	



	
	public void drawGameObject()
	{
		//ISAAC Spite
		StdDraw.picture(getPosition().getX(), getPosition().getY(), getImagePath(),
						getSize().getX(), getSize().getY(), 0);
		drawHUD();
		
	}
	
	//TODO if select is pressed show the map bigger
	public void drawHUD() {
		//Health Meter
		//EMPTY HEART
		StdDraw.picture(0.1, 0.9, ImagePaths.EMPTY_HEART_HUD, 0.05, 0.05, 0);
	}

//--GETTER/SETTER-----------------------------------------
	
	public int getheartContainer() {
		return heartContainer;
	}
	public void setheartContainer(int heartContainer) {
		this.heartContainer = heartContainer;
	}
	public double getblueHeart() {
		return blueHeart;
	}
	public void setblueHeart(double blueHeart) {
		this.blueHeart = blueHeart;
	}

	public double getLuck() {
		return luck;
	}

	public void setLuck(double luck) {
		this.luck = luck;
	}

	public double getDevilDeal() {
		return devilDeal;
	}

	public void setDevilDeal(double devilDeal) {
		this.devilDeal = devilDeal;
	}

	public double getAngelRoom() {
		return angelRoom;
	}

	public void setAngelRoom(double angelRoom) {
		this.angelRoom = angelRoom;
	}

}
	
	