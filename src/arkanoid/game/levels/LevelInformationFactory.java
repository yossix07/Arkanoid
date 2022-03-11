package arkanoid.game.levels;

import java.util.ArrayList;

/**
 * Creates a list of LevelInformations acorrding to the received strings array.
 * @author Yossi Maatook.
 */
public class LevelInformationFactory {

    /**
     * Returns a list of LevelInformations acorrding to the received strings array.
     * Each number 1-4 in the array is the level 1-4 accordingly and ignore any other strings.
     * @param input - the wanted levels.
     * @return list of LevelInformations acorrding to the received strings array.
     */
    public ArrayList<LevelInformation> createGameLevels(String[] input) {

        //In case the array is empty, returns the default levels//
        if (input.length == 0) {
            return this.defaultGameLevels();
        }

        ArrayList<LevelInformation> levels = new ArrayList<>();
        //For each string in the array, add a relevant level if string is valid//
        for (int i = 0; i < input.length; i++) {
            this.addToList(levels, input[i]);
        }
        if (levels.size() == 0) {
            return this.defaultGameLevels();
        }
        return levels;
    }

    /**
     * Add the relevant level to the received list, according to the received string.
     * @param levels - list of levels to add to.
     * @param s - the level to add, if valid.
     */
    private void addToList(ArrayList<LevelInformation> levels, String s) {
        if (s.equals("1")) {
            levels.add(new LevelOne());
        }
        if (s.equals("2")) {
            levels.add(new LevelTwo());
        }
        if (s.equals("3")) {
            levels.add(new LevelThree());
        }
        if (s.equals("4")) {
            levels.add(new LevelFour());
        }
    }

    /**
     * Returns a default LevelInformation list - list with level 1 to 4.
     * @return default LevelInformation list.
     */
    private ArrayList<LevelInformation> defaultGameLevels() {
        ArrayList<LevelInformation> levels = new ArrayList<>();
        levels.add(new LevelOne());
        levels.add(new LevelTwo());
        levels.add(new LevelThree());
        levels.add(new LevelFour());
        return levels;
    }
}
