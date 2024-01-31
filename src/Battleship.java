import java.util.Scanner;

public class Battleship {
   public static boolean startGame = false;
    public static void main(String[] args) {
        Board gameBoard = new Board();

        Scanner drake = new Scanner(System.in);
        String playerInput;
        int rowInput;
        int colInput;
        int lenInput;
        boolean horizontalCheck;
        boolean startMenu = true;




        while (startMenu) {
            playerInput = InputHelper.getNonZeroLenString(drake, "Player: Enter \"a\" to add a ship, \"b\" to see the board, \"p\" to play, or \"q\" to quit.");
            if(playerInput.equalsIgnoreCase("a")) {
                rowInput = InputHelper.getInt(drake, "What row would you like this ship to be in? [1-10]") - 1;
                colInput = InputHelper.getInt(drake, "What column would you like this ship to be in? [1-10]") - 1;
                lenInput = InputHelper.getInt(drake, "How long would you like this ship to be? [1-10]");
                horizontalCheck = InputHelper.getYNConfirm(drake, "Would you like the ship to be horizontal?  Y or N.");
                if (gameBoard.addShip(rowInput, colInput, lenInput, horizontalCheck)) {
                    System.out.println("New ship added!");
                } else {
                    System.out.println("Can't put a ship there!");
                }
            }

            if(playerInput.equalsIgnoreCase("b")) {
                System.out.println(gameBoard.toString());
            }

            if(playerInput.equalsIgnoreCase("p")) {
                if (gameBoard.foundShip(3) && gameBoard.foundShip(4)){
                    System.out.println("OK, let's play!");
                    startMenu = false;
                    startGame = true;
                } else {
                    System.out.println("You need a ship of length 3 and 4 before you can play!");
                }
            }

            if(playerInput.equalsIgnoreCase("q")) {
                startMenu = false;
                System.out.println("Thanks for playing!");
            }
        }

        while (startGame) {
            playerInput = InputHelper.getNonZeroLenString(drake, "Player: Press \"s\" to shoot at a square, \"b\" to see the board, or \"q\" to quit.");

            if(playerInput.equalsIgnoreCase("s")) {
                rowInput = InputHelper.getInt(drake, "What row would you like to shoot? [1-10]") - 1;
                colInput = InputHelper.getInt(drake, "What column would you like to shoot? [1-10]") - 1;
                int result = gameBoard.shoot(rowInput, colInput);

                if (result == 0) {
                    System.out.println("Miss!");
                } else if (result == 1) {
                    System.out.println("Hit!");
                } else if (result == 2) {
                    System.out.println("Already guessed that spot!");
                } else if (result == (-1)) {
                    System.out.println("Invalid coordinates!");
                }
            }

            if(playerInput.equalsIgnoreCase("b")) {
                System.out.println(gameBoard.toString());
            }

            if(playerInput.equalsIgnoreCase("q")) {
                startGame = false;
                System.out.println("Thanks for playing!");

            }

            if (gameBoard.gameOver()) {
                startGame = false;
                System.out.println(gameBoard.toString());
                System.out.println("Game Over!  You hit all of the ships!");
            }
        }

    }
}
