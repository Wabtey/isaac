package gameobjects.pickup;

import libraries.Vector2;
import resources.ImagePaths;
import resources.PickUpInfos;
import resources.Random;

public class Heart extends PickUp{

	private String color;
	private double value;
	private String image;
	
	public Heart(Vector2 position) {
		super(position);
		generate();

	}

//--GRAPHIC-----------------------------------
	public void drawGameObject() {

	}
	
	
//--CREATE HEART's PROPERTIES-----------------
	private void generate() {
		redOrBlue();
		entireOrHalf(color);
	}
	
	private void redOrBlue() {
		if(Random.SuccessByPercentage(PickUpInfos.BLUE_HEART_DROP)) {
			this.color = PickUpInfos.BLUE_HEART;
			this.image = ImagePaths.BLUE_HEART_PICKABLE;
		}else
			this.color = PickUpInfos.RED_HEART;
			
	}
	
	/**
	 * set Image from value and color
	 * 
	 * @param color
	 */
	private void entireOrHalf(String color) {
		
		if (Random.SuccessByPercentage(PickUpInfos.HALF_HEART_DROP)) {
			this.value = PickUpInfos.HALF_HEART;
			if (color == PickUpInfos.BLUE_HEART) {
				this.image = ImagePaths.HALF_BLUE_HEART_PICKABLE;
			} else // atm no other color than red or blue
				this.image = ImagePaths.HALF_HEART_PICKABLE;
		} else {
			this.value = PickUpInfos.FULL_HEART;
			if (color == PickUpInfos.BLUE_HEART) {
				this.image = ImagePaths.BLUE_HEART_PICKABLE;
			} else
				this.image = ImagePaths.HEART_PICKABLE;
		}
	}

//--GETTERS/SETTERS------------------------------
	
	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public double getValue() {
		return value;
	}

	public void setValue(double value) {
		this.value = value;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

}