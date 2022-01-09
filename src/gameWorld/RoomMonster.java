
package gameWorld;

import java.util.ArrayList;
import java.util.LinkedList;
import gameobjects.Door;
import gameobjects.moving_entity.Hero;
import gameobjects.moving_entity.Projectile;
import gameobjects.moving_entity.monsters.Monsters;
import libraries.StdDraw;
import libraries.Vector2;
import resources.HeroInfos;

public class RoomMonster extends Room {
	private LinkedList<Monsters> monsters;

	public RoomMonster(Hero hero) {
		super(hero);
		this.getDoors().add(new Door(new Vector2(0.5,0.9), new Boss(hero)));
		this.getDoors().add(new Door(new Vector2(0.9,0.5), new Shop(hero)));
		this.monsters = new LinkedList<Monsters>();
		this.monsters.add(new Monsters(new Vector2(0.5,0.5),HeroInfos.ISAAC_SIZE,0.01, hero.getPosition(), 1, 2, "images/Spider.png", hero.getPosition()));
	}
	
	public void updateRoom() {
		super.updateRoom();
		makeMonstersPlay();
		checkCollision();
	}
	
	private void makeMonstersPlay() {			
		for (Monsters monster : monsters) {
			Vector2 lastPosition = monster.getPosition();
			monster.updateGameObject(); //DO NOT MOVE you monster
			if (inAnObstacle(monster.getPosition()))
				monster.setPosition(lastPosition);
		}
	}
	
	private void checkCollision(){//implementer tout type de collision (cac et projectile)
		checkRangeCollision();
		checkCloseCollision();
	}
	
	private void checkCloseCollision() {
		if (collisionWithMonster(getHero().getPosition(), getHero().getSize())!=null) {
			Monsters contactMonster = collisionWithMonster(getHero().getPosition(), getHero().getSize());
			contactMonster.addFreezeTime(20);
			getHero().getHitted(contactMonster.getDamage());
			getHero().addInvincibilityFrames(HeroInfos.ISAAC_INVINCIBILITY);
			System.out.println("hp : " + getHero().getHitPoint());
		}
	}
	
	
	private void checkRangeCollision() {
		ArrayList<Monsters> monster_delete = new ArrayList<Monsters>();
		ArrayList<Projectile> projectile_delete = new ArrayList<Projectile>();
		ArrayList<Projectile> projectiles = new ArrayList<Projectile>();
		projectiles.addAll(getHero().getProjectile());
		if (!projectiles.isEmpty()) {
			for (Projectile projectile:projectiles) {
				if (collisionWithMonster(projectile.getProjPosition(), projectile.getProjSize())!=null) {
				monster_delete.add(collisionWithMonster(projectile.getProjPosition(), projectile.getProjSize()));
				projectile_delete.add(projectile);
				}
			
			}
		}
		monsters.removeAll(monster_delete);
		getHero().removeProjectile(projectile_delete);
	}
	
	private Monsters collisionWithMonster(Vector2 coordonnees, Vector2 size) {
		double posX0 = coordonnees.getX() - (size.getX() / 2);
		double posX1 = coordonnees.getX() + (size.getX() / 2);
		double posY0 = coordonnees.getY() - (size.getY() / 2);
		double posY1 = coordonnees.getY() + (size.getY() / 2);
		Monsters guilty = null;
		for (Monsters monster : monsters) {
			double monX0 = monster.getPosition().getX() - (monster.getSize().getX() / 2);
			double monX1 = monster.getPosition().getX() + (monster.getSize().getX() / 2);
			double monY0 = monster.getPosition().getY() - (monster.getSize().getY() / 2);
			double monY1 = monster.getPosition().getY() + (monster.getSize().getY() / 2);
			if (posX0>monX1 || posX1<=monX0 || posY0>=monY1 || posY1<=monY0)
				return null;//pas de collision
			guilty = monster;
		}
		return guilty;
	}
	
	public void drawRoom() {
		super.drawRoom();
		double posX0 = this.getHero().getPosition().getX() - (this.getHero().getSize().getX() / 2);//TODO a supp
		double posX1 = this.getHero().getPosition().getX() + (this.getHero().getSize().getX() / 2);
		double posY0 = this.getHero().getPosition().getY() - (this.getHero().getSize().getY() / 2);
		double posY1 = this.getHero().getPosition().getY() + (this.getHero().getSize().getY() / 2);
		StdDraw.setPenColor(StdDraw.BLUE);
		StdDraw.filledCircle(posX1, posY0, 0.01);
		StdDraw.filledCircle(posX1, posY1, 0.01);
		StdDraw.filledCircle(posX0, posY0, 0.01);
		StdDraw.filledCircle(posX0, posY1, 0.01);
		StdDraw.filledCircle(0.5, 0.5, 0.02);
		for (Monsters monster : monsters) {
			monster.drawGameObject();
		}
	}
	
}
