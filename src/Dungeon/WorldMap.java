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
		addBossFrame();
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
		int counter = numberOfRooms-1;
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
	
	private void addBossFrame() {
		Vector2 beforeBoss = choseAFrameBeforeBoss();
		Vector2 bossFrame = beforeBoss.addVector(randomDirection());
		int compteur = 0;
		while(!(isAnEligibleBossFrame(bossFrame) && checkNeighborhood(bossFrame,beforeBoss))) {
			beforeBoss = choseAFrameBeforeBoss();
			bossFrame = beforeBoss.addVector(randomDirection());
			compteur++;
			System.out.println(compteur);
		}
			List<Integer> temp = new ArrayList<Integer>();
			temp.addAll(tab.get((int) bossFrame.getX()));
			temp.set((int)bossFrame.getY(), 2);
			tab.set((int) bossFrame.getX(), temp);
		
	}
	
	private Vector2 choseAFrameBeforeBoss() {
		List<Vector2> temp = new LinkedList<Vector2>();
		for (int i = 0 ; i<= tab.size()-1; i++) {
			for (int j = 0; j<=tab.size()-1; j++) {
				if (tab.get(i).get(j)==1)
					temp.add(new Vector2(i,j));
			}
		}
		return (randomElement(temp));
	}
	
	private Vector2 randomElement(List<Vector2> liste) {
		int randomIndice = (int) Math.round(Math.random()*(liste.size()-1));
		return liste.get(randomIndice);
	}
	
	private boolean isAnEligibleBossFrame(Vector2 frame) {
		if (frame.getX()>=tab.size() || frame.getX()<0 ||frame.getY()>=tab.size() || frame.getY()<0)
			return false;
		return true;
	}
	
	private boolean isAnEligibleFrame(Vector2 frame) {
		if (tab.get((int)frame.getX()).get((int) frame.getY())==null)
			return false;
		int tabframe = tab.get((int) frame.getX()).get((int) frame.getY());
		return (tabframe != 0 && canICreateARoomHere((int)frame.getX(),(int)frame.getY()));
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
	
	//-> cet horreur fonctionne
	private boolean checkNeighborhood(Vector2 frame, Vector2 officialNeighbor) {
		Integer X = (int) frame.getX();
		Integer Y = (int) frame.getY();
		Integer neighbor = tab.get((int)officialNeighbor.getX()).get((int)officialNeighbor.getY());
		Integer haut = null;
		Integer bas = null;
		Integer droite = null; 
		Integer gauche = null;
		if (X<(tab.size()-1))
			haut = tab.get(X+1).get(Y);
		if (X>0)
			bas = tab.get(X-1).get(Y);
		if (Y<(tab.size()-1))
			droite = tab.get(X).get(Y+1);
		if (Y>0)
			gauche = tab.get(X).get(Y-1);
		if(haut==null || haut == 0 || new Vector2(X+1,Y).equals(officialNeighbor)) {
			if(bas==null|| bas == 0 || new Vector2(X-1,Y).equals(officialNeighbor)) {
				if (droite==null|| droite == 0 || new Vector2(X,Y+1).equals(officialNeighbor)) {
					if (gauche==null|| gauche == 0 || new Vector2(X,Y-1).equals(officialNeighbor))
						return true;
				}
			}
		}
		return false;
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
