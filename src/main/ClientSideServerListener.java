package main;

public class ClientSideServerListener implements Runnable {

    private ClackClient client;

    public ClientSideServerListener(ClackClient client) {
        this.client = client;
    }

    @Override
    public void run() {
        while (!client.getCloseConnection()) {
            client.receiveData();
            client.printData();
        }
    }
}
