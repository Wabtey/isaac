package test;

import java.util.LinkedList;
import java.util.List;

import libraries.Vector2;

public class Dungeon {	
	
	private int[][] tab;
	private int numberOfRooms;
	
	public Dungeon(int length, int height) {
		for (int i = 0; i <= length; i++) {
			for (int j = 0; j <= height; j++) {
				tab[i][j] = 0;
			}
		}
	}
	
	private void createRoomPath() {
		//on choisit la premiere case du tableau aleatoirement
		Vector2 base = randomCaseInArray();
		List<Vector2> oned = new LinkedList<>();
		oned.add(base);
		//on va choisir des cases adjacente a chaque case et les changer une a une
		for (int i = 0; i < numberOfRooms; i++) {
			for (Vector2 current:oned) {
				Vector2 newOne = randomDirection();
				if (canICreateARoomHere((int)(current.getX()+newOne.getX()), (int)(current.getY()+newOne.getY())))
					oned.add(newOne);
				else numberOfRooms ++; //si l'ajout d'un oned echoue on recommence ailleur
			}
		}
	}
	
	//renvoie une direction aleatoire dans le plan soit (0,1)(0,-1)(1,0)(-1,0)
	private Vector2 randomDirection() {
		double randomValue = Math.random();
		if (randomValue<(1/4))
			return new Vector2(0,1);
		else if (randomValue>(1/4) && randomValue<(1/2)) 
			return new Vector2(0,-1);
		else if (randomValue>(1/2) && randomValue<(3/4))
			return new Vector2(1,0);
		else
			return new Vector2(-1,0);
	}
	
	private Vector2 randomCaseInArray(){
		int x = (int) (Math.random() * tab[0].length);
		int y = (int) (Math.random() * tab.length);
		return new Vector2(x,y);
	}
	
	private boolean canICreateARoomHere(int x, int y) {
		if (y < 0 || y > tab[0].length || x < 0 || x > tab.length) {
			return false;
		} else {
			return true;
		}
	}
	
}
