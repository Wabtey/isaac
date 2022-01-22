package gameWorld.room.specialsRoom;

import java.util.LinkedList;
import java.util.List;

import gameWorld.room.Room;
import gameobjects.Door;
import gameobjects.moving_entity.Hero;
import gameobjects.obstacles.Machine;
import gameobjects.stuff.Item;
import gameobjects.stuff.pickup.PickUp;
import resources.MachineInfos;
import resources.Random;

public class Shop extends Room {

	private int level;
	private Machine machine;
	
	//--LVL 1--
	private LinkedList<PickUp> placementOne;
	private LinkedList<Item> placementTwo;
	private LinkedList<PickUp> placementThree;
	//--LVL 2--
	private LinkedList<PickUp> placementFour;
	private LinkedList<Item> placementFive;
	private LinkedList<PickUp> placementSix;

	
	//private ShopKeeper shopKeeper;
	
	public Shop(Hero hero, List<Door> doors) {
		super(hero, doors);
		this.level = 1;
	}
	
	public void initialise() {
		generateShop();
	}

	public void drawRoom() {
		drawGameObject();
		super.drawRoom();
	  //getShopKeeper.drawGameObject();
	}
	
//--GENERATION-------------------------------

	/**
	 * creation the selection of item and pickup offers
	 * create one LinkedList<> for every shopPlacement in case of hero having reStocker
	 */
	private void generateShop() {
		generatePlacement();
		//generateMachine();
	}
	
	//Keep the specials room without reward
	@Override
	public PickUp generateReward() {
		return null;
	}
	
	private void generatePlacement() {
		if(getLevel()==1) {
			
		}
		
	}
	
//	private void generateMachine() {
//		if(Random.SuccessByPercentage(75))
//			setMachine(MachineInfos.DONATION_MACHINE);
//  }
	
//--DRAW ITERFACE----------------------------
	
	public void drawGameObject() {
		addItems(placementTwo.get(0));
		placementTwo.remove(); //remove the head
	}
	

	
//--GETTERS/SETTERS--------------------------

	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
	}

	public Machine getMachine() {
		return machine;
	}

	public void setMachine(Machine machine) {
		this.machine = machine;
	}
	

}
