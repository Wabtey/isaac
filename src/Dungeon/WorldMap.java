package Dungeon;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import libraries.Vector2;

public class WorldMap {	
/**
 * ceci a ete fait de nuit a base de cafer dans un etat de conscience fortement diminuer
 * il conviandra donc de clear tout ce beaux bordel.
 *liste des choses faire:
 *-finir le constructeur
 *
 *
 */


	private ArrayList<List<Integer>> tab ;
	private int numberOfRooms;
	
	public WorldMap(int size) {
		this.tab = new ArrayList<List<Integer>>(size);
		this.numberOfRooms = size;
	}
	
	public void newGame() {
		initalisation();
		createRoomPath(createListOfFrames());
		affichage();
	}
	
	private void initalisation() {
		List<Integer> temp = new ArrayList<Integer>(numberOfRooms);
		for (int i = 0; i <= numberOfRooms; i++) {
			temp.add(0);
			tab.add(temp);
		}
	}

	private void affichage(){
		for (int i = 0; i < tab.size(); i++) {
			for (int j = 0; j < tab.get(i).size(); j++) {
				System.out.print(tab.get(i).get(j) + " ");
			}
			System.out.println();
		}	
	}
	
	private void createRoomPath(ArrayList<Vector2> frames) {
		for (Vector2 frame:frames) {
			List<Integer> temp = new ArrayList<Integer>(numberOfRooms);
			int jeSuisX = (int)frame.getX();
			int jeSuisY = (int)frame.getY();
			temp.addAll(tab.get(jeSuisX));
			temp.set(jeSuisY, 1);
			tab.set(jeSuisX, temp);
					
		}
	}
	
	private ArrayList<Vector2> createListOfFrames() {
		// on choisit la premiere case du tableau aleatoirement
		Vector2 base = randomFrameInArray();
		ArrayList<Vector2> frames = new ArrayList<Vector2>();
		frames.add(base);
		int counter = numberOfRooms;
		while (counter != 0) {
			frames = (selectFrames(frames));
			counter--;
		}
		return frames;
	}
	
	private Vector2 randomFrameInArray(){
		int x = (int) (Math.random() * (numberOfRooms-1));
		int y = (int) (Math.random() * (numberOfRooms-1));
		return new Vector2(x,y);
	}
	
	//On selectionne une frame au hasard a laquelle on ajoutera un voisin
	
	private ArrayList<Vector2> selectFrames(ArrayList<Vector2>frames){
		ArrayList<Vector2> newFrames = frames;
		Vector2 whoWantToBecomeAOne = randomDirection();
		int randomIndice = frames.size();
		randomIndice = (int)(Math.round(Math.random()*(randomIndice-1)));
		int imX = (int)whoWantToBecomeAOne.getX()+(int)frames.get(randomIndice).getX();
		int imY = (int)whoWantToBecomeAOne.getY()+(int)frames.get(randomIndice).getY();
		if(canICreateARoomHere(imX, imY) && !isInArray(frames,new Vector2(imX, imY)) ){
			newFrames.add(new Vector2(imX, imY));
		}else selectFrames(frames);
		return newFrames;
	}
	
	private boolean isInArray(ArrayList<Vector2> elements, Vector2 test) {
		for (Vector2 element: elements) {
			if (element.getX()==test.getX() && element.getY()==test.getY())
				return true;
		}
		return false;
	}
	
	//renvoie une direction aleatoire dans le plan soit (0,1)(0,-1)(1,0)(-1,0)
	private Vector2 randomDirection() {
		double randomValue = Math.round(Math.random()*3);
		if (randomValue==0)
			return new Vector2(0,1);
		else if (randomValue==1) 
			return new Vector2(0,-1);
		else if (randomValue==2)
			return new Vector2(1,0);
		else
			return new Vector2(-1,0);
	}
	
	
	private boolean canICreateARoomHere(int x, int y) {
		if (x>=0 && x<tab.size() && y>=0 && y<tab.size()) {
			return true;
		} else {
			return false;
		}
	}
	
	public ArrayList<List<Integer>> getWorldMap(){
		return tab;
	}
	
}
