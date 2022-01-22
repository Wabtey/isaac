package gameobjects.moving_entity.monsters;

import gameobjects.moving_entity.Hero;
import libraries.StdDraw;
import libraries.Vector2;
import resources.CreaturesInfos;
import resources.ImagePaths;
import resources.Random;

public class Spider extends Monsters{
	
	private int state;
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
				addFreezeTime(10);
				distanceTraveled = 0.00;
			}
		}else
			decreaseFreezeTime();
		if(isMoving()) {
			if(state<CreaturesInfos.SPIDER_CELLS_NB-1)
				state+=1;
			else
				state=2;
		}else {
			if(state<1)
				state+=1;
			else
				state=0;
		}
			
		
	}
	
	private void move(Vector2 victime) {
		if (inRange(victime) && Random.SuccessByPercentage(80)) {
			moveTo(victime);
		}
		else {
			moveToRandom(getDestination());
		}
	}
	
	private boolean inRange(Vector2 cible) {
		return (((Math.abs(getPosition().getX())-Math.abs(cible.getX()))<(CreaturesInfos.SPIDER_VISION.getX()))&&
		((Math.abs(getPosition().getY())-Math.abs(cible.getY()))<(CreaturesInfos.SPIDER_VISION.getY())));
	}

	// TODO better animation
	@Override
	public void drawGameObject() {
		switch (state) {
		case 0:
			StdDraw.picture(getPosition().getX(), getPosition().getY(), ImagePaths.SPIDER, getSize().getX(),
					getSize().getY());
			break;
			
		case 1:
			StdDraw.picture(getPosition().getX(), getPosition().getY(), ImagePaths.SPIDER_OFF, getSize().getX(),
					getSize().getY());
			break;
			
		case 2:
			StdDraw.picture(getPosition().getX(), getPosition().getY(), ImagePaths.SPIDER_0, getSize().getX(),
					getSize().getY());
			break;		

		case 3:
			StdDraw.picture(getPosition().getX(), getPosition().getY(), ImagePaths.SPIDER_1, getSize().getX(),
					getSize().getY());
			break;
			
		case 4:
			StdDraw.picture(getPosition().getX(), getPosition().getY(), ImagePaths.SPIDER_2, getSize().getX(),
					getSize().getY());
			break;
			
		case 5:
			StdDraw.picture(getPosition().getX(), getPosition().getY(), ImagePaths.SPIDER_3, getSize().getX(),
				getSize().getY());
			break;
			
		case 6:
			StdDraw.picture(getPosition().getX(), getPosition().getY(), ImagePaths.SPIDER_4, getSize().getX(),
					getSize().getY());
			break;
		
		case 7:
			StdDraw.picture(getPosition().getX(), getPosition().getY(), ImagePaths.SPIDER_5, getSize().getX(),
					getSize().getY());
			break;
			
		default:
			StdDraw.picture(getPosition().getX(), getPosition().getY(), ImagePaths.SPIDER_0, getSize().getX(),
					getSize().getY());
			state = 0;
			break;
		}
//			animation.update();
	}

	public int getState() {
		return state;
	}

	public void setState(int state) {
		this.state = state;
	}

	@Override
	public boolean isANewPhase() {
		// TODO Auto-generated method stub
		return false;
	}
	
}
