package cs413.ace_group_01.brewduino_server.server;


import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

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
     * Port number the server is listening on.
     */
    private static final int PORT = 6789;

    /**
     * A toggle for whether or not the server is actively listening.
     */
    private boolean run = true;

    /**
     * THe server socket listening for incoming traffic.
     */
    private ServerSocket serverSocket;

    /**
     * Thread pool handling the threads for each request.
     */
    private ExecutorService threadPool;

    public void run() {
        open();
        System.out.println("Begin listening");
        listen();
    }

    private void open() {
        try {
            System.out.println("Opening server");
            serverSocket = new ServerSocket(PORT);
            threadPool = Executors.newCachedThreadPool();
        } catch(IOException e) {
            System.err.println("Error opening server.");
            e.printStackTrace();
        }
    }

    /**
     * Starts a loop that allows for the socket to accept requests constantly.
     */
    private void listen() {
        while(run) {
            try {
                Socket client = serverSocket.accept();
                System.out.println("Accepted: " + client.toString());
            } catch (IOException e) {
                System.err.println("Error accepting request.");
                e.printStackTrace();
            }
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
