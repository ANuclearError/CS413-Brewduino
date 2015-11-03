package cs413.ace_group_01.brewduino_server.server;

import cs413.ace_group_01.brewduino_server.view.Input;

/**
 * The server class is the class that brings everything together, initialising
 * it all so that it works. It ensures that when the user inputs "quit", the
 * system will gracefully close.
 *
 * @author Aidan O'Grady
 * @since 0.1
 */
public class Server {

    /**
     * The WebServer that will take in requests.
     */
    private WebServer webServer;

    /**
     * Constructor
     */
    public Server() {
        webServer = new WebServer();
    }

    /**
     * Starts the webServer and waits for the user to wish to quit.
     */
    public void run() {

        new Thread(webServer).start();
        while(true) {
            if(getQuit()) {
                webServer.quit();
                break;
            }
        }
        System.out.println("Closing system");
        System.exit(0);
    }

    /**
     * Returns whether the user
     * @return
     */
    private boolean getQuit() {
        String input = Input.getString().toLowerCase();
        return input.equals("quit");
    }
}
