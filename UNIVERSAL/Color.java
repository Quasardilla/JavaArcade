package UNIVERSAL;



public class Color {
    // Reset
    public static String RESET = "\033[0m";  // Text Reset

    // Regular Colors
    private static String BLACK = "\033[0;30m";   // BLACK
    private static String RED = "\033[0;31m";     // RED
    private static String GREEN = "\033[0;32m";   // GREEN
    private static String YELLOW = "\033[0;33m";  // YELLOW
    private static String BLUE = "\033[0;34m";    // BLUE
    private static String PURPLE = "\033[0;35m";  // PURPLE
    private static String CYAN = "\033[0;36m";    // CYAN
    private static String WHITE = "\033[0;37m";   // WHITE

    // Bold
    private static String BLACK_BOLD = "\033[1;30m";  // BLACK
    private static String RED_BOLD = "\033[1;31m";    // RED
    private static String GREEN_BOLD = "\033[1;32m";  // GREEN
    private static String YELLOW_BOLD = "\033[1;33m"; // YELLOW
    private static String BLUE_BOLD = "\033[1;34m";   // BLUE
    private static String PURPLE_BOLD = "\033[1;35m"; // PURPLE
    private static String CYAN_BOLD = "\033[1;36m";   // CYAN
    private static String WHITE_BOLD = "\033[1;37m";  // WHITE

    // Underline
    private static String BLACK_UNDERLINED = "\033[4;30m";  // BLACK
    private static String RED_UNDERLINED = "\033[4;31m";    // RED
    private static String GREEN_UNDERLINED = "\033[4;32m";  // GREEN
    private static String YELLOW_UNDERLINED = "\033[4;33m"; // YELLOW
    private static String BLUE_UNDERLINED = "\033[4;34m";   // BLUE
    private static String PURPLE_UNDERLINED = "\033[4;35m"; // PURPLE
    private static String CYAN_UNDERLINED = "\033[4;36m";   // CYAN
    private static String WHITE_UNDERLINED = "\033[4;37m";  // WHITE

    // Background
    private static String BLACK_BACKGROUND = "\033[40m";  // BLACK
    private static String RED_BACKGROUND = "\033[41m";    // RED
    private static String GREEN_BACKGROUND = "\033[42m";  // GREEN
    private static String YELLOW_BACKGROUND = "\033[43m"; // YELLOW
    private static String BLUE_BACKGROUND = "\033[44m";   // BLUE
    private static String PURPLE_BACKGROUND = "\033[45m"; // PURPLE
    private static String CYAN_BACKGROUND = "\033[46m";   // CYAN
    private static String WHITE_BACKGROUND = "\033[47m";  // WHITE

    // High Intensity
    private static String BLACK_BRIGHT = "\033[0;90m";  // BLACK
    private static String RED_BRIGHT = "\033[0;91m";    // RED
    private static String GREEN_BRIGHT = "\033[0;92m";  // GREEN
    private static String YELLOW_BRIGHT = "\033[0;93m"; // YELLOW
    private static String BLUE_BRIGHT = "\033[0;94m";   // BLUE
    private static String PURPLE_BRIGHT = "\033[0;95m"; // PURPLE
    private static String CYAN_BRIGHT = "\033[0;96m";   // CYAN
    private static String WHITE_BRIGHT = "\033[0;97m";  // WHITE

    // Bold High Intensity
    private static String BLACK_BOLD_BRIGHT = "\033[1;90m"; // BLACK
    private static String RED_BOLD_BRIGHT = "\033[1;91m";   // RED
    private static String GREEN_BOLD_BRIGHT = "\033[1;92m"; // GREEN
    private static String YELLOW_BOLD_BRIGHT = "\033[1;93m";// YELLOW
    private static String BLUE_BOLD_BRIGHT = "\033[1;94m";  // BLUE
    private static String PURPLE_BOLD_BRIGHT = "\033[1;95m";// PURPLE
    private static String CYAN_BOLD_BRIGHT = "\033[1;96m";  // CYAN
    private static String WHITE_BOLD_BRIGHT = "\033[1;97m"; // WHITE

    // High Intensity backgrounds
    private static String BLACK_BACKGROUND_BRIGHT = "\033[0;100m";// BLACK
    private static String RED_BACKGROUND_BRIGHT = "\033[0;101m";// RED
    private static String GREEN_BACKGROUND_BRIGHT = "\033[0;102m";// GREEN
    private static String YELLOW_BACKGROUND_BRIGHT = "\033[0;103m";// YELLOW
    private static String BLUE_BACKGROUND_BRIGHT = "\033[0;104m";// BLUE
    private static String PURPLE_BACKGROUND_BRIGHT = "\033[0;105m"; // PURPLE
    private static String CYAN_BACKGROUND_BRIGHT = "\033[0;106m";  // CYAN
    private static String WHITE_BACKGROUND_BRIGHT = "\033[0;107m";   // WHITE
    
