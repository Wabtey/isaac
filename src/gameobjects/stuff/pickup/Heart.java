package gameobjects.stuff.pickup;

import libraries.StdDraw;
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
	
	public Heart(Vector2 position, String color, double value, String image) {
		super(position);
		this.color = color;
		this.value = value;
		this.image = image;
	}

//--GRAPHIC-----------------------------------
	public void drawGameObject() {
		StdDraw.picture(getPosition().getX(), getPosition().getY(), getImage(),
				getSize().getX(), getSize().getY());
	}
	
	
//--CREATE HEART's PROPERTIES-----------------
	private void generate() {
		redOrBlue();
		entireOrHalf(color);
	}
	
	private void redOrBlue() {
		if(Random.SuccessByPercentage(PickUpInfos.BLUE_HEART_DROP)) {
			this.color = PickUpInfos.BLUE_HEART_COLOR;
		}else
			this.color = PickUpInfos.RED_HEART_COLOR;
			
	}
	
	/**
	 * set Image from value and color
	 * 
	 * @param color
	 */
	private void entireOrHalf(String color) {
		
		if (Random.SuccessByPercentage(PickUpInfos.HALF_HEART_DROP)) {
			this.value = PickUpInfos.HALF_HEART_VALUE;
			if (color == PickUpInfos.BLUE_HEART_COLOR) {
				this.image = ImagePaths.HALF_BLUE_HEART_PICKABLE;
			} else // atm no other color than red or blue
				this.image = ImagePaths.HALF_HEART_PICKABLE;
		} else {
			this.value = PickUpInfos.FULL_HEART_VALUE;
			if (color == PickUpInfos.BLUE_HEART_COLOR) {
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
