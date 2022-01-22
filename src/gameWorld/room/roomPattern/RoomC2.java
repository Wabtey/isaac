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
import resources.RoomInfos;

public class RoomC2 extends Room {

	public RoomC2(Hero hero, List<Door> doors) {
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

	//TODO magical number
	public void createObstacles() {
		ArrayList<Obstacle> obstacle = new ArrayList<Obstacle>();
		obstacle.add(new Rock(new Vector2(0.25, 0.35), 0.16, 0.16));
		obstacle.add(new Rock(new Vector2(0.6, 0.25), 0.04, 0.08));
		obstacle.add(new Rock(new Vector2(0.85, 0.3), 0.1, 0.1));
		obstacle.add(new Rock(new Vector2(0.3, 0.8), 0.1, 0.1));
		getObstacles().addAll(obstacle);
	}

	public void createMonsters() {
		ArrayList<Monsters> monster = new ArrayList<Monsters>();
		monster.add(new Fly(new Vector2(0.48, 0.4), new Vector2(0.25, 0.4)));
		monster.add(new Fly(new Vector2(0.45, 0.6), new Vector2(0.45, 0.6)));
		monster.add(new Fly(new Vector2(0.25, 0.68), new Vector2(0.25, 0.68)));
		monster.add(new Fly(new Vector2(0.70, 0.68), new Vector2(0.70, 0.68)));
		monster.add(new Spider(new Vector2(0.31, 0.5), new Vector2(0.3, 0.5)));
		monster.add(new Spider(new Vector2(0.75, 0.3), new Vector2(0.8, 0.3)));
		monster.add(new Spider(new Vector2(0.8, 0.8), new Vector2(0.8, 0.8)));
		monster.add(new Moter(new Vector2(0.5, 0.5), new Vector2(0.5, 0.5)));
		getMonsters().addAll(monster);
	}

	public void drawRoom() {
		super.drawRoom();

	}

}