    public static void textRed()
    {
        System.out.print(RED);
    }
    public static void textBlack()
    {
        System.out.print(BLACK);
    }
    public static void textYellow()
    {
        System.out.print(YELLOW);
    }
    public static void textGreen()
    {
        System.out.print(GREEN);
    }
    public static void textBlue()
    {
        System.out.print(BLUE);
    }
    public static void textPurple()
    {
        System.out.print(PURPLE);
    }
    public static void textCyan()
    {
        System.out.print(CYAN);
    }
    public static void textWhite()
    {
        System.out.print(WHITE);
    }
    public static void textReset()
    {
        System.out.print(RESET);
    }
    //make methods that will change the background colors of the text
    public static void backgroundRed()
    {
        System.out.print(RED_BACKGROUND);
    }
    public static void backgroundBlack()
    {
        System.out.print(BLACK_BACKGROUND);
    }
    public static void backgroundYellow()
    {
        System.out.print(YELLOW_BACKGROUND);
    }
    public static void backgroundGreen()
    {
        System.out.print(GREEN_BACKGROUND);
    }
    public static void backgroundBlue()
    {
        System.out.print(BLUE_BACKGROUND);
    }
    public static void backgroundPurple()
    {
        System.out.print(PURPLE_BACKGROUND);
    }
    public static void backgroundCyan()
    {
        System.out.print(CYAN_BACKGROUND);
    }
    public static void backgroundWhite()
    {
        System.out.print(WHITE_BACKGROUND);
    }
    public static void backgroundReset()
    {
        System.out.print(RESET);
    }
    //make methods that will change the text to bold
    public static void textBoldRed()
    {
        System.out.print(RED_BOLD);
    }
    public static void textBoldBlack()
    {
        System.out.print(BLACK_BOLD);
    }
    public static void textBoldYellow()
    {
        System.out.print(YELLOW_BOLD);
    }
    public static void textBoldGreen()
    {
        System.out.print(GREEN_BOLD);
    }
    public static void textBoldBlue()
    {
        System.out.print(BLUE_BOLD);
    }
    public static void textBoldPurple()
    {
        System.out.print(PURPLE_BOLD);
    }
    public static void textBoldCyan()
    {
        System.out.print(CYAN_BOLD);
    }
    public static void textBoldWhite()
    {
        System.out.print(WHITE_BOLD);
    }
    public static void textBoldReset()
    {
        System.out.print(RESET);
    }
    //make methods that will change the text to bold and bright
    public static void textBoldBrightRed()
    {
        System.out.print(RED_BOLD_BRIGHT);
    }
    public static void textBoldBrightBlack()
    {
        System.out.print(BLACK_BOLD_BRIGHT);
    }
    public static void textBoldBrightYellow()
    {
        System.out.print(YELLOW_BOLD_BRIGHT);
    }
    public static void textBoldBrightGreen()
    {
        System.out.print(GREEN_BOLD_BRIGHT);
    }
    public static void textBoldBrightBlue()
    {
        System.out.print(BLUE_BOLD_BRIGHT);
    }
    public static void textBoldBrightPurple()
    {
        System.out.print(PURPLE_BOLD_BRIGHT);
    }
    public static void textBoldBrightCyan()
    {
        System.out.print(CYAN_BOLD_BRIGHT);
    }
    public static void textBoldBrightWhite()
    {
        System.out.print(WHITE_BOLD_BRIGHT);
    }
    public static void textBoldBrightReset()
    {
        System.out.print(RESET);
    }
    //make methods that will change the background to bold
    public static void backgroundBoldRed()
    {
        System.out.print(RED_BACKGROUND_BRIGHT);
    }
    public static void backgroundBoldBlack()
    {
        System.out.print(BLACK_BACKGROUND_BRIGHT);
    }
    public static void backgroundBoldYellow()
    {
        System.out.print(YELLOW_BACKGROUND_BRIGHT);
    }
    public static void backgroundBoldGreen()
    {
        System.out.print(GREEN_BACKGROUND_BRIGHT);
    }
    public static void backgroundBoldBlue()
    {
        System.out.print(BLUE_BACKGROUND_BRIGHT);
    }
    public static void backgroundBoldPurple()
    {
        System.out.print(PURPLE_BACKGROUND_BRIGHT);
    }
    public static void backgroundBoldCyan()
    {
        System.out.print(CYAN_BACKGROUND_BRIGHT);
    }
    public static void backgroundBoldWhite()
    {
        System.out.print(WHITE_BACKGROUND_BRIGHT);
    }
    public static void backgroundBoldReset()
    {
        System.out.print(RESET);
    }
    //make methods that will change the background to bold and bright
    public static void backgroundBoldBrightRed()
    {
        System.out.print(RED_BACKGROUND_BRIGHT);
    }
    public static void backgroundBoldBrightBlack()
    {
        System.out.print(BLACK_BACKGROUND_BRIGHT);
    }
    public static void backgroundBoldBrightYellow()
    {
        System.out.print(YELLOW_BACKGROUND_BRIGHT);
    }
    public static void backgroundBoldBrightGreen()
    {
        System.out.print(GREEN_BACKGROUND_BRIGHT);
    }
    public static void backgroundBoldBrightBlue()
    {
        System.out.print(BLUE_BACKGROUND_BRIGHT);
    }
    public static void backgroundBoldBrightPurple()
    {
        System.out.print(PURPLE_BACKGROUND_BRIGHT);
    }
    public static void backgroundBoldBrightCyan()
    {
        System.out.print(CYAN_BACKGROUND_BRIGHT);
    }
    public static void backgroundBoldBrightWhite()
    {
        System.out.print(WHITE_BACKGROUND_BRIGHT);
    }
    public static void backgroundBoldBrightReset()
    {
        System.out.print(RESET);
    }

