package gameWorld.room.specialsRoom;

import java.util.LinkedList;
import java.util.List;

import gameWorld.room.Room;
import gameobjects.Door;
import gameobjects.moving_entity.Hero;
import gameobjects.moving_entity.monsters.Monsters;
import gameobjects.obstacles.Machine;
import gameobjects.stuff.Item;
import gameobjects.stuff.pickup.*;
import libraries.Physics;
import libraries.StdDraw;
import libraries.Vector2;
import resources.CreaturesInfos;
import resources.DisplaySettings;
import resources.ImagePaths;
import resources.ItemInfos;
import resources.MachineInfos;
import resources.Random;
import resources.RoomInfos;
import resources.ShopInfos;

public class Shop extends Room {

	private int level;
	private Machine machine;
	
//	private LinkedList<Item> shopItems;
//	private LinkedList<PickUp> shopPickups;
	
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
		
//		this.shopItems = new LinkedList<Item>();
//		this.shopPickups = new LinkedList<PickUp>();
		
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
	
	/**
	 * @return PickUp choosed to be sell
	 */
	public PickUp generatePurchasablePickUp() {
		double percent = Random.roomRewardPercentage(getHero().getLuck());
		PickUp drop = new Coin(RoomInfos.POSITION_CENTER_OF_ROOM); //not suppose to be one, so it's like a bug spotter
			if (percent <0.40) {
				drop = new Heart(RoomInfos.POSITION_CENTER_OF_ROOM); //Generate a redHeart, half one, BlueHeart or a half one
			}else if (percent < 0.70) {
				drop = new Key(RoomInfos.POSITION_CENTER_OF_ROOM);
			}else if (percent < 1.00) {
				drop = new Bomb(RoomInfos.POSITION_CENTER_OF_ROOM);
			}
		return drop;

	}
	
	
	private void generatePlacement() {
		if(getLevel()==1) {
			//You can buy coin for 5coin worth.
			placementOne.add(generatePurchasablePickUp()); //create a semi random pickup for the first element of this placement
			placementTwo.add(generateItem(ItemInfos.STRING_SHOP_POOL));
			placementThree.add(generatePurchasablePickUp());
		}
		
	}
	
//	private void generateMachine() {
//		if(Random.SuccessByPercentage(75))
//			setMachine(MachineInfos.DONATION_MACHINE);
//  }
	
//-------------------------------------------
	
	private void checkBuyStuffPickUpList(LinkedList<PickUp> pickups) {
		for (PickUp pickUp : pickups) {
			
			int heroGold = getHero().getGold();
			int price = ShopInfos.PICKUP_PRICE;
			
			if(pickUp instanceof Heart) {
				Heart heart = (Heart) pickUp;
				if(heart.getColor() == "red") {
					price = ShopInfos.RED_HEART_PRICE;
				}
			} 
			// TODO shrink hero collision to his feet about pick up the reward
			if (heroGold>=ShopInfos.PICKUP_PRICE &&
				Physics.rectangleCollision(getHero().getPosition(), getHero().getSize(), pickUp.getPosition(), pickUp.getSize())) {
				PickUp bhoughtPickup = pickUp;
				if (getHero().hasPickedUp(bhoughtPickup)) {
					pickups.remove(bhoughtPickup);
					getHero().setGold(heroGold-ShopInfos.PICKUP_PRICE);
				}
			}
		}
	}

	private void checkBuyStuffItemList(LinkedList<Item> items) {
		for (Item item : items) {
			int heroGold = getHero().getGold();
			if (heroGold>= ShopInfos.ITEM_PRICE &&
				Physics.rectangleCollision(getHero().getPosition(), getHero().getSize(), item.getPosition(), item.getSize())) {
				Item purchasedItem = item;
				if (getHero().takeItem(purchasedItem)) {
					items.remove(purchasedItem);
					getHero().setGold(heroGold-ShopInfos.ITEM_PRICE);
				}
			}
		}
	}
	
	private void checkBuyStuff() {		
		//--REWARD--
		checkBuyStuffPickUpList(placementOne);
		checkBuyStuffPickUpList(placementThree);

		//--ITEM--
		checkBuyStuffItemList(placementTwo);
	}
	
//--DRAW ITERFACE----------------------------

	/**
	 * the placement of setPosition can be think of random but opti
	 */
	private void drawCurrenties() {
		if (getLevel() == 1) {
			for(PickUp pickup : placementOne) {
				pickup.setPosition(new Vector2(RoomInfos.POSITION_DIAG_5.getX(), RoomInfos.POSITION_DIAG_5.getY()));
				pickup.drawGameObject();
			}
			
			for (Item item : placementTwo) {
				item.setPosition(new Vector2(RoomInfos.POSITION_DIAG_6.getX(), RoomInfos.POSITION_DIAG_4.getY())); //replace it by spawnItem() method
				item.drawGameObject();
			}
			
			for(PickUp pickup : placementThree) {
				pickup.setPosition(new Vector2(RoomInfos.POSITION_DIAG_7.getX(), RoomInfos.POSITION_DIAG_5.getY()));
				pickup.drawGameObject();
			}
		}
	}
	
	public void drawWallnFloor() {
		Vector2 center = RoomInfos.POSITION_CENTER_OF_ROOM;
		//TODO removing the display of wall and floor make the game faster: learn why
		StdDraw.picture(center.getX(), center.getY(), ImagePaths.FLOOR, DisplaySettings.SCALE, DisplaySettings.SCALE);
		StdDraw.picture(center.getX(), center.getY(), ImagePaths.SHOP_WALL, DisplaySettings.SCALE, DisplaySettings.SCALE);
	}
	
	public void drawRoom() {
		super.drawRoom();
		drawCurrenties();
		StdDraw.picture(0.8, 0.8, "images/post_it_cheat.png", 0.02, 0.02);
//		getShopKeeper.drawGameObject();
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

//	public LinkedList<Item> getShopItems() {
//		return shopItems;
//	}
//
//	public void setShopItems(LinkedList<Item> shopItems) {
//		this.shopItems = shopItems;
//	}
//
//	public LinkedList<PickUp> getShopPickups() {
//		return shopPickups;
//	}
//
//	public void setShopPickups(LinkedList<PickUp> shopPickups) {
//		this.shopPickups = shopPickups;
//	}


}
