package gameWorld.room.specialsRoom;

import java.util.LinkedList;
import java.util.List;

import gameWorld.room.Room;
import gameobjects.Door;
import gameobjects.moving_entity.Hero;
import gameobjects.obstacles.Machine;
import gameobjects.stuff.Item;
import gameobjects.stuff.pickup.PickUp;
import libraries.StdDraw;
import resources.ImagePaths;
import resources.ItemInfos;
import resources.MachineInfos;
import resources.Random;
import resources.RoomInfos;

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
		this.placementOne = new LinkedList<PickUp>();
		this.placementTwo = new LinkedList<Item>();
		this.placementThree = new LinkedList<PickUp>();
		
		this.placementFour = new LinkedList<PickUp>();
		this.placementFive = new LinkedList<Item>();
		this.placementSix = new LinkedList<PickUp>();
		
		initialise(); //Meant to be called in setSpecialRooms() (Dungeon) into changeTypeOfRoom(String type) (GameWorld)
	}
	
	public void initialise() {
		generateShop();
		System.out.println("shop initialised"); //TODO test to remove
	}
	
//	public void updateRoom() {
//		super.updateRoom();
//	}

	public void drawRoom() {
		//drawGameObject();
		super.drawRoom();
		StdDraw.picture(RoomInfos.POSITION_CENTER_OF_ROOM.getX()+0.2, RoomInfos.POSITION_CENTER_OF_ROOM.getY()+0.2, ImagePaths.JESUS_JUICE);
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
			placementOne.add(super.generateReward()); //create a semi random pickup for the first element of this placement
			placementTwo.add(generateItem(ItemInfos.STRING_SHOP_POOL));
			if(placementTwo!=null)
				addItems(placementTwo.remove()); //placementTwo.get(0)
		}
		
	}
	
//	private void generateMachine() {
//		if(Random.SuccessByPercentage(75))
//			setMachine(MachineInfos.DONATION_MACHINE);
//  }
	
//--DRAW ITERFACE----------------------------
	
//	public void drawGameObject() {
//		if(placementTwo!=null)
//			addItems(placementTwo.remove()); //placementTwo.get(0)
//	}
	

	
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
