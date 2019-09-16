package tictactoe;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {

        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        char[] inCh = new char[9]; //inCh = inputCharacters
        boolean[] board = new boolean[9];


        inCh = enterBoardState(reader, inCh);

        for (int i = 0; i < inCh.length; i++) { // initialize board array
            if (inCh[i] == 'X' || inCh[i] == 'O') {
                board[i] = true;
            } else {
                board[i] = false;
            }
        }

        drawBoard(inCh);

        //System.out.println(checkBoard(inCh));

        drawBoard(addMove(reader, inCh, board, 'X'));

    }


    private static char[] addMove(BufferedReader reader, char[] chars, boolean[] board, char player) throws IOException { //index formula = x + y * width
        boolean valid = false;
        int x = -10, y = -10;

        while (!valid) {
            System.out.print("Enter the coordinates: ");
            // Reading data using readLine
            String inputCoords = reader.readLine();
            char[] inCords = new char[3];

            if (inputCoords.length() != 3) {
                System.err.println("Error! Wrong input length.");
                valid = false;
            } else {

                try {
                    inputCoords.getChars(0, 3, inCords, 0);

                    int checker = 0;
                    for (char ch : inCords) {
                        if (ch != ' ') {
                            checker++;
                        }
                    }
                    if (checker > 2) {
                        System.err.println("Error! Wrong input.");
                        valid = false;
                    }

                    x = Character.getNumericValue(inCords[0]);
                    y = Character.getNumericValue(inCords[2]);

                    if (x < 1 || x > 3 || y < 1 || y > 3) {
                        System.err.println("Coordinates should be from 1 to 3!");
                        valid = false;
                    }

                    int index = x + y * 3 - 4;

                    if (!board[index]) {
                        board[index] = true;
                        chars[index] = player;
                        valid = true;
                    } else {
                        System.err.println("This cell is occupied! Choose another one!");
                        valid = false;
                    }
                } catch (Exception ex) {
                    System.err.println("You should enter numbers!\n" + ex);
                }
            }
        }

        return chars;
    }

    private static void drawBoard(char[] chars) {
        System.out.println("---------\n" +
                "| " + chars[6] + " " + chars[7] + " " + chars[8] + " " + "|\n" +
                "| " + chars[3] + " " + chars[4] + " " + chars[5] + " " + "|\n" +
                "| " + chars[0] + " " + chars[1] + " " + chars[2] + " " + "|\n" +
                "---------");
    }

    private static String checkBoard(char[] chars) {

        int oNum = 0, xNum = 0;

        for (char ch : chars) {
            if (ch == 'O') oNum++;
            if (ch == 'X') xNum++;
        }

        int oxDiff = Math.abs(oNum - xNum);

        if (oxDiff > 1) return "Impossible";

        for (int i = 0; i < 3; i++) {
            if (checkPlayer(i, chars, 'O')) { //checks O player
                for (int j = 0; j < 3; j++) {
                    if (checkPlayer(j, chars, 'X')) return "Impossible"; //checks if there is any X win at the same time
                }
                return "O wins";
            } else if (checkPlayer(i, chars, 'X')) { //checks X player
                for (int j = 0; j < 3; j++) {
                    if (checkPlayer(j, chars, 'O')) return "Impossible"; //checks if there is any O win at the same time
                }
                return "X wins";
            }
        }

        for (char ch : chars) {
            if (ch == ' ') return "Game not finished";
        }

        return "Draw";
    }

    private static boolean checkPlayer(int i, char[] chars, char player) {
        return (chars[i] == chars[i + 3] && chars[i + 3] == chars[i + 6] && chars[i] == player) || // vertical
                (chars[i * 3] == chars[i * 3 + 1] && chars[i * 3 + 1] == chars[i * 3 + 2] && chars[i * 3] == player) || // horizontal
                (chars[0] == chars[4] && chars[4] == chars[8] && chars[0] == player) || // diagonal
                (chars[2] == chars[4] && chars[4] == chars[6] && chars[2] == player);   // counter diagonal;
    }

    private static char[] enterBoardState(BufferedReader reader, char[] chars) throws IOException {

        System.out.print("Enter cells: ");
        // Reading data using readLine
        String inputCells = reader.readLine().replace("\"", "");

        System.out.println(inputCells);

        if (inputCells.length() != 9) {
            System.err.println("Error! Wrong input length.");
            System.exit(0);
        } else {

            try {
                inputCells.getChars(0, 9, chars, 0);
                int checker = 0;
                for (char ch : chars) {
                    if (ch == 'X' || ch == 'O' || ch == ' ') {
                        checker++;
                    } else {
                        System.out.println("Error! Wrong characters.");
                        System.exit(1);
                    }
                    if (checker == 9) System.out.println("Valid input!");
                }
            } catch (Exception ex) {
                System.err.println(ex);
            }
        }

        char[] temp = new char[3];

        for (int i = 0; i < 3; i++) {
            temp[i] = chars[i];
            chars[i] = chars[i + 6];
            chars[i + 6] = temp[i];
        }

        return chars;
    }
}
