package gameobjects.moving_entity.monsters;

import gameobjects.moving_entity.Hero;
import libraries.Vector2;
import resources.CreaturesInfos;
import resources.ImagePaths;

public class Spider extends Monsters{

	public Spider(Vector2 position, Vector2 destination)
	{
		super(position, CreaturesInfos.SPIDER_SIZE, destination, CreaturesInfos.SPIDER_HEALTH,
				CreaturesInfos.SPIDER_SPEED, CreaturesInfos.SPIDER_TEARRATE, CreaturesInfos.SPIDER_DAMAGE,
				CreaturesInfos.SPIDER_RANGE, CreaturesInfos.SPIDER_SHOOTSPEED, ImagePaths.SPIDER);
	}
	
	public void updateGameObject(Hero hero)
	{
		super.updateGameObject(hero);
		move(hero.getPosition());
	}
	
	private void move(Vector2 victime) {
		if (inRange(victime))
			moveTo(victime);
		else
			moveToRandom(getDestination());
			
	}
	private boolean inRange(Vector2 cible) {
		return (((Math.abs(getPosition().getX())-Math.abs(cible.getX()))<(CreaturesInfos.SPIDER_VISION.getX()))&&
		((Math.abs(getPosition().getY())-Math.abs(cible.getY()))<(CreaturesInfos.SPIDER_VISION.getY())));
	}//TODO valeur magique ^^
	
	private void moveToRandom(Vector2 destination) {
		System.out.println("je te suit pas");
		double posx = (double) Math.round(this.getPosition().getX() * 10) / 10;
		double posy = (double) Math.round(this.getPosition().getY() * 10) / 10;
		double cibx = (double) Math.round(destination.getX() * 10) / 10;
		double ciby = (double) Math.round(destination.getY() * 10) / 10;
		System.out.println("=>" + posx + "|" + posy + "|" + cibx + "|" + ciby + "|");
		if (posx == cibx && posy == ciby) {
			setDestination(chooseRandomPoint());
			return;
		}
		/*
		 * else if (posx<cibx && posy<ciby) { //pour bouger en diagonale
		 * goRightNext();goUpNext(); }else if (posx<cibx && posy>ciby) {
		 * goRightNext();goDownNext(); }else if(posx>cibx && posy<ciby) {
		 * goLeftNext();goUpNext(); }else if (posx>cibx && posy>ciby) {
		 * goLeftNext();goDownNext(); }
		 */if (posx < cibx) {
			goRightNext();
		} else if (posx > cibx) {
			goLeftNext();
		} else if (posy < ciby) {
			goUpNext();
		} else {
			goDownNext();
		}
		Vector2 normalizedDirection = getNormalizedDirection();
		Vector2 positionAfterMoving = getPosition().addVector(normalizedDirection);
		setPosition(positionAfterMoving);
		this.setDirection(new Vector2());
	}
	@Override
	protected void moveTo(Vector2 cible) {
		System.out.println("je te suit");
		double posx =(double) Math.round(this.getPosition().getX()*100)/1000;
		double posy =(double) Math.round(this.getPosition().getY()*100)/1000;
		double cibx =(double) Math.round(cible.getX()*10)/100;
		double ciby =(double) Math.round(cible.getY()*10)/100;
		if (posx == cibx && posy==ciby) {
			return;
		}
		if (posx<cibx && posy<ciby) { //pour bouger en diagonale
			goRightNext();goUpNext();
		}else if (posx<cibx && posy>ciby) {
			goRightNext();goDownNext();
		}else if(posx>cibx && posy<ciby) {
			goLeftNext();goUpNext();
		}else if (posx>cibx && posy>ciby) {
			goLeftNext();goDownNext();
		}
		if (posx<cibx) {
			goRightNext();
		}else if (posx>cibx) {
			goLeftNext();
		}else if (posy<ciby){
			goUpNext();
		}else {
			goDownNext();
		}
		
		Vector2 normalizedDirection = getNormalizedDirection();
		Vector2 positionAfterMoving = getPosition().addVector(normalizedDirection);
		setPosition(positionAfterMoving);
		this.setDirection(new Vector2());
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
	
	private Vector2 getNormalizedDirection()
	{
		Vector2 normalizedVector = new Vector2(getDirection());
		normalizedVector.euclidianNormalize(getSpeed());
		return normalizedVector;
	}
	
}
