package gameobjects.moving_entity.monsters;

import gameobjects.moving_entity.Hero;
import libraries.Vector2;
import resources.CreaturesInfos;
import resources.ImagePaths;
import resources.Random;

public class Spider extends Monsters{

	private double distanceTraveled;
	public Spider(Vector2 position, Vector2 destination)
	{
		super(position, CreaturesInfos.SPIDER_SIZE, destination, CreaturesInfos.SPIDER_HEALTH,
				CreaturesInfos.SPIDER_SPEED, CreaturesInfos.SPIDER_TEARRATE, CreaturesInfos.SPIDER_DAMAGE,
				CreaturesInfos.SPIDER_RANGE, CreaturesInfos.SPIDER_SHOOTSPEED, ImagePaths.SPIDER);
		distanceTraveled = 0.00;
	}
	
	public void updateGameObject(Hero hero)
	{
		super.updateGameObject(hero);
		if(getFreezeTime()==0) {
			move(hero.getPosition());
			distanceTraveled+=0.01;
			if(distanceTraveled>=0.1){
				System.out.println("Freeze!");
				addFreezeTime(10);
				distanceTraveled = 0.00;
			}
		}else
			decreaseFreezeTime();
	}
	
	private void move(Vector2 victime) {
		if (inRange(victime) && Random.SuccessByPercentage(80)) {
			System.out.println("je te suis");
			moveTo(victime);
		}
		else {
			System.out.println("je te suis pas");
			moveToRandom(getDestination());
		}
	}
	
	private boolean inRange(Vector2 cible) {
		return (((Math.abs(getPosition().getX())-Math.abs(cible.getX()))<(CreaturesInfos.SPIDER_VISION.getX()))&&
		((Math.abs(getPosition().getY())-Math.abs(cible.getY()))<(CreaturesInfos.SPIDER_VISION.getY())));
	}//TODO valeur magique ^^

	
}
