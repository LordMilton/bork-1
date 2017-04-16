/**
 * 
 */

/** ItemSpecificCommand deals with any Command relating to interacting with an Object. This does not including attacking
 * with an object.
 * @author Team Red
 *
 */
import java.util.ArrayList;
class ItemSpecificCommand extends Command {

    private String verb;
    private String noun;
                        

    ItemSpecificCommand(String verb, String noun) {
        this.verb = verb;
        this.noun = noun;
    }

    public String execute() {
        
        Item itemReferredTo = null;
        try {
            itemReferredTo = GameState.instance().getItemInVicinityNamed(noun);
        } catch (Item.NoItemException e) {
            return "There's no " + noun + " here.";
        }
        
        String msg = itemReferredTo.getMessageForVerb(verb);
        ArrayList<String> events = itemReferredTo.getEventForVerb(verb);
        
        if(msg == null)
        	return ("Sorry, you can't " + verb + " the " + noun + "." + "\n");
            
        EventFactory.execute(itemReferredTo, events);
        return msg;
    }
}
