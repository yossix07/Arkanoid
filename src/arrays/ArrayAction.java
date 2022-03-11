package arrays;
import arkanoid.geometry.Point;

/**
 * Arrays.ArrayAction has method that regard to array manipulation.
 * @author Yossi Maatook.
 */
public class ArrayAction {
    public static final int SINGLE_MEMBER_ARRAY = 1;

    /**
     * This method receives a list of points and returns the same list without any duplicates.
     * @param pointsList - the list which we want to remove duplicates from.
     * @return the same list, but without duplicates.
     */
    public java.util.List<Point> removeDuplicates(java.util.List<Point> pointsList) {

        //In case the list is already a non-duplicates one//
        if (pointsList == null || pointsList.size() == SINGLE_MEMBER_ARRAY) {
            return pointsList;
        }

        //The loop goes through the list and removes the duplicate, if exist.
        for (int i = 0; i < pointsList.size(); i++) {
            for (int j = i + 1; j < pointsList.size(); j++) {
                if (pointsList.get(i).equals(pointsList.get(j))) {
                    pointsList.remove(j);
                    j--;
                }
            }
        }
        return pointsList;
    }


}
