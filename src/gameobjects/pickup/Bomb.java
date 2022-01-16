package gameobjects.pickup;

import libraries.Vector2;
import resources.ImagePaths;

public class Bomb extends PickUp{

	public Bomb(Vector2 position) {
		super(position);
		setImage(ImagePaths.BOMB);
	}

}
