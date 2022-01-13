package gameloop;

import Dungeon.Dungeon;
import gameWorld.GameWorld;
import gameobjects.moving_entity.Hero;
import libraries.StdDraw;
import libraries.Timer;
import resources.DisplaySettings;
import resources.CreaturesInfos;
import resources.RoomInfos;

public class Main
{
	public static void main(String[] args)
	{
		// Hero, world and display initialization.
		//NOT THE GOODS STATS (go to CreatureINFOS
		Hero isaac = CreaturesInfos.ISAAC;
		GameWorld world = new GameWorld(isaac);	
		Dungeon monde = new Dungeon(isaac, 5);
		monde.initalise();
		initializeDisplay();

		// Main loop of the game
		while (!world.gameOver())
		{
			processNextStep(world);
		}
	}

	private static void processNextStep(GameWorld world)
	{
		Timer.beginTimer();
		StdDraw.clear();
		world.processUserInput();
		world.updateGameObjects();
		world.drawGameObjects();
		StdDraw.show();
		Timer.waitToMaintainConstantFPS();
	}

	private static void initializeDisplay()
	{
		// Set the window's size, in pixels.
		// It is strongly recommended to keep a square window.
		StdDraw.setCanvasSize(RoomInfos.NB_TILES * DisplaySettings.PIXEL_PER_TILE,
				RoomInfos.NB_TILES * DisplaySettings.PIXEL_PER_TILE);

		// Enables double-buffering.
		// https://en.wikipedia.org/wiki/Multiple_buffering#Double_buffering_in_computer_graphics
		StdDraw.enableDoubleBuffering();
	}
}
