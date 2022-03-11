package arkanoid.game;
import arkanoid.game.levels.GameLevel;
import arkanoid.game.levels.LevelInformation;
import arkanoid.animation.AnimationRunner;
import arkanoid.animation.GameOver;
import arkanoid.animation.KeyPressStoppableAnimation;
import arkanoid.animation.YouWin;
import biuoop.GUI;
import biuoop.KeyboardSensor;
import counters.Counter;
import java.util.List;

/**
 * GameFlow hold the wanted levels and runs them according to the game rules.
 * @author Yossi Maatook.
 */
public class GameFlow {
    public static final int SCORE_START = 0;
    public static final int LIVES_START = 3;
    public static final int LOST_A_LIFE = 1;
    private GUI gui;
    private KeyboardSensor keyboardSensor;
    private AnimationRunner animationRunner;
    private Counter score;
    private Counter lives;

    /**
     * Constructor.
     * @param g - gui of the game.
     * @param ar - AnimationRunner of the game.
     * @param ks - KeyboardSensor of the game.
     */
    public GameFlow(GUI g, AnimationRunner ar, KeyboardSensor ks) {
        this.gui = g;
        this.keyboardSensor = ks;
        this.animationRunner = ar;

        //Initialize the game score starting from the wanted amount//
        this.score = new Counter(SCORE_START);

        //Initialize the game lives starting from the wanted amount//
        this.lives = new Counter(LIVES_START);
    }

    /**
     * Constructor.
     * Set the fields according to the game that is received.
     * @param game - holds the relevant fields of the game.
     */
    public GameFlow(Game game) {
        this(game.getGui(), game.getAnimationRunner(), game.getKeyboardSensor());
    }

    /**
     * Runs the game in the order of levels in the received list.
     * @param levels - list of LevelInformation that is part of the game.
     */
    public void runLevels(List<LevelInformation> levels) {

        //For every level in the list, initialize and run it according to the game rules//
        for (LevelInformation levelInfo : levels) {
            //Create a game level according to the current level in the list//
            GameLevel level = new GameLevel(levelInfo, this.keyboardSensor,
                    this.animationRunner, this.score, this.lives);

            //Initialize it's data//
            level.initialize();

            //As long there are lives and blocks in the game, run current level//
            while (this.lives.getValue() != 0 && level.getRemainingBlocks().getValue() != 0) {
                level.run();

                //In case there are no balls in game, decrease life//
                if (level.getRemainingBalls().getValue() == 0) {
                    this.lives.decrease(LOST_A_LIFE);
                    level.resetRemainingBalls(levelInfo.numberOfBalls());
                    level.getPaddle().removeFromGame(level);
                }
            }

            //In case there are no lives, print to the user he lost and exit game//
            if (this.lives.getValue() == 0) {
                this.animationRunner.run(new KeyPressStoppableAnimation(new GameOver(score),
                        this.keyboardSensor, "space"));
                this.gui.close();
                break;
            }

        }
        //Print to the user he won//
        this.animationRunner.run(new KeyPressStoppableAnimation(new YouWin(score),
                this.keyboardSensor, "space"));
        //Exit the game//
        this.gui.close();
    }
}