package gameobjects.moving_entity;

import libraries.StdDraw;
import libraries.Vector2;

public class Hero extends Living_Creature
{

	
	public Hero(Vector2 position, Vector2 size, double speed, int hitPoint, int damage, double tearRate, String imagePath) 
	{
		super(position, size, speed, hitPoint, damage, tearRate, imagePath);

	}
	
	public void updateGameObject() {
		super.updateGameObject();
		move();
		if (getInvincibilityFrames()>0)
			decreaseInvincibilityFrames(); ;
	}
	



	
	public void drawGameObject()
	{
		StdDraw.picture(getPosition().getX(), getPosition().getY(), getImagePath(), getSize().getX(), getSize().getY(),
				0);
	}



}
	
	