    //make methods that will change the text to underline
    public static void textUnderlineRed()
    {
        System.out.print(RED_UNDERLINED);
    }
    public static void textUnderlineBlack()
    {
        System.out.print(BLACK_UNDERLINED);
    }
    public static void textUnderlineYellow()
    {
        System.out.print(YELLOW_UNDERLINED);
    }
    public static void textUnderlineGreen()
    {
        System.out.print(GREEN_UNDERLINED);
    }
    public static void textUnderlineBlue()
    {
        System.out.print(BLUE_UNDERLINED);
    }
    public static void textUnderlinePurple()
    {
        System.out.print(PURPLE_UNDERLINED);
    }
    public static void textUnderlineCyan()
    {
        System.out.print(CYAN_UNDERLINED);
    }
    public static void textUnderlineWhite()
    {
        System.out.print(WHITE_UNDERLINED);
    }
    public static void textUnderlineReset()
    {
        System.out.print(RESET);
    }
    //make methods that will change the text to bright

    public static void textBrightRed()
    {
        System.out.print(RED_BRIGHT);
    }
    public static void textBrightBlack()
    {
        System.out.print(BLACK_BRIGHT);
    }
    public static void textBrightYellow()
    {
        System.out.print(YELLOW_BRIGHT);
    }
    public static void textBrightGreen()
    {
        System.out.print(GREEN_BRIGHT);
    }
    public static void textBrightBlue()
    {
        System.out.print(BLUE_BRIGHT);
    }
    public static void textBrightPurple()
    {
        System.out.print(PURPLE_BRIGHT);
    }
    public static void textBrightCyan()
    {
        System.out.print(CYAN_BRIGHT);
    }
    public static void textBrightWhite()
    {
        System.out.print(WHITE_BRIGHT);
    }
    public static void textBrightReset()
    {
        System.out.print(RESET);
    }
    //make methods that will change the background to bright
    public static void backgroundBrightRed()
    {
        System.out.print(RED_BACKGROUND_BRIGHT);
    }
    public static void backgroundBrightBlack()
    {
        System.out.print(BLACK_BACKGROUND_BRIGHT);
    }
    public static void backgroundBrightYellow()
    {
        System.out.print(YELLOW_BACKGROUND_BRIGHT);
    }
    public static void backgroundBrightGreen()
    {
        System.out.print(GREEN_BACKGROUND_BRIGHT);
    }
    public static void backgroundBrightBlue()
    {
        System.out.print(BLUE_BACKGROUND_BRIGHT);
    }
    public static void backgroundBrightPurple()
    {
        System.out.print(PURPLE_BACKGROUND_BRIGHT);
    }
    public static void backgroundBrightCyan()
    {
        System.out.print(CYAN_BACKGROUND_BRIGHT);
    }
    public static void backgroundBrightWhite()
    {
        System.out.print(WHITE_BACKGROUND_BRIGHT);
    }
    public static void backgroundBrightReset()
    {
        System.out.print(RESET);
    }
}