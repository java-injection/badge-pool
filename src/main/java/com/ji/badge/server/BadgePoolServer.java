package com.ji.badge.server;

import com.ji.badge.cli.CliColors;
import io.javalin.Javalin;

public class BadgePoolServer {
    private static BadgePoolServer _instance = null;

    private BadgePoolServer(){

    }

    public static BadgePoolServer getInstance(){
        if(_instance == null){
            _instance = new BadgePoolServer();
        }
        return _instance;
    }

    public void start(){
        Javalin app = Javalin.create().start(7070);
        app.get("/", ctx -> ctx.result("Hello World"));
        System.out.println("[BadgePool] starting server../t" + CliColors.GREEN_BRIGHT.code()+"SUCCESS"+CliColors.ANSI_RESET.code());
    }
}
