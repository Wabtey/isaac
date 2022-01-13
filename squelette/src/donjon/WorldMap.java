package donjon;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

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
		for (int i = 0; i <= size-1; i++) {
			tab.add(Arrays.asList(0,0,0,0,0,0,0,0,0,0)); //TODO evidement ca va pas il faut automatiser tout ca
		
		}
	}
	
	public void newGame() {
		createRoomPath(createListOfFrames());
		affichage();
	}

	private void affichage(){
		for (int i = 0; i < tab.size(); i++) {
			for (int j = 0; j < tab.get(i).size(); j++) {
				System.out.print(tab.get(i).get(j) + " ");
			}
			System.out.println();
		}	
	}
	
	private void createRoomPath(LinkedList<Vector2> frames) {
		for (Vector2 frame:frames) {
			int jeSuisX = (int)frame.getX();
			int jeSuisY = (int)frame.getY();
			tab.get(jeSuisX).set(jeSuisY, 1);
			
		}
	}
	
	
	private LinkedList<Vector2> createListOfFrames() {
		// on choisit la premiere case du tableau aleatoirement
		Vector2 base = randomFrameInArray();
		LinkedList<Vector2> frames = new LinkedList<Vector2>();
		frames.add(base);
		int counter = numberOfRooms/2;
		while (counter != 0) {
			frames.addAll(selectFrames(frames));
			counter--;
		}
		return frames;
	}
	
	private Vector2 randomFrameInArray(){
		int x = (int) (Math.random() * numberOfRooms);
		int y = (int) (Math.random() * numberOfRooms);
		return new Vector2(x,y);
	}
	
	//Pour chaque frame on selectionne une nouvelle frame adjacente
	private LinkedList<Vector2> selectFrames(LinkedList<Vector2>frames) {
		LinkedList<Vector2> newFrames = frames;
		LinkedList<Vector2> temp = new LinkedList<Vector2>();
		int i = 0;
		for(Vector2 frame:newFrames) {// ce for peut entrainer la creation de trop de room on control le resulats a l'aide d'une fonctin ulterieurement
			Vector2 whoWantToBecomeAOne = randomDirection();
			System.out.println(i + "eme direction: " + whoWantToBecomeAOne);
			int imX = (int)whoWantToBecomeAOne.getX()+(int)frame.getX();
			int imY = (int)whoWantToBecomeAOne.getY()+(int)frame.getY();
			System.out.println("puis-je creer: " +canICreateARoomHere(imX, imY));
			if(canICreateARoomHere(imX, imY)){
				temp.add(new Vector2(imX, imY));
			}
			i++;
		}
		newFrames=checkFramesNumbers(newFrames);
			newFrames.addAll(temp);
		System.out.println(newFrames);
		return newFrames;
	}
	
	private LinkedList<Vector2> checkFramesNumbers(LinkedList<Vector2> toCheck){
		if (toCheck.size()<numberOfRooms)
			return toCheck;
		else if(toCheck.size()==numberOfRooms) {
			numberOfRooms=0;
			return toCheck;
		}
		else {
			while(toCheck.size()>numberOfRooms) {
				toCheck.removeLast();
			}
			return toCheck;
		}
	}
	
	//renvoie une direction aleatoire dans le plan soit (0,1)(0,-1)(1,0)(-1,0)
	private Vector2 randomDirection() {
		double randomValue = Math.round(Math.random()*3);
		System.out.println("la random value: " + randomValue);
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

	public ArrayList<List<Integer>> getTab() {
		return tab;
	}
	
}
