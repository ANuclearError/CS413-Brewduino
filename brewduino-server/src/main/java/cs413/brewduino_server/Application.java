package cs413.brewduino_server;

import cs413.brewduino_server.view.Output;
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
        Output.minorLineBreak();
        System.out.println("AUTHORS:");
        for(String author : authors) {
            System.out.println("\t" + author);
        }
        Output.lineBreak();

        SpringApplication.run(Application.class, args);
    }
}
