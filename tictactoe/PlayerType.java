package tictactoe;

public enum PlayerType {
    USER, BOT_EASY, BOT_MEDIUM, BOT_HARD;

    static PlayerType get(String symbol) {
        switch (symbol) {
            case "user": return USER;
            case "easy": return BOT_EASY;
            case "medium": return BOT_MEDIUM;
            case "hard": return BOT_HARD;
            default: return null;
        }
    }
}
