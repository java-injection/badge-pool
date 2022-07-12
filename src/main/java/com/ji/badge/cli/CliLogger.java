package com.ji.badge.cli;

public class CliLogger {

    public static void info(String message){
        System.out.println("["+CliColors.BLUE_BRIGHT.code()+"INFO"+CliColors.ANSI_RESET.code()+"]" + message);
    }

    public static void warning(String message){
        System.out.println("["+CliColors.YELLOW_BRIGHT.code()+"INFO"+CliColors.ANSI_RESET.code()+"]" + CliColors.ANSI_YELLOW.code()+message+CliColors.ANSI_RESET.code());
    }


    public static void error(String message){
        System.out.println("["+CliColors.RED_BLOODY.code()+"INFO"+CliColors.ANSI_RESET.code()+"]" + CliColors.ANSI_RED.code()+message+CliColors.ANSI_RESET.code());
    }
}
