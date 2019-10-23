package tictactoe;

import java.util.*;

class Minimax {

    private static double maxDepth;
    private static ArrayList<Move> moves;

    private Minimax() {}

    static ArrayList<Move> getMoves() {return moves;}

    static void run (char[] board, char player, double maxDepth) {
        if (maxDepth < 1) {
            throw new IllegalArgumentException("Maximum depth must be greater than 0.");
        }

        Minimax.maxDepth = maxDepth;
        board = adaptBoard(board);
        moves = new ArrayList<>(9);
        miniMax(board, player, 0);
    }

    private static Move miniMax (char[] board, char player, int currentDepth) {
        if (currentDepth++ == maxDepth && Game.isGameOver(board)) {
            System.out.println("end " + currentDepth);
            moves.add(new Move(0, score(board, player)));
            return new Move(0, score(board, player));
        }
        System.out.println("availspots: " + getAvailableSpots(board).length);
        System.out.println("moves: " + moves.size());
        if (Game.getTurn(board) == player) {
            System.out.println("max " + currentDepth);
            Move max = getMax(board, player, currentDepth);
            moves.add(max);
            return max;
        } else {
            Move min = getMin(board, player, currentDepth);
            moves.add(min);
            return min;
        }
    }

    private static Move getMax (char[] board, char player, int currentDepth) {
        double bestScore = Double.NEGATIVE_INFINITY;
        int indexOfBestMove = -1;

        for (Integer theMove : getAvailableSpots(board)) {

            char[] modifiedBoard = board;
            modifiedBoard[theMove] = player;

            int score = miniMax(modifiedBoard, player, currentDepth).score;

            if (score >= bestScore) {
                bestScore = score;
                indexOfBestMove = theMove;
            }

        }

        return new Move(indexOfBestMove ,(int)bestScore);

    }

    private static Move getMin (char[] board, char player, int currentDepth) {
        double bestScore = Double.POSITIVE_INFINITY;
        int indexOfBestMove = -1;
        for (Integer theMove : getAvailableSpots(board)) {
            char[] modifiedBoard = board;
            modifiedBoard[theMove] = player;

            int score = miniMax(modifiedBoard, player, currentDepth).score;

            if (score <= bestScore) {
                bestScore = score;
                indexOfBestMove = theMove;
            }

        }

        return new Move(indexOfBestMove ,(int)bestScore);
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

    private static char[] adaptBoard(char[] chars) {
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
