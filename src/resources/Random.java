package resources;

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
    Nothing (< 0.22, base 22% chance)
    A coin (0.22 - 0.37, base 15% chance)
    A heart (0.37 - 0.52, base 15% chance)
    A key (0.52 - 0.72, base 20% chance)
    A bomb (0.72 - 0.87, base 15% chance)
    A chest (<0.87) (for now nothing)
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
}
