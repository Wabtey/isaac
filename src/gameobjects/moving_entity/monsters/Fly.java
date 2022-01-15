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
}
