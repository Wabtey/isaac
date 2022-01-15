package gameobjects.moving_entity.monsters;

import gameobjects.moving_entity.Hero;
import libraries.Vector2;
import resources.CreaturesInfos;
import resources.ImagePaths;

public class Fly extends Monsters {
		
	public Fly(Vector2 position, Vector2 destination) {
		super(position, CreaturesInfos.FLY_SIZE, destination, CreaturesInfos.FLY_HEALTH, CreaturesInfos.FLY_SPEED, CreaturesInfos.FLY_TEARRATE, CreaturesInfos.FLY_DAMAGE, CreaturesInfos.FLY_RANGE,  CreaturesInfos.FLY_SHOOTSPEED, ImagePaths.FLY);
	}
	
	public void updateGameObject(Hero hero)
	{
		super.updateGameObject(hero);
		moveTo(hero.getPosition());
	}

	@Override
	protected void moveTo(Vector2 cible) {
		double posx =(double) Math.round(this.getPosition().getX()*100)/1000;
		double posy =(double) Math.round(this.getPosition().getY()*100)/1000;
		double cibx =(double) Math.round(cible.getX()*100)/1000;
		double ciby =(double) Math.round(cible.getY()*100)/1000;
		System.out.println(new Vector2(posx,posy) + "||" + new Vector2(cibx,ciby));
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
