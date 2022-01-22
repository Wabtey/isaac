package gameobjects.stuff.pickup;

import libraries.StdDraw;
import libraries.Vector2;
import resources.ImagePaths;
import resources.PickUpInfos;
import resources.Random;

public class Coin extends PickUp{

	private int value;
	private String image;
	
	public Coin(Vector2 position) {
		super(position);
		generate();
	}
	
	public Coin(Vector2 position, int value, String image) {
		super(position);
		this.value=value;
		this.image=image;
	}
	
	
//--DRAW-----------------------------------------
	
	public void drawGameObject() {
		StdDraw.picture(getPosition().getX(), getPosition().getY(), getImage(),
				getSize().getX(), getSize().getY());
	}

	
	
//--ROOM REWARD DROP-----------------------------
	
	private void generate() {
		whichValue();
	}
	
	/*
	 * is the fact that we roll two time does not interfere with the drop result ?
	 * @see in test.Test for the result :
	 * using the method SuccessByPercentage permit us to respect the drop indicate in PickUpInfos simply
	 */
	private void whichValue() {
		if(Random.SuccessByPercentage(PickUpInfos.DIME_DROP)) {
			this.value = PickUpInfos.DIME_VALUE;
			this.image = ImagePaths.DIME;
		}else if(Random.SuccessByPercentage(PickUpInfos.NICKEL_DROP)) {
			this.value = PickUpInfos.NICKEL_VALUE;
			this.image = ImagePaths.NICKEL;
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
