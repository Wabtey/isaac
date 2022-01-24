package gameobjects.moving_entity;

import java.util.LinkedList;

import gameobjects.stuff.*;
import gameobjects.stuff.pickup.*;
import libraries.StdDraw;
import libraries.Vector2;
import resources.CreaturesInfos;
import resources.ImagePaths;

public class Hero extends Living_Creature
{
	private int heartContainer;
	
	private double damage_multiplier;
	
	private double blueHeart;
	private double luck;
	private double devilDeal;	//chance to spawn a extra room after defeting the boss
	private double angelRoom;	//between 0.00 and 100.00
	
	private int gold;
	private int bomb;
	private int key;
	
	private LinkedList<Item> items; //To keep up the order of item took 
									//(situation : remove one of them + one of them was a damage multiplier)
	
	private boolean showHUD;
	
	//TRICHE
	private boolean cheating;
	
	private boolean invincibility;
	private boolean ultraSpeed;
	private boolean Powerful;
	
	private double[] statsRecovery;



	
	public Hero(Vector2 position, Vector2 size,
			    double redHeart, double blueHeart, 
			    double speed, double tearRate, double damage, double range, double shootSpeed,
			    LinkedList<Item> starterItems, String imagePath) 
	{
		super(position, size, redHeart, speed, tearRate, damage, range, shootSpeed, imagePath);
		this.heartContainer = (int)redHeart;
		this.blueHeart = blueHeart;
		
		this.showHUD = true;
		
		items = starterItems;
	}
	
	public void updateGameObject() {
		super.updateGameObject();
		move();
		
		//--TRICHE------------And no OneShooted gameplay
		if (!isInvincible() && getInvincibilityFrames()>0 ) //
			decreaseInvincibilityFrames();
		
//		//TODO clean this place up
//		setCheating(isUltraSpeed() || isPowerful());
//		if(!isCheating())
//			this.statsRecovery = convertAllStatsIntoList();
//		for(int i=0; i<statsRecovery.length; i++) {
//			System.out.print(statsRecovery[i]+" ");
//			if(i==statsRecovery.length-1)
//				System.out.println();
//		}
		
	}
	
//--STATS ARRAY-----------------------------------
	/**
	 * Duplicated methods from Item class
	 * @return allStats in a array
	 */
	@Deprecated
	private double[] convertAllStatsIntoList(){
		
		double[] allStats = new double[15];
		
		allStats[0]=getheartContainer();
		allStats[1]=getRedHeart();
		allStats[2]=getblueHeart();
		allStats[3]=getSpeed();
		allStats[4]=getTearRate();
		allStats[5]=getDamage();
		allStats[6]=getRange();
		allStats[7]=getShootSpeed();
		allStats[8]=getLuck();
		allStats[9]=getDevilDeal();
		allStats[10]=getAngelRoom();
		allStats[11]=getGold();
		allStats[12]=getBomb();
		allStats[13]=getKey();
		//allStats[14]=getheroSize();
		
		return allStats;
	}
	
//--ITEM----------------------------------------------
	
	public boolean takeItem(Item stuff) {
		boolean currentlyRisingItem= false; //TODO code better -> create methods and getter and attribute to know if it's true or false
		
		//TODO use convert array of (double)stats into current stats or just add each stats from the item
		//TODO sprite of rising item
		//TODO if not currently taking a Item tempo multiple takes
		
		if(!currentlyRisingItem) {
			addStats(stuff);
			return true;
		}
		return false;
		
		
	}
	
	private void addStats(Item stuff) {
		
		double[] allStats = stuff.getArrayOfStats();
		
		setheartContainer(getheartContainer()+(int)allStats[0]);
		if(getRedHeart() != getheartContainer()) {
			if(getRedHeart()+allStats[1]<=getheartContainer())
				setRedHeart(getRedHeart()+allStats[1]);
			else
				setRedHeart(getheartContainer());
		}
		setblueHeart(getblueHeart()+allStats[2]);

		setSpeed(super.getSpeed()+allStats[3]); 
		setTearRate(super.getTearRate()+allStats[4]);
		setDamage(super.getDamage()+allStats[5]);
		setRange(super.getRange()+allStats[6]);
		setShootSpeed(super.getShootSpeed()+allStats[7]);
		
		setLuck(getLuck()+allStats[8]);
		setDevilDeal(getDevilDeal()+allStats[9]); //TODO verify and implement deal after boss
		setAngelRoom(allStats[10]);
		
		setGold(getGold()+(int)allStats[11]);
		setBomb(getBomb()+(int)allStats[12]);
		setKey(getKey()+(int)allStats[13]);
		
		double sizeModifier = allStats[14];
		if(sizeModifier>0)
			setSize(new Vector2(getSize().getX()*sizeModifier, getSize().getY()*sizeModifier));
		
		//setInvincibilityFrame(allStats[?]);
		
		items.add(stuff);
	}
	
	
//--PICKUP--------------------------------------------
	
