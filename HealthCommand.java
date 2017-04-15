
/**
 *
 */

/**
 * HealthCommand deals with any Commands relating to a player checking their health.
 *
 * @author Team Red
 *
 */
class HealthCommand extends Command {

    private int health;

    /**
     * Instantiates a HealthCommand
     *
     */
    HealthCommand() {
        this.health = GameState.instance().getAdventurersHealth();
    }

    /**
     * @override From Command Made up some bogus health values, 1-3
     */
    String execute() {
        switch (health) {
            case 1:
                return "You are about to die.";
            case 2:
                return "Each step is a stagger"
                        + "from the pain of your wounds.";
            case 3:
                return "You're a bit light-headed.";
            case 4:
                return "You are about to die.";
            default:
                return "You feel fit as a fiddle.";
        }

    }
}
