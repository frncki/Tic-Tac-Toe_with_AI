package tictactoe;

import java.util.*;
import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {

        Scanner scanner = new Scanner(System.in);

        char[] inCh = initGame("         "); //inCh = inputCharacters
        boolean[] board = initializeBoardArray(inCh);

        //Bot bot = new Bot(PlayerType.BOT_EASY);
        char firstPlayerCharacter = 'X';

        //theGame(scanner, bot, inCh, board);

        boolean menu = true;

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
                    theGame(scanner, inCh, board, PlayerType.get(params[1]), PlayerType.get(params[2]));
                    break;

                case "exit":
                    menu = false;
                    break;

                default:
                    System.err.println("Bad parameters!");
            }
        }
    }

    private static char[] initGame(String state) {
        char[] chars = new char[9];
        state.getChars(0, 9, chars, 0);
        return rearrangeInput(chars);
    }

    private static void theGame(Scanner scanner, char[] inCh, boolean[] board, PlayerType firstPlayer, PlayerType secondPlayer) throws IOException { // probably should overload this function but fk it
        boolean game = true;

        drawBoard(inCh); //drawing board with initial state

        boolean firstPlayerMoved = false;
        char firstPlayerCharacter = 'X', secondPlayerCharacter = 'O';

        while (game) { // game loop
            switch (checkBoard(inCh)) {
                case "Game not finished":  // this is fkn spaghetti! ;o
                    if (firstPlayer == PlayerType.USER && secondPlayer == PlayerType.USER) { //user user
                        if (!firstPlayerMoved) {
                            drawBoard(addPlayerMove(scanner, inCh, board, firstPlayerCharacter));
                            firstPlayerMoved = true;
                        } else {
                            drawBoard(addPlayerMove(scanner, inCh, board, secondPlayerCharacter));
                            firstPlayerMoved = false;
                        }
                    } else if (firstPlayer == PlayerType.USER && checkBotDifficulty(secondPlayer)) { //user bot
                        if (!firstPlayerMoved) {
                            drawBoard(addPlayerMove(scanner, inCh, board, firstPlayerCharacter));
                            firstPlayerMoved = true;
                        } else {
                            drawBoard(addBotMove(new Bot(secondPlayer, secondPlayerCharacter), inCh, board));
                            firstPlayerMoved = false;
                        }
                    } else if (checkBotDifficulty(firstPlayer) && secondPlayer == PlayerType.USER) {  //bot user
                        if (!firstPlayerMoved) {
                            drawBoard(addBotMove(new Bot(firstPlayer, firstPlayerCharacter), inCh, board));
                            firstPlayerMoved = true;
                        } else {
                            drawBoard(addPlayerMove(scanner, inCh, board, secondPlayerCharacter));
                            firstPlayerMoved = false;
                        }
                    } else if (checkBotDifficulty(firstPlayer) && checkBotDifficulty(secondPlayer)) { //bot bot
                        if (!firstPlayerMoved) {
                            drawBoard(addBotMove(new Bot(firstPlayer, firstPlayerCharacter), inCh, board));
                            firstPlayerMoved = true;
                        } else {
                            drawBoard(addBotMove(new Bot(secondPlayer, secondPlayerCharacter), inCh, board));
                            firstPlayerMoved = false;
                        }
                    }
                    //gameMove(scanner, inCh, board, firstPlayer, secondPlayer, firstPlayerMoved); // this should work but it does not
                    break;

                case "O wins":
                    System.out.println(checkBoard(inCh));
                    game = false;
                    break;

                case "X wins":
                    System.out.println(checkBoard(inCh));
                    game = false;
                    break;

                case "Draw":
                    System.out.println(checkBoard(inCh));
                    game = false;
                    break;

                case "Impossible":
                    System.out.println(checkBoard(inCh));
                    game = false;
                    break;

            }

        }
    }

    private static boolean checkBotDifficulty(PlayerType diff) {
        if (diff == PlayerType.BOT_EASY || diff == PlayerType.BOT_MEDIUM || diff == PlayerType.BOT_HARD) return true;
        return false;
    }

    private static void gameMove(Scanner scanner, char[] inCh, boolean[] board, PlayerType firstPlayer, PlayerType secondPlayer, boolean firstPlayerMoved) throws IOException {
        /*
        char firstPlayerCharacter = 'X', secondPlayerCharacter = 'O';
        if (firstPlayer == PlayerType.USER && secondPlayer == PlayerType.USER) {
            //user user
            if (!firstPlayerMoved) {
                drawBoard(addPlayerMove(scanner, inCh, board, firstPlayerCharacter));
                firstPlayerMoved = true;
            } else {
                drawBoard(addPlayerMove(scanner, inCh, board, secondPlayerCharacter));
                firstPlayerMoved = false;
            }
        } else if (firstPlayer == PlayerType.USER && (secondPlayer == PlayerType.BOT_EASY || secondPlayer == PlayerType.BOT_MEDIUM || secondPlayer == PlayerType.BOT_HARD)) {
            //user bot
            if (!firstPlayerMoved) {
                drawBoard(addPlayerMove(scanner, inCh, board, firstPlayerCharacter));
                firstPlayerMoved = true;
            } else {
                drawBoard(addBotMove(new Bot(secondPlayer), inCh, board, secondPlayerCharacter));
                firstPlayerMoved = false;
            }
        } else if (secondPlayer == PlayerType.USER && (firstPlayer == PlayerType.BOT_EASY || firstPlayer == PlayerType.BOT_MEDIUM || firstPlayer == PlayerType.BOT_HARD)) {
            //bot user
            if (!firstPlayerMoved) {
                drawBoard(addBotMove(new Bot(firstPlayer), inCh, board, firstPlayerCharacter));
                firstPlayerMoved = true;
            } else {
                drawBoard(addPlayerMove(scanner, inCh, board, secondPlayerCharacter));
                firstPlayerMoved = false;
            }
        } else if ((firstPlayer == PlayerType.BOT_EASY || firstPlayer == PlayerType.BOT_MEDIUM || firstPlayer == PlayerType.BOT_HARD) && (secondPlayer == PlayerType.BOT_EASY || secondPlayer == PlayerType.BOT_MEDIUM || secondPlayer == PlayerType.BOT_HARD)) {
            //bot bot
            if (!firstPlayerMoved) {
                drawBoard(addBotMove(new Bot(firstPlayer), inCh, board, firstPlayerCharacter));
                firstPlayerMoved = true;
            } else {
                drawBoard(addBotMove(new Bot(secondPlayer), inCh, board, secondPlayerCharacter));
                firstPlayerMoved = false;
            }
        } */
    }

    private static char[] enterBoardState(Scanner scanner) throws IOException {

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

        return rearrangeInput(chars);
    }

    private static char[] rearrangeInput(char[] chars) {
        char[] temp = new char[3];

        for (int i = 0; i < 3; i++) { // rearranges the characters so it matches input method
            temp[i] = chars[i];
            chars[i] = chars[i + 6];
            chars[i + 6] = temp[i];
        }

        return chars;
    }

    private static boolean[] initializeBoardArray(char[] chars) {
        boolean[] board = new boolean[9];

        for (int i = 0; i < chars.length; i++) {
            if (chars[i] == 'X' || chars[i] == 'O') {
                board[i] = true;
            } else {
                board[i] = false;
            }
        }
        return board;
    }

    private static void drawBoard(char[] chars) {
        System.out.println("---------\n" +
                "| " + chars[6] + " " + chars[7] + " " + chars[8] + " " + "|\n" +
                "| " + chars[3] + " " + chars[4] + " " + chars[5] + " " + "|\n" +
                "| " + chars[0] + " " + chars[1] + " " + chars[2] + " " + "|\n" +
                "---------");
    }

    private static char[] addPlayerMove(Scanner scanner, char[] chars, boolean[] board, char player) throws IOException { //index formula = x + y * width
        boolean valid = false;
        int x, y;

        while (!valid) {
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

                        int index = x + y * 3 - 4;

                        if (!board[index]) {
                            board[index] = true;
                            chars[index] = player;
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
        }

        return chars;
    }

    private static char[] addBotMove(Bot bot, char[] chars, boolean[] board) {
        int index = bot.move(board, chars);
        chars[index] = bot.getBotChar();
        board[index] = true;
        return chars;
    }

    private static String checkBoard(char[] chars) {

        int oNum = 0, xNum = 0;

        for (char ch : chars) {
            if (ch == 'O') oNum++;
            if (ch == 'X') xNum++;
        }

        int oxDiff = Math.abs(oNum - xNum);

        if (oxDiff > 1) {
            return "Impossible";
        }

        for (int i = 0; i < 3; i++) {
            if (checkPlayer(i, chars, 'O')) { //checks O player
                for (int j = 0; j < 3; j++) {
                    if (checkPlayer(j, chars, 'X')) {
                        return "Impossible"; //checks if there is any X win at the same time
                    }
                }
                return "O wins";
            } else if (checkPlayer(i, chars, 'X')) { //checks X player
                for (int j = 0; j < 3; j++) {
                    if (checkPlayer(j, chars, 'O')) {
                        return "Impossible"; //checks if there is any O win at the same time
                    }
                }
                return "X wins";
            }
        }

        for (char ch : chars) {
            if (ch == ' ') return "Game not finished";
        }

        return "Draw";
    }

    private static boolean checkPlayer(int i, char[] chars, char playerCharacter) {
        return (chars[i] == chars[i + 3] && chars[i + 3] == chars[i + 6] && chars[i] == playerCharacter) || // vertical
                (chars[i * 3] == chars[i * 3 + 1] && chars[i * 3 + 1] == chars[i * 3 + 2] && chars[i * 3] == playerCharacter) || // horizontal
                (chars[0] == chars[4] && chars[4] == chars[8] && chars[0] == playerCharacter) || // counter diagonal
                (chars[2] == chars[4] && chars[4] == chars[6] && chars[2] == playerCharacter);   // diagonal
    }
}
