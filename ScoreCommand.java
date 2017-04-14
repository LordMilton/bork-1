/**
 * 
 */

/** ScoreCommand deals with any Commands relating to a player checking their score.
 * @author Team Red
 *
 */
 
class ScoreCommand extends Command{

	/** Instantiates a ScoreCommand
	 * 
	 */
	ScoreCommand()
	{
	}
	
	/**
	 * @override From Command
	 */
	String execute()
	{
		GameState state = GameState.instance();
		int currScore = state.getAdventurersScore();
		
		return("Your score is "+ currScore);
	}
}
