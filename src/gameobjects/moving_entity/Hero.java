package gameobjects.moving_entity;

import libraries.StdDraw;
import libraries.Vector2;
import resources.ImagePaths;

public class Hero extends Living_Creature
{

	
	public Hero(Vector2 position, Vector2 size, double speed, int hitPoint, int damage, double tearRate, double shootSpeed, String imagePath) 
	{
		super(position, size, speed, hitPoint, damage, tearRate, shootSpeed, imagePath);

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
		//EMPTY HEART
		StdDraw.picture(0.1, 0.9, ImagePaths.EMPTY_HEART_HUD, 0.05, 0.05, 0);
	}



}
	
	