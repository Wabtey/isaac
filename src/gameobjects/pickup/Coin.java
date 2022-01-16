package gameobjects.pickup;

import libraries.Vector2;
import resources.ImagePaths;
import resources.PickUpInfos;
import resources.Random;

public class Coin extends PickUp{

	private int value;
	private String image;
	
	public Coin(Vector2 position) {
		super(position);
		// TODO Auto-generated constructor stub
		generate();
	}
	
	public Coin(Vector2 position, int value, String image) {
		super(position);
		this.value=value;
		this.image=image;
	}
	
	private void generate() {
		whichValue();
	}
	
	//TODO is the fact that we roll two time does not interfere with the drop result ?
	private void whichValue() {
		if(Random.SuccessByPercentage(PickUpInfos.NICKEL_DROP)) {
			this.value = PickUpInfos.NICKEL_VALUE;
			this.image = ImagePaths.NICKEL;
		}else if(Random.SuccessByPercentage(PickUpInfos.DIME_DROP)) {
			this.value = PickUpInfos.DIME_VALUE;
			this.image = ImagePaths.DIME;
		}else {
			this.value = PickUpInfos.COIN_VALUE;
			this.image = ImagePaths.COIN;
		}
			
	}

	
	
	
//--GETTERS/SETTERS-----------------------------
	
	public int getValue() {
		return value;
	}

	public void setValue(int value) {
		this.value = value;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

}
