package gameobjects.moving_entity.monsters;

import gameobjects.moving_entity.Hero;
import libraries.StdDraw;
import libraries.Vector2;
import resources.CreaturesInfos;
import resources.ImagePaths;

public class Moter extends Monsters{
	
//	//images\SpriteSheet\originals\SpriteSheet_ISAAC.png
//	//ImagePaths.MOTER
//	// Images for each animation
//	BufferedImage[] flying = {Sprite.getSprite(0, 0), Sprite.getSprite(0, 2)};
//
//	// These are animation states
//	Animation fly = new Animation(flying, 1);
//
//	// This is the actual animation
//	Animation animation = fly;
	
	private int state;
	
	/**
	 * Moters appear as two conjoined Attack Flies.
	 * They behave similarly to regular Attack Flies,
	 * but split into two Attack Flies upon being killed.
	 * Optionnal :
	 * The directions the Attack Flies will split off is parallel to Isaac
	 * (vertical if Isaac is to either side, and horizontal if he is above or below). 
	 * @param position to spawn
	 * @param destination
	 */
	public Moter(Vector2 position, Vector2 destination) {
		super(position, CreaturesInfos.MOTER_SIZE, destination, CreaturesInfos.MOTER_HEALTH,
				CreaturesInfos.MOTER_SPEED, CreaturesInfos.MOTER_TEARRATE, CreaturesInfos.MOTER_DAMAGE,
				CreaturesInfos.MOTER_RANGE,  CreaturesInfos.MOTER_SHOOTSPEED, ImagePaths.MOTER);
		this.state = 0;
	}
	
	public void updateGameObject(Hero hero)
	{
		super.updateGameObject(hero);
		moveTo(hero.getPosition());
		drawGameObject();
		if(state<CreaturesInfos.MOTER_CELLS_NB-1)
			state+=1;
		else
			state=0;
	}
	
	// TODO better animation
	@Override
	public void drawGameObject() {
		switch (state) {
			case 0: StdDraw.picture(getPosition().getX(), getPosition().getY(), ImagePaths.MOTER_0,
					getSize().getX(), getSize().getY());
					break;
			case 1: StdDraw.picture(getPosition().getX(), getPosition().getY(), ImagePaths.MOTER_1,
					getSize().getX(), getSize().getY());
					break;
			case 2: StdDraw.picture(getPosition().getX(), getPosition().getY(), ImagePaths.MOTER_2,
					getSize().getX(), getSize().getY());
					break;
				
			case 3: StdDraw.picture(getPosition().getX(), getPosition().getY(), ImagePaths.MOTER_3,
					getSize().getX(), getSize().getY());
					break;
			default:StdDraw.picture(getPosition().getX(), getPosition().getY(), ImagePaths.MOTER_0,
					getSize().getX(), getSize().getY());
					state=0;
					break;
		}
//		animation.update();
	}

	@Override
	public boolean isANewPhase() {
		// TODO Auto-generated method stub
		return false;
	}
	
}
