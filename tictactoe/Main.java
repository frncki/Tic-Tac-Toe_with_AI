package tictactoe;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {

        //Enter data
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        System.out.print("Enter cells: ");
        // Reading data using readLine
        String input = reader.readLine().replace("\"", "");

        System.out.println(input);

        if (input.length() != 9) {
            System.out.println("Error! Wrong input length.");
            System.exit(0);
        } else {

            char[] inCh = new char[9];

            try {
                input.getChars(0, 9, inCh, 0);
                int checker = 0;
                for (char ch : inCh) {
                    if (ch == 'X' || ch == 'O' || ch == ' ') {
                        checker++;
                    } else {
                        System.out.println("Error! Wrong characters.");
                        System.exit(1);
                    }
                    if (checker == 9) System.out.println("Valid input!");
                }
                drawBoard(inCh);
                System.out.println(checkBoard(inCh));
            } catch (Exception ex) {
                System.out.println(ex);
            }
        }
    }

    private static void drawBoard(char[] chars) {
        System.out.println("---------\n" +
                "| " + chars[0] + " " + chars[1] + " " + chars[2] + " " + "|\n" +
                "| " + chars[3] + " " + chars[4] + " " + chars[5] + " " + "|\n" +
                "| " + chars[6] + " " + chars[7] + " " + chars[8] + " " + "|\n" +
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

}
