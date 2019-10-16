package tictactoe;

import java.util.*;

public class Main {
    public static void main(String[] args) {
        menu();
    }

    private static void menu() {
        boolean menu = true;
        Scanner scanner = new Scanner(System.in);
        Game game;

        while (menu) {
            System.out.print("Input command: ");
            String command = scanner.nextLine();

            switch (command) {
                case "start user easy":
                case "start user medium":
                case "start user hard":
                case "start user user":
                case "start easy easy":
                case "start easy medium":
                case "start easy hard":
                case "start easy user":
                case "start medium easy":
                case "start medium medium":
                case "start medium hard":
                case "start medium user":
                case "start hard easy":
                case "start hard medium":
                case "start hard hard":
                case "start hard user":
                    String[] params = command.split(" ");
                    game = new Game(PlayerType.get(params[1]), PlayerType.get(params[2]));
                    game.Play();
                    break;

                case "exit":
                    menu = false;
                    break;

                default:
                    System.err.println("Bad parameters!");
            }
        }
    }


    private static char[] enterBoardState(Scanner scanner) {

        char[] chars = new char[9];

        System.out.print("Enter cells: ");
        // Reading data using readLine
        String inputCells = scanner.nextLine().replace("\"", "");

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

        return chars; //should be rearranged!!!
    }
}
