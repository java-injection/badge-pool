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
