package me.imsergioh.jbackend;

import me.imsergioh.jbackend.api.ConnectionHandler;
import me.imsergioh.jbackend.api.ObjectStreamReader;
import me.imsergioh.jbackend.api.ObjectStreamWriter;
import me.imsergioh.jbackend.api.manager.BackendActionManager;

import java.io.IOException;
import java.net.Socket;
import java.util.*;
import java.util.concurrent.TimeUnit;
import java.util.function.Consumer;

public class BackendConnection extends Thread {

    private static boolean logsEnabled = false;

    private final String host;
    private final int port;
    private boolean connect;

    private ConnectionHandler connectionHandler;

    private final Set<Consumer<BackendConnection>> connectActions = new HashSet<>();

    private final Timer reconnectTimer = new Timer();
    private final Queue<Object> failedQueue = new LinkedList<>();

    public BackendConnection(String host, int port) {
        this.host = host;
        this.port = port;
        startConnectionCheck();
    }

    public void send(Object object) {
        try {
            connectionHandler.getWriter().sendObject(object);
        } catch (Exception e) {
            System.out.println("New object added to the failed queue! (" + object + ")");
            failedQueue.add(object);
            e.printStackTrace();
        }
    }

    public boolean isConnected() {
        if (connectionHandler == null) return false;
        return connectionHandler.isConnected();
    }

    @Override
    public void run() {
        try {
            connect = true;
            log("Connecting...");
            Socket connection = new Socket(host, port);
            connectionHandler = new ConnectionHandler(connection);
            log("Connected!");
            sendFailed();
        } catch (IOException e) {
            log("Connection to " + host + ":" + port + " failed!");
        }
    }

    private void sendFailed() {
        if (failedQueue.isEmpty()) return;
        for (Object o : new LinkedList<>(failedQueue)) {
            try {
                connectionHandler.getWriter().sendObject(o);
                failedQueue.remove(o);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    private void startConnectionCheck() {
        reconnectTimer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                if (!connect) return;
                if (connectionHandler != null && connectionHandler.isConnected()) return;
                BackendConnection.this.run();
            }
        }, 750, 1000 * 3);
    }

    public void disconnect() throws IOException {
        connectionHandler.close();
    }

    public static void log(String message) {
        if (!logsEnabled) return;
        System.out.println("[BackendConnection - Client] " + message);
    }

    public static void setLogging(boolean active) {
        logsEnabled = active;
    }

}
