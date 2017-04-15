
/**
 *
 */
/**
 * ScoreCommand deals with any Commands relating to a player checking their score.
 *
 * @author Team Red
 *
 */
class ScoreCommand extends Command {

    private int score;
    private String rank;

    /**
     * Instantiates a ScoreCommand
     *
     */
    ScoreCommand() {
        this.score = GameState.instance().getAdventurersScore();

        if (score <= 10) {
            rank = "Amateur Adventurer";
        }
        if (score > 10 && score <= 50) {
            rank = "Novice Adventurer";
        }
        if (score > 50 && score <= 100) {
            rank = "Proficient Adventurer";
        }
        if (score > 100) {
            rank = "Legendary Adventurer";
        }
    }

    /**
     * @override From Command
     */
    String execute() {
        return "You have accumulated" + score + " points."
                + "This gives you a rank of " + this.rank + ".";
    }
}
