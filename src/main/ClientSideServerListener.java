package main;

/**
 * Class that waits for data from the server and prints it to the client. Implements the runnable class.
 */
public class ClientSideServerListener implements Runnable {

    private ClackClient client;

    /**
     * Constructor that instantiates the client based on user input
     * @param client the client
     */
    public ClientSideServerListener(ClackClient client) {
        this.client = client;
    }

    /**
     * Implements the run method from the Runnable interface. Receives data from the server and prints it to the client.
     */
    @Override
    public void run() {
        while (!client.getCloseConnection()) {
            client.receiveData();
            client.printData();
        }
    }
}
