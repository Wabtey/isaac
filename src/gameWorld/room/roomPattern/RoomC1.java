package gameWorld.room.roomPattern;

import java.util.ArrayList;
import java.util.List;

import gameWorld.room.Room;
import gameobjects.Door;
import gameobjects.moving_entity.Hero;
import gameobjects.obstacles.Obstacle;
import resources.RoomInfos;

public class RoomC1 extends Room{

	
	private ArrayList<Obstacle> obstacles;

	public RoomC1(Hero hero, List<Door> doors) {
		super(hero, doors);
		this.obstacles = new ArrayList<Obstacle>(8);
		
//		obstacles.add(RoomInfos.WALL_DOWN); // BAS
//		obstacles.add(RoomInfos.WALL_UP); // HAUT
//		obstacles.add(RoomInfos.WALL_LEFT);// GAUCHE
//		obstacles.add(RoomInfos.WALL_RIGHT);// DROIT
	}

	
	
	
	public ArrayList<Obstacle> getObstacles() {
		return obstacles;
	}

	public void setObstacles(ArrayList<Obstacle> obstacles) {
		this.obstacles = obstacles;
	}
}
