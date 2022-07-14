package com.ji.badge.server;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.ji.badge.cli.CliColors;
import com.ji.badge.cli.CliLogger;
import com.ji.badge.logic.test.Account;
import com.ji.badge.logic.test.FakeDB;
import com.ji.badge.server.data.TestData;
import io.javalin.Javalin;
import io.javalin.plugin.json.JavalinJackson;
import io.javalin.plugin.openapi.OpenApiOptions;
import io.javalin.plugin.openapi.OpenApiPlugin;
import io.javalin.plugin.openapi.ui.SwaggerOptions;
import io.swagger.v3.oas.models.info.Info;

import java.util.Date;

import static io.javalin.apibuilder.ApiBuilder.*;

public class BadgePoolServer {
    private static BadgePoolServer _instance = null;
    private Javalin server = null;
    private Thread serverThread;

    private ObjectMapper objectMapper = new ObjectMapper();
    private boolean online = false;

    private BadgePoolServer() {

    }

    public static BadgePoolServer getInstance() {
        if (_instance == null) {
            _instance = new BadgePoolServer();
        }
        return _instance;
    }

    public boolean isOnline() {
        return online;
    }

    private OpenApiOptions getOpenApiOptions() {
        Info applicationInfo = new Info()
                .version("1.0")
                .description("Badge-Pool");
        return new OpenApiOptions(applicationInfo)
                .path("/docs")
                .swagger(new SwaggerOptions("/swagger").title("Badge-Pool Documentation"));
    }

    public void start() {
        serverThread = new Thread(new Runnable() {
            @Override
            public void run() {



                server = Javalin.create(
                        config -> {
                            config.registerPlugin(new OpenApiPlugin(getOpenApiOptions()));
                        }


                ).start(7070);

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
                            path("/{id}" ,() -> {
                                get(ctx -> {
                                        int id = Integer.parseInt(ctx.pathParam("id"));
                                            CliLogger.info("retrieving Account data with id: "+id);
                                        ctx.result(new ObjectMapper().writerWithDefaultPrettyPrinter().writeValueAsString(FakeDB.getInstance().getAccountByID(id)));
                                }
                                );
                                path("/addmoney/{money}" ,() -> {
                                    patch(ctx -> {
                                                int money = Integer.parseInt(ctx.pathParam("money"));
                                                int id = Integer.parseInt(ctx.pathParam("id"));
                                                CliLogger.info("adding "+money+" € to the account with id: "+id);
                                                FakeDB.getInstance().addMoneyToAccount(id, money);
                                                ctx.result("ok fra");
                                                ctx.status(201);
                                            }
                                    );
                                });
                            });
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

                online = true;
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
        online = false;
    }

    public String generateTestData() throws JsonProcessingException {
        //Gson gson = new Gson();
        ObjectMapper mapper = new ObjectMapper();
        TestData td = new TestData(new Date(), true, 33);
        return mapper.writeValueAsString(td);
    }
}
