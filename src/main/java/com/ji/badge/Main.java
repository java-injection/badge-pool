package com.ji.badge;

import com.ji.badge.cli.CliColors;
import com.ji.badge.cli.CliStandards;
import org.fusesource.jansi.AnsiConsole;

public class Main {
    public static void main(String[] args) {
        AnsiConsole.systemInstall();
       // CliStandards.title("Developed by");
       // System.out.println(CliStandards.JAVA_INJECTION);
       // CliStandards.closeTitle("Developed by");

        //String text = new CliStandards.ChainedString("Developed by").withBody(CliStandards.JAVA_INJECTION).close().build();

        String welcomeMessage = CliStandards.initBlock("Developed by").
                withBody(CliStandards.JAVA_INJECTION, CliColors.ANSI_GREEN).
                close().
                br().addLine().br().
                newBlock(CliStandards.BADGE_POOL, CliColors.RED_BLOODY).
                close().br().addLine().
                build();
        System.out.println("\n"+welcomeMessage);
    }
}