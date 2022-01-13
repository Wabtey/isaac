package Dungeon;

import java.util.ArrayList;
import java.util.List;

import gameWorld.GameWorld;
import gameobjects.moving_entity.Hero;

public class Dungeon {
	private ArrayList<List<GameWorld>> gameWorlds;
	private WorldMap worldMap;
	private int dungeonSize;
	private Hero hero;
	
	public Dungeon(Hero hero, int dungeonSize) {
		this.hero = hero;
		this.dungeonSize = dungeonSize;
		this.gameWorlds = new ArrayList<List<GameWorld>>(dungeonSize);
		this.worldMap = new WorldMap(dungeonSize);
	}
	
	public void initalise() {
		worldMap.newGame();
		createGameWorldArray();
		System.out.println(worldMap.getWorldMap().get(0));
		System.out.println(worldMap.getWorldMap().get(1));
		System.out.println(worldMap.getWorldMap().get(2));
		System.out.println(worldMap.getWorldMap().get(3));
		createGameWorlds();
		affichage();
		System.out.println(gameWorlds.isEmpty());
		System.out.println("ras");
	}

	private void affichage(){
		for (int i = 0; i < gameWorlds.size(); i++) {
			for (int j = 0; j < gameWorlds.get(i).size(); j++) {
				System.out.print(gameWorlds.get(i).get(j) + " ");
			}
			System.out.println();
		}	
	}
	
	private void createGameWorldArray() {
		ArrayList<GameWorld> temp = new ArrayList<GameWorld>(dungeonSize);
		temp.add(new GameWorld(hero));
		for (int i = 0; i <= dungeonSize-1; i++) {
			gameWorlds.add(temp);
			gameWorlds.get(i).add(new GameWorld(hero));
		}
	}
	private void createGameWorlds() {
		for (int i = 0; i <= dungeonSize-1; i++) {
			for (int j = 0; j <= dungeonSize; j++) {
				if (worldMap.getWorldMap().get(i).get(j) == 0) {
					ArrayList<GameWorld> temp = new ArrayList<GameWorld>(dungeonSize);
					temp.addAll(gameWorlds.get(i));
					temp.set(j, null);
					gameWorlds.set(i, temp);
				}
			}
		}
	}
	
	public ArrayList<List<GameWorld>> getGameWorld(){
		return gameWorlds;
	}
}
