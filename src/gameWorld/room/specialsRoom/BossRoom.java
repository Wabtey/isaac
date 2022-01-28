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
import resources.Random;
import resources.RoomInfos;
import gameobjects.stuff.Item;
import gameobjects.stuff.pickup.PickUp;

public class BossRoom extends Room {

	//private boolean isDefeat;
	private Boss boss;

	
	//TODO learn why sometimes the bossRoom don't spawn!!
	public BossRoom(Hero hero, List<Door> doors) {
		super(hero, doors);
		//this.isDefeat = false;
		boss = CreaturesInfos.SWARMER;
	}
	
//--GESTION OF THE ROOM--
	
	@Override
	public void initialise() {
		createMonsters();
	}

	public void updateRoom() {		
//		checkMonstersHP();
//		checkDoorState(); //BossRoom method
//		makeHeroPlay();
//		makeMonstersPlay();
//		updateProjectile();
//		checkCollision();
		super.updateRoom();
		
		checkPhase(); //BossRoom method
//		if(isDefeat && getBoss().getRedHeart()<=0) { //if dead
//			generateItem()
//		}
	}
	
//	//TODO find a another solution rather this duplicated methods to spawn a reward after boss death
//	private void checkDoorState() {
//		if (getMonsters().isEmpty()) {
//			for (Door door : getDoors()) {
//				door.openDoor();
//				generateRoomReward(); //here a item
//			}
//		}
//	}
	
//--CLEAR THE ROOM---------

	
	
	public void generateRoomReward() {
		if(!isClear()) {
			spawnItem(generateItem(ItemInfos.STRING_BOSS_POOL));
			setIsClear(true);
			spawnTrap();
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
	    //getMonsters().addAll(flies);
	}



	@Override
	public PickUp generateReward() { // no need we import the checkDoorState() method down there
		return null;
	}	

}
