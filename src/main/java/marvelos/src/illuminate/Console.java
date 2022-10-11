package marvelos.src.illuminate;
import java.io.*;

public class Console {
    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_BLACK = "\u001B[30m";
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_GREEN = "\u001B[32m";
    public static final String ANSI_YELLOW = "\u001B[33m";
    public static final String ANSI_BLUE = "\u001B[34m";
    public static final String ANSI_PURPLE = "\u001B[35m";
    public static final String ANSI_CYAN = "\u001B[36m";
    public static final String ANSI_WHITE = "\u001B[37m";
    public static final String SPACE1 = " ";
    public static final String SPACE2 = "  ";

    public void console(String[] args) {
        if(args.length != 0) {
            switch (args[0]) {
                case "make:model":
                    System.out.println("Membuat model bernama " + args[1]);
                    try {
                        FileWriter file = new FileWriter("src/main/java/app/models/"+ args[1] + ".java");
                        String program = "package app.models;\n" +
                                "\n" +
                                "import marvelos.src.illuminate.database.Model;\n" +
                                "\n" +
                                "public class "+ args[1] + " extends Model {\n" +
                                "    protected String table;\n" +
                                "\n" +
                                "    @Override\n" +
                                "    public void setTable(String table) {\n" +
                                "        table = this.table;\n" +
                                "        super.setTable(table);\n" +
                                "    }\n" +
                                "\n" +
                                "    @Override\n" +
                                "    public String getTable() {\n" +
                                "        return table;\n" +
                                "    }\n" +
                                "}\n" +
                                "\n";

                        file.write(program);
                        file.close();
                    } catch(Exception e) {
                        e.getStackTrace();
                    }

                    break;
                default:
                    System.out.println("Command not found");
            }
        } else {
            System.out.println("Marvelos Framework" + ANSI_GREEN + " alpha");
            System.out.println(ANSI_YELLOW + "Usage:" );
            System.out.println( ANSI_RESET + SPACE2 + "command [options] [arguments]" + "\n");
            options();
            availableCommands();
            System.out.println(ANSI_RESET);
        }
    }
    public static void options() {
        System.out.println(ANSI_YELLOW + "Options:");
        System.out.println(SPACE2 + ANSI_GREEN + "-V, --version");
        System.out.println();
    }

    public static void availableCommands() {
        System.out.println(ANSI_YELLOW + "Available commands:" );
        System.out.println(SPACE2 + ANSI_GREEN + "docs");
        make();
    }
    public static void make() {
        System.out.println(SPACE1 + ANSI_YELLOW + "make");
        System.out.println(SPACE2 + ANSI_GREEN + "make:model");
    }
}
