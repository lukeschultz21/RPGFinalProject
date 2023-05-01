import java.util.Random;
import java.util.ArrayList;
//provides Random assignment of bombs every game
public class MinesweeperUtils {
    private static final Random random = new Random();

    public static void initializeBoardWithMines(boolean[][] board, int numMines) {
        int rows = board.length;
        int cols = board[0].length;
        int minesPlaced = 0;
        while (minesPlaced < numMines) {
            int row = random.nextInt(rows);
            int col = random.nextInt(cols);
            if (!board[row][col]) {
                board[row][col] = true;
                minesPlaced++;
            }
        }
    }
//this is so the program will output what mines are Adjacent to a given spot on the grid
    public static int countAdjacentMines(boolean[][] board, int row, int col) {
        int count = 0;
        int rows = board.length;
        int cols = board[0].length;

        for (int i = Math.max(0, row - 1); i <= Math.min(row + 1, rows - 1); i++) {
            for (int j = Math.max(0, col - 1); j <= Math.min(col + 1, cols - 1); j++) {
                if (board[i][j] && !(i == row && j == col)) {
                    count++;
                }
            }
        }
        return count;
    }
}
