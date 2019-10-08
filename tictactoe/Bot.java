package tictactoe;

import java.util.*;

public class Bot {

    private Random random;
    private int to, from, index;
    private int[] move;
    private final PlayerType difficulty;

    Bot(PlayerType diff) {
        random = new Random();
        to = 3 + 1; // +1 because the upper limit must be incremented for it to be inclusive
        from = 1;
        move = new int[2];
        difficulty = diff;
    }

    public int move(boolean[] board, char[] chars) {
        switch(difficulty) {
            case BOT_EASY: return easy(board);
            case BOT_MEDIUM: return medium(board, chars);
            case BOT_HARD: return hard(board);
        }
        return easy(board); //TODO refactor this!
    }

    private int easy(boolean[] board) {
        System.out.println("Making move level \"easy\"");

        boolean valid;

        do {
            move[0] = random.nextInt(to - from) + from;
            move[1] = random.nextInt(to - from) + from;

            index = move[0] + move[1] * 3 - 4;

            if (!board[index]) {
                valid = true;
            } else {
                valid  = false;
            }
        } while(!valid);

        return index;
    }

    private int medium(boolean[] board,  char[] chars) {
        System.out.println("Making move level \"medium\"");
        index = 1;
        return index;
    }

    private int hard(boolean[] board) {
        System.out.println("Making move level \"hard\"");
        index = 2;
        return index;
    }

}