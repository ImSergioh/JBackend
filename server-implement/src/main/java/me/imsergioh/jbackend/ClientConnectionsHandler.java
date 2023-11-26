package me.imsergioh.jbackend;

import java.net.Socket;
import java.util.HashMap;

public class ClientConnectionsHandler {

    private static final HashMap<Socket, ClientConnection> connections = new HashMap<>();


    public static void createClientConnection(Socket socket) {
        try {
            connections.put(socket, new ClientConnection(socket));
            get(socket).start();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static ClientConnection get(Socket socket) {
        return connections.get(socket);
    }

    public static void close(Socket socket) {
        get(socket).disconnect();
    }

}
