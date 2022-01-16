package gameWorld.room.specialsRoom;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import gameWorld.room.Room;
import gameobjects.Door;
import gameobjects.moving_entity.Hero;
import gameobjects.moving_entity.monsters.Boss;
import gameobjects.moving_entity.monsters.Fly;
import gameobjects.moving_entity.monsters.Monsters;
import gameobjects.obstacles.Obstacle;
import libraries.Vector2;

public class BossRoom extends Room {

	public BossRoom(Hero hero, List<Door> doors) {
		super(hero, doors);
	}

	public void updateRoom() {		
		super.updateRoom();
		isANewPhase();
	}

	public void createMonsters() {
		getMonsters().add(new Boss());
	}

	public void isANewPhase() {
		LinkedList<Monsters> monsters = new LinkedList<Monsters>();
		for (Monsters monster : monsters) {
			if (monster.isANewPhase())
				makeFly(monster.getPosition());
		}
	}

	private void makeFly(Vector2 position) {
		System.out.println("atteint");
		LinkedList<Monsters> flyes = new LinkedList<Monsters>();
		getMonsters().add(new Fly(new Vector2(position.getX() + 0.1, position.getY()),
				new Vector2(position.getX() + 0.1, position.getY())));
		getMonsters().add(new Fly(new Vector2(position.getX() - 0.1, position.getY()),
				new Vector2(position.getX() - 0.1, position.getY())));
		getMonsters().add(new Fly(new Vector2(position.getX(), position.getY() + 0.1),
				new Vector2(position.getX(), position.getY() + 0.1)));
		getMonsters().add(new Fly(new Vector2(position.getX(), position.getY() - 0.1),
				new Vector2(position.getX(), position.getY() - 0.1)));
	//	getMonsters().addAll(flyes);
	}

	@Override
	public void initialise() {
		createMonsters();

	}

}
