/**
 * 
 */

/** Daytime keeps track of the current time in the game and the light that is appropriate for that time. It also keeps
 * track of the number of days that have passed.
 * @author Team Red
 *
 */
public class Daytime {
	
	private static int light; //light value
	private static int currentmin; //number of mins
	private static int currenthour; //number of hours
	private static int days; //number of days
	
	public static void main (String[] args) {
	}
	
	/** Constructor for Daytime class
	 * 
	 * 
	 * */
	public static void Daytime(){
		light = 0;
		currentmin = 0;
		currenthour = 0;
	}
	
	/** adds time (2 min) after each action to determine the light value
	 * 
	 * 
	 * */
	public static void addTime(){
		
		//24 hour clock: 0 to 23
		
		currentmin = currentmin + 2;
		
		//resets currentmin once it has hit 60
		if(currentmin == 60){
			currentmin = 0;
			currenthour++;
		}
		
		//resets currenthour once it has hit 24
		if(currenthour == 24){
			currenthour = 0;
			days++;
		}
		
		//sets light value based on current hour
		if(currenthour == 0){
			setLightValue(1);
		}
		else if(currenthour == 1){
			setLightValue(0);
		}
		else if(currenthour == 2){
			setLightValue(1);
		}
		else if(currenthour == 3){
			setLightValue(2);
		}
		else if(currenthour == 4){
			setLightValue(3);
		}
		else if(currenthour == 5){
			setLightValue(4);
		}
		else if(currenthour == 6){
			setLightValue(5);
		}
		else if(currenthour == 7){
			setLightValue(6);
		}
		else if(currenthour == 8){
			setLightValue(7);
		}
		else if(currenthour == 9){
			setLightValue(8);
		}
		else if(currenthour == 10){
			setLightValue(9);
		}
		else if(currenthour == 11){
			setLightValue(10);
		}
		else if(currenthour == 12){
			setLightValue(11);
		}
		else if(currenthour == 13){
			setLightValue(12);
		}
		else if(currenthour == 14){
			setLightValue(11);
		}
		else if(currenthour == 15){
			setLightValue(10);
		}
		else if(currenthour == 16){
			setLightValue(9);
		}
		else if(currenthour == 17){
			setLightValue(8);
		}
		else if(currenthour == 18){
			setLightValue(7);
		}
		else if(currenthour == 19){
			setLightValue(6);
		}
		else if(currenthour == 20){
			setLightValue(5);
		}
		else if(currenthour == 21){
			setLightValue(4);
		}
		else if(currenthour == 22){
			setLightValue(3);
		}
		else if(currenthour == 23){
			setLightValue(2);
		}
		
	}
	
	/** Returns the time for general use
	 * 
	 * 
	 * */
	public static String getTime(){
		
		String totalTime = currenthour + ":" + currentmin;
		return totalTime;
	}
	
	/** Sets light value based on hour
	 * 
	 * @param addLight int that is the new light value derived from the current hour
	 * */
	public static void setLightValue(int addLight){
		light = addLight;
	}
	
	/** Returns the light value for use by NPCs and Rooms
	 * 
	 * @return light
	 * */
	public static int getLightValue(){
		return light;
	}
	
}
