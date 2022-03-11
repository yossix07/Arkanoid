import arkanoid.game.Game;
import arkanoid.game.GameFlow;
import arkanoid.game.levels.LevelInformationFactory;

/**
 * Ass6Game runs the game for assignment 6.
 */
public class Ass6Game {

    /**
     * Runs the game.
     * @param args - command line arguments.
     */
    public static void main(String[] args) {
        GameFlow gameFlow = new GameFlow(new Game());
        gameFlow.runLevels(new LevelInformationFactory().createGameLevels(args));
    }
}
