package resources;

import java.util.LinkedList;

import gameobjects.stuff.Item;

public class Random {

	/**
	 * 
	 * @param double: percentage of success
	 * @return boolean: the Success of the test
	 */
	public static boolean SuccessByPercentage(double percentage) {
		double result = Math.random()*100;
		return result < percentage;
	}
	
	/**
	 * Semi-randomly being placed in rooms.
	 * Formula :			(RandomFloat * Luck * 0.1) + pickupPercent
	 * Rule 1 : RandomFloat and pickupPercent are random numbers between 0 and 1
	 * Rule 2 : Luck is clamped to 0 if Isaac’s luck is less than 0 and 10 if Isaac’s luck is greater than 10
	 * 
	 * That number is then used to determine the reward given after clearing the room, using the following index:
	 * 
     * Nothing (< 0.22, base 22% chance)
     * A coin (0.22 - 0.37, base 15% chance)
     * A heart (0.37 - 0.52, base 15% chance)
     * A key (0.52 - 0.72, base 20% chance)
     * A bomb (0.72 - 0.87, base 15% chance)
     * A chest (<0.87) (for now nothing)
     * 
	 * @return percentage obtained by the above formula
	 */
	public static double roomRewardPercentage(double heroLuck) {
		//Rule 1
		double random = Math.random();
		double pickupPercent = Math.random();
		//Rule 2
		double luck = Math.max(Math.min(heroLuck, 10), 0);
//		double luck = heroLuck;
//		if(luck<0) {
//			luck = 0;
//		}else if(luck>10) {
//			luck = 10;
//		}else
//			luck = (int)luck;
		
		//Formula
		return random*luck*0.1+pickupPercent;
		
	}

	/**
	 * pick An Item From A specific Pool
	 * @param pool
	 * @return item choosed randomly
	 */
	public static Item getRewardPool(String pool) {
		
		Item reward = ItemInfos.HP_UP; //base item
		//System.out.println(reward.getImagePath());
		
		if(pool == ItemInfos.STRING_BOSS_POOL) {
			//int randomNb = pickRandomlyIntoAList(ItemInfos.BOSS_POOL);
			reward = ItemInfos.BOSS_POOL.remove(); //the suffle is done in the itemInfos
		}
		
		else if(pool == ItemInfos.STRING_DEVIL_POOL){
			//int randomNb = pickRandomlyIntoAList(ItemInfos.DEVIL_POOL);
			reward = ItemInfos.DEVIL_POOL.remove();
		}
		
		else if(pool == ItemInfos.STRING_ITEM_POOL){
			//int randomNb = pickRandomlyIntoAList(ItemInfos.ITEM_POOL);
			reward = ItemInfos.ITEM_POOL.remove();
		}else if(pool == ItemInfos.STRING_SHOP_POOL){
			reward = ItemInfos.SHOP_POOL.remove();
		}else {
		
			//int randomNb = pickRandomlyIntoAList(ItemInfos.HP_UP_POOL);
			reward = ItemInfos.HP_UP_POOL.remove(); //TODO : when no more item left make it like there is no doublon except HpUp
		}
		//System.out.println("getRewardPool() return :"+ reward); //TODO test to remove
		
		return reward;
	}
	
	/**
	 * 
	 * @param list
	 * @return a random number between 0 and 
	 */
	public static int pickRandomlyIntoAList(LinkedList<Item> list) {
		int random = (int)Math.random()*(list.size()-1); //TODO carefull about empty list
		System.out.println("pickRandomlyIntoAList() :"+random); //TODO test to remove
		return random;
	}
}
