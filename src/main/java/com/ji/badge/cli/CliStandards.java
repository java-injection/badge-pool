package com.ji.badge.cli;

import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.stream.Collectors;

public class CliStandards {

    private static int blockSize = 0;
    private static  final int titleDirty = 4+4+4+8;

    public static final String JAVA_INJECTION ="""
                        \s            %s _                        _        _           _   _                        \s        
                        \s            (_) __ ___   ____ _      (_)_ __  (_) ___  ___| |_(_) ___  _ __             \s        
                        \s            | |/ _` \\ \\ / / _` |%s_____%s| | '_ \\ | |/ _ \\/ __| __| |/ _ \\| '_ \\            \s        
                        \s            | | (_| |\\ V / (_| |%s_____%s| | | | || |  __/ (__| |_| | (_) | | | |           \s
                        \s           _/ |\\__,_| \\_/ \\__,_|     |_|_| |_|/ |\\___|\\___|\\__|_|\\___/|_| |_|           \s         
                        \s          |__/                              |__/                                        \s
                        \s                                                                                         %s""".formatted(
                        CliColors.ANSI_GREEN.code(),
                        CliColors.BLUE_BRIGHT.code(),
                        CliColors.ANSI_GREEN.code(),
                        CliColors.BLUE_BRIGHT.code(),
                        CliColors.ANSI_GREEN.code(),
                        CliColors.ANSI_RESET.code()
            );

    public static final String BADGE_POOL = """
                                                                                                     \s
            \s                    %s888888b.%s                 888                                        \s
            \s                    %s888  "88b%s                888                                        \s
            \s                    %s888  .88P%s                888                                        \s
            \s                    %s8888888K.%s   8888b.   .d88888  .d88b.   .d88b.                       \s
            \s                    %s888  "Y88b%s     "88b d88" 888 d88P"88b d8P  Y8b                      \s
            \s                    %s888    888%s .d888888 888  888 888  888 88888888                      \s
            \s                    %s888   d88P%s 888  888 Y88b 888 Y88b 888 Y8b.                          \s
            \s                    %s8888888P"%s  "Y888888  "Y88888  "Y88888  "Y8888                       \s
            \s                                                      888                               \s
            \s                                                 Y8b d88P                               \s
            \s                                                  "Y88P"                                \s
            \s                                    8888888b.                    888                    \s
            \s                                    888   Y88b                   888                    \s
            \s                                    888    888                   888                    \s
            \s                                    888   d88P  .d88b.   .d88b.  888                    \s
            \s                                    8888888P"  d88""88b d88""88b 888                    \s
            \s                                    888        888  888 888  888 888                    \s
            \s                                    888        Y88..88P Y88..88P 888                    \s
            \s                                    888         "Y88P"   "Y88P"  888                    \s
                                                                                                     \s                                                                                                            
            """.formatted(
            CliColors.ANSI_YELLOW.code(),
            CliColors.RED_BLOODY.code(),
            CliColors.ANSI_YELLOW.code(),
            CliColors.RED_BLOODY.code(),
            CliColors.ANSI_YELLOW.code(),
            CliColors.RED_BLOODY.code(),
            CliColors.ANSI_YELLOW.code(),
            CliColors.RED_BLOODY.code(),
            CliColors.ANSI_YELLOW.code(),
            CliColors.RED_BLOODY.code(),
            CliColors.ANSI_YELLOW.code(),
            CliColors.RED_BLOODY.code(),
            CliColors.ANSI_YELLOW.code(),
            CliColors.RED_BLOODY.code(),
            CliColors.ANSI_YELLOW.code(),
            CliColors.RED_BLOODY.code());


