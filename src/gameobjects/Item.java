package gameobjects;

import java.util.ArrayList;

import libraries.StdDraw;
import libraries.Vector2;
import resources.RoomInfos;

public class Item {

	//TODO choose one of thoses : statsChange or allStats (type of List)
	private double[] statsChange; //change to statsChange (can be negative)
	private ArrayList<Double> allStats;
	
	//can be negative
	private int heartContainer;
	private double redHeart; //Full heal
	private double blueHeart;
	
	private double speed;
	private double tearRate;
	private double damage;
	private double range;
	private double shootSpeed;
	
	private double luck;
	
	private double devilDeal;
	private double angelRoom;
	
	private int gold;
	private int bomb;
	private int key;
	
	private double heroSize;	//Multiplier changing the heroSize change @see in CreaturesInfos heroSize : 0.7 atm
	
	private Vector2 position;
	private Vector2 size;
	
	private String imagePath;
	
//--CONSTRUCTORS PLAINS----------------------
	
	/**
	 * Every statistics
	 * Be careful with healing (TODO : When taking a item don't overpass the max of RedHeart)
	 * @param HC
	 * @param RH
	 * @param BH
	 * @param speed
	 * @param tearRate
	 * @param damage
	 * @param range
	 * @param shootSpeed
	 * @param luck
	 * @param devilDeal
	 * @param angelRoom
	 * @param gold
	 * @param bomb
	 * @param key
	 * @param heroSize
	 * @param imagePath
	 */
	public Item(int HC, double RH, double BH, double speed, double tearRate, double damage, double range, double shootSpeed,
			   double luck, double devilDeal, double angelRoom, int gold, int bomb, int key, double heroSize, Vector2 position, Vector2 size, String imagePath) {
		this.heartContainer = HC;
		this.redHeart = RH + HC;
		this.blueHeart = BH;
		
		this.speed = speed;
		this.tearRate = tearRate;
		this.damage = damage;
		this.range = range;
		this.shootSpeed = shootSpeed;
		
		this.luck = luck;
		
		this.devilDeal = devilDeal;
		this.angelRoom = angelRoom;
		
		this.gold = gold;
		this.bomb = bomb;
		this.key = key;
		
		this.heroSize = heroSize;
		
		this.position = position;
		this.size = size;
		this.imagePath = imagePath;
	}
	
	/**
	 * Every Utilities
	 * @param luck
	 * @param devilDeal
	 * @param angelRoom
	 * @param gold
	 * @param bomb
	 * @param key
	 * @param heroSize
	 * @param imagePath
	 */
	public Item(double luck, double devilDeal, double angelRoom,
			    int gold, int bomb, int key, double heroSize,
			    Vector2 position, Vector2 size, String imagePath) {
		
		this.luck = luck;
		
		this.devilDeal = devilDeal;
		this.angelRoom = angelRoom;
		
		this.gold = gold;
		this.bomb = bomb;
		this.key = key;
		
		this.position = position;
		this.size = size;
		this.imagePath = imagePath;
		
		//--AVOID adding null statistic to hero--
		
		this.heartContainer = 0;
		this.redHeart = 0;
		this.blueHeart = 0;
		
		this.speed = 0;
		this.tearRate = 0;
		this.damage = 0;
		this.range = 0;
		this.shootSpeed = 0;
		
		this.heroSize = 0;
	}
	
