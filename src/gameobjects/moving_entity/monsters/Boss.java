package gameobjects.moving_entity.monsters;

import gameobjects.moving_entity.Hero;
import libraries.StdDraw;
import libraries.Vector2;
import resources.CreaturesInfos;
import resources.ImagePaths;
import resources.RoomInfos;

public class Boss extends Monsters {
	private int phase;
	public Boss() {
		super(RoomInfos.POSITION_CENTER_OF_ROOM, CreaturesInfos.BOSS_SIZE, RoomInfos.POSITION_CENTER_OF_ROOM, CreaturesInfos.BOSS_HEALTH,
				CreaturesInfos.BOSS_SPEED, CreaturesInfos.BOSS_TEARRATE, CreaturesInfos.BOSS_DAMAGE,
				CreaturesInfos.BOSS_RANGE,  CreaturesInfos.BOSS_SHOOTSPEED, ImagePaths.BOSS);
		if(getRedHeart()>=10) {
			this.phase = (int) CreaturesInfos.BOSS_HEALTH/10;
		}
	}

	
	public void updateGameObject(Hero hero) {
		super.updateGameObject(hero);
		reSizeBoss();
		System.out.println(phase);
	}
	
	private void reSizeBoss() {
		int difference = phase - (int)(getRedHeart()/10);
		System.out.println(difference);
		if (difference == 0) {
			return;
		}
		if (phase>0) {
			Vector2 newSize = getSize().scalarMultiplication((phase-difference)*phase);
			setSize(newSize);
			phase -= difference;
		}
	}
	
	public void drawGameObject() {
		StdDraw.picture(getPosition().getX(), getPosition().getY(), ImagePaths.BOSS, getSize().getX(), getSize().getY());
	}
}
