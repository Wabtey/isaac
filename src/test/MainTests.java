package test;

import gameobjects.stuff.Item;
import resources.ItemInfos;

public class MainTests {

	public static void main(String[] args) {
		Test toutcapourca = new Test();
		toutcapourca.testOfWhichValue();
		System.out.println("--");
		toutcapourca.testOfWhichValue2();
		
		ItemInfos.generatePool();
		Item reward = ItemInfos.SHOP_POOL.remove();
		System.out.println(reward.getImagePath());

	}

}
