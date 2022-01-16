package gameobjects;

public class Item {

	private double[] statsUp; //change to statsChange (can be negative)
	private double[] statsDown;
	
	//can be negative
	private int heartContainer;
	public double[] getStatsUp() {
		return statsUp;
	}

	

	private double blueHeart;
	
	private double speed;
	private double tearRate;
	private double damage;
	private double range;
	private double shootSpeed;
	private double luck;
	
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
	}
	
	public void setStatsUp(double[] statsUp) {
		this.statsUp = statsUp;
	}

	public double[] getStatsDown() {
		return statsDown;
	}

	public void setStatsDown(double[] statsDown) {
		this.statsDown = statsDown;
	}

	public int getHeartContainer() {
		return heartContainer;
	}

	public void setHeartContainer(int heartContainer) {
		this.heartContainer = heartContainer;
	}

	public double getBlueHeart() {
		return blueHeart;
	}

	public void setBlueHeart(double blueHeart) {
		this.blueHeart = blueHeart;
	}

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

	public String getImagePath() {
		return imagePath;
	}

	public void setImagePath(String imagePath) {
		this.imagePath = imagePath;
	}
}
