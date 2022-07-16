package com.ji.badge.logic.test;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BadgeGeneratorTest {

    @Test
    void sonarBadgeFix() {

        String sampleBadBadge = "<svg xmlns=\"http://www.w3.org/2000/svg\" height=\"20\" width=\"124\">\n" +
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
                "        <rect fill=\"#999999\" height=\"20\" width=\"42\" x=\"82\"/>\n" +
                "        <rect fill=\"url(#b)\" height=\"20\" width=\"124\"/>\n" +
                "    </g>\n" +
                "    <g fill=\"#fff\" font-family=\"DejaVu Sans,Verdana,Geneva,sans-serif\" font-size=\"11\" text-anchor=\"left\">\n" +
                "        <text x=\"26\" y=\"15\" textLength=\"50\" fill=\"#010101\" fill-opacity=\".3\">coverage</text>\n" +
                "        <text x=\"26\" y=\"14\" textLength=\"50\">coverage</text>\n" +
                "        <text x=\"88\" y=\"15\" textLength=\"30\" fill=\"#010101\" fill-opacity=\".3\">3.2%</text>\n" +
                "        <text x=\"88\" y=\"14\" textLength=\"30\">3.2%</text>\n" +
                "    </g>\n" +
                "    <g fill=\"#4e9bcd\">\n" +
                "        <path d=\"M17.975 16.758h-.815c0-6.557-5.411-11.893-12.062-11.893v-.813c7.102 0 12.877 5.7 12.877 12.706z\"/>\n" +
                "        <path d=\"M18.538 12.386c-.978-4.116-4.311-7.55-8.49-8.748l.186-.65c4.411 1.266 7.93 4.895 8.964 9.243zm.626-3.856a12.48 12.48 0 0 0-4.832-5.399l.282-.464a13.031 13.031 0 0 1 5.044 5.63z\"/>\n" +
                "    </g>\n" +
                "</svg>";

        String correctedBadge = "<svg xmlns=\"http://www.w3.org/2000/svg\" height=\"20\" width=\"124\">\n" +
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
                "        <rect fill=\"#660E0E\" height=\"20\" width=\"42\" x=\"82\"/>\n" +
                "        <rect fill=\"url(#b)\" height=\"20\" width=\"124\"/>\n" +
                "    </g>\n" +
                "    <g fill=\"#fff\" font-family=\"DejaVu Sans,Verdana,Geneva,sans-serif\" font-size=\"11\" text-anchor=\"left\">\n" +
                "        <text x=\"26\" y=\"15\" textLength=\"50\" fill=\"#010101\" fill-opacity=\".3\">coverage</text>\n" +
                "        <text x=\"26\" y=\"14\" textLength=\"50\">coverage</text>\n" +
                "        <text x=\"88\" y=\"15\" textLength=\"30\" fill=\"#010101\" fill-opacity=\".3\">3.2%</text>\n" +
                "        <text x=\"88\" y=\"14\" textLength=\"30\">3.2%</text>\n" +
                "    </g>\n" +
                "    <g fill=\"#4e9bcd\">\n" +
                "        <path d=\"M17.975 16.758h-.815c0-6.557-5.411-11.893-12.062-11.893v-.813c7.102 0 12.877 5.7 12.877 12.706z\"/>\n" +
                "        <path d=\"M18.538 12.386c-.978-4.116-4.311-7.55-8.49-8.748l.186-.65c4.411 1.266 7.93 4.895 8.964 9.243zm.626-3.856a12.48 12.48 0 0 0-4.832-5.399l.282-.464a13.031 13.031 0 0 1 5.044 5.63z\"/>\n" +
                "    </g>\n" +
                "</svg>";

            String betterBadge = BadgeGenerator.sonarBadgeFix(sampleBadBadge);
            assertEquals(betterBadge,correctedBadge,"il colore dovrebbe cambiare perché sotto il 10%");
        System.out.println("ok");


    }
}