public class Board {
    private String[][] squares = new String[10][10];

    public String toString() {
        StringBuilder boardOut = new StringBuilder(); // I had to use ChatGPT to help with this including the first for loop.  Couldn't figure out how to do it and you weren't here

        if (!Battleship.startGame) {
            for (int i = 0; i < squares.length; i++) {
                String[] row = squares[i];
                for (int j = 0; j < row.length; j++) {
                    String value = row[j];
                    boardOut.append(value).append(" ");
                }
                boardOut.append("\n");
            }
        } else {
            for (int i = 0; i < squares.length; i++) {
                String[] row = squares[i];
                for (int j = 0; j < row.length; j++) {
                    String col = row[j];
                    if (squares[i][j].equals("b")) {
                        boardOut.append("- ");
                    } else {
                        boardOut.append(col).append(" ");
                    }
                }
                boardOut.append("\n");
            }
        }

        return boardOut.toString();
    }
    public boolean addShip(int row, int col, int len, boolean horizontal) {
        if (horizontal) {
            for(int i = 0; i < len; i++) {
                if (squares[row][col + i].equals("b") || col+i > squares.length) {
                    return false;
                }

                squares[row][col+i] = "b";
            }

        } else {
            for(int i = 0; i < len; i++) {
                if (squares[row + i][col].equals("b") || row+i > squares.length) {
                    return false;
                }

                squares[row+i][col] = "b";
            }
        }



        return true;

    }

    public boolean foundShip(int len) {
        int counter = 0;

        for(int r = 0; r < squares.length; r++){
            for(int c = 0; c < squares.length; c++){
                if (squares[r][c].contains("b")){
                    counter++;
                } else {
                    counter = 0;
                }

                if (counter == len) {
                    return true;
                }

            }
        }

        for(int c = 0; c < squares.length; c++){
            for(int r = 0; r < squares.length; r++){
                if (squares[r][c].contains("b")){
                    counter++;
                } else {
                    counter = 0;
                }

                if (counter == len) {
                    return true;
                }

            }
        }

        return false;
    }

    public int shoot(int row, int col) {
        if (squares[row][col].equals("b")) {
            squares[row][col] = "x";
            return 1;
        } else if (squares[row][col].equals("-")){
            squares[row][col] = "m";
            return 0;
        } else if (squares[row][col].equals("x") || squares[row][col].equals("m")) {
            return 2;
        } else {
            return (-1);
        }
    }

    public boolean gameOver() {
        for(int r = 0; r < squares.length; r++){
            for(int c = 0; c < squares.length; c++){
                if(squares[r][c].equals("b")) {
                    return false;
                }
            }
        }
        return true;
    }

    public Board() {
        for (int r = 0; r < squares.length; r++) {
            for (int c = 0; c < squares[0].length; c++) {
                squares[r][c] = "-";
            }
        }
    }
}