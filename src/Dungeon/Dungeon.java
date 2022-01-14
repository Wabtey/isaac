package Dungeon;

import java.util.ArrayList;
import java.util.List;

import gameWorld.GameWorld;
import gameobjects.moving_entity.Hero;
import libraries.StdDraw;
import libraries.Timer;
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
		initialiseGameWorlds();
		affichage();
		setCurrentWorld();
		System.out.println("currentWorld: " + currentGameWorld);
		System.out.println("voisin nord:" + currentGameWorld.getNeighbors().getNorth());
		System.out.println("voisin sud:" + currentGameWorld.getNeighbors().getSouth());
		System.out.println("voisin east:" + currentGameWorld.getNeighbors().getEast());
		System.out.println("voisin weast:" + currentGameWorld.getNeighbors().getWeast());
		System.out.println(gameWorlds.isEmpty());
		System.out.println("initialisation done");
	}

	public void refreshDungeon() {
		processNextStep(currentGameWorld);
		GameWorld temp = currentGameWorld.checkDoorGW();
		if (temp != null)
			this.currentGameWorld = temp;
	}

	private static void processNextStep(GameWorld currentGameWorld) {
		Timer.beginTimer();
		StdDraw.clear();
		currentGameWorld.processUserInput();
		currentGameWorld.updateGameObjects();
		currentGameWorld.drawGameObjects();
		StdDraw.show();
		Timer.waitToMaintainConstantFPS();
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
		for (int i = 0; i <= dungeonSize - 1; i++) {
			List<GameWorld> temp = new ArrayList<GameWorld>(dungeonSize);
			for (int j = 0; j <= dungeonSize - 1; j++) {
				temp.add(new GameWorld(hero));
			}
			gameWorlds.add(temp);

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
		if (x > 0 && gameWorlds.get(x - 1).get(y) != null)
			neighbors.setNorth(gameWorlds.get(x - 1).get(y));
		if (x < dungeonSize - 1 && gameWorlds.get(x + 1).get(y) != null)
			neighbors.setSouth(gameWorlds.get(x + 1).get(y));
		if (y < dungeonSize - 1 && gameWorlds.get(x).get(y + 1) != null)
			neighbors.setEast(gameWorlds.get(x).get(y + 1));
		if (y > 0 && gameWorlds.get(x).get(y - 1) != null)
			neighbors.setWeast(gameWorlds.get(x).get(y - 1));
		return neighbors;
	}

	private void createGameWorlds() {
		for (int i = 0; i <= dungeonSize - 1; i++) {
			for (int j = 0; j <= dungeonSize - 1; j++) {
				if (worldMap.getWorldMap().get(i).get(j) == 0) {
					ArrayList<GameWorld> temp = new ArrayList<GameWorld>(dungeonSize);
					temp.addAll(gameWorlds.get(i));
					temp.set(j, null);
					gameWorlds.set(i, temp);
				}
			}
		}
	}

	private void initialiseGameWorlds() {
		for (int i = 0; i <= dungeonSize - 1; i++) {
			for (int j = 0; j <= dungeonSize - 1; j++) {
				if (gameWorlds.get(i).get(j) != null)
					gameWorlds.get(i).get(j).initalise();
			}
		}
	}

	private void setCurrentWorld() {
		for (int i = 0; i <= dungeonSize - 1; i++) {
			for (int j = 0; j <= dungeonSize - 1; j++) {
				if (gameWorlds.get(i).get(j) != null) {
					this.currentGameWorld = gameWorlds.get(i).get(j);
					break;
				}
			}
		}
	}

	public ArrayList<List<GameWorld>> getGameWorld() {
		return gameWorlds;
	}
}
