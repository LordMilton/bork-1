/**
 * 
 */

/** HealthCommand deals with any Commands relating to a player checking their health.
 * @author Team Red
 *
 */
class HealthCommand extends Command{
	
	/** Instantiates a HealthCommand
	 * 
	 */
	HealthCommand()
	{
	}
	
	/**
	 * @override From Command
	 */
	String execute()
	{
		GameState state = GameState.instance();
		int currentHealth = state.getAdventurersHealth();
		
		if(currentHealth >= 41)
			return "You feel as healthy and strong as ever";
		else if(currentHealth >= 31)
			return "Your wounds sting slightly, but you have plenty of strength for adventuring";
		else if(currentHealth >= 21)
			return "Your health has clearly degraded";
		else if(currentHealth >= 11)
			return "You stagger from the pain of your wounds";
		else
			return "You feel as if you have one foot in your grave already";
	}
}
