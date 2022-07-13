package com.ji.badge;

import com.ji.badge.cli.CliColors;
import com.ji.badge.cli.CliLogger;
import com.ji.badge.cli.CliStandards;
import com.ji.badge.logic.test.FakeDB;
import com.ji.badge.server.BadgePoolServer;
import org.fusesource.jansi.AnsiConsole;

import java.util.Scanner;

public class Main {

    public static final String version = "0.1";
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
        System.out.println("\n" + welcomeMessage);
        System.out.println("[BadgePool] starting server..");
        BadgePoolServer.getInstance().start();
        System.out.println("-- end --");
        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNextLine()) {
            String command = scanner.nextLine();
            if (command.equals("start server")) {
                BadgePoolServer.getInstance().start();
            } else if (command.equals("stop server")) {
                BadgePoolServer.getInstance().stop();
            } else if (command.equals("test")) {
                FakeDB.getInstance().initWithFakeData();
            }  else if (command.equals("exit")) {
                System.out.println(("bye."));
                return;
            } else if (command.equals("version")) {
                CliLogger.info(" Version: "+ version);
            }else if (command.equals("status")) {
                if(BadgePoolServer.getInstance().isOnline()){
                    CliLogger.info("Server is "+CliColors.GREEN_BRIGHT.code()+"ONLINE"+CliColors.ANSI_RESET.code());
                }else{
                    CliLogger.error("Server is OFFLINE");
                }
            }  else {
                System.out.println("[BadgePool] comando sconosciuto..");
            }
        }

    }
}