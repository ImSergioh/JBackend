package me.imsergioh.jbackend.api;

import me.imsergioh.jbackend.api.manager.BackendActionManager;

import java.io.IOException;
import java.net.Socket;

public class ConnectionHandler {

    private final Socket connection;
    private final ObjectStreamReader reader;
    private final ObjectStreamWriter writer;

    public ConnectionHandler(Socket connection) throws IOException {
        this.connection = connection;
        this.writer = new ObjectStreamWriter(this, connection.getOutputStream());
        this.reader = new ObjectStreamReader(this, connection.getInputStream());
        reader.start();
        BackendActionManager.registerEstablishedConnection(this);
    }

    public void send(Object object) {
        try {
            writer.sendObject(object);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public ObjectStreamReader getReader() {
        return reader;
    }

    public ObjectStreamWriter getWriter() {
        return writer;
    }

    public boolean isConnected() {
        return reader.isActive();
    }

    public void close() {
        try {
            reader.disable();
            connection.close();
            BackendActionManager.registerDisconnectConnection(this);
        } catch (IOException ignore) {}
    }

    public Socket getConnection() {
        return connection;
    }
}