    public static final String JAVA_INJECTION_BADGE_WEB = "<svg\n" +
            "\txmlns=\"http://www.w3.org/2000/svg\" height=\"20\" width=\"151\">\n" +
            "\t<!-- SONARQUBE MEASURE -->\n" +
            "\t<linearGradient id=\"b\" x2=\"0\" y2=\"100%\">\n" +
            "\t\t<stop offset=\"0\" stop-color=\"#bbb\" stop-opacity=\".1\"/>\n" +
            "\t\t<stop offset=\"1\" stop-opacity=\".1\"/>\n" +
            "\t</linearGradient>\n" +
            "\t<clipPath id=\"a\">\n" +
            "\t\t<rect width=\"141\" height=\"20\" rx=\"3\" fill=\"#fff\"/>\n" +
            "\t</clipPath>\n" +
            "\t<g clip-path=\"url(#a)\">\n" +
            "\t\t<rect fill=\"#333\" height=\"20\" width=\"122\"/>\n" +
            "\t\t<rect fill=\"#2136A1\" height=\"20\" width=\"69\" x=\"102\"/>\n" +
            "\t\t<rect fill=\"url(#b)\" height=\"20\" width=\"171\"/>\n" +
            "\t</g>\n" +
            "\t<g fill=\"#fff\" font-family=\"DejaVu Sans,Verdana,Geneva,sans-serif\" font-size=\"11\" text-anchor=\"left\">\n" +
            "\t\t<text x=\"26\" y=\"15\" textLength=\"70\" fill=\"#010101\" fill-opacity=\".3\">Java-Injection</text>\n" +
            "\t\t<text x=\"26\" y=\"14\" textLength=\"70\">Java-Injection</text>\n" +
            "\t\t<text x=\"108\" y=\"15\" textLength=\"25\" fill=\"#010101\" fill-opacity=\".3\">Web</text>\n" +
            "\t\t<text x=\"108\" y=\"14\" textLength=\"25\">Web</text>\n" +
            "\t</g>\n" +
            "<g transform=\"translate(5.000000,18.000000) scale(0.100000,-0.100000)\"\n" +
            "fill=\"#21753D\" stroke=\"none\">\n" +
            "<path d=\"M143 148 c-22 -28 -23 -48 -3 -48 16 0 20 7 20 30 0 33 -3 36 -17 18z\"/>\n" +
            "<path d=\"M0 75 c0 -20 28 -56 51 -66 37 -15 82 2 98 37 14 32 14 34 -7 34 -9\n" +
            "0 -23 -9 -30 -20 -7 -11 -21 -20 -32 -20 -11 0 -25 9 -32 20 -11 17 -48 29\n" +
            "-48 15z\"/>\n" +
            "</g>\n" +
            "</svg>";


    public static final String JAVA_INJECTION_SVG_LOGO = "<?xml version=\\\"1.0\\\" standalone=\\\"no\\\"?>\\n\" +\n" +
            "                            \"<!DOCTYPE svg PUBLIC \\\"-//W3C//DTD SVG 20010904//EN\\\"\\n\" +\n" +
            "                            \" \\\"http://www.w3.org/TR/2001/REC-SVG-20010904/DTD/svg10.dtd\\\">\\n\" +\n" +
            "                            \"<svg version=\\\"1.0\\\" xmlns=\\\"http://www.w3.org/2000/svg\\\"\\n\" +\n" +
            "                            \" width=\\\"498.000000pt\\\" height=\\\"500.000000pt\\\" viewBox=\\\"0 0 498.000000 500.000000\\\"\\n\" +\n" +
            "                            \" preserveAspectRatio=\\\"xMidYMid meet\\\">\\n\" +\n" +
            "                            \"\\n\" +\n" +
            "                            \"<g transform=\\\"translate(0.000000,500.000000) scale(0.100000,-0.100000)\\\"\\n\" +\n" +
            "                            \"fill=\\\"#000000\\\" stroke=\\\"none\\\">\\n\" +\n" +
            "                            \"<path d=\\\"M4921 4984 c-117 -31 -275 -141 -435 -300 -345 -345 -602 -825 -700\\n\" +\n" +
            "                            \"-1307 -23 -114 -39 -236 -50 -384 l-4 -63 624 0 624 0 0 1035 c0 569 -1 1035\\n\" +\n" +
            "                            \"-2 1034 -2 0 -27 -7 -57 -15z\\\"/>\\n\" +\n" +
            "                            \"<path d=\\\"M0 2483 c0 -5 5 -71 10 -148 38 -530 225 -1010 554 -1415 323 -398\\n\" +\n" +
            "                            \"791 -702 1280 -831 1036 -273 2116 133 2711 1021 248 370 382 768 415 1225 5\\n\" +\n" +
            "                            \"77 10 143 10 148 0 4 -278 7 -618 7 l-618 0 -12 -112 c-59 -560 -452 -997\\n\" +\n" +
            "                            \"-1002 -1114 -116 -24 -352 -25 -473 -1 -248 50 -467 167 -647 347 -217 217\\n\" +\n" +
            "                            \"-339 483 -366 798 l-7 82 -618 0 c-341 0 -619 -3 -619 -7z\\\"/>\\n\" +\n" +
            "                            \"</g>\\n\" +\n" +
            "                            \"</svg>";

