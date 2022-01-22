package test;

import resources.ImagePaths;
import resources.PickUpInfos;
import resources.Random;

public class Test {
	/**
	 * created for prove if "the fact that we roll two time does not interfere with the drop result" was true or false
	 * in whichValue() method from Coin.java
	 * 
	 * RESULTS :
	 * 
WhichValue1():
75895  -> the remaining 75%
18934  -> 20% like NICKEL_DROP
5171   -> 5% like DIME_DROP
--
WhichValue2():
79885
14967 -> when the 5% of dime drop goes for dime it left only the 5 to 20 so 15% to Nickel
5148  -> 5% like intended
	 * we can conclude that the first method win (for respecting final value drop indicate in PickUpInfos)
	 */
	private int value;
	private String image;
	
	private Long monzbi;
	
	public Test() {
		this.monzbi=(long)0;
	}

	private void whichValue() {

		if(Random.SuccessByPercentage(PickUpInfos.DIME_DROP)) {
			this.value = PickUpInfos.DIME_VALUE;
			this.image = ImagePaths.DIME;
			//System.out.println("dime");
		}else if(Random.SuccessByPercentage(PickUpInfos.NICKEL_DROP)) {
			this.value = PickUpInfos.NICKEL_VALUE;
			this.image = ImagePaths.NICKEL;
			//System.out.println("nickel");
		}else {
			this.value = PickUpInfos.COIN_VALUE;
			this.image = ImagePaths.COIN;
			//System.out.println("coin");
		}
			
	}
	
	public void testOfWhichValue() {
		int nbOfCoin = 0;
		int nbOfNickel = 0;
		int nbOfDime = 0;
		int total = 100000;
		for(int i = 0; i<total; i++) {
			whichValue();
			if(getValue()==1) {
				nbOfCoin++;
			}else if(getValue()==5) {
				nbOfNickel++;
			}else
				nbOfDime++;
		}
		System.out.println(nbOfCoin);
		System.out.println(nbOfNickel);
		System.out.println(nbOfDime);
		double percentageOfDime = nbOfDime/total;
		double percentageOfNickel = nbOfNickel/total;
		double percentageOfCoin = nbOfCoin/total;
		
		//System.out.println("Dime% :"+percentageOfDime+"\n"+"Nickel% :"+percentageOfNickel+"\n"+"Coin% :"+percentageOfCoin);

	}
	
	//--WHICHVALUE 2----------------
	
	private void whichValue2() {
		double random = Math.random()*100;
		if(random < PickUpInfos.DIME_DROP) {
			this.value = PickUpInfos.DIME_VALUE;
			this.image = ImagePaths.DIME;
		}else if(random < PickUpInfos.NICKEL_DROP) { //(5>NICKEL_DROP && 25 < NICKEL_DROP) to fix 
			this.value = PickUpInfos.NICKEL_VALUE;
			this.image = ImagePaths.NICKEL;
		}else {
			this.value = PickUpInfos.COIN_VALUE;
			this.image = ImagePaths.COIN;

		}
			
	}
	public void testOfWhichValue2() {
		int nbOfCoin = 0;
		int nbOfNickel = 0;
		int nbOfDime = 0;
		int total = 100000;
		for(int i = 0; i<total; i++) {
			whichValue2();
			if(getValue()==1) {
				nbOfCoin++;
			}else if(getValue()==5) {
				nbOfNickel++;
			}else
				nbOfDime++;
		}
		System.out.println(nbOfCoin);
		System.out.println(nbOfNickel);
		System.out.println(nbOfDime);
		double percentageOfDime = nbOfDime/total;
		double percentageOfNickel = nbOfNickel/total;
		double percentageOfCoin = nbOfCoin/total;
		
		//System.out.println("Dime% :"+percentageOfDime+"\n"+"Nickel% :"+percentageOfNickel+"\n"+"Coin% :"+percentageOfCoin);

	}
	

	public int getValue() {
		return value;
	}

	public void setValue(int value) {
		this.value = value;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

}
