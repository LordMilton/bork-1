/**
 * 
 */

/** DaytimeUtil provides and interpreter to interact with the Daytime class
 * independent of game itself
 * 
 * @author Team Red
 *
 */

import java.util.Scanner;

public class DaytimeUtil {
	
	private static int exitCmd; //1 = exit, 0 = do not exit
	private static int clockSwitch; //1 = use game's clock, 0 = use new clock to demo
	
	public static void main (String[] args) {
		utilMethod();
	}
	
	/** Interpreter for the Daytime class
	 * 
	 * 
	 * */
	public static void utilMethod(){
		
		Scanner commandLine = new Scanner(System.in);
		
		System.out.println("Bork DaytimeUtil");
		System.out.println("\t A frontend for Bork's in-game clock");
		System.out.println("");
		
		System.out.println("Do you wish to interact with the running game's internal clock? [y/n]");
		System.out.print("> ");
		String game_demo = commandLine.nextLine();
		System.out.println("");
		
		Daytime timeUtil = new Daytime();
		
		if(game_demo.equals("y")){
			//adjusts the current running clock in game
			exitCmd = 0;
			clockSwitch = 1;
		}
		else if(game_demo.equals("n")){
			//creates new clock
			exitCmd = 0;
			clockSwitch = 0;
		}
		else{
			System.out.println("invalid");
			utilMethod();
		}
		
		while(exitCmd != 1){
			System.out.print("> ");
			String usrInput = commandLine.nextLine();
			
			//exits DaytimeUtil
			if(usrInput.equals("exit")){
				exitCmd = 1;
			}
			
			//displays this dialouge
			else if(usrInput.equals("help")){
				System.out.println("Bork DaytimeUtil Help");
				System.out.println("\t exit - exits daytime util");
				System.out.println("\t help - displays this dialogue");
				System.out.println("\t addTime - adds 2 min to the clock");
				System.out.println("\t getLightValue - returns the light value");
				System.out.println("\t getTime - returns the current time");
				System.out.println("\t setLightValue - sets the light value");
				System.out.println("\t setTime - sets the time");
			}
			
			//adds 2 min to the clock
			else if(usrInput.equals("addTime")){
				if(clockSwitch == 0){
					timeUtil.addTime();
				}
				else if(clockSwitch == 1){
					Daytime.addTime();
				}
			}
			
			//returns light value
			else if(usrInput.equals("getLightValue")){
				if(clockSwitch == 0){
					System.out.println(timeUtil.getLightValue());
				}
				else if(clockSwitch == 1){
					Daytime.getLightValue();
				}
			}
			
			//returns the current time
			else if(usrInput.equals("getTime")){
				if(clockSwitch == 0){
					System.out.println(timeUtil.getTime());
				}
				else if(clockSwitch == 1){
					Daytime.getTime();
				}
			}
			
			//sets the light value
			else if(usrInput.equals("setLightValue")){
				
				System.out.println("What light value between 0 and 12 would you like to set?");
				int settingLight = commandLine.nextInt();
				
				if(clockSwitch == 0){
					timeUtil.setLightValue(settingLight);
				}
				else if(clockSwitch == 1){
					Daytime.setLightValue(settingLight);
				}
			}
			
			//sets the time
			else if(usrInput.equals("setTime")){
				
				System.out.println("in HH:MM format what time would you like to set?");
				String settingTime = commandLine.nextLine();
				
				if(clockSwitch == 0){
					timeUtil.setTime(settingTime);
				}
				else if(clockSwitch == 1){
					Daytime.setTime(settingTime);
				}
			}
			else{
				System.out.println("unknown command");
			}
			
			System.out.println("");
		}
		System.exit(0);
	}
}

