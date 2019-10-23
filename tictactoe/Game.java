package tictactoe;

class Game {

    private boolean menu;
    private char[] inCh; //inCh = inputCharacters
    private boolean[] board;
    private PlayerType firstPlayer, secondPlayer;

    Game(PlayerType first, PlayerType second) {
        menu = true;
        inCh = initGame("         ");
        board = initBoardArray(inCh);
        firstPlayer = first;
        secondPlayer = second;
    }

    private char[] initGame(String state) {
        char[] chars = new char[9];
        state.getChars(0, 9, chars, 0);
        return rearrangeInput(chars);
    }

    private char[] rearrangeInput(char[] chars) {
        char[] temp = new char[3];

        for (int i = 0; i < 3; i++) { // rearranges the characters so it matches input method
            temp[i] = chars[i];
            chars[i] = chars[i + 6];
            chars[i + 6] = temp[i];
        }

        return chars;
    }

    private boolean[] initBoardArray(char[] chars) {
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

    void Play() {
        boolean game = true;

        drawBoard(inCh); //drawing board with initial state

        boolean firstPlayerMoved = false;
        char firstPlayerCharacter = 'X', secondPlayerCharacter = 'O';

        while (game) { // game loop
            switch (checkBoard(inCh)) {
                case "Game not finished":  // Im aware that... this is fkn spaghetti! ;o
                    if (firstPlayer == PlayerType.USER && secondPlayer == PlayerType.USER) { //user user
                        if (!firstPlayerMoved) {
                            drawBoard(addMove(new User(firstPlayerCharacter), inCh, board));
                            firstPlayerMoved = true;
                        } else {
                            drawBoard(addMove(new User(secondPlayerCharacter), inCh, board));
                            firstPlayerMoved = false;
                        }
                    } else if (firstPlayer == PlayerType.USER && checkBotDifficulty(secondPlayer)) { //user bot
                        if (!firstPlayerMoved) {
                            drawBoard(addMove(new User(firstPlayerCharacter), inCh, board));
                            firstPlayerMoved = true;
                        } else {
                            drawBoard(addMove(new Bot(secondPlayer, secondPlayerCharacter), inCh, board));
                            firstPlayerMoved = false;
                        }
                    } else if (checkBotDifficulty(firstPlayer) && secondPlayer == PlayerType.USER) {  //bot user
                        if (!firstPlayerMoved) {
                            drawBoard(addMove(new Bot(firstPlayer, firstPlayerCharacter), inCh, board));
                            firstPlayerMoved = true;
                        } else {
                            drawBoard(addMove(new User(secondPlayerCharacter), inCh, board));
                            firstPlayerMoved = false;
                        }
                    } else if (checkBotDifficulty(firstPlayer) && checkBotDifficulty(secondPlayer)) { //bot bot
                        if (!firstPlayerMoved) {
                            drawBoard(addMove(new Bot(firstPlayer, firstPlayerCharacter), inCh, board));
                            firstPlayerMoved = true;
                        } else {
                            drawBoard(addMove(new Bot(secondPlayer, secondPlayerCharacter), inCh, board));
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

    private void drawBoard(char[] chars) {
        System.out.println("---------\n" +
                "| " + chars[6] + " " + chars[7] + " " + chars[8] + " " + "|\n" +
                "| " + chars[3] + " " + chars[4] + " " + chars[5] + " " + "|\n" +
                "| " + chars[0] + " " + chars[1] + " " + chars[2] + " " + "|\n" +
                "---------");
    }

    private String checkBoard(char[] chars) {

        int oxDiff = Math.abs(getOXDiff(chars));

        if (oxDiff > 1) {
            return "Impossible";
        }

        if (checkPlayer(chars, 'O')) { //checks O player
            if (checkPlayer(chars, 'X')) {
                return "Impossible"; //checks if there is any X win at the same time
            }
            return "O wins";
        } else if (checkPlayer(chars, 'X')) { //checks X player
            if (checkPlayer(chars, 'O')) {
                return "Impossible"; //checks if there is any O win at the same time
            }
            return "X wins";
        }

        for (char ch : chars) {
            if (ch == ' ') return "Game not finished";
        }

        return "Draw";
    }

    private char[] addMove(Bot bot, char[] chars, boolean[] board) {
        int index = bot.move(chars, board);
        chars[index] = bot.getBotChar();
        board[index] = true;
        return chars;
    }

    private char[] addMove(User user, char[] chars, boolean[] board) {
        int index = user.move(board);
        chars[index] = user.getUserChar();
        board[index] = true;
        return chars;
    }

    private boolean checkBotDifficulty(PlayerType diff) {
        if (diff == PlayerType.BOT_EASY || diff == PlayerType.BOT_MEDIUM || diff == PlayerType.BOT_HARD) return true;
        return false;
    }

    static boolean checkPlayer(char[] chars, char playerCharacter) {
        boolean win = false;
        for (int i = 0; i < 3; i++) {
            win = (chars[i] == chars[i + 3] && chars[i + 3] == chars[i + 6] && chars[i] == playerCharacter) || // vertical
                    (chars[i * 3] == chars[i * 3 + 1] && chars[i * 3 + 1] == chars[i * 3 + 2] && chars[i * 3] == playerCharacter) || // horizontal
                    (chars[0] == chars[4] && chars[4] == chars[8] && chars[0] == playerCharacter) || // counter diagonal
                    (chars[2] == chars[4] && chars[4] == chars[6] && chars[2] == playerCharacter);   // diagonal
        }
        return win;
    }

    static int getCharNum(char[] chars, char player) {
        int charNum = 0;
        for (char ch : chars) {
            if (ch == player) charNum++;
        }
        return charNum;
    }

    static int getOXDiff(char[] chars) {
        int oNum = getCharNum(chars, 'O');
        int xNum = getCharNum(chars, 'X');
        return oNum - xNum;
    }

    static char getTurn(char[] chars) {
        int oxDiff = getOXDiff(chars);
        return oxDiff > 0 ? 'O' : 'X';
    }

    static boolean isGameOver(char[] chars) {
        int spaceNum = 0;
        for(char ch : chars) if(ch == ' ') spaceNum++;
        return checkPlayer(chars, 'O') || checkPlayer(chars, 'X') || spaceNum == 0;

    }
}
