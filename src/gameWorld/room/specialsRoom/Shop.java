package gameWorld.room.specialsRoom;

import java.util.LinkedList;
import java.util.List;

import gameWorld.room.Room;
import gameobjects.Door;
import gameobjects.moving_entity.Hero;
import gameobjects.moving_entity.monsters.Monsters;
import gameobjects.obstacles.Machine;
import gameobjects.stuff.Item;
import gameobjects.stuff.pickup.PickUp;
import libraries.Physics;
import libraries.StdDraw;
import libraries.Vector2;
import resources.CreaturesInfos;
import resources.ImagePaths;
import resources.ItemInfos;
import resources.MachineInfos;
import resources.Random;
import resources.RoomInfos;

public class Shop extends Room {

	private int level;
	private Machine machine;
	
	private LinkedList<Item> shopItems;
	private LinkedList<PickUp> shopPickups;
	
	//--LVL 1--
	private LinkedList<PickUp> placementOne;
	private LinkedList<Item> placementTwo;
	private LinkedList<PickUp> placementThree;
	//--LVL 2--
	private LinkedList<PickUp> placementFour;
	private LinkedList<Item> placementFive;
	private LinkedList<PickUp> placementSix;
	
	//TODO change this king of ultra static price into a couple or anything else
	private int pickupPrice = 5;
	private int itemPrice = 15;
	
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
		
		this.shopItems = new LinkedList<Item>();
		this.shopPickups = new LinkedList<PickUp>();
		
		initialise(); //Meant to be called in setSpecialRooms() (Dungeon) into changeTypeOfRoom(String type) (GameWorld)
	}
	
	public void initialise() {

		generateShop();
		System.out.println("shop initialised"); //TODO test to remove
	}
	
	public void updateRoom() {
		super.updateRoom();
		checkBuyStuff();
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
				addBuyableItems(placementTwo.remove()); //placementTwo.get(0) //
		}
		
	}
	
//	private void generateMachine() {
//		if(Random.SuccessByPercentage(75))
//			setMachine(MachineInfos.DONATION_MACHINE);
//  }
	
//-------------------------------------------

	/**
	 * Override the Room.addItems method to keep them payable but still permit the player to spawn item
	 */
	private void addBuyableItems(Item stuff) {
		getShopItems().add(stuff);
	}
	
	private void checkBuyStuff() {		
		//--REWARD--
		for (PickUp pickUp : getShopPickups()) {
			int heroGold = getHero().getGold();
			// TODO shrink hero collision to his feet about pick up the reward
			if (heroGold>=getPickupPrice() &&
				Physics.rectangleCollision(getHero().getPosition(), getHero().getSize(), pickUp.getPosition(), pickUp.getSize())) {
				PickUp bhoughtPickup = pickUp;
				if (getHero().hasPickedUp(bhoughtPickup)) {
					getShopPickups().remove(bhoughtPickup);
					getHero().setGold(heroGold-getPickupPrice());
				}
			}
		}
		
		//--ITEM--
		for (Item item : getShopItems()) {
			int heroGold = getHero().getGold();
			if (heroGold>=getItemPrice() &&
				Physics.rectangleCollision(getHero().getPosition(), getHero().getSize(), item.getPosition(), item.getSize())) {
				Item purchasedItem = item;
				if (getHero().takeItem(purchasedItem)) {
					getShopItems().remove(purchasedItem);
					getHero().setGold(heroGold-getItemPrice());
				}
			}
		}

		
	}
	
//--DRAW ITERFACE----------------------------
	
	public void drawItems() {
		if (getLevel() == 1) {
			for (Item item : getShopItems()) {
				item.setPosition(new Vector2(0.5, 0.4)); //replace it by spawnItem() method
				item.drawGameObject();
			}
			for(PickUp pickup : getShopPickups()) {
				pickup.setPosition(new Vector2(0.3, 0.4));
				pickup.drawGameObject();
			}
		}
	}
	
	public void drawRoom() {
		//drawGameObject();
		super.drawRoom();
		StdDraw.picture(RoomInfos.POSITION_CENTER_OF_ROOM.getX()+0.2, RoomInfos.POSITION_CENTER_OF_ROOM.getY()+0.2, ImagePaths.JESUS_JUICE);
	  //getShopKeeper.drawGameObject();
	}
	
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

	public LinkedList<Item> getShopItems() {
		return shopItems;
	}

	public void setShopItems(LinkedList<Item> shopItems) {
		this.shopItems = shopItems;
	}

	public LinkedList<PickUp> getShopPickups() {
		return shopPickups;
	}

	public void setShopPickups(LinkedList<PickUp> shopPickups) {
		this.shopPickups = shopPickups;
	}

	public int getPickupPrice() {
		return pickupPrice;
	}

	public void setPickupPrice(int pickupPrice) {
		this.pickupPrice = pickupPrice;
	}

	public int getItemPrice() {
		return itemPrice;
	}

	public void setItemPrice(int itemPrice) {
		this.itemPrice = itemPrice;
	}
	

}
