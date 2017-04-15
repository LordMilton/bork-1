/**
 * 
 */

/** EventFactory deals with the execution of any Item induced events.
 * 
 * @author Team Red
 *
 */
import java.util.ArrayList;
public class EventFactory {
	
	/** Executes a list of Strings representing events that are related to the parameter Item
	 * 
	 * @param item Item which the parameter events are related to
	 * @param events Set of Strings representing events to execute, potentially on the parameter Item
	 */
	public static void execute(Item item, ArrayList<String> events)
	{
		for(String event:events)
		{
			String[] eventParsed = event.split("(");
			
			if(eventParsed[0].equalsIgnoreCase("Score"))
			{
				//Argument is score amount
				item.scoreEvent(Integer.parseInt(eventParsed[1].substring(0, eventParsed[1].length())));
			}
			else if(eventParsed[0].equalsIgnoreCase("Wound"))
			{
				//Argument is wound amount
				item.woundEvent(Integer.parseInt(eventParsed[1].substring(0, eventParsed[1].length())));
			}
			else if(eventParsed[0].equalsIgnoreCase("Die"))
			{
				item.dieEvent();
			}
			else if(eventParsed[0].equalsIgnoreCase("Win"))
			{
				item.winEvent();
			}
			else if(eventParsed[0].equalsIgnoreCase("Disappear"))
			{
				item.disappearEvent();
			}
			else if(eventParsed[0].equalsIgnoreCase("Transform"))
			{
				try{
					item.transformEvent(GameState.instance().getDungeon().getItem(eventParsed[1].substring(0, eventParsed[1].length())));
				}catch(Item.NoItemException e){
					System.err.println("A transform event for "+ item.getPrimaryName() +" had a non-existent Item to"+
								       "into.");
				}
			}
			else if(eventParsed[0].equalsIgnoreCase("Teleport"))
			{
				//Argument is Item to transform into
				item.teleportEvent();
			}
		}
	}
}
