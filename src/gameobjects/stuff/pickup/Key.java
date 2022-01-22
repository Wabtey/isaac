package gameobjects.stuff.pickup;

import libraries.StdDraw;
import libraries.Vector2;
import resources.ImagePaths;

public class Key extends PickUp{
	
	private String image;

	public Key(Vector2 position) {
		super(position);
		setImage(ImagePaths.KEY); //or this.image = ImagePaths.KEY;
	}
	
	
//--DRAW-------------------------------
	
	public void drawGameObject() {
		StdDraw.picture(getPosition().getX(), getPosition().getY(), getImage(),
				getSize().getX(), getSize().getY());
	}

//--GETTERS/SETTERS--------------------
	
	public String getImage() {
		return image;
	}


	public void setImage(String image) {
		this.image = image;
	}

}
