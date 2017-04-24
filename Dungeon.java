/**
 * 
 */

/** Dungeon contains the Rooms, Items, and any other information related to representing a dungeon in a bork game. It
 * also holds the names of Files relating to the current game.
 * @author Team Red
 *
 */
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Scanner;
import java.util.Set;

public class Dungeon {

    public static class IllegalDungeonFormatException extends Exception {
        public IllegalDungeonFormatException(String e) {
            super(e);
        }
    }

    // Variables relating to both dungeon file and game state storage.
    public static String TOP_LEVEL_DELIM = "===";
    public static String SECOND_LEVEL_DELIM = "---";

    // Variables relating to dungeon file (.bork) storage.
    public static String ROOMS_MARKER = "Rooms:";
    public static String EXITS_MARKER = "Exits:";
    public static String ITEMS_MARKER = "Items:";
    
    // Variables relating to game state (.sav) storage.
    static String FILENAME_LEADER = "Dungeon file: ";
    static String ROOM_STATES_MARKER = "Room states:";

    private String name;
    private Room entry;
    private Hashtable<String,Room> rooms;
    private Hashtable<String,Item> items;
    private Hashtable<String,NPC> npcs;
    private String filename;

    Dungeon(String name, Room entry) {
        init();
        this.filename = null;    // null indicates not hydrated from file.
        this.name = name;
        this.entry = entry;
        rooms = new Hashtable<String,Room>();
    }

    /**
     * Read from the .bork filename passed, and instantiate a Dungeon object
     * based on it.
     * 
     * @param filename
     * @throws IllegalDungeonFormatException, FileNotFoundException
     * 
     */
    public Dungeon(String filename) throws FileNotFoundException, 
        IllegalDungeonFormatException {

        this(filename, true);
    }

    /**
     * Read from the .bork filename passed, and instantiate a Dungeon object
     * based on it, including (possibly) the items in their original locations.
     * 
     * @param filename, initState
     * @throws IllegalDungeonFormatException, FileNotFoundException
     * 
     */
    public Dungeon(String filename, boolean initState) 
        throws FileNotFoundException, IllegalDungeonFormatException {

        init();
        this.filename = filename;

        Scanner s = new Scanner(new FileReader(filename));
        name = s.nextLine();

        s.nextLine();   // Throw away version indicator.

        // Throw away delimiter.
        if (!s.nextLine().equals(TOP_LEVEL_DELIM)) {
            throw new IllegalDungeonFormatException("No '" +
                TOP_LEVEL_DELIM + "' after version indicator.");
        }

        // Throw away Items starter.
        if (!s.nextLine().equals(ITEMS_MARKER)) {
            throw new IllegalDungeonFormatException("No '" +
                ITEMS_MARKER + "' line where expected.");
        }

        try {
            // Instantiate items.
            while (true) {
                add(new Item(s));
            }
        } catch (Item.NoItemException e) {  /* end of items */ }

        // Throw away Rooms starter.
        if (!s.nextLine().equals(ROOMS_MARKER)) {
            throw new IllegalDungeonFormatException("No '" +
                ROOMS_MARKER + "' line where expected.");
        }

        try {
            // Instantiate and add first room (the entry).
            entry = new Room(s, this, initState);
            add(entry);

            // Instantiate and add other rooms.
            while (true) {
                add(new Room(s, this, initState));
            }
        } catch (Room.NoRoomException e) {  /* end of rooms */ }

        // Throw away Exits starter.
        if (!s.nextLine().equals(EXITS_MARKER)) {
            throw new IllegalDungeonFormatException("No '" +
                EXITS_MARKER + "' line where expected.");
        }

        try {
            // Instantiate exits.
            while (true) {
                Exit exit = new Exit(s, this);
            }
        } catch (Exit.NoExitException e) {  /* end of exits */ }

        s.close();
    }
    
    /** Common object initialization tasks, regardless of which constructor is used.
     * 
     */
    private void init() {
        rooms = new Hashtable<String,Room>();
        items = new Hashtable<String,Item>();
    }

