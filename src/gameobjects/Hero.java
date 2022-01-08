package gameobjects;

import java.util.ArrayList;

import libraries.StdDraw;
import libraries.Vector2;

public class Hero extends Personnage
{
	private ArrayList<Projectile> tears;
	
	public Hero(Vector2 position, Vector2 size, double speed, int hitPoint, int damage, String imagePath) 
	{
		super(position, size, speed, hitPoint, damage, imagePath);
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
			tears.add(new Projectile(getPosition(),getOrientation(),new Vector2(getSize().getX()/2,getSize().getY()/2),getSpeed(),getDamage(),"images/tear.png"));
			reload(40);
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
	
	