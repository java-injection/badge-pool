package com.ji.badge.cli;

import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.stream.Collectors;

public class CliStandards {

    public static final String JAVA_INJECTION ="""
                          %s _                        _        _           _   _            \s
                          (_) __ ___   ____ _      (_)_ __  (_) ___  ___| |_(_) ___  _ __ \s
                          | |/ _` \\ \\ / / _` |%s_____%s| | '_ \\ | |/ _ \\/ __| __| |/ _ \\| '_ \\\s
                          | | (_| |\\ V / (_| |%s_____%s| | | | || |  __/ (__| |_| | (_) | | | |
                         _/ |\\__,_| \\_/ \\__,_|     |_|_| |_|/ |\\___|\\___|\\__|_|\\___/|_| |_|
                        |__/                              |__/ \s
                %s""".formatted(
                        CliColors.ANSI_GREEN,
                        CliColors.BLUE_BRIGHT,
                        CliColors.ANSI_GREEN,
                        CliColors.BLUE_BRIGHT,
                        CliColors.ANSI_GREEN,
                        CliColors.ANSI_RESET
            );

    /**
     * return a formatted fancy title string with the title in the middle
     * @param title
     * @return
     */
    public static String title(String title){
        return CliColors.ANSI_YELLOW+"╔══════════════════════════════════════ "+CliColors.ANSI_WHITE+title+CliColors.ANSI_YELLOW+" ══════════════════════════════════════╗ "+CliColors.ANSI_RESET;
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
        return CliColors.ANSI_YELLOW+"╚══════════════════════════════════════"+filling+"══════════════════════════════════════╝ "+CliColors.ANSI_RESET;
    }

    public static ChainedString initBlock(String title){
        return new ChainedString(title);
    }

    public static class ChainedString{

        private String text;
        private boolean started = false;
        private int titleLength;

        public ChainedString(String title){
            this.titleLength = title.length();
            this.text = CliStandards.title(title);
            this.started = true;
        }

        public ChainedString withBody(String body){
            if(!this.started){
                throw new RuntimeException("you can't add more text after closin the chain");
            }
            String result = Arrays.stream(body.split(System.lineSeparator()))
                    //.filter(/* filter for lines you are interested in*/)
                    .map(s -> s = "║"+s+"║")
                    .collect(Collectors.joining());
            this.text+="\n"+result+"\n";
            return this;
        }

        public ChainedString close(){
            if(!this.started){
                throw new RuntimeException("you can't close an already closed instance of ChainedString");
            }
            StringBuilder filling = new StringBuilder("══");
            for (int i = 0; i < titleLength; i++) {
                filling.append("═");
            }
            this.text+=CliColors.ANSI_YELLOW+"╚══════════════════════════════════════"+filling+"══════════════════════════════════════╝ \n"+CliColors.ANSI_RESET;
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
