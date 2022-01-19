package gameobjects;

public class Item {

	private double[] statsChange; //change to statsChange (can be negative)
	
	//can be negative
	private int heartContainer;
	private double redHeart; //
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
	
	private double size;	
	
	private String imagePath;
	
	public Item(int HP, double BH, double speed, double tearRate, double damage, double range, double shootSpeed,
			   String imagePath) {
		this.heartContainer = HP;
		this.blueHeart = BH;
		this.speed = speed;
		this.tearRate = tearRate;
		this.damage = damage;
		this.range = range;
		this.shootSpeed = shootSpeed;
		
		this.imagePath = imagePath;
		
		//TODO create Pedestal
	}
	
//--GETTERS/SETTERS---------------------------------
	
	public String getImagePath() {
		return imagePath;
	}

	public void setImagePath(String imagePath) {
		this.imagePath = imagePath;
	}
	
	public double getSize() {
		return size;
	}

	public void setSize(double size) {
		this.size = size;
	}

//--STATS GLOBAL-------------------------------------------
	
	public double[] getStatsChange() {
		return statsChange;
	}

	public void setStatsChange(double[] statsChange) {
		this.statsChange = statsChange;
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