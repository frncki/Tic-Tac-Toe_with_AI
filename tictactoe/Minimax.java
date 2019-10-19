package tictactoe;

import javax.print.DocFlavor;
import java.util.ArrayList;
import java.util.Vector;

public class Minimax {

    private char botChar, userChar;
    private ArrayList<Integer> availSpots;
    //private Move[] moves;
    private static double maxDepth;

    private char[] adaptBoard(char[] chars) {
        for (int i = 0; i < chars.length; i++) {
            if (chars[i] == ' ') chars[i] = Character.forDigit(i, 10);
        }
        return chars;
    }

    private ArrayList<Integer> checkAvailableSpots(char[] chars) {
        ArrayList<Integer> availableSpots = new ArrayList<>();
        for(Character spot : chars) {
            if (!spot.equals('X') && !spot.equals('O')) availableSpots.add(Character.getNumericValue(spot));
        }
        return availableSpots;
    }


    int evaluate(char[] newBoard, char player) {

        botChar = player;
        if (botChar == 'O') userChar = 'X';
        else userChar = 'O';
        //moves = new Move[9];

        newBoard = adaptBoard(newBoard);
        availSpots = checkAvailableSpots(newBoard);

        // checks for the terminal states such as win, lose, and tie and returning a value accordingly
        if (Game.checkPlayer(newBoard, botChar)) return 1;
        else if (Game.checkPlayer(newBoard, userChar)) return -1;
        else if (availSpots.isEmpty()) return 0;

        // loop through available spots
        for (int i = 0; i < availSpots.size(); i++) {

            //create an object for each and store the index of that spot that was stored as a number in the object's index key
            //moves[i].index = newBoard[availSpots.get(i)];

            // set the empty spot to the current player
            newBoard[availSpots.get(i)] = player;

            //if collect the score resulted from calling minimax on the opponent of the current player
            if (player == botChar) {
                //moves[i].score = evaluate(newBoard, userChar);
            } else {
               //moves[i].score = evaluate(newBoard, botChar);
            }

            //reset the spot to empty
            //newBoard[availSpots.get(i)] = moves[i].index;
        }

        int bestMove = 10000;
        int bestScore = 10000;

        // if it is the computer's turn loop over the moves and choose the move with the highest score
        if(player == botChar) {
            bestScore = -10000;
            for(int i = 0; i < moves.length; i++) {
                if(moves[i].score > bestScore) {
                    bestScore = moves[i].score;
                    bestMove = i;
                }
            }
        } else {
            // else loop over the moves and choose the move with the lowest score
            for(int i = 0; i < moves.length; i++){
                if(moves[i].score < bestScore){
                    bestScore = moves[i].score;
                    bestMove = i;
                }
            }
        }

        return bestMove;
    }

}
