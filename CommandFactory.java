/**
 * 
 */

/** CommandFactory parses through given text to look for and instantiate Commands appropriately
 * @author Team Red
 *
 */
import java.util.List;
import java.util.Arrays;

public class CommandFactory {

    private static CommandFactory theInstance;
    public static List<String> MOVEMENT_COMMANDS = 
        Arrays.asList("n","w","e","s","u","d" );

    public static synchronized CommandFactory instance() {
        if (theInstance == null) {
            theInstance = new CommandFactory();
        }
        return theInstance;
    }
    
    /** Constructor for CommandFactory class
     * 
     * 
     */
    private CommandFactory() {}

    /** Creates command for user interaction with environment
    * 
    * @param command String
    * @return Command
    * */    
    public Command parse(String command) {
        String parts[] = command.split(" ");
        String verb = parts[0];
        String noun = parts.length >= 2 ? parts[1] : "";
        if (verb.equals("save")) {
            return new SaveCommand(noun);
        }
        if (verb.equals("take")) {
            return new TakeCommand(noun);
        }
        if (verb.equals("drop")) {
            return new DropCommand(noun);
        }
        if (verb.equals("i") || verb.equals("inventory")) {
            return new InventoryCommand();
        }
        if (MOVEMENT_COMMANDS.contains(verb)) {
            return new MovementCommand(verb);
        }
        //Wait and Item Specific Commands both can take a verb and a noun
        if (parts.length == 2) {
        	//wait command for time
            if (verb.equals("wait")) {
    			int time = Integer.parseInt(noun);
                return new WaitCommand(time);
            }
            //In case of attack command with no weapon
            if(verb.equals("attack")){
            	return new AttackCommand(null, noun);
            }
            //In case of trade command with no NPC
            if(verb.equals("trade")){
            	return new TradeCommand(null, noun);
            }
            //ItemSpecificCommand
            return new ItemSpecificCommand(verb, noun);
        }
        if(parts.length == 3)
        {
        	//In case of attack command with no weapon but with a 'with'
        	if(verb.equals("attack")){
            	return new AttackCommand(null, noun);
            }
        	//In case of trade command with no NPC but with a 'with'
        	if(verb.equals("trade")){
            	return new AttackCommand(null, noun);
            }
        }
        if(parts.length == 4)
        {
        	//Properly formatted attack command
        	if(verb.equals("attack")){
            	return new AttackCommand(parts[3], noun);
            }
        	//Properly formatted trade command
        	if(verb.equals("trade")){
            	return new AttackCommand(parts[3], noun);
            }
        }
        //Wait command that has no specified wait time
        if (verb.equals("wait")) {
        	return new WaitCommand();
        }
        
        //health command
        if (verb.equals("health")) {
            return new HealthCommand();
        }
        
        //score command
        if (verb.equals("score")) {
            return new ScoreCommand();
        }
        
        //trade command
        //if (verb.equals("trade")) {
            //return new TradeCommand();
        //}
        
        return new UnknownCommand(command);
    }
}
