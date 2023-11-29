package me.imsergioh.jbackend;

import me.imsergioh.jbackend.api.ConnectionHandler;

import java.io.IOException;
import java.net.Socket;

public class ClientConnection extends Thread {

    private final Socket connection;
    private ConnectionHandler connectionHandler;

    public ClientConnection(Socket connection) {
        this.connection = connection;
    }

    public void send(Object object) {
        try {
            connectionHandler.getWriter().sendObject(object);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void run() {
        try {
            connectionHandler = new ConnectionHandler(connection);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public ConnectionHandler getConnectionHandler() {
        return connectionHandler;
    }
}
