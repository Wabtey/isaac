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
		if (getFreezeTime() == 0) 
			moveTo(hero.getPosition());
		else decreaseFreezeTime();
	}


	@Override
	protected void moveTo(Vector2 cible) {
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
	
	private Vector2 getNormalizedDirection()
	{
		Vector2 normalizedVector = new Vector2(getDirection());
		normalizedVector.euclidianNormalize(getSpeed());
		return normalizedVector;
	}
	
}
