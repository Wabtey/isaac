package gameobjects.moving_entity.monsters;

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
	
}
