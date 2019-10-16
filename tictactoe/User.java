package tictactoe;

import java.util.*;

public class User {

    private Scanner scanner;
    private char userChar;

    User(char playingCharacter) {
        scanner = new Scanner(System.in);
        userChar = playingCharacter;
    }

    char getUserChar() {
        return userChar;
    }

    int move(boolean[] board) {
        boolean valid = false;
        int x, y, index = -1;

        do {
            System.out.print("Enter the coordinates: ");
            // Reading data using readLine
            String inputCoords = scanner.nextLine();
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
                    } else {

                        index = x + y * 3 - 4;

                        if (!board[index]) {
                            board[index] = true;
                            valid = true;
                        } else {
                            System.err.println("This cell is occupied! Choose another one!");
                            valid = false;
                        }
                    }

                } catch (Exception ex) {
                    System.err.println("\nYou should enter numbers!\n" + ex);
                    System.exit(0);
                }
            }
        }  while (!valid);
        return index;
    }
}
