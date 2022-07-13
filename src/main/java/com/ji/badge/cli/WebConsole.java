package com.ji.badge.cli;

import com.sun.net.httpserver.HttpContext;
import com.sun.net.httpserver.HttpServer;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintStream;
import java.net.InetSocketAddress;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.Instant;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class WebConsole {


    public static void main(String[] args) {
        final Path myOutputFile = Paths.get("./MyOutputFile.txt");
        final PrintStream myStream;
        try {
            myStream = new PrintStream(myOutputFile.toFile());
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }

        System.out.println("Going to redirect to : " + myOutputFile.toAbsolutePath());

        System.setOut(myStream);
        System.setErr(myStream);

        System.out.println("Starting the Output");

        //Have something that logs every 5 seconds
        final ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);
        scheduler.scheduleAtFixedRate(() ->
        {
            System.out.println("Hello - the time is now " + Instant.now());
        }, 1, 5, TimeUnit.SECONDS);

        // Start the simple Java Built in Web Server.
        final HttpServer http;
        try {
            http = HttpServer.create(new InetSocketAddress(8888), 0);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        final HttpContext context = http.createContext("/");
        context.setHandler(exchange ->
        {
            byte[] data = Files.readAllBytes(myOutputFile);
            exchange.sendResponseHeaders(200, data.length);
            OutputStream os = exchange.getResponseBody();
            os.write(data);
            os.close();
        });
        http.start();
    }

}
