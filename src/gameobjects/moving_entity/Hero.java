package gameobjects.moving_entity;

import gameobjects.Item;
import gameobjects.pickup.*;
import libraries.StdDraw;
import libraries.Vector2;
import resources.CreaturesInfos;
import resources.DisplaySettings;
import resources.ImagePaths;

public class Hero extends Living_Creature
{
	private int heartContainer;
	private double blueHeart;
	private double luck;
	private double devilDeal;	//chance to spawn a extra room after defeting the boss
	private double angelRoom;	//between 0.00 and 100.00
	
	private int gold;
	private int bomb;
	private int key;
	
	//TRICHE
	private boolean invincibility;
	private boolean ultraSpeed;
	private boolean powerfull;


	
	public Hero(Vector2 position, Vector2 size,
			    double redHeart, double blueHeart, 
			    double speed, double tearRate, double damage, double range, double shootSpeed,
			    String imagePath) 
	{
		super(position, size, redHeart, speed, tearRate, damage, range, shootSpeed, imagePath);
		this.heartContainer = (int)redHeart;
		this.blueHeart = blueHeart;
	}
	
	public void updateGameObject() {
		super.updateGameObject();
		move();
		
		//--TRICHE------------And no OneShooted gameplay
		if (!isInvincible() && getInvincibilityFrames()>0)
			decreaseInvincibilityFrames();
		if(isUltraSpeed()) {
			setSpeed(0.05*DisplaySettings.SCALE);
		}else
			setSpeed(CreaturesInfos.ISAAC_SPEED);
		if(isPowerfull()) {
			setDamage(50); setTearRate(8);
		}else {
			setDamage(CreaturesInfos.ISAAC_DAMAGE); setTearRate(CreaturesInfos.ISAAC_TEARRATE);
		}
	}
	
//--ITEM----------------------------------------------
	public void takeItem(Item stuff) {
		//TODO use convert array of (double)stats into current stats or just add each stats from the item
		//TODO sprite of rising item
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
		if(thing instanceof Heart) {
			Heart heal = (Heart)thing;
			if(getRedHeart()+heal.getValue() < heartContainer) {
				setRedHeart(getRedHeart()+heal.getValue());
				success = true;
			}else if(getRedHeart()>= heartContainer) {
				success = false; //to permit the player to play with the heart (implementing if !success then apply speed+inertie to move the pick
			}else if(getRedHeart()+heal.getValue() > heartContainer) {
				setRedHeart(heartContainer); //Full heal
				success = true;
			}
		}
		
		if(thing instanceof Coin) {
			Coin pay = (Coin)thing;
			if(this.gold+pay.getValue()<99) {
				this.gold++;
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
			
		
		if(thing instanceof Bomb && this.bomb<99) {
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
		}
	}

//--DRAW HERO and HUD----------------------------------
	
	public void drawGameObject()
	{
		//ISAAC Spite
		StdDraw.picture(getPosition().getX(), getPosition().getY(), getImagePath(),
						getSize().getX(), getSize().getY());
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
		// Red Heart
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

		// Blue/Black Heart
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
		StdDraw.picture(0.1, 0.5, ImagePaths.FOUND_HUD, 0.08, 0.691764, 0);
	}

//--GETTER/SETTER-----------------------------------------
	
	public int getheartContainer() {
		return heartContainer;
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

//--TRICHE------------------------------------
	
	public boolean isInvincible() {
		return invincibility;
	}

	//will be unused
	public void setInvincibility(boolean invisibility) {
		this.invincibility = invisibility;
	}
	
	public void changeInvincibility() {
		this.invincibility = !invincibility;
	}

	public boolean isUltraSpeed() {
		return ultraSpeed;
	}

	//will be unused
	public void setUltraSpeed(boolean ultraSpeed) {
		this.ultraSpeed = ultraSpeed;
	}

	public void changeUltraSpeed() {
		this.ultraSpeed = !ultraSpeed;
	}
	
	public boolean isPowerfull() {
		return powerfull;
	}

	public void setPowerfull(boolean powerfull) {
		this.powerfull = powerfull;
	}

	public void changePowerfull() {
		this.powerfull=!powerfull;
	}
}
	
	