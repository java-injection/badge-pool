package com.ji.badge.logic.test;

import com.ji.badge.cli.CliLogger;
import kotlin.Pair;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.invoke.StringConcatException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class BadgeGenerator {

    public static final String WRAPPED_DECIMAL_NUMBER_REGEX = ">[0-9]+(\\.[0-9]+)?%<\\/text";
    public static final String DECIMAL_NUMBER_REGEX = "[0-9]+(\\.[0-9]+)?";
    public static final String COVERAGE_COLOR = "#999999";


    public static Pair<String,Float> fetchSonarCoverageValue(String serverURL, String projectKey, String token) throws IOException {

        CliLogger.info(" serverURL: "+serverURL);
        CliLogger.info("projectKey: "+projectKey);
        CliLogger.info("     token: "+token);

        String baseGET = "https://"+serverURL+"/api/project_badges/measure?project="+projectKey+"&metric=coverage&token="+token;
        CliLogger.info("GET URL: "+baseGET);
        URL url = new URL(baseGET);
        HttpURLConnection con = (HttpURLConnection) url.openConnection();
        con.setRequestMethod("GET");
        con.setConnectTimeout(5000);
        con.setReadTimeout(5000);
        StringBuilder result = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(
                new InputStreamReader(con.getInputStream()))) {
            for (String line; (line = reader.readLine()) != null; ) {
                result.append(line);
            }
        }
        String s_result = result.toString();
        CliLogger.info("message received: "+s_result);
        float value = extractCoverageValue(s_result);

        return new Pair<>(s_result,value);
    }

    private static float extractCoverageValue(String badBadge){
        if(null != badBadge && !badBadge.isEmpty()){
            System.out.println("qui");
            Pattern pattern = Pattern.compile(WRAPPED_DECIMAL_NUMBER_REGEX);
            Matcher matcher = pattern.matcher(badBadge);
            if (matcher.find())
            {
                String s_value= matcher.group(0);
                CliLogger.info("wrapped string value: "+s_value);

                Pattern singleNumberPattern = Pattern.compile(DECIMAL_NUMBER_REGEX);
                Matcher singleNumPatternMatcher = singleNumberPattern.matcher(s_value);
                if(singleNumPatternMatcher.find()) {
                    String s_number = singleNumPatternMatcher.group(0);
                    CliLogger.info("string number: " + s_number);


                    float value = Float.parseFloat(s_number);
                    CliLogger.info("value from bad badge: " + value);
                   return value;
                }
            }else{
                System.out.println("pattern not found");
            }
        }
        throw new IllegalArgumentException();
    }

    public static String sonarBadgeFix(String badBadge, float value){

        if(null != badBadge && !badBadge.isEmpty()){
            System.out.println("qui");
            Pattern pattern = Pattern.compile(WRAPPED_DECIMAL_NUMBER_REGEX);
            Matcher matcher = pattern.matcher(badBadge);

                    CliLogger.info("value from bad badge: " + value);
                    if (value <= 10) {
                        badBadge = badBadge.replace(COVERAGE_COLOR, "#660E0E");
                    } else if (value > 10 && value <= 20) {
                        badBadge = badBadge.replace(COVERAGE_COLOR, "#870D0D");
                    } else if (value > 20 && value <= 40) {
                        badBadge = badBadge.replace(COVERAGE_COLOR, "#F50E0E");
                    } else if (value > 40 && value <= 55) {
                        badBadge = badBadge.replace(COVERAGE_COLOR, "#B97105");
                    } else if (value > 55 && value <= 65) {
                        badBadge = badBadge.replace(COVERAGE_COLOR, "#CE4C13");
                    } else if (value > 65 && value <= 79) {
                        badBadge = badBadge.replace(COVERAGE_COLOR, "#CAAF03");
                    } else if (value >= 80 && value <= 90) {
                        badBadge = badBadge.replace(COVERAGE_COLOR, "#0AA533");
                    } else if (value > 90) {
                        badBadge = badBadge.replace(COVERAGE_COLOR, "#007F22");
                    }

            }else{
                System.out.println("pattern not found");
            }

        return badBadge;
    }
}
