package cs413.ace_group_01.brewduino_server;

import cs413.ace_group_01.brewduino_server.view.Output;

/**
 * The Driver class is responsible for being the entry point of the server, it
 * includes the main method and initializes the system.
 *
 * @author Aidan O'Grady
 * @since 0.0
 */
public class Driver {
    public static void main(String[] args) {
        String name = "BREWDUINO SERVER";
        String version = "0.0";

        String[] authors = {"Andi Anderson", "Fraser George", "Adam McGhie",
        "Aidan O'Grady", "Kristine Semjonova"};

        System.out.println(name + " " + "v" + version);
        Output.minorLineBreak();
        System.out.println("AUTHORS:");
        for(String author : authors) {
            System.out.println("\t" + author);
        }
        Output.lineBreak();
    }
}
