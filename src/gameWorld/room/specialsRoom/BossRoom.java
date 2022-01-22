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
import resources.ItemInfos;
import resources.Random;
import gameobjects.pickup.PickUp;
import gameobjects.stuff.Item;

public class BossRoom extends Room {

	private boolean isDefeat;
	
	public BossRoom(Hero hero, List<Door> doors) {
		super(hero, doors);
		this.isDefeat = false;
	}

	public void updateRoom() {		
		super.updateRoom();
		checkPhase();
//		if(isDefeat && getBoss().getRedHeart()<=0) { //if dead
//			generateItem()
//		}
	}
	
//--CLEAR THE ROOM---------

	
	public void generateRoomReward() {
		if(!isClear()) {
			//generateItem(); //Item reward = 
//			if(reward!=null)
//				getItems().add(reward);
			setIsClear(true);
		}
	}

	public void createMonsters() {
		getMonsters().add(new Boss());
	}

	public void checkPhase() {
		LinkedList<Monsters> monsters = getMonsters();
		for (Monsters monster : monsters) {
			if (monster instanceof Boss && monster.isANewPhase())
				makeFly(monster.getPosition());
		}
	}

	private void makeFly(Vector2 position) {
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

	@Override
	public PickUp generateReward() {
		return null;
	}
	
	public Item generateItem() {
		Item reward = Random.getRewardPool(ItemInfos.STRING_BOSS_POOL);
		addItems(reward);
		return reward;
	}
	
	

}
