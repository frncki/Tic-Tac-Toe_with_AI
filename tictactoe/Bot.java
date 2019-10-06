package tictactoe;

import java.util.*;

public class Bot {

    Random random;
    int to, from, index;
    int[] move;

    Bot() {
        random = new Random();
        to = 3 + 1; // +1 because the upper limit must be incremented for it to be inclusive
        from = 1;
        move = new int[2];
    }

    public int easy(boolean[] board) {

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

    public int medium(boolean[] board) {
        System.out.println("Work in progress: medium");
        index = 1;
        return index;
    }

    public int hard(boolean[] board) {
        System.out.println("Work in progress: hard");
        index = 2;
        return index;
    }

}