    /**
     * return a formatted fancy title string with the title in the middle
     * @param title
     * @return
     */
    public static String title(String title){

        return CliColors.ANSI_YELLOW.code()+"╔══════════════════════════════════════ "+CliColors.ANSI_WHITE.code()+title+CliColors.ANSI_YELLOW.code()+" ══════════════════════════════════════╗ "+CliColors.ANSI_RESET.code();
    }

    /**
     * Return fancy closure with same size of the title with the same argument as title
     * @param title
     * The title to display in the middle of the starting text block
     * @return
     */
    public static String closeTitle(String title){
        StringBuilder filling = new StringBuilder("==");
        for (int i = 0; i < title.length(); i++) {
            filling.append("=");
        }
        return CliColors.ANSI_YELLOW.code()+"╚══════════════════════════════════════"+filling+"══════════════════════════════════════╝ "+CliColors.ANSI_RESET.code();
    }

    public static ChainedString initBlock(String title){
        return new ChainedString(title);
    }

    public static String line(){
        return CliColors.BLUE_BRIGHT.code() + "=".repeat(blockSize) + CliColors.ANSI_RESET.code();
    }

    public static class ChainedString{

        private String text;
        private boolean started = false;
        private int titleLength;

        public ChainedString(String title){
            this.titleLength = Math.max(title.length(), blockSize);
            this.text = CliStandards.title(title);
            this.started = true;
            blockSize = text.length()-titleDirty;
        }

        public ChainedString addLine(){
            this.text+="\n"+CliStandards.line()+"\n";
            return this;
        }

        public ChainedString br(){
            this.text+="\n";
            return this;
        }

        public ChainedString withBody(String body,CliColors bodyColor ){
            if(!this.started){
                throw new RuntimeException("you can't add more text after closing the chain");
            }
            String result = body.lines().map(
                    (line) -> CliColors.ANSI_YELLOW.code()+"║"+bodyColor.code() + line +CliColors.ANSI_YELLOW.code()+"║"
                    ).collect(Collectors.joining("\n"));
            this.text+="\n"+result+"\n";
            return this;
        }

        public ChainedString newBlock(String body,CliColors bodyColor ){
            if(this.started){
                throw new RuntimeException("you can't add new blocks when the previous is not closed");
            }
            this.started = true;

            String result = body.lines().map(
                    (line) -> CliColors.ANSI_YELLOW.code()+"║"+bodyColor.code() + line +CliColors.ANSI_YELLOW.code()+"║"
            ).collect(Collectors.joining("\n"));
            String t = CliColors.ANSI_YELLOW.code()+"╔"+"═".repeat(blockSize-2)+"╗"+CliColors.ANSI_RESET.code();
            this.text+=t+"\n"+result+"\n";
            return this;
        }

        public ChainedString close(){
            if(!this.started){
                throw new RuntimeException("you can't close an already closed instance of ChainedString");
            }
            this.started = false;
            StringBuilder filling = new StringBuilder("══");
            for (int i = 0; i < titleLength; i++) {
                filling.append("═");
            }
            this.text+=CliColors.ANSI_YELLOW.code()+"╚══════════════════════════════════════"+filling+"══════════════════════════════════════╝ \n"+CliColors.ANSI_RESET.code();
            return this;
        }

        public String build(){
            ByteBuffer buffer = StandardCharsets.UTF_8.encode(text);

            return StandardCharsets.UTF_8.decode(buffer).toString();
        }

        public void print(){
            System.out.println(this.text);
        }


    }

}
