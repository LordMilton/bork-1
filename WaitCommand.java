/**
 * 
 */

/** WaitCommand deals with any Commands relating to a player waiting to pass time.
 * @author Team Red
 *
 */
class WaitCommand extends Command{
	
	/** Instantiates a WaitCommand with the parameter time as the time the Player intends to wait
	 * 
	 * @param time The time to wait
	 */
	WaitCommand(int time)
	{
		
	}
	
	/**
	 * @override From Command
	 */
	String execute()
	{
		Daytime.addTime();
		return("You relax for a couple of minutes.");
	}
}
