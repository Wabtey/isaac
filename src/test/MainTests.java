package test;

import gameobjects.stuff.Item;
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

	}

}
