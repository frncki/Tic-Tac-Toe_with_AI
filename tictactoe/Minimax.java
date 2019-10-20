package tictactoe;

import java.util.*;

public class Minimax {

    private static double maxDepth;

    private Minimax() {}

    static void run (char[] board, char player, double maxDepth) {
        if (maxDepth < 1) {
            throw new IllegalArgumentException("Maximum depth must be greater than 0.");
        }

        Minimax.maxDepth = maxDepth;
        miniMax(board, player, 0);
    }

     static int miniMax (char[] board, char player, int currentDepth) {
        currentDepth++;
        if (currentDepth == maxDepth || Game.isGameOver(board)) {
            return score(board, player);
        }

        if (Game.getTurn(board) == player) {
            return getMax(board, player, currentDepth);
        } else {
            return getMin(board, player, currentDepth);
        }
    }

    private static int getMax (char[] board, char player, int currentDepth) {
        double bestScore = Double.NEGATIVE_INFINITY;
        int indexOfBestMove = -1;

        for (Integer theMove : getAvailableSpots(board)) {

            char[] modifiedBoard = board;
            modifiedBoard[theMove] = player;

            int score = miniMax(modifiedBoard, player, currentDepth);

            if (score >= bestScore) {
                bestScore = score;
                indexOfBestMove = theMove;
            }

        }

        board[indexOfBestMove] = player;
        return (int)bestScore;
    }

    private static int getMin (char[] board, char player, int currentDepth) {
        double bestScore = Double.POSITIVE_INFINITY;
        int indexOfBestMove = -1;

        for (Integer theMove : getAvailableSpots(board)) {

            char[] modifiedBoard = board;
            modifiedBoard[theMove] = player;

            int score = miniMax(modifiedBoard, player, currentDepth);

            if (score <= bestScore) {
                bestScore = score;
                indexOfBestMove = theMove;
            }

        }

        board[indexOfBestMove] = player;
        return (int)bestScore;
    }

    private static int score (char[] board, char player) {
        char opponent = (player == 'X') ? 'O' : 'X';

        if (Game.isGameOver(board) && Game.checkPlayer(board, player)) {
            return 10;
        } else if (Game.isGameOver(board) && Game.checkPlayer(board, opponent)) {
            return -10;
        } else {
            return 0;
        }
    }

    private char[] adaptBoard(char[] chars) {
        for (int i = 0; i < chars.length; i++) {
            if (chars[i] == ' ') chars[i] = Character.forDigit(i, 10);
        }
        return chars;
    }

    private static int[] getAvailableSpots(char[] chars) {
        List<Integer> availableSpots = new ArrayList<>();
        for(Character spot : chars) {
            if (!spot.equals('X') && !spot.equals('O')) availableSpots.add(Character.getNumericValue(spot));
        }
        int[] availSpots = new int[availableSpots.size()];
        for (int i =0; i < availableSpots.size(); i++)
            availSpots[i] = availableSpots.get(i);
        return availSpots;
    }

}
