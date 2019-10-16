package tictactoe;

import javax.print.DocFlavor;
import java.util.ArrayList;

public class Minimax {

    private String[] origBoard;
    private char[] chars;
    private char botChar, userChar;
    private ArrayList<String> availSpots;

    Minimax(char[] ch, char aiPlayer) {
        chars = ch;
        origBoard = adaptBoard(chars);
        botChar = aiPlayer;
        if (botChar == 'O') userChar = 'X';
        else userChar = 'O';
        availSpots = checkAvailableSpots();
    }

    private String[] adaptBoard(char[] chars) {
        String[] board = new String[9];
        for(int i = 0; i < chars.length; i++) {
            if(chars[i] == 'X') board[i] = String.valueOf(chars[i]);
            else if(chars[i] == 'O') board[i] = String.valueOf(chars[i]);
            else if(chars[i] == ' ') board[i] = String.valueOf(i);
        }
        return board;
    }

    private ArrayList<String> checkAvailableSpots() {
        ArrayList<String> availableSpots = new ArrayList<>(9);
        for(String spot : origBoard) {
            if (!spot.equals("X") && !spot.equals("O")) availableSpots.add(spot);
        }
        return availableSpots;
    }

    private int getTerminalState() {
        Integer termState = null;
        if(Game.checkPlayer(chars, botChar)) termState = 1;
        else if (Game.checkPlayer(chars, userChar)) termState = -1;
        else if (availSpots.isEmpty()) termState = 0;
        return termState;
    }

    int evaluate(char player) {
        int bestMove = -1;
        evaluate(botChar);
        return bestMove;
    }

}
