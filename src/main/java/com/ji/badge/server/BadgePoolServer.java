package com.ji.badge.server;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.ji.badge.cli.CliColors;
import com.ji.badge.server.data.TestData;
import io.javalin.Javalin;

import java.util.Date;

public class BadgePoolServer {
    private static BadgePoolServer _instance = null;
    private Javalin server = null;
    private Thread serverThread;

    private BadgePoolServer(){

    }

    public static BadgePoolServer getInstance(){
        if(_instance == null){
            _instance = new BadgePoolServer();
        }
        return _instance;
    }

    public void start(){
        serverThread = new Thread(new Runnable() {
            @Override
            public void run() {
                server = Javalin.create().start(7070);
                server.get("/", ctx -> ctx.result("Hello World"));
                server.get("/test", ctx -> ctx.result(generateTestData()));
                System.out.println("[BadgePool] starting server..\t" + CliColors.GREEN_BRIGHT.code()+"SUCCESS"+CliColors.ANSI_RESET.code());
            }
        });
        server = Javalin.create().start(7070);
        server.get("/", ctx -> ctx.result("Hello World"));

        server.get("/test", ctx -> ctx.result(generateTestData()));

        System.out.println("[BadgePool] starting server..\t" + CliColors.GREEN_BRIGHT.code()+"SUCCESS"+CliColors.ANSI_RESET.code());

    }

    public void stop(){
        System.out.println("[BadgePool] stopping the server..\t");
        server.stop();
        serverThread.interrupt();
        System.out.println("[BadgePool] stopping the server..\t" + CliColors.RED_BLOODY.code()+"SUCCESS"+CliColors.ANSI_RESET.code());
    }

    public String generateTestData() throws JsonProcessingException {
        //Gson gson = new Gson();
        ObjectMapper mapper = new ObjectMapper();
        TestData td = new TestData(new Date(), true, 33);
        return mapper.writeValueAsString(td);
    }
}