    /** Store the current (changeable) state of this dungeon using the parameter Writer.
     * 
     * @param w The Writer to use when writing the saved information to the .sav File
     * @throws IOException
     */
    void storeState(PrintWriter w) throws IOException {
        w.println(FILENAME_LEADER + getFilename());
        w.println(ROOM_STATES_MARKER);
        for (Room room : rooms.values()) {
            room.storeState(w);
        }
        w.println(TOP_LEVEL_DELIM);
    }

    /** Restore the (changeable) state of this dungeon to that reflected in the Scanner passed.
     * 
     * @param s The Scanner containing the .sav File to read from
     * @throws GameState.IllegalSaveFormatException If the .sav File is improperly formatted
     */
    void restoreState(Scanner s) throws GameState.IllegalSaveFormatException {

        // Note: the filename has already been read at this point.
        
        if (!s.nextLine().equals(ROOM_STATES_MARKER)) {
            throw new GameState.IllegalSaveFormatException("No '" +
                ROOM_STATES_MARKER + "' after dungeon filename in save file.");
        }

        String roomName = s.nextLine();
        while (!roomName.equals(TOP_LEVEL_DELIM)) {
            getRoom(roomName.substring(0,roomName.length()-1)).
                restoreState(s, this);
            roomName = s.nextLine();
        }
    }

    public Room getEntry() { return entry; }
    public String getName() { return name; }
    public String getFilename() { return filename; }
    public void add(Room room) { rooms.put(room.getTitle(),room); }
    public void add(Item item) { items.put(item.getPrimaryName(),item); }

    public Room getRoom(String roomTitle) {
        return rooms.get(roomTitle);
    }

    /**
     * Get the Item object whose primary name is passed. This has nothing to
     * do with where the Adventurer might be, or what's in his/her inventory,
     * etc.
     * 
     * @param String primaryItemName
     * @return Item primaryItemName
     */
    public Item getItem(String primaryItemName) throws Item.NoItemException {
        
        if (items.get(primaryItemName) == null) {
            throw new Item.NoItemException();
        }
        return items.get(primaryItemName);
    }
    
    /** Searches for and return an NPC in the Dungeon going by the parameter name
     * 
     * @param npcName Name of the NPC to search for
     * @return The NPC with the parameter name if it exists in the Dungeon
     * @throws NPC.NoNPCException If there is no NPC that goes by the parameter name
     */
    public NPC getNPC(String npcName) throws NPC.NoNPCException {
    	//return null;
    }
    
    /** Completely removes the parameter Item from this Dungeon
     * 
     * @param item Item to remove from this Dungeon
     */
    void obliterateItem(Item item)
    {
    	Set<String> roomNames = rooms.keySet();
    	for(String roomName:roomNames)
    	{
    		if(rooms.get(roomName).obliterateItem(item))
    			break;
    	}
    }
    
    /** Completely removes the parameter itemToDestroy from this Dungeon and replaces it with the parameter itemToAdd
     * 
     * @param itemToDestroy Item to remove from this Dungeon
     * @param itemToAdd Item with which to replace the parameter itemToDestroy
     */
    void transformItem(Item itemToDestroy, Item itemToAdd)
    {
    	Set<String> roomNames = rooms.keySet();
    	for(String roomName:roomNames)
    	{
    		if(rooms.get(roomName).transformItem(itemToDestroy, itemToAdd))
    			break;
    	}
    }
    
    /** Teleports the player to a random Room in the Dungeon
     * 
     */
    void teleportAdventurer()
    {
    	Set<String> roomNames = rooms.keySet();
    	//Needed an ordered list (Sets have no order) for random Room picking
    	ArrayList<String> roomNamesList = new ArrayList<>();
    	for(String roomName:roomNames)
    	{
    		roomNamesList.add(roomName);
    	}
    	//Finds a random room and throws the adventurer into it
    	int roomNumber = ((int)(Math.random()*roomNames.size()));
    	GameState.instance().setAdventurersCurrentRoom(rooms.get(roomNamesList.get(roomNumber)));
    }
}
