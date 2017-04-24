/**
 * 
 */

/** TradeCommand deals with any Commands relating to the player trying to trade an Item with an NPC.
 * @author Team Red
 *
 */
public class TradeCommand extends Command{
	private NPC npc;
	private Item item;
	private boolean itemProvided;
	
	/** Instantiates a TradeCommand with the parameter Item to the parameter NPC
	 * 
	 * @param npc NPC to trade with
	 * @param item Item to trade to the parameter NPC
	 */
	TradeCommand(String npc, String item)
	{
		this.npc = npc;
		this.item = item;
	}
	
	/**
	 * @override From Command
	 */
	String execute()
	{
		if(npc.getIsHostile()){
			return "Whoa buddy, they're not gonna let you do that";
		}
		
	}
}
