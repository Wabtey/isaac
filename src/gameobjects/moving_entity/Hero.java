package gameobjects.moving_entity;

import libraries.StdDraw;
import libraries.Vector2;
import resources.ImagePaths;

public class Hero extends Living_Creature
{
	private int heartContainer;
	private double blueHeart;
	private double luck;
	private double devilDeal;	//chance to spawn a extra room after defeting the boss
	private double angelRoom;	//between 0.00 and 100.00

	
	public Hero(Vector2 position, Vector2 size,
			    double redHeart, double blueHeart, 
			    double speed, double damage, double tearRate, double range, double shootSpeed,
			    String imagePath) 
	{
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
	
		//Red Heart
		for(int i= 0; i<getheartContainer(); i++) {
			
			double pos;
			if(i==0)
				pos = 0.1;
			else
				pos = 0.1+0.05*(i);
			
			StdDraw.picture(pos, 0.9, ImagePaths.EMPTY_HEART_HUD, 0.05, 0.05, 0);
			if(getRedHeart()-i>0)
				if(getRedHeart()-i==0.5)
					StdDraw.picture(pos, 0.9, ImagePaths.HALF_HEART_HUD, 0.05, 0.05, 0);
				else
					StdDraw.picture(pos, 0.9, ImagePaths.HEART_HUD, 0.05, 0.05, 0);
				
		}
		
		//Blue/Black Heart
		for(int i=0; i<getblueHeart(); i++) {
			double posR = 0.1+0.05*(getheartContainer());
			double posB;
			posB = posR+0.05*(i);
			//StdDraw.picture(posB, 0.9, ImagePaths.BLUE_HEART, 0.05, 0.05, 0);
			
		}
		
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
	
	