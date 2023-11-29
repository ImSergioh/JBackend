package me.imsergioh.jbackend.api;

import me.imsergioh.jbackend.api.manager.DataProcessorManager;

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;

public class ObjectStreamReader extends Thread implements Runnable {

    private final ConnectionHandler handler;
    private final ObjectInputStream stream;
    private boolean active = true;

    public ObjectStreamReader(ConnectionHandler handler, InputStream stream) throws IOException {
        this.handler = handler;
        this.stream = new ObjectInputStream(stream);
    }

    @Override
    public void run() {
        while (active) {
            try {
                Object received = readObject();
                DataProcessorManager.processObject(handler, received);
            } catch (Exception e) {
                System.out.println("Disconnected from " + handler.getConnection().getInetAddress().getHostAddress());
                handler.close();
                disable();
                break;
            }
        }
    }

    public Object readObject() {
        try {
            return stream.readObject();
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public boolean isActive() {
        return active;
    }

    public void disable() {
        active = false;
    }

}
