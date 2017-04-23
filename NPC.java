import java.util.Scanner;
import java.util.ArrayList;

/**
 * 
 */

/** NPC is the class defining non-player characters that can interact with the
 * player via combat and trade. NPC objects can move around the Dungeon and only
 * appear in the Dungeon at certain light levels according to the Daytime class.
 * @author Team Red
 *
 */
class NPC {
	class NoNPCException extends Exception {}
	
	private String name;
	private Room currentRoom;
	private boolean isHostile;
	private int health;
	private boolean isAlive;
	private int maxLightLevelToSpawn;
	private ArrayList<Item> inventory;
	
	/** Creates a new NPC from a given Scanner connected to a .bork file
	 * 
	 * @param scan Scanner connected to the .bork file to read the NPC information from
	 * @param d The containing Dungeon object, necessary to retrieve Item objects.
	 * @param initState Whether or not the NPC should be set to its initial state, or set to a saved state from a .sav file
	 * @throws NoNPCException Thrown when a parsing line marking the end of the NPC section in the file is reached
	 */
	public NPC(Scanner scan, Dungeon d, boolean initState) throws NoNPCException
	{
		
	}
	
	/** Determines if the NPC goes by the paramater name
	 * 
	 * @param name Name to check against NPC's name
	 * @return True if this name is the same as the parameter name, false otherwise
	 */
	public boolean goesBy(String name)
	{
		
	}
	
	/** Returns the NPC's name
	 * 
	 * @return name
	 */
	public String getName()
	{
		return name;
	}
	
	/** Returns whether or not the NPC is currently alive
	 * 
	 * @return isAlive
	 */
	public boolean getIsAlive()
	{
		return isAlive;
	}
	
	/** Returns whether or not the NPC is hostile to the player
	 * 
	 * @return isHostile
	 */
	public boolean getIsHostile()
	{
		return isHostile;
	}
	
	/** Returns the Room the NPC is currently in, null if it has not spawned yet
	 * 
	 * @return currentRoom
	 */
	public Room getCurrentRoom()
	{
		return currentRoom;
	}
	
	/** A list of the names of the Item's in the NPC's inventory
	 * 
	 * @return An ArrayList<String> of all the names of the Items in inventory
	 */
	public ArrayList<String> getInventoryNames()
	{
		
	}
	
	/** Trades the parameter Item with the first Item in the NPC's inventory, will not be enacted if the NPC's inventory is empty
	 * 
	 * @param itemToTrade Item that the NPC will receive
	 * @return Item that the NPC will give in return
	 */
	public Item trade(Item itemToTrade)
	{
		
	}
	
	/** Adds an Item to the NPC's inventory
	 * 
	 * @param item Item to add to inventory
	 */
	public void addToInventory(Item item)
	{
		inventory.add(item);
	}
	
	/** Removes an Item from the NPC's inventory
	 * 
	 * @param item Item remove from inventory
	 */
	private void removeFromInventory(Item item)
	{
		inventory.remove(item);
	}
	
	/** Returns an Item from the NPC's inventory going by the parameter name
	 * 
	 * @param name Name of the Item to search for in inventory
	 * @return The Item going by the parameter name if it is in inventory
	 * @throws NoItemException If there is no Item in inventory that goes by the parameter name
	 */
	public Item getItemFromInventoryNamed(String name) throws NoItemException
	{
		
	}
	
	/** Adjusts this NPC's health by the parameter amount
     * 
     * @param change Amount to change this NPC's health by (negative to increase health)
     */
	public void changeHealth(int wound)
	{
		health -= wound;
	}
	
	/** Moves the NPC to a new Room
	 * 
	 * @return The Room the NPC moved to
	 */
	public Room move()
	{
		
	}
}
