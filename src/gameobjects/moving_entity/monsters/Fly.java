package gameobjects.moving_entity.monsters;

import gameobjects.moving_entity.Hero;
import gameobjects.moving_entity.Projectile;
import libraries.StdDraw;
import libraries.Vector2;
import libraries.Keybinding.SpecialKeys;
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
		if (getReloadTime()==0)
			shootHero(hero.getPosition());
		drawGameObject();
		if(state<CreaturesInfos.FLY_CELLS_NB-1)
			state+=1;
		else
			state=0;
		}
	
		private void shootHero(Vector2 hero) {
			int heroX = (int) Math.round(hero.getX() * 100) / 10;
			int heroY = (int) Math.round(hero.getY() * 100) / 10;
			int flyX = (int) Math.round(getPosition().getX() * 100) / 10;
			int flyY = (int) Math.round(getPosition().getY() * 100) / 10;
			System.out.println("(" + heroX + ", " + heroY + ") (" + flyX + ", " + flyY + ")");
			Vector2 shootDirection = new Vector2(0,0);
			if (heroX == flyX) {
				if (heroY < flyY) {
					shootDirection = new Vector2(0.0, -0.1);

				} else {
					shootDirection = new Vector2(0.0, 0.1);
				}

			}
			if (heroY == flyY) {
				if (heroX < flyX) {
					shootDirection = new Vector2(-1.0, 0.0);
				} else {
					shootDirection = new Vector2(1.0, 0.0);
				}

			}
			addProjectile(new Projectile(getPosition(), shootDirection, getSize(), getDamage(),
					CreaturesInfos.FLY_SHOOTSPEED, ImagePaths.BLOOD_TEAR, false));
			setReloadTime(CreaturesInfos.SPIDERT_RELOADTIME);
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
