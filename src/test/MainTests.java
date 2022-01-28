package test;

import gameobjects.stuff.Item;
import libraries.StdDraw;
import resources.ImagePaths;
import resources.ItemInfos;
import resources.RoomInfos;

public class MainTests {

	public static void main(String[] args) {
		Test toutcapourca = new Test();
		toutcapourca.testOfWhichValue();
		System.out.println("--");
		toutcapourca.testOfWhichValue2();
		
		System.out.println("before adding a new item :" + ItemInfos.SHOP_POOL);
		ItemInfos.SHOP_POOL.add(new Item(2, 2, 0, RoomInfos.POSITION_CENTER_OF_ROOM, ItemInfos.ITEM_SIZE, ImagePaths.BLOOD_TEAR));
		System.out.println("after addition"+ItemInfos.SHOP_POOL);
		ItemInfos.generatePool();
		System.out.println("after suffle"+ItemInfos.SHOP_POOL);
		Item reward = ItemInfos.SHOP_POOL.remove();
		System.out.println(reward.getImagePath());
		
		
		/** Test client. */

		    StdDraw.square(.2, .8, .1);
		    StdDraw.filledSquare(.8, .8, .2);
		    StdDraw.circle(.8, .2, .2);

		    StdDraw.setPenColor(StdDraw.BOOK_RED);
		    StdDraw.setPenRadius(.02);
		    StdDraw.arc(.8, .2, .1, 200, 45);

		    // draw a blue diamond
		    StdDraw.setPenRadius();
		    StdDraw.setPenColor(StdDraw.BOOK_BLUE);
		    double[] x = {.1, .2, .3, .2};
		    double[] y = {.2, .3, .2, .1};
		    StdDraw.filledPolygon(x, y);

		    // text
		    StdDraw.setPenColor(StdDraw.BLACK);
		    StdDraw.text(0.2, 0.5, "black text");
		    StdDraw.setPenColor(StdDraw.WHITE);
		    StdDraw.text(0.8, 0.8, "white text");
		
	}

}
