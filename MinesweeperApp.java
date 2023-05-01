/* Luke Schultz
CPSC 1060
Personal Project 2
1:25-2:15
*/
//main class responsible for setting up board, running the io, making sure program runs smoothly etc.
public class MinesweeperApp {
    public static void main(String[] args) {
        System.out.println("Luke's Mine Sweeper Project!");
        System.out.println("My name is Luke and i'll be helping you avoid bombs!");
        MinesweeperGame game = new MinesweeperGame(10, 10, 15);
        MinesweeperBoard board = new MinesweeperBoard(game);
        board.play();
    }
}