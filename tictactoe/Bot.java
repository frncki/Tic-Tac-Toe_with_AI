package tictactoe;

import java.util.*;

public class Bot {

    private Random random;
    private int to, from, index;
    private int[] move;
    private final PlayerType difficulty;
    private char botChar, userChar;

    Bot(PlayerType diff, char playingCharacter) {
        random = new Random();
        to = 3 + 1; // +1 because the upper limit must be incremented for it to be inclusive
        from = 1;
        move = new int[2];
        difficulty = diff;
        botChar = playingCharacter;
        if (botChar == 'O') userChar = 'X';
        else userChar = 'O';
    }

    char getBotChar() {
        return botChar;
    }

    int move(boolean[] board, char[] chars) {
        switch (difficulty) {
            case BOT_EASY:
                return easy(board);
            case BOT_MEDIUM:
                return medium(board, chars);
            case BOT_HARD:
                return hard(board);
            default:
                return -1;
        }
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
                valid = false;
            }
        } while (!valid);

        return index;
    }

    private int medium(boolean[] board, char[] chars) {
        System.out.println("Making move level \"medium\"");
        index = 1;
        return index;
    }

    private int hard(boolean[] board) {
        System.out.println("Making move level \"hard\"");
        index = 2;
        return index;
    }

    private int possibleWin(int i, boolean[] board, char[] chars, char charToCheck) {
        // checking template if (place Im checking if free and place next to it is same as place further, and those places are a certain character) return index of a free
        if (!board[i * 3] && chars[i * 3 + 1] == chars[i * 3 + 2] && chars[i * 3 + 1] == charToCheck) { // horizontal 1. | |X|X|
            return i * 3;
        } else if (!board[i * 3 + 1] && chars[i * 3] == chars[i * 3 + 2] && chars[i * 3] == charToCheck) { //horizontal 2. |X| |X|
            return i * 3 + 1;
        } else if (!board[i * 3 + 2] && chars[i * 3] == chars[i * 3 + 1] && chars[i * 3] == charToCheck) { //horizontal 3. |X|X| |
            return i * 3 + 2;
        } else if (!board[i + 6] && chars[i] == chars[i + 3] && chars[i] == charToCheck) { //vertical 4.  | |
            return i + 6;                                                                  //             |X|
                                                                                           //             |X|

        } else if (!board[i + 3] && chars[i] == chars[i + 6] && chars[i] == charToCheck) { //vertical 5.  |X|
            return i + 3;                                                                  //             | |
                                                                                           //             |X|
            
        } else if (!board[i] && chars[i + 3] == chars[i + 6] && chars[i] == charToCheck) { //vertical 6.  |X|
            return i;                                                                      //             |X|
                                                                                           //             | |
        } else if () { //diagonal 7.

        } else if () { //diagonal 8.

        } else if () { //diagonal 9.

        } else if () { //counter diagonal 10.

        } else if () { //counter diagonal 11.

        } else if () { //counter diagonal 12.

        } else return -1;
    }

}

/*
horizontal
1. | |X|X|

2. |X| |X|

3. |X|X| |

vertical
4.  | |
    |X|
    |X|

5.  |X|
    | |
    |X|

6.  |X|
    |X|
    | |

diagonal
7.  | |
       |X|
          |X|

8.  |X|
       | |
          |X|

9. |X|
       |X|
          | |

counter diagonal
10.       | |
       |X|
    |X|

11.       |X|
       | |
    |X|

12.       |X|
       |X|
    | |

 */