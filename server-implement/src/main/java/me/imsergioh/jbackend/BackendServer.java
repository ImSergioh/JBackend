package me.imsergioh.jbackend;

import me.imsergioh.jbackend.api.manager.DataProcessorManager;
import me.imsergioh.jbackend.api.test.StringProcessor;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class BackendServer extends Thread {

    private final ServerSocket serverSocket;
    private boolean active = true;

    public BackendServer(int port) throws IOException {
        serverSocket = new ServerSocket(port);
        DataProcessorManager.register(new StringProcessor());
    }

    @Override
    public void run() {
        while (active) {
            Socket connection = null;
            try {
                connection = serverSocket.accept();
                ClientConnectionsHandler.createClientConnection(connection);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public void disable() {
        active = false;
    }

}
