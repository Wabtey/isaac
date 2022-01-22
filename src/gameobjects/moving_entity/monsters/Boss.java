package gameobjects.moving_entity.monsters;

import gameobjects.moving_entity.Hero;
import libraries.StdDraw;
import libraries.Vector2;
import resources.CreaturesInfos;
import resources.ImagePaths;
import resources.RoomInfos;

public class Boss extends Monsters {
	private int phase;
	private int phase_max;
	private boolean newPhase;
	
	public Boss(Vector2 position, Vector2 size, Vector2 destination,
			double hp, double speed, double tearRate, double damage, double range, double shootSpeed,
			String imagePath) {
		super(position, size, destination,
				hp, speed, tearRate, damage, range, shootSpeed,
				imagePath);
		if(getRedHeart()>=10) { //
			this.phase = this.phase_max = (int) CreaturesInfos.BOSS_HEALTH/10;
		}
	}

	
	public void updateGameObject(Hero hero) {
		super.updateGameObject(hero);
		moveTo(hero.getPosition());
		reSizeBoss();
		if (IsAVictory())
			drawVictory();
	}
	
	private void reSizeBoss() {
		int difference = phase - (int)(getRedHeart()/10);
		if (difference == 0) {
			return;
		}
		if (difference > 0) {
			Vector2 newSize = new Vector2(getSize().getX()/1.25,getSize().getY()/1.25);
			setSize(newSize);
			this.newPhase = true;
			phase -= difference;
		}
	}
	
	
	public void drawGameObject() {
		StdDraw.picture(getPosition().getX(), getPosition().getY(), ImagePaths.BOSS, getSize().getX(), getSize().getY());
	}
	
	public boolean isANewPhase() {
		if (newPhase) {
			newPhase=false;
			return true;
		}
		return false;
	}
	
	private boolean IsAVictory(){
		if (getRedHeart()<=0) {
			return true;
		}
		return false;
	}
	
	public void drawVictory() {
		StdDraw.picture(RoomInfos.POSITION_CENTER_OF_ROOM.getX(), RoomInfos.POSITION_CENTER_OF_ROOM.getY(), ImagePaths.WIN_SCREEN);
	}
}
