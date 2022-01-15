package resources;

public class Random {

	/**
	 * 
	 * @param double: percentage
	 * @return boolean: the Success of the 
	 */
	public static boolean SuccessByPercentage(double percentage) {
		double result = Math.random()*100;
		return result < percentage;
	}
}
