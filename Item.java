/**
 * 
 */

/** Item represents an object in a bork game. It can be interacted with and affected by the player and knows whether or
 * not it can be used as a weapon. If it can, then it knows how much damage it can deal.
 * @author Team Red
 *
 */
import java.util.Scanner;
import java.util.Hashtable;
import java.util.ArrayList;

public class Item {

	static class NoItemException extends Exception {}
	
	private boolean isAWeapon;
	private Hashtable<String, ArrayList<String>> events;
	private int damage;
	private String primaryName;
	private int weight;
	private Hashtable<String,String> messages;


	Item(Scanner s) throws NoItemException,
	Dungeon.IllegalDungeonFormatException {

		messages = new Hashtable<String,String>();
		events = new Hashtable<String, ArrayList<String>>();

		// Read item name
		String nextLine = s.nextLine();
		String[] nextLineParsed = nextLine.split(" ");
		primaryName = nextLineParsed[0];
		if (primaryName.equals(Dungeon.TOP_LEVEL_DELIM)) {
			throw new NoItemException();
		}

		// Read item weight.
		weight = Integer.valueOf(nextLineParsed[1]);

		// Read and parse verbs lines, as long as there are more.
		String verbLine = s.nextLine();
		if(verbLine.startsWith("weapon"))
		{
			isAWeapon = true;
			String[] weaponLineParsed = verbLine.split(":");
			if(weaponLineParsed.length < 2)
			{
				throw new Dungeon.IllegalDungeonFormatException("No damage stat on weapon line.");
			}
			try{
				damage = Integer.parseInt(weaponLineParsed[1]);
			}catch(Exception e){
				throw new Dungeon.IllegalDungeonFormatException("No damage stat on weapon line or damage stat is."+
																"is not an integer.");
			}
		}
		while (!verbLine.equals(Dungeon.SECOND_LEVEL_DELIM)) {
			if (verbLine.equals(Dungeon.TOP_LEVEL_DELIM)) {
				throw new Dungeon.IllegalDungeonFormatException("No '" +
						Dungeon.SECOND_LEVEL_DELIM + "' after item.");
			}
			//Gets an array of the verb and events, then the message
			String[] verbParts = verbLine.split(":");
			//Gets an array of the verb, then the events
			String[] verbAndEvents = verbParts[0].split("\\[");
			//Gets an array of the events
			String[] eventsParsed = null;
			if(verbAndEvents.length > 1)
			{
				eventsParsed = verbAndEvents[1].substring(0,verbAndEvents[1].length()-1).split(",");
			}
			
			ArrayList<String> eventsList = new ArrayList<>();
			if(eventsParsed != null)
			{
				for(String event:eventsParsed)
				{
					eventsList.add(event);
				}
			}
			this.events.put(verbAndEvents[0], eventsList);
			messages.put(verbAndEvents[0],verbParts[1]);

			verbLine = s.nextLine();
		}
	}

	boolean goesBy(String name) {
		// could have other aliases
		return this.primaryName.equals(name);
	}
	
	/** Returns whether or not the Item is considered a weapon
	 * 
	 * @return The Item's isAWeapon field, True if the weapon is considered a weapon, false otherwise
	 */
	boolean getIsAWeapon()
	{
		return isAWeapon;
	}
	
	/** Returns the Item's damage stat
	 * 
	 * @return The Item's damage field, -1 if the Item is not a weapon
	 */
	int getDamage()
	{
		return damage;
	}
	
	/** Finds a list of events applying to a specific verb
	 * 
	 * @param verb Verb to find applicable events for
	 * @return A list of events that apply to the parameter verb, can be empty
	 */
	ArrayList<String> getEventForVerb(String verb)
	{
		return events.get(verb);
	}
	

	String getPrimaryName() { return primaryName; }

	public String getMessageForVerb(String verb) {
		return messages.get(verb);
	}

	public String toString() {
		return primaryName;
	}
	
	/** Item event that adds the parameter score to the player's current score
	 * 
	 * @param addToScore Score to add to player's current score
	 */
	void scoreEvent(int addToScore)
	{
		GameState.instance().changeScore(addToScore);
	}
	
	/** Item event that lowers the player's health by the parameter amount
	 * 
	 * @param wound Amount to lower player's health by, if negative, player's health is increased
	 */
	void woundEvent(int wound)
	{
		GameState.instance().changeHealth(wound);
	}
	
	/** Item event that kills the player by lowering player's health as low as it can possibly go
	 * 
	 */
	void dieEvent()
	{
		GameState.instance().changeHealth(Integer.MAX_VALUE);
	}
	
	/** Item event that causes the player to win
	 * 
	 */
	//TODO
	//This method has WAAAAYYY too much power and needs to be fixed
	void winEvent()
	{
		GameState.instance().changeScore(Integer.MAX_VALUE);
	}
	
	/** Item event that removes this Item from the Dungeon
	 * 
	 */
	void disappearEvent()
	{
		GameState.instance().obliterateItem(this);
	}
	
	/** Item event that removes this Item from the Dungeon and replaces it with the parameter Item
	 * 
	 * @param newItem Item to replace this Item with
	 */
	void transformEvent(Item newItem)
	{
		GameState instance = GameState.instance();
		instance.transformItem(this, newItem);
	}
	
	/** Item event that moves the player to a random Room in the Dungeon
	 * 
	 */
	void teleportEvent()
	{
		GameState.instance().getDungeon().teleportAdventurer();
	}
}
