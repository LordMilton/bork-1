import java.util.Scanner;

import dufour_borkv3.Dungeon;

/**
 * 
 */

/**
 * @author Team Red
 *
 */
 
class NPC {
	private String name;
	private boolean isHostile;
	private int health;
	private boolean isAlive;
	private int maxLightLevelToSpawn;
	
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
	
	/**
	 * 
	 * @param name
	 * @return
	 */
	public boolean goesBy(String name)
	{
		
	}
	
	/**
	 * 
	 * @return
	 */
	public String getName()
	{
		
	}
	
	/**
	 * 
	 * @return
	 */
	public boolean getIsAlive()
	{
		
	}
	
	/**
	 * 
	 * @return
	 */
	public boolean getIsHostile()
	{
		
	}
	
	/**
	 * 
	 * @return
	 */
	public Room getCurrentRoom()
	{
		
	}
	
	/**
	 * 
	 * @return
	 */
	public ArrayListM<String> getInventoryNames()
	{
		
	}
	
	/**
	 * 
	 * @param itemToTrade
	 * @return
	 */
	public Item trade(Item itemToTrade)
	{
		
	}
	
	/**
	 * 
	 * @param item
	 */
	public void addToInventory(Item item)
	{
		
	}
	
	/**
	 * 
	 * @param item
	 */
	private void removeFromInventory(Item item)
	{
		
	}
	
	/**
	 * 
	 * @param name
	 * @return
	 */
	public Item getItemFromInventoryNamed(String name)
	{
		
	}
	
	/**
	 * 
	 * @return
	 */
	public Room move()
	{
		
	}
}
