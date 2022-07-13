package com.ji.badge.server;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.ji.badge.cli.CliColors;
import com.ji.badge.logic.test.Account;
import com.ji.badge.logic.test.FakeDB;
import com.ji.badge.server.data.TestData;
import io.javalin.Javalin;
import io.javalin.plugin.json.JavalinJackson;

import java.util.Date;

import static io.javalin.apibuilder.ApiBuilder.get;
import static io.javalin.apibuilder.ApiBuilder.path;

public class BadgePoolServer {
    private static BadgePoolServer _instance = null;
    private Javalin server = null;
    private Thread serverThread;

    private ObjectMapper objectMapper = new ObjectMapper();

    private BadgePoolServer() {

    }

    public static BadgePoolServer getInstance() {
        if (_instance == null) {
            _instance = new BadgePoolServer();
        }
        return _instance;
    }

    public void start() {
        serverThread = new Thread(new Runnable() {
            @Override
            public void run() {
                server = Javalin.create().start(7070);

                server.ws("/console", ws -> {
                    ws.onConnect(ctx -> System.out.println("Connected"));
                });
                server.get("/", ctx -> ctx.result("Hello World 2"));
                server.get("/test", ctx -> ctx.result(generateTestData()));
                server.routes(() -> {
                    path("/test", () -> {
                        path("/accounts", () -> {
                            get(ctx -> ctx.result(
                                    new ObjectMapper().writerWithDefaultPrettyPrinter().writeValueAsString(FakeDB.getInstance().getAccounts())));
                        });
                    });
                });

                server.post("register", ctx -> {
                    Account account = objectMapper.readValue(ctx.body(), Account.class);
                    int id = FakeDB.getInstance().register(account.getUsername(),account.getPassword());
                    System.out.println("account id: "+account.getId());
                    ctx.result(""+id);
                });

                
                server.get("/accounts", ctx -> ctx.result(
                        new ObjectMapper().writerWithDefaultPrettyPrinter().writeValueAsString(FakeDB.getInstance().getAccounts())));


                System.out.println("[BadgePool] starting server..\t" + CliColors.GREEN_BRIGHT.code() + "SUCCESS" + CliColors.ANSI_RESET.code());
            }
        });
//        server = Javalin.create().start(7070);
//        server.get("/", ctx -> ctx.result("Hello World"));
//
//        server.get("/test", ctx -> ctx.result(generateTestData()));
        serverThread.start();
        System.out.println("[BadgePool] starting server..\t" + CliColors.GREEN_BRIGHT.code() + "SUCCESS" + CliColors.ANSI_RESET.code());

    }

    public void stop() {
        System.out.println("[BadgePool] stopping the server..\t");
        server.stop();
        serverThread.interrupt();
        System.out.println("[BadgePool] stopping the server..\t" + CliColors.RED_BLOODY.code() + "SUCCESS" + CliColors.ANSI_RESET.code());
    }

    public String generateTestData() throws JsonProcessingException {
        //Gson gson = new Gson();
        ObjectMapper mapper = new ObjectMapper();
        TestData td = new TestData(new Date(), true, 33);
        return mapper.writeValueAsString(td);
    }
}