	/**
	 * Every HPs
	 * Be careful with healing (TODO : When taking a item don't overpass the max of RedHeart)
	 * @param HC
	 * @param RH
	 * @param BH
	 * @param imagePath
	 */
	public Item(int HC, double RH, double BH, Vector2 position, Vector2 size, String imagePath) {
		this.heartContainer = HC;
		this.redHeart = RH + HC;
		this.blueHeart = BH;
		
		this.position = position;
		this.size = size;
		this.imagePath = imagePath;
		
		//--AVOID adding null statistic to hero--
		
		this.speed = 0;
		this.tearRate = 0;
		this.damage = 0;
		this.range = 0;
		this.shootSpeed = 0;
		
		this.luck = 0;
		
		this.devilDeal = 0;
		this.angelRoom = 0;
		
		this.gold = 0;
		this.bomb = 0;
		this.key = 0;
		
		this.heroSize = 0;
	}
	
	
	/**
	 * Every basic stats
	 * @param speed
	 * @param tearRate
	 * @param damage
	 * @param range
	 * @param shootSpeed
	 * @param luck
	 * @param imagePath
	 */
	public Item(double speed, double tearRate, double damage, double range, double shootSpeed,
			   double luck, Vector2 position, Vector2 size, String imagePath) {
		
		this.speed = speed;
		this.tearRate = tearRate;
		this.damage = damage;
		this.range = range;
		this.shootSpeed = shootSpeed;
		
		this.luck = luck;
		
		this.position = position;
		this.size = size;
		this.imagePath = imagePath;
		
		//--AVOID adding null statistic to hero--
		
		this.heartContainer = 0;
		this.redHeart = 0;
		this.blueHeart = 0;
		
		this.devilDeal = 0;
		this.angelRoom = 0;
		
		this.gold = 0;
		this.bomb = 0;
		this.key = 0;
		
		this.heroSize = 0;
	}
	
	
//	public Item(ArrayList<Integer> allStats) {
//		this.allStats = allStats;
//	}
	
//--DRAW--------------------------------------------
	
	public void drawGameObject() {
		
		StdDraw.picture(position.getX(), position.getY(), imagePath);
		//TODO create temp Pedestal with casual comportement
	}
	
//--Export ITEM Stats-------------------------------
	

	/**
	 * 
	 * @return allStats in a array
	 */
	private double[] convertAllStatsIntoList(){
		
		double[] allStats = new double[15];
		
		allStats[0]=getHeartContainer();
		allStats[1]=getRedHeart();
		allStats[2]=getBlueHeart();
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
		allStats[14]=getheroSize();
		
		return allStats;
	}
	
//--GETTERS/SETTERS---------------------------------
	
	public Vector2 getPosition() {
		return position;
	}

	public void setPosition(Vector2 position) {
		this.position = position;
	}

	public Vector2 getSize() {
		return size;
	}

	public void setSize(Vector2 size) {
		this.size = size;
	}

	public String getImagePath() {
		return imagePath;
	}

	public void setImagePath(String imagePath) {
		this.imagePath = imagePath;
	}
	
//--HERO modifier------------------------------------------
	
	public double getheroSize() {
		return heroSize;
	}

	public void setheroSize(double heroSize) {
		this.heroSize = heroSize;
	}

//--STATS GLOBAL-------------------------------------------
	

	public double[] getArrayOfStats() {
		return convertAllStatsIntoList();
	}

	@Deprecated
	public void setStatsChange(double[] statsChange) {
		this.statsChange = statsChange;
	}
	
	@Deprecated
	public ArrayList<Double> getListOFStats(){
		return null;
		//return convertAllStatsIntoList();
	}
	
	@Deprecated
	public ArrayList<Double> getAllStats() {
		return allStats;
	}
	
	@Deprecated
	public void setAllStats(ArrayList<Double> allStats) {
		this.allStats = allStats;
	}

//--HP----------------------------------------------

	public int getHeartContainer() {
		return heartContainer;
	}

	public void setHeartContainer(int heartContainer) {
		this.heartContainer = heartContainer;
	}

	public double getRedHeart() {
		return redHeart;
	}

	public void setRedHeart(double redHeart) {
		this.redHeart = redHeart;
	}

	public double getBlueHeart() {
		return blueHeart;
	}

	public void setBlueHeart(double blueHeart) {
		this.blueHeart = blueHeart;
	}

//--STATS-------------------------------------------
	public double getSpeed() {
		return speed;
	}

	public void setSpeed(double speed) {
		this.speed = speed;
	}

	public double getTearRate() {
		return tearRate;
	}

	public void setTearRate(double tearRate) {
		this.tearRate = tearRate;
	}

	public double getDamage() {
		return damage;
	}

	public void setDamage(double damage) {
		this.damage = damage;
	}

	public double getRange() {
		return range;
	}

	public void setRange(double range) {
		this.range = range;
	}

	public double getShootSpeed() {
		return shootSpeed;
	}

	public void setShootSpeed(double shootSpeed) {
		this.shootSpeed = shootSpeed;
	}

	public double getLuck() {
		return luck;
	}

	public void setLuck(double luck) {
		this.luck = luck;
	}

//--DEAL LUCK-----------------------------------

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
	
//--PICK UP-----------------------------------

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


}