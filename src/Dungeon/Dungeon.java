package Dungeon;

import java.util.ArrayList;
import java.util.List;

import gameWorld.GameWorld;
import gameobjects.moving_entity.Hero;
import test.Cardinal_Points;

public class Dungeon {
	private ArrayList<List<GameWorld>> gameWorlds;
	private GameWorld currentGameWorld;
	private WorldMap worldMap;
	private int dungeonSize;
	private Hero hero;

	public Dungeon(Hero hero, int dungeonSize) {
		this.hero = hero;
		this.dungeonSize = dungeonSize;
		this.gameWorlds = new ArrayList<List<GameWorld>>(dungeonSize);
		this.worldMap = new WorldMap(dungeonSize);
	}
	
	public void updateDugeon() {
		currentGameWorld.processUserInput();
		currentGameWorld.updateGameObjects();
	}

	public void initalise() {
		worldMap.newGame();
		createGameWorldArray();
		createGameWorlds();
		addNeighbors();
		affichage();
		System.out.println(gameWorlds.isEmpty());
		System.out.println("initialisation done");
	}

	private void affichage() {
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
		for (int i = 0; i <= dungeonSize - 1; i++) {
			gameWorlds.add(temp);
			gameWorlds.get(i).add(new GameWorld(hero));
		}
	}

	private void addNeighbors() {
		for (int i = 0; i <= dungeonSize - 1; i++) {
			for (int j = 0; j <= dungeonSize - 1; j++) {
				if (gameWorlds.get(i).get(j) != null) {
					Cardinal_Points temp = findNeighbors(i, j);
					if (!temp.isEmpty())
						gameWorlds.get(i).get(j).setNeighbors(temp);
				}
			}
		}
	}

	private Cardinal_Points findNeighbors(int x, int y) {
		Cardinal_Points neighbors = new Cardinal_Points();
		if (x < dungeonSize - 1 && gameWorlds.get(x + 1).get(y) != null)
			neighbors.setNorth(gameWorlds.get(x + 1).get(y));
		if (x > 0 && gameWorlds.get(x - 1).get(y) != null)
			neighbors.setSouth(gameWorlds.get(x - 1).get(y));
		if (y < dungeonSize - 1 && gameWorlds.get(x).get(y + 1) != null)
			neighbors.setEast(gameWorlds.get(x).get(y + 1));
		if (y > 0 && gameWorlds.get(x).get(y - 1) != null)
			neighbors.setWeast(gameWorlds.get(x).get(y - 1));
		return neighbors;
	}

	private void createGameWorlds() {
		for (int i = 0; i <= dungeonSize - 1; i++) {
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

	public ArrayList<List<GameWorld>> getGameWorld() {
		return gameWorlds;
	}
}
