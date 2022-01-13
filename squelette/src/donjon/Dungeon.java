package donjon;

import java.util.ArrayList;
import java.util.List;

import gameWorld.GameWorld;

public class Dungeon {
	private ArrayList<List<GameWorld>> gameWorlds;
	
	public Dungeon(ArrayList<List<Integer>> worldMap) {
		this.gameWorlds = new ArrayList<List<GameWorld>>(worldMap.size());
		for (int i=0; i<=worldMap.size(); i++) {
			for (int j = 0; j <= worldMap.size(); j++) {
				if (worldMap.get(i).get(j)==1);
				//ceci est un commentaire de code modifier
				//ceci est un deuxieme commentaire de code a modifier
			}
		}
	}
	
}
