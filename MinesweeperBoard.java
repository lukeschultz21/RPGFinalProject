//imported scanner
import java.util.Scanner;

public class MinesweeperBoard {
    //private vars
    private final MinesweeperGame game;
    private final Scanner scanner;

    public MinesweeperBoard(MinesweeperGame game) {
        this.game = game;
        this.scanner = new Scanner(System.in);
    }
//shows board output
    public void printBoard() {
        boolean[][] board = game.getBoard();
        boolean[][] revealed = game.getRevealed();
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
//retrieves user input
    public int[] getUserInput() {
        System.out.print("Enter row and column to reveal (e.g. 3 4): ");
        String input = scanner.nextLine();
        String[] parts = input.split(" ");
        int[] coordinates = new int[2];
        coordinates[0] = Integer.parseInt(parts[0]);
        coordinates[1] = Integer.parseInt(parts[1]);
        return coordinates;
    }
//play method
    public void play() {
        while (!game.isGameOver()) {
            printBoard();
            int[] coordinates = getUserInput();
            int row = coordinates[0];
            int col = coordinates[1];
            game.play(row, col);
        }
        scanner.close();
    }

}
