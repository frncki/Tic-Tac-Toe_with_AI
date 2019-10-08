package tictactoe;

import java.util.*;

public class Bot {

    private Random random;
    private int to, from, index;
    private int[] move;
    Difficulty difficulty;

    Bot(Difficulty diff) {
        random = new Random();
        to = 3 + 1; // +1 because the upper limit must be incremented for it to be inclusive
        from = 1;
        move = new int[2];
        difficulty = diff;
    }

    public int move(boolean[] board) {
        switch(difficulty) {
            case EASY:
                return easy(board);

            case MEDIUM:
                return medium(board);

            case HARD:
                return hard(board);
        }
        return easy(board);
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

    private int medium(boolean[] board) {
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