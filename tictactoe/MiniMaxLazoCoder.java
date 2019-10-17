package tictactoe;

public class MiniMaxLazoCoder {

    /**
     * Uses the MiniMax algorithm to play a move in a game of Tic Tac Toe.
     */

        private static double maxPly;

        /**
         * MiniMax cannot be instantiated.
         */
        private MiniMaxLazoCoder() {}

        /**
         * Execute the algorithm.
         * @param player        the player that the AI will identify as
         * @param board         the Tic Tac Toe board to play on
         * @param maxPly        the maximum depth
         */
        static void run (char player, Board board, double maxPly) {
            if (maxPly < 1) {
                throw new IllegalArgumentException("Maximum depth must be greater than 0.");
            }

            MiniMaxLazoCoder.maxPly = maxPly;
            miniMax(player, board, 0);
        }

        /**
         * The meat of the algorithm.
         * @param player        the player that the AI will identify as
         * @param board         the Tic Tac Toe board to play on
         * @param currentDepth    the current depth
         * @return              the score of the board
         */
        private static int miniMax (char player, Board board, int currentDepth) {
            if (currentDepth++ == maxPly || board.isGameOver()) {
                return score(player, board);
            }

            if (board.getTurn() == player) {
                return getMax(player, board, currentDepth);
            } else {
                return getMin(player, board, currentDepth);
            }

        }

        /**
         * Play the move with the highest score.
         * @param player        the player that the AI will identify as
         * @param board         the Tic Tac Toe board to play on
         * @param currentDepth    the current depth
         * @return              the score of the board
         */
        private static int getMax (char player, Board board, int currentDepth) {
            double bestScore = Double.NEGATIVE_INFINITY;
            int indexOfBestMove = -1;

            for (Integer theMove : board.getAvailableMoves()) {

                Board modifiedBoard = board.getDeepCopy();
                modifiedBoard.move(theMove);

                int score = miniMax(player, modifiedBoard, currentDepth);

                if (score >= bestScore) {
                    bestScore = score;
                    indexOfBestMove = theMove;
                }

            }

            board.move(indexOfBestMove);
            return (int)bestScore;
        }

        /**
         * Play the move with the lowest score.
         * @param player        the player that the AI will identify as
         * @param board         the Tic Tac Toe board to play on
         * @param currentDepth    the current depth
         * @return              the score of the board
         */
        private static int getMin (char player, Board board, int currentDepth) {
            double bestScore = Double.POSITIVE_INFINITY;
            int indexOfBestMove = -1;

            for (Integer theMove : board.getAvailableMoves()) {

                Board modifiedBoard = board.getDeepCopy();
                modifiedBoard.move(theMove);

                int score = miniMax(player, modifiedBoard, currentDepth);

                if (score <= bestScore) {
                    bestScore = score;
                    indexOfBestMove = theMove;
                }

            }

            board.move(indexOfBestMove);
            return (int)bestScore;
        }

        /**
         * Get the score of the board.
         * @param player        the play that the AI will identify as
         * @param board         the Tic Tac Toe board to play on
         * @return              the score of the board
         */
        private static int score (char player, Board board) {
            if (player == Board.State.Blank) {
                throw new IllegalArgumentException("Player must be X or O.");
            }

            Board.State opponent = (player == Board.State.X) ? Board.State.O : Board.State.X;

            if (board.isGameOver() && board.getWinner() == player) {
                return 10;
            } else if (board.isGameOver() && board.getWinner() == opponent) {
                return -10;
            } else {
                return 0;
            }
        }


    }
}
