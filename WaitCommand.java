/**
 * 
 */

/** WaitCommand deals with any Commands relating to a player waiting to pass time.
 * @author Team Red
 *
 */

class WaitCommand extends Command{
	
	private int minTime;
	
	/** Instantiates a WaitCommand with the parameter time as the time the Player intends to wait
	 * 
	 * @param time The time to wait
	 */
	WaitCommand(int time)
	{
		minTime = time;
	}
	
	/** Instantiates a WaitCommand with a wait time of 2 (in-game minutes)
	 * 
	 */
	WaitCommand()
	{
		new WaitCommand(2);
	}
	
	/**
	 * @override From Command
	 */
	String execute()
	{
		while(minTime > 0){
			Daytime.addTime();
			minTime = minTime - 2;
		}
		return("You relax for a couple of minutes.");
	}
}
