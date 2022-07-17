package com.ji.badge.server;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.ji.badge.cli.CliColors;
import com.ji.badge.cli.CliLogger;
import com.ji.badge.logic.test.Account;
import com.ji.badge.logic.test.BadgeGenerator;
import com.ji.badge.logic.test.FakeDB;
import com.ji.badge.server.data.TestData;
import io.javalin.Javalin;
import io.javalin.plugin.json.JavalinJackson;
import io.javalin.plugin.openapi.OpenApiOptions;
import io.javalin.plugin.openapi.OpenApiPlugin;
import io.javalin.plugin.openapi.ui.SwaggerOptions;
import io.swagger.v3.oas.models.info.Info;
import kotlin.Pair;

import java.util.Date;
import java.util.List;
import java.util.Map;

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
                    path("/coverage", () ->{
                        path("/sonarqube", () ->{
                            get(ctx -> {
                                Map<String, List<String>> stringListMap = ctx.formParamMap();
                                System.out.println("mappa size: "+stringListMap);
                                System.out.println(stringListMap);
                                String url = ctx.queryParam("url");
                                String project_key = ctx.queryParam("project-key");
                                String token = ctx.queryParam("token");
                                Pair<String, Float> svgFileValuePair = BadgeGenerator.fetchSonarCoverageValue(url, project_key, token);
                                CliLogger.info("value fetched from coverage url is: "+svgFileValuePair.getSecond());
                                ctx.contentType("image/svg+xml");
                                ctx.result(BadgeGenerator.sonarBadgeFix(svgFileValuePair.getFirst(), svgFileValuePair.getSecond()));
                            });
                        });
                    });
                });
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
                            path("/reset/{withgames}" ,() -> {
                                patch(ctx -> {
                                            boolean withGames = Boolean.parseBoolean(ctx.pathParam("withgames"));
                                            CliLogger.warning("Resetting accounts table .. [withgames="+withGames+"]");
                                            if(withGames){
                                                FakeDB.getInstance().resetWithAccountsAndGame();
                                            }else{
                                                FakeDB.getInstance().reset();
                                            }
                                            ctx.result("ok fra");
                                            ctx.status(201);
                                        }
                                );
                            });
                        });
                    });
                });

                server.get("svgtest", ctx ->{
                    ctx.contentType("image/svg+xml");
                    ctx.result("<?xml version=\"1.0\" standalone=\"no\"?>\n" +
                            "<!DOCTYPE svg PUBLIC \"-//W3C//DTD SVG 20010904//EN\"\n" +
                            " \"http://www.w3.org/TR/2001/REC-SVG-20010904/DTD/svg10.dtd\">\n" +
                            "<svg version=\"1.0\" xmlns=\"http://www.w3.org/2000/svg\"\n" +
                            " width=\"498.000000pt\" height=\"500.000000pt\" viewBox=\"0 0 498.000000 500.000000\"\n" +
                            " preserveAspectRatio=\"xMidYMid meet\">\n" +
                            "\n" +
                            "<g transform=\"translate(0.000000,500.000000) scale(0.100000,-0.100000)\"\n" +
                            "fill=\"#000000\" stroke=\"none\">\n" +
                            "<path d=\"M4921 4984 c-117 -31 -275 -141 -435 -300 -345 -345 -602 -825 -700\n" +
                            "-1307 -23 -114 -39 -236 -50 -384 l-4 -63 624 0 624 0 0 1035 c0 569 -1 1035\n" +
                            "-2 1034 -2 0 -27 -7 -57 -15z\"/>\n" +
                            "<path d=\"M0 2483 c0 -5 5 -71 10 -148 38 -530 225 -1010 554 -1415 323 -398\n" +
                            "791 -702 1280 -831 1036 -273 2116 133 2711 1021 248 370 382 768 415 1225 5\n" +
                            "77 10 143 10 148 0 4 -278 7 -618 7 l-618 0 -12 -112 c-59 -560 -452 -997\n" +
                            "-1002 -1114 -116 -24 -352 -25 -473 -1 -248 50 -467 167 -647 347 -217 217\n" +
                            "-339 483 -366 798 l-7 82 -618 0 c-341 0 -619 -3 -619 -7z\"/>\n" +
                            "</g>\n" +
                            "</svg>");
                });
                server.get("sonar-cover-badge-pool", ctx ->{
                    ctx.contentType("image/svg+xml");
                    ctx.result("<svg xmlns=\"http://www.w3.org/2000/svg\" height=\"20\" width=\"124\">\n" +
                            "    <!-- SONARQUBE MEASURE -->\n" +
                            "    <linearGradient id=\"b\" x2=\"0\" y2=\"100%\">\n" +
                            "        <stop offset=\"0\" stop-color=\"#bbb\" stop-opacity=\".1\"/>\n" +
                            "        <stop offset=\"1\" stop-opacity=\".1\"/>\n" +
                            "    </linearGradient>\n" +
                            "    <clipPath id=\"a\">\n" +
                            "        <rect width=\"124\" height=\"20\" rx=\"3\" fill=\"#fff\"/>\n" +
                            "    </clipPath>\n" +
                            "    <g clip-path=\"url(#a)\">\n" +
                            "        <rect fill=\"#555\" height=\"20\" width=\"82\"/>\n" +
                            "        <rect fill=\"#00FF00\" height=\"20\" width=\"42\" x=\"82\"/>\n" +
                            "        <rect fill=\"url(#b)\" height=\"20\" width=\"124\"/>\n" +
                            "    </g>\n" +
                            "    <g fill=\"#fff\" font-family=\"DejaVu Sans,Verdana,Geneva,sans-serif\" font-size=\"11\" text-anchor=\"left\">\n" +
                            "        <text x=\"26\" y=\"15\" textLength=\"50\" fill=\"#FFFFFF\" fill-opacity=\".3\">Cepilli</text>\n" +
                            "        <text x=\"26\" y=\"14\" textLength=\"50\">Cepilli</text>\n" +
                            "        <text x=\"88\" y=\"15\" textLength=\"30\" fill=\"#000\" fill-opacity=\".3\">CHAD</text>\n" +
                            "        <text x=\"88\" y=\"14\" textLength=\"30\" fill=\"#000\">CHAD</text>\n" +
                            "    </g>\n" +
                            "    <g fill=\"#4e9bcd\">\n" +
                            "        <path d=\"M17.975 16.758h-.815c0-6.557-5.411-11.893-12.062-11.893v-.813c7.102 0 12.877 5.7 12.877 12.706z\"/>\n" +
                            "        <path d=\"M18.538 12.386c-.978-4.116-4.311-7.55-8.49-8.748l.186-.65c4.411 1.266 7.93 4.895 8.964 9.243zm.626-3.856a12.48 12.48 0 0 0-4.832-5.399l.282-.464a13.031 13.031 0 0 1 5.044 5.63z\"/>\n" +
                            "    </g>\n" +
                            "</svg>");
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
