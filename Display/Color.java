package Display;
/**
 * Ebben az osztalyban a megjeleniteshez hasznalatos szinek vannak eltarolva.
 */
public class Color {
    public static final String RESET = "\u001B[0m";
    public static final String WHITE = "\u001B[37;1m";
    public static final String RED = "\u001B[31;1m";
    public static final String ORANGE = "\u001B[31;1m";
    public static final String GREEN = "\u001B[32;1m";
    public static final String BLACK = "\u001B[30m";
    public static final String YELLOW = "\u001B[33;1m";
    public static final String BLUE = "\u001B[34;1m";
    public static final String PURPLE = "\u001B[35m";
    public static final String CYAN = "\u001B[36m";
    public static final String BROWN = "\u001B[38;5;94m";
    public static final String BRIGHT_BLUE = "\u001b[38;5;87m";
    public static final String BRIGHT_ORANGE = "\u001b[38;5;208m";

    public static final String GREEN_BACKGROUND = "\u001B[42m";
    public static final String RED_BACKGROUND = "\u001B[41m";
    public static final String BLACK_BACKGROUND = "\u001B[40m";
    public static final String WHITE_BACKGROUND = "\u001B[47;1m";
    public static final String YELLOW_BACKGROUND = "\u001B[43m";
    public static final String BLUE_BACKGROUND = "\u001B[44m";
    public static final String PURPLE_BACKGROUND = "\u001B[45m";
    public static final String CYAN_BACKGROUND = "\u001B[46m";

    public static final String BOLD = "\u001b[1m";
    public static final String UNDERLINE = "\u001b[4m";
    public static final String REVERSED = "\u001b[7m";

    public static String sebzesColor(){
        return RED;
    }

    public static String blackBg(String t){
        return BLACK_BACKGROUND + t + RESET;
    }
    public static String redBg(String t){
        return RED_BACKGROUND + t + RESET;
    }
}
