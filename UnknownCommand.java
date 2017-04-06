/**
 * 
 */

/** UnknownCommand deals with any Commands that do not fall under any other kind of Command and will not work.
 * @author Team Red
 *
 */

class UnknownCommand extends Command {

    private String bogusCommand;

    UnknownCommand(String bogusCommand) {
        this.bogusCommand = bogusCommand;
    }

    String execute() {
        return "I'm not sure what you mean by \"" + bogusCommand + "\".\n";
    }
}
