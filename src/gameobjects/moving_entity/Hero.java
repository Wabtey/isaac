package gameobjects.moving_entity;

import java.util.ArrayList;

import libraries.StdDraw;
import libraries.Vector2;
import resources.CreaturesInfos;

public class Hero extends Living_Creature
{
	private ArrayList<Projectile> tears;
	
	public Hero(Vector2 position, Vector2 size, double speed, int hitPoint, int damage, double tearRate, String imagePath) 
	{
		super(position, size, speed, hitPoint, damage, tearRate, imagePath);
		this.tears = new ArrayList<Projectile> (10);
	}
	
	public void updateGameObject() {
		move();
		if (getInvincibilityFrames()>0)
			decreaseInvincibilityFrames(); ;
		for (Projectile tear:tears) {
			tear.move();
		}
		isReloading();
	}
	
	public void shoot() {
		if(getReloadTime()==0) {
			tears.add(new Projectile(getPosition(),getOrientation(),new Vector2(getSize().getX()/2,getSize().getY()/2),getDamage(), getSpeed(),getImagePath()));
			reload(CreaturesInfos.convertTearRateToTicks(getTearRate()));
		}
	}
	

	public void removeProjectile(ArrayList<Projectile> toRemove) {
		deleteProjectile(toRemove);
	}
	
	private void deleteProjectile(ArrayList<Projectile> toRemove) {
		if(tears.containsAll(toRemove))
			tears.removeAll(toRemove);
	}
	
	public void drawGameObject()
	{
		StdDraw.picture(getPosition().getX(), getPosition().getY(), getImagePath(), getSize().getX(), getSize().getY(),
				0);
	}
	
	public void updateProjectile(ArrayList<Projectile> projectileUpdated) {
		tears = projectileUpdated;
	}
	
	public ArrayList<Projectile> getProjectile() {
		return tears;
	}
		


}
	
	