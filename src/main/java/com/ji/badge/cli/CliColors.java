package com.ji.badge.cli;

public enum CliColors {

     ANSI_RESET("\u001B[0m"),
     ANSI_BLACK ("\u001B[30m"),
     ANSI_RED ("\u001B[31m"),
     ANSI_GREEN ("\u001B[32m"),
     ANSI_YELLOW ("\u001B[33m"),
     ANSI_BLUE ("\u001B[34m"),
     ANSI_PURPLE ("\u001B[35m"),
     ANSI_CYAN ("\u001B[36m"),
     ANSI_WHITE ("\u001B[37m"),
     ANSI_ORANGE ("\033[38;2;199;109;0m"),


     ANSI_BLACK_BACKGROUND ("\u001B[40m"),
     ANSI_RED_BACKGROUND ("\u001B[41m"),
     ANSI_GREEN_BACKGROUND ("\u001B[42m"),
     ANSI_YELLOW_BACKGROUND ("\u001B[43m"),
     ANSI_BLUE_BACKGROUND ("\u001B[44m"),
     ANSI_PURPLE_BACKGROUND ("\u001B[45m"),
     ANSI_CYAN_BACKGROUND ("\u001B[46m"),
     ANSI_WHITE_BACKGROUND ("\u001B[47m"),

     RED_BLOODY ("\033[38;2;68;0;0m"),

     IVORY ("\033[38;2;145;201;0m"),

    // Bold
     BLACK_BOLD ("\033[1;30m"),  // BLACK
     RED_BOLD ("\033[1;31m"),    // RED
     GREEN_BOLD ("\033[1;32m"),  // GREEN
     YELLOW_BOLD ("\033[1;33m"), // YELLOW
     BLUE_BOLD ("\033[1;34m"),   // BLUE
     PURPLE_BOLD ("\033[1;35m"), // PURPLE
     CYAN_BOLD ("\033[1;36m"),   // CYAN
     WHITE_BOLD ("\033[1;37m"),  // WHITE

    // Underline
     BLACK_UNDERLINED ("\033[4;30m"),  // BLACK
     RED_UNDERLINED ("\033[4;31m"),    // RED
     GREEN_UNDERLINED ("\033[4;32m"),  // GREEN
     YELLOW_UNDERLINED ("\033[4;33m"), // YELLOW
     BLUE_UNDERLINED ("\033[4;34m"),   // BLUE
     PURPLE_UNDERLINED ("\033[4;35m"), // PURPLE
     CYAN_UNDERLINED ("\033[4;36m"),   // CYAN
     WHITE_UNDERLINED ("\033[4;37m"),  // WHITE

    // Background
     BLACK_BACKGROUND ("\033[40m"),  // BLACK
     RED_BACKGROUND ("\033[41m"),    // RED
     GREEN_BACKGROUND ("\033[42m"),  // GREEN
     YELLOW_BACKGROUND ("\033[43m"), // YELLOW
     BLUE_BACKGROUND ("\033[44m"),   // BLUE
     PURPLE_BACKGROUND ("\033[45m"), // PURPLE
     CYAN_BACKGROUND ("\033[46m"),   // CYAN
     WHITE_BACKGROUND ("\033[47m"),  // WHITE

    // High Intensity
     BLACK_BRIGHT ("\033[0;90m"),  // BLACK
     RED_BRIGHT ("\033[0;91m"),    // RED
     GREEN_BRIGHT ("\033[0;92m"),  // GREEN
     YELLOW_BRIGHT ("\033[0;93m"), // YELLOW
     BLUE_BRIGHT ("\033[0;94m"),   // BLUE
     PURPLE_BRIGHT ("\033[0;95m"), // PURPLE
     CYAN_BRIGHT ("\033[0;96m"),   // CYAN
     WHITE_BRIGHT ("\033[0;97m"),  // WHITE

    // Bold High Intensity
     BLACK_BOLD_BRIGHT ("\033[1;90m"), // BLACK
     RED_BOLD_BRIGHT ("\033[1;91m"),   // RED
     GREEN_BOLD_BRIGHT ("\033[1;92m"), // GREEN
     YELLOW_BOLD_BRIGHT ("\033[1;93m"),// YELLOW
     BLUE_BOLD_BRIGHT ("\033[1;94m"),  // BLUE
     PURPLE_BOLD_BRIGHT ("\033[1;95m"),// PURPLE
     CYAN_BOLD_BRIGHT ("\033[1;96m"),  // CYAN
     WHITE_BOLD_BRIGHT ("\033[1;97m"), // WHITE

    // High Intensity backgrounds
     BLACK_BACKGROUND_BRIGHT ("\033[0;100m"),// BLACK
     RED_BACKGROUND_BRIGHT ("\033[0;101m"),// RED
     GREEN_BACKGROUND_BRIGHT ("\033[0;102m"),// GREEN
     YELLOW_BACKGROUND_BRIGHT ("\033[0;103m"),// YELLOW
     BLUE_BACKGROUND_BRIGHT ("\033[0;104m"),// BLUE
     PURPLE_BACKGROUND_BRIGHT ("\033[0;105m"), // PURPLE
     CYAN_BACKGROUND_BRIGHT ("\033[0;106m"),  // CYAN
     WHITE_BACKGROUND_BRIGHT ("\033[0;107m");   // WHITE

    private final String code;

    private CliColors(String code){
        this.code = code;
    }

    public String code(){
        return this.code;
    }

}
