import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.ArrayList;
public class MinesweeperGame {
    //private vars
    private boolean[][] board;
    private boolean[][] revealed;
    private boolean[][] flags;
    private boolean gameOver;
    private int numMines;
    private int numCellsToReveal;
//initlizes game
   public MinesweeperGame(int numRows, int numCols, int numMines) {
        board = new boolean[numRows][numCols];
        revealed = new boolean[numRows][numCols];
        flags = new boolean[numRows][numCols];
        this.numMines = numMines;
        gameOver = false;
        numCellsToReveal = numRows * numCols - numMines;
        MinesweeperUtils.initializeBoardWithMines(board, numMines);
    }
//this will load the board from a file using java.io.* commands
    public void loadBoardFromFile(String fileName) throws FileNotFoundException {
        File file = new File(fileName);
        Scanner scanner = new Scanner(file);
        int numRows = scanner.nextInt();
        int numCols = scanner.nextInt();
        this.board = new boolean[numRows][numCols];
        this.revealed = new boolean[numRows][numCols];
        this.flags = new boolean[numRows][numCols];
        int numMines = scanner.nextInt();
        for (int i = 0; i < numMines; i++) {
            int row = scanner.nextInt();
            int col = scanner.nextInt();
            board[row][col] = true;
        }
        scanner.close();
    }

//if you want to set a flag to mark a bomb you can
    public void setFlag(int row, int col) {
        flags[row][col] = !flags[row][col];
    }

    public boolean[][] getFlags() {
        return flags;
    }
//prints the board with flags
    public void printBoard() {
        System.out.print("  ");
        for (int j = 0; j < board[0].length; j++) {
            System.out.print(j + " ");
        }
        System.out.println();
        for (int i = 0; i < board.length; i++) {
            System.out.print(i + " ");
            for (int j = 0; j < board[0].length; j++) {
                if (revealed[i][j]) {
                    if (board[i][j]) {
                        System.out.print("* ");
                    } else {
                        int adjacentMines = MinesweeperUtils.countAdjacentMines(board, i, j);
                        System.out.print(adjacentMines + " ");
                    }
                } else {
                    System.out.print(". ");
                }
            }
            System.out.println();
        }
    }
//get commands
    public boolean[][] getBoard() {
        return board;
    }

    public boolean[][] getRevealed() {
        return revealed;
    }

    public int getNumMines() {
        return numMines;
    }

    public int getNumCellsToReveal() {
        return numCellsToReveal;
    }

    public boolean isGameOver() {
        return gameOver;
    }

    public void play(int row, int col) {
        if (gameOver) {
            return;
        }
        if (board[row][col]) {
            System.out.println("Game over! You revealed a mine.");
            gameOver = true;
        } else {
            revealCell(row, col);
            checkForWin();
        }
    }
//command will reveal cell to user
    private void revealCell(int row, int col) {
        if (revealed[row][col]) {
            return;
        }
        revealed[row][col] = true;
        numCellsToReveal--;
        int adjacentMines = MinesweeperUtils.countAdjacentMines(board, row, col);
        if (adjacentMines == 0) {
            for (int i = Math.max(0, row - 1); i <= Math.min(row + 1, board.length - 1); i++) {
                for (int j = Math.max(0, col - 1); j <= Math.min(col + 1, board[0].length - 1); j++) {
                    if (i != row || j != col) {
                        revealCell(i, j);
                    }
                }
            }
        }
    }
//checks to see if you won
    private void checkForWin() {
        if (numCellsToReveal == 0) {
            System.out.println("Congratulations! You won the game.");
            gameOver = true;
        }
    }
}
