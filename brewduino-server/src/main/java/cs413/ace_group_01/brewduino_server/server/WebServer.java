package cs413.ace_group_01.brewduino_server.server;


/**
 * The WebServer class is the main class of the system. It contains the main
 * method and is responsible for listening and delegating any requests it is
 * sent.
 *
 * @author Aidan O'Grady
 * @since 0.1
 *
 */public class WebServer implements Runnable {

    /**
     * A toggle for whether or not the server is actively listening.
     */
    private boolean run = true;

    public void run() {
        open();
        System.out.println("Begin listening");
        listen();
    }

    private void open() {
        System.out.println("Opening server");
    }

    /**
     * Starts a loop that allows for the socket to accept requests constantly.
     */
    private void listen() {
        while(run) {

        }
        close();
    }

    /**
     * Gracefully closes down the server when requested.
     */
    private void close() {
        System.out.println("End listening");
    }

    /**
     * When invoked, sets the running flag to alert that the server is to be
     * closed.
     */
    public void quit() {
        run = false;
    }
}
