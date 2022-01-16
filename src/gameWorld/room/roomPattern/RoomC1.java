package gameWorld.room.roomPattern;

import java.util.ArrayList;
import java.util.List;

import gameWorld.room.Room;
import gameobjects.Door;
import gameobjects.moving_entity.Hero;
import gameobjects.moving_entity.monsters.Fly;
import gameobjects.moving_entity.monsters.Monsters;
import gameobjects.moving_entity.monsters.Moter;
import gameobjects.moving_entity.monsters.Spider;
import gameobjects.obstacles.Obstacle;
import gameobjects.obstacles.collisionable.Rock;
import libraries.Vector2;
import resources.Random;
import resources.RoomInfos;

public class RoomC1 extends Room{


	public RoomC1(Hero hero, List<Door> doors) {
		super(hero, doors);
	}
	
	public void initialise() {
		createObstacles();
		createMonsters();
		setFreePosition(RoomInfos.POSITION_CENTER_OF_ROOM); //TODO Change it to something coherent
	}
	
	public void updateRoom() {
		super.updateRoom();
	}
	

	private void createObstacles() {
		ArrayList<Obstacle> obstacle = new ArrayList<Obstacle>();//TODO scaling
		obstacle.add(new Rock(new Vector2(0.2,0.4), 0.05, 0.05));
		obstacle.add(new Rock(new Vector2(0.6,0.3), 0.06, 0.02));
		obstacle.add(new Rock(new Vector2(0.8,0.35), 0.1, 0.09));
		obstacle.add(new Rock(new Vector2(0.6,0.75), 0.05, 0.05));
		obstacle.add(new Rock(new Vector2(0.1,0.35), 0.1, 0.1));
		this.getObstacles().addAll(obstacle);
	}
	
	private void createMonsters() {
		ArrayList<Monsters> monster = new ArrayList<Monsters>();
		monster.add(new Spider(new Vector2(0.25,0.5), new Vector2(0.25,0.5)));
		monster.add(new Moter(new Vector2(0.46,0.5), new Vector2(0.4,0.5)) );
		monster.add(new Fly(new Vector2(0.7,0.7), getHero().getPosition()));
		monster.add(new Moter(new Vector2(0.7,0.2),new Vector2(0.7,0.2)));
		getMonsters().addAll(monster);
	}
	
	public void drawRoom() {
		super.drawRoom();
		ArrayList<Obstacle> obstacles =getObstacles();
		for (Obstacle obstacle: obstacles) {
			obstacle.drawGameObject();
		}
	}
	
}