	/**
	 * 
	 * @param the PickUp that Hero picked up
	 * @return true if Hero has taked it, false if it will remain in the room
	 */
	public boolean hasPickedUp(PickUp thing)
	{
		boolean success = false;

		//TODO implement blue heart
		if (thing instanceof Heart) {
			Heart heal = (Heart) thing;
			if (heal.getColor() == "red") {
				if (getRedHeart() + heal.getValue() < heartContainer) {
					setRedHeart(getRedHeart() + heal.getValue());
					success = true;
				} else if (getRedHeart() >= heartContainer) {
					success = false; // to permit the player to play with the heart (implementing if !success then
										// apply speed+inertie to move the pick
				} else if (getRedHeart() + heal.getValue() > heartContainer) {
					setRedHeart(heartContainer); // Full heal
					success = true;
				}
			}else if(heal.getColor()=="blue") {
				setblueHeart(getblueHeart()+heal.getValue());
				success = true;
			}
		}
		
		if(thing instanceof Coin) {
			Coin pay = (Coin)thing;
			if(this.gold+pay.getValue()<99) {
				this.gold+=pay.getValue();
				success=true;
			}else {
				this.gold=99;
				success=true; //not aloid to play with coin/bomb/key
			}
		}
		
		if(thing instanceof Key) {
			if(this.key+1<99) {
				this.key++;
				success = true;
			}else {
				this.key=99;
				success = true;
			}

		}
			
		
		if(thing instanceof Bomb) {
			if(this.bomb+1<99) {
				this.bomb++;
				success = true;
			}else {
				this.bomb=99;
				success = true;
			}
		}
		System.out.println("coin : "+getGold()+"\n"
						  +"bomb: "+getBomb()+"\n"
						  +"key: "+getKey()+"\n");
		return success;
	}
	
	
//--HIT-----------------------------------------------
	
	public void getHitted(double damage) {
		if (getInvincibilityFrames()==0) {
			if(getblueHeart()!=0)
				if (damage>=getblueHeart()+getRedHeart()) {
					setblueHeart(0);
					setRedHeart(0);
				}
				else if (damage>=getblueHeart()) {
					setRedHeart(getRedHeart()-(damage-getblueHeart()));
					setblueHeart(0);
				}
				else
					setblueHeart(getblueHeart()-damage);
			else if (damage>=getRedHeart())
				setRedHeart(0);
			else 
				setRedHeart(getRedHeart()-damage);
			
			addInvincibilityFrames(CreaturesInfos.HERO_INVINCIBILITY);
		}
	}

//--DRAW HERO and HUD----------------------------------
	
	public void drawGameObject()
	{
		//ISAAC Spite
		StdDraw.picture(getPosition().getX(), getPosition().getY(), getImagePath(),
						getSize().getX(), getSize().getY());
		if(showHUD)
			drawHUD();
		
	}
	
	private void drawHUD()
	{
		drawHealhMeter();	
		drawStatsFlex();	
	}
	
