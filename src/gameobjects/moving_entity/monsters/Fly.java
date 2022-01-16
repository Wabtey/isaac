package gameobjects.moving_entity.monsters;

import gameobjects.moving_entity.Hero;
import libraries.StdDraw;
import libraries.Vector2;
import resources.CreaturesInfos;
import resources.ImagePaths;

public class Fly extends Monsters {
	
	private int state;
		
	public Fly(Vector2 position, Vector2 destination) {
		super(position, CreaturesInfos.FLY_SIZE, destination, CreaturesInfos.FLY_HEALTH,
				CreaturesInfos.FLY_SPEED, CreaturesInfos.FLY_TEARRATE, CreaturesInfos.FLY_DAMAGE,
				CreaturesInfos.FLY_RANGE,  CreaturesInfos.FLY_SHOOTSPEED, ImagePaths.FLY);
	}
	
	public void updateGameObject(Hero hero)
	{
		super.updateGameObject(hero);
		moveTo(hero.getPosition());
		drawGameObject();
		if(state<CreaturesInfos.FLY_CELLS_NB-1)
			state+=1;
		else
			state=0;
		}
	
	// TODO better animation
	@Override
	public void drawGameObject() {
		switch (state) {
			case 0: StdDraw.picture(getPosition().getX(), getPosition().getY(), ImagePaths.FLY_0,
					getSize().getX(), getSize().getY());
					break;
			case 1: StdDraw.picture(getPosition().getX(), getPosition().getY(), ImagePaths.FLY_1,
					getSize().getX(), getSize().getY());
					break;
			case 2: StdDraw.picture(getPosition().getX(), getPosition().getY(), ImagePaths.FLY_2,
					getSize().getX(), getSize().getY());
					break;
				
			case 3: StdDraw.picture(getPosition().getX(), getPosition().getY(), ImagePaths.FLY_3,
					getSize().getX(), getSize().getY());
					break;
			default:StdDraw.picture(getPosition().getX(), getPosition().getY(), ImagePaths.FLY_0,
					getSize().getX(), getSize().getY());
					state=0;
					break;
		}
//		animation.update();
	}
}
