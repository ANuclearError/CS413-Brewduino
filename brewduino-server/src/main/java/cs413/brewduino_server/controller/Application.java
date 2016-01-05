package cs413.brewduino_server.controller;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author Aidan O'Grady
 * @version 1.0
 */
@SpringBootApplication
public class Application {

    public static void main(String[] args) {
        String name = "BREWDUINO SERVER";
        String version = "0.0";

        String[] authors = {"Andi Anderson", "Fraser George", "Adam McGhie",
                "Aidan O'Grady", "Kristine Semjonova"};
        System.out.println(name + " " + "v" + version);
        System.out.println("AUTHORS:");
        for(String author : authors) {
            System.out.println("\t" + author);
        }
        SpringApplication.run(Application.class, args);
    }
}
