package gameobjects.pickup;

import libraries.Vector2;
import resources.ImagePaths;

public class Coin extends PickUp{

	public Coin(Vector2 position) {
		super(position);
		// TODO Auto-generated constructor stub
		setImage(ImagePaths.COIN);
	}

}