	private void drawHealhMeter()
	{
		double heigth = 0.9;
		// Red Heart //TODO magical number
		for (int i = 0; i < getheartContainer(); i++) {

			double pos;
			if (i == 0)
				pos = 0.1;
			else
				pos = 0.1 + 0.05 * (i);

			StdDraw.picture(pos, heigth, ImagePaths.EMPTY_HEART_HUD, 0.05, 0.05, 0);
			if (getRedHeart() - i > 0)
				if (getRedHeart() - i == 0.5)
					StdDraw.picture(pos, heigth, ImagePaths.HALF_HEART_HUD, 0.05, 0.05, 0);
				else
					StdDraw.picture(pos, heigth, ImagePaths.HEART_HUD, 0.05, 0.05, 0);

		}

		// Blue/Black Heart //TODO magical number
		for (int i = 0; i < getblueHeart(); i++) {
			double posR = 0.1 + 0.05 * (getheartContainer());
			double posB = posR + 0.05 * (i);
			if (getRedHeart() - i > 0) {
				if (getblueHeart() - i == 0.5) {
					StdDraw.picture(posB, heigth, ImagePaths.HALF_BLUE_HEART_HUD, 0.05, 0.05, 0);
					StdDraw.picture(posB, heigth, ImagePaths.HALF_BLUE_HEART_HUD, 0.05, 0.05, 0); // fake solution for
																								// shadows
				} else {
					StdDraw.picture(posB, heigth, ImagePaths.BLUE_HEART_HUD, 0.05, 0.05, 0);
					StdDraw.picture(posB, heigth, ImagePaths.BLUE_HEART_HUD, 0.05, 0.05, 0); // fake solution for shadows
				}
			}

		}
	}
	/**
	 * HUD addition that appears on the left side of the screen that gives numerical values to the player's current stats
	 */
	private void drawStatsFlex()
	{
		StdDraw.picture(0.05, 0.5, ImagePaths.FOUND_HUD, 0.08, 0.691764, 0); //TODO magical number
	}

//--GETTER/SETTER-----------------------------------------
	
	//--SHOW OR NOT THE HUD-------------------------------
	public void changeHUD() {
		this.showHUD=!showHUD;
	}
	
	//--OVERRIDE To ALLOWED CHETING NICELY
	public double getSpeed() {
		if(isUltraSpeed())
			return CreaturesInfos.BROKEN_SPEED;
		else
			return super.getSpeed(); //this current stats is preserved from changes due to cheat
	}
	
	public double getTearRate() {
		if(isPowerful())
			return CreaturesInfos.BROKEN_TEAR_RATE;
		else
			return super.getTearRate();
	}

	
	public double getDamage() {
		if(isPowerful())
			return CreaturesInfos.BROKEN_DAMAGE;
		else
			return super.getDamage();
	}

	public double getRange() {
		if(isPowerful())
			return CreaturesInfos.BROKEN_RANGE;
		else
			return super.getRange();
	}

//--CURRENT STATS--
	
	public int getheartContainer() {
		return heartContainer;
	}
	
	public double getDamage_multiplier() {
		return damage_multiplier;
	}

	public void setDamage_multiplier(double damage_multiplier) {
		this.damage_multiplier = damage_multiplier;
	}
	
	public void setheartContainer(int heartContainer) {
		this.heartContainer = heartContainer;
	}
	public double getblueHeart() {
		return blueHeart;
	}
	public void setblueHeart(double blueHeart) {
		this.blueHeart = blueHeart;
	}

	public double getLuck() {
		return luck;
	}

	public void setLuck(double luck) {
		this.luck = luck;
	}

	public double getDevilDeal() {
		return devilDeal;
	}

	public void setDevilDeal(double devilDeal) {
		this.devilDeal = devilDeal;
	}

	public double getAngelRoom() {
		return angelRoom;
	}

	public void setAngelRoom(double angelRoom) {
		this.angelRoom = angelRoom;
	}

	public int getGold() {
		return gold;
	}

	public void setGold(int gold) {
		this.gold = gold;
	}

	public int getBomb() {
		return bomb;
	}

	public void setBomb(int bomb) {
		this.bomb = bomb;
	}

	public int getKey() {
		return key;
	}

	public void setKey(int key) {
		this.key = key;
	}

//--ITEMS LIST--------------------------------
	
	public LinkedList<Item> getItems() {
		return items;
	}
	
	@Deprecated
	public void setItems(LinkedList<Item> items) {
		this.items = items;
	}

//--TRICHE------------------------------------

	public boolean isCheating() {
		return cheating;
	}

	public void setCheating(boolean cheating) {
		this.cheating = cheating;
	}

	public double[] getStatsRecovery() {
		return statsRecovery;
	}

	public void setStatsRecovery(double[] statsRecovery) {
		this.statsRecovery = statsRecovery;
	}

	public boolean isInvincible() {
		return invincibility;
	}

	public void changeInvincibility() {
		this.invincibility = !invincibility;
		addInvincibilityFrames(1);
	}

	public boolean isUltraSpeed() {
		return ultraSpeed;
	}


	public void changeUltraSpeed() {
		this.ultraSpeed = !ultraSpeed;
	}
	
	public boolean isPowerful() {
		return Powerful;
	}

	public void setPowerful(boolean Powerful) {
		this.Powerful = Powerful;
	}

	public void changePowerful() {
		this.Powerful=!Powerful;
	}

	
	

}
	
	