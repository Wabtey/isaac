package gameWorld.room.specialsRoom;

import java.util.List;

import gameWorld.room.Room;
import gameobjects.Door;
import gameobjects.moving_entity.Hero;
import gameobjects.moving_entity.monsters.Boss;
import gameobjects.moving_entity.monsters.Fly;
import libraries.Vector2;
import resources.CreaturesInfos;
import resources.ItemInfos;
import gameobjects.stuff.Item;
import gameobjects.stuff.pickup.PickUp;

public class BossRoom extends Room {

	//private boolean isDefeat;
	private Boss boss;
	
	//TODO learn why sometimes the bossRoom don't spawn 
	
	public BossRoom(Hero hero, List<Door> doors) {
		super(hero, doors);
		//this.isDefeat = false;
		boss = CreaturesInfos.SWARMER;
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
		getMonsters().add(boss);
	}

	public void checkPhase() {
//		LinkedList<Monsters> monsters = getMonsters();
//		for (Monsters monster : monsters) {
//			if (monster instanceof Boss && monster.isANewPhase())
//				makeFly(monster.getPosition());
//		}
		 if(boss.isANewPhase()) //TODO turn it into a getBoss()
			 makeFly(boss.getPosition());
	}

	//TODO fix SOFTLOCK when invoke close to a wall flies can be stuck
	private void makeFly(Vector2 position) {
		//LinkedList<Monsters> flies = new LinkedList<Monsters>();
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
		//Item reward = Random.getRewardPool(ItemInfos.STRING_BOSS_POOL);
		Item reward = ItemInfos.HP_UP;
		spawnItem(reward);
		return reward;
	}
	
	

}
