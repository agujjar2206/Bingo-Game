import java.util.Arrays;
import java.util.*;

public class BingoGame {

    /**
     * Checks if Bingo is won or not
     * @param bingoCard   input array of 25 numbers
     * @param drawnNumbers input array of drawn numbers
     * @return true if game is won, else false
     */
    private static boolean checkForBingo(String[] bingoCard, String[] drawnNumbers) {
        // if number of of drawnNumbers is less than 4, player cannot win
        if (drawnNumbers.length < 4)
            return false;
        // set of drawn numbers
        Set<String> drawnNumbersSet = new HashSet<>(Arrays.asList(drawnNumbers));
        // adding middle square cell as it is wildcard match
        drawnNumbersSet.add("FREE");
        // check if any row is filled
        for (int row = 0; row < 5; row++) {
            if (checkRow(bingoCard, row, drawnNumbersSet))
                return true;
        }
        // check if any column in filled
        for (int col = 0; col < 5; col++) {
            if (checkColumn(bingoCard, col, drawnNumbersSet))
                return true;
        }
        // check if any diagonal is filled
        return checkDiagonals(bingoCard, drawnNumbersSet);
    }

    /**
     * Checks if a row has been filled
     *
     * @param bingoCard input array of 25 numbers
     * @param row       current row
     * @return true if a row has been filled, else false
     */
    private static boolean checkRow(String[] bingoCard, int row, Set<String> drawNumbersSet) {
        for (int col = 0; col < 5; col++) {
            if (!drawNumbersSet.contains(bingoCard[row * 5 + col]))
                return false;
        }
        return true;
    }

    /**
     * Checks if a column has been filled
     *
     * @param bingoCard input array of 25 numbers
     * @param col       current column
     * @return true if a column has been filled, else false
     */
    private static boolean checkColumn(String[] bingoCard, int col, Set<String> drawNumbersSet) {
        for (int row = 0; row < 5; row++) {
            if (!drawNumbersSet.contains(bingoCard[row * 5 + col]))
                return false;
        }
        return true;
    }

    /**
     * Checks if the either of the diagonals has been filled
     * @param bingoCard input array of 25 numbers
     * @return true if either of the columns has been filled, else false
     */
    private static boolean checkDiagonals(String[] bingoCard, Set<String> drawNumbersSet) {
        // diagonal from left to right
        boolean leftToRight = true;
        // diagonal from right to left
        boolean rightToLeft = true;
        int row = 0, index;
        // check leftToRight diagonal
        while (row < 5) {
            // indexes from left to right
            index = row * 5 + row;
            if (!drawNumbersSet.contains(bingoCard[index])) {
                leftToRight = false;
                break;
            }
            row++;
        }
        row = 5;
        // check rightToLeft diagonal
        while (row > 0) {
            // indexes from right to left
            index = row * 5 - row;
            if (!drawNumbersSet.contains(bingoCard[index])) {
                rightToLeft = false;
                break;
            }
            row--;
        }
        return leftToRight || rightToLeft;
    }

}
