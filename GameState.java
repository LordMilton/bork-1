/**
 * @author Team Red
 * GameState is a singleton class that is essentially our player.
 It keeps track of items in inventory, adventureresCurrentRoom, which dungeon is being used, and health and score.
 
 It also has the incredibly important task of ensuring our save file can Load (@throw IllegalSaveFormatException)
 contains the correct save file name, the format, 
 
 */

import java.util.Scanner;
import java.util.ArrayList;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;

public class GameState {

    public static class IllegalSaveFormatException extends Exception {
        public IllegalSaveFormatException(String e) {
            super(e);
        }
    }

    static String DEFAULT_SAVE_FILE = "bork_save";
    static String SAVE_FILE_EXTENSION = ".sav";
    static String SAVE_FILE_VERSION = "Bork v3.0 save data";

    static String ADVENTURER_MARKER = "Adventurer:";
    static String CURRENT_ROOM_LEADER = "Current room: ";
    static String INVENTORY_LEADER = "Inventory: ";

    private static GameState theInstance;
    private Dungeon dungeon;
    private ArrayList<Item> inventory;
    private Room adventurersCurrentRoom;
    private int playerScore;
    private int playerHealth;
    private boolean playerIsDead;

    static synchronized GameState instance() {
        if (theInstance == null) {
            theInstance = new GameState();
        }
        return theInstance;
    }

    private GameState() {
        inventory = new ArrayList<Item>();
        playerScore = 0;
        playerHealth = 0;
        playerIsDead = false;
    }

    /*
    * @throws IllegalSaveFormatException, Dungeon.IllegalDungeonFormatException
    *Creates a new Scanner to read in our *correct* save file,
    * raeds dungeonFileLine and checks if correct name against Dungeon.FILENAME_LEADER,
    * passes Scanner to Dungeon constructor to restore previous dungeon used
    * next throws away "Adventurer:" line
    * reads in adventureres Current Room
    * gets current list of items in inventory
    */
    void restore(String filename) throws FileNotFoundException,
        IllegalSaveFormatException, Dungeon.IllegalDungeonFormatException {

        Scanner s = new Scanner(new FileReader(filename));

        if (!s.nextLine().equals(SAVE_FILE_VERSION)) {
            throw new IllegalSaveFormatException("Save file not compatible.");
        }

        String dungeonFileLine = s.nextLine();

        if (!dungeonFileLine.startsWith(Dungeon.FILENAME_LEADER)) {
            throw new IllegalSaveFormatException("No '" +
                Dungeon.FILENAME_LEADER + 
                "' after version indicator.");
        }

        dungeon = new Dungeon(dungeonFileLine.substring(
            Dungeon.FILENAME_LEADER.length()), false);
        dungeon.restoreState(s);

        s.nextLine();  // Throw away "Adventurer:".
        String currentRoomLine = s.nextLine();
        adventurersCurrentRoom = dungeon.getRoom(
            currentRoomLine.substring(CURRENT_ROOM_LEADER.length()));
        if (s.hasNext()) {
            String inventoryList = s.nextLine().substring(
                INVENTORY_LEADER.length());
            String[] inventoryItems = inventoryList.split(",");
            for (String itemName : inventoryItems) {
                try {
                    addToInventory(dungeon.getItem(itemName));
                } catch (Item.NoItemException e) {
                    throw new IllegalSaveFormatException("No such item '" +
                        itemName + "'");
                }
            }
        }
    }
    
    /** Calls the main store(saveName : String) method with a default save File name
     * 
     * @throws IOException In the case of DEFAULT_SAVE_FILE being a non-existent File
     */
    void store() throws IOException {
        store(DEFAULT_SAVE_FILE);
    }

  
  /** Saves necessary information to a .sav File
   * 
   * @param String saveName Name of the File to save to
   * @throws IOException In the case that saveName is the name of a non-existent File
   */
    void store(String saveName) throws IOException {
        String filename = saveName + SAVE_FILE_EXTENSION;
        PrintWriter w = new PrintWriter(new FileWriter(filename));
        w.println(SAVE_FILE_VERSION);
        dungeon.storeState(w);
        w.println(ADVENTURER_MARKER);
        w.println(CURRENT_ROOM_LEADER + adventurersCurrentRoom.getTitle());
        if (inventory.size() > 0) {
            w.print(INVENTORY_LEADER);
            for (int i=0; i<inventory.size()-1; i++) {
                w.print(inventory.get(i).getPrimaryName() + ",");
            }
            w.println(inventory.get(inventory.size()-1).getPrimaryName());
        }
        w.close();
    }

	/** Sets the GameState instance's Dungeon to be the parameter Dungeon and sets the Adventurer's current room as the specified Dungeon's entry Room
	 * 
	 * @param dungeon Dungeon to set as the GameState's current Dungeon
	 */
    void initialize(Dungeon dungeon) {
        this.dungeon = dungeon;
        adventurersCurrentRoom = dungeon.getEntry();
    }

	/** Returns the names of the Items currently in the player's inventory
	 * 
	 * @return An ArrayList of the names of the Items in inventory
	 */
    ArrayList<String> getInventoryNames() {
        ArrayList<String> names = new ArrayList<String>();
        for (Item item : inventory) {
            names.add(item.getPrimaryName());
        }
        return names;
    }

    /** Adds the parameter Item to the player's inventory
     * 
     * @param item Item to add to inventory
     */
    void addToInventory(Item item) /* throws TooHeavyException */ {
        inventory.add(item);
    }

    /** Removes the parameter Item from the player's inventory
     *
     * @param item Item to remove from inventory
     */
    void removeFromInventory(Item item) {
        inventory.remove(item);
    }

    /** Returns the Item in the vicinity that has the parameter name as its primaryName
     * 
     * @return Item The Item going by the parameter name if it is in the player's vicinity
     * @param String name Name of the Item to search for
     * @throws Item.NoItemException If there is no Item with the parameter name in the player's inventory or current Room
     */
    Item getItemInVicinityNamed(String name) throws Item.NoItemException {

        // First, check inventory.
        for (Item item : inventory) {
            if (item.goesBy(name)) {
                return item;
            }
        }

        // Next, check room contents.
        for (Item item : adventurersCurrentRoom.getContents()) {
            if (item.goesBy(name)) {
                return item;
            }
        }

        throw new Item.NoItemException();
    }

	/**
	 * 
	 * @return Item The Item going by the parameter name if it is in the player's inventory
	 * @param String name Name of the Item to search for
	 * @throws Item.NoItem.Exception If there is no Item going by the parameter name in the player's inventory
	 */
    Item getItemFromInventoryNamed(String name) throws Item.NoItemException {

        for (Item item : inventory) {
            if (item.goesBy(name)) {
                return item;
            }
        }
        throw new Item.NoItemException();
    }

    /** Returns the current Room of the player
     * 
     * @return adventurersCurrentRoom
     */
    Room getAdventurersCurrentRoom() {
        return adventurersCurrentRoom;
    }

    /** Sets the player's current Room to the parameter Room
     * 
     * @param room Room to set adventurersCurrentRoom as
     */
    void setAdventurersCurrentRoom(Room room) {
        adventurersCurrentRoom = room;
    }

    /** Returns the Dungeon currently being used by the GameState instance
     * 
     * @return The Dungeon currently being used
     */
    Dungeon getDungeon() {
        return dungeon;
    }
 
  //change health @param integer
 // this.health += integer
 
 //@return health
 //gets health and returns integer
 
 //change Score @integer
 //Score += integer
 
 //@return Score
 //score += integer
 

}
