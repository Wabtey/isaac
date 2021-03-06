package gameloop;

import dungeon.Dungeon;
import gameWorld.GameWorld;
import gameobjects.moving_entity.Hero;
import libraries.StdDraw;
import libraries.Timer;
import resources.DisplaySettings;
import resources.ImagePaths;
import resources.ItemInfos;
import resources.CreaturesInfos;
import resources.RoomInfos;

public class Main
{
	public static void main(String[] args)
	{
		// Hero, world and display initialisation.
		ItemInfos.generatePool(); //TODO find a good place to put it
		Hero isaac = CreaturesInfos.ISAAC;
		Dungeon monde = new Dungeon(isaac, 8);
		monde.initalise();
		initializeDisplay();
		

		// Main loop of the game
		while (!monde.gameOver())
		{
			monde.refreshDungeon();
			//world.processOneStep();
		}
		StdDraw.picture(RoomInfos.POSITION_CENTER_OF_ROOM.getX(), RoomInfos.POSITION_CENTER_OF_ROOM.getY(), ImagePaths.LOSE_SCREEN);
	}

	/*private static void processNextStep(GameWorld world)
	{
		Timer.beginTimer();
		StdDraw.clear();
		world.processUserInput();
		world.updateGameObjects();
		world.drawGameObjects();
		StdDraw.show();
		Timer.waitToMaintainConstantFPS();
	}*/

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
