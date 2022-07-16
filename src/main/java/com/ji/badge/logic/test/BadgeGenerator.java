package com.ji.badge.logic.test;

import com.ji.badge.cli.CliLogger;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class BadgeGenerator {

    public static final String WRAPPED_DECIMAL_NUMBER_REGEX = ">[0-9]+(\\.[0-9]+)?%<\\/text";
    public static final String DECIMAL_NUMBER_REGEX = "[0-9]+(\\.[0-9]+)?";
    public static final String COVERAGE_COLOR = "999999";

    public static String sonarBadgeFix(String badBadge){

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
                    if (value <= 10) {
                        badBadge = badBadge.replace(COVERAGE_COLOR, "660E0E");
                    } else if (value > 10 && value <= 20) {

                    } else if (value > 20 && value <= 40) {

                    } else if (value > 40 && value <= 55) {

                    } else if (value > 55 && value <= 65) {

                    } else if (value > 65 && value <= 79) {

                    } else if (value >= 80 && value <= 90) {

                    } else if (value > 90) {

                    }
                }
            }else{
                System.out.println("pattern not found");
            }
        }
        return badBadge;
    }
}
