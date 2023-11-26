package me.imsergioh.jbackend.api;

import java.io.*;

public class ObjectStreamWriter {

    private final ConnectionHandler handler;
    private final ObjectOutputStream stream;

    public ObjectStreamWriter(ConnectionHandler handler, OutputStream stream) throws IOException {
        this.handler = handler;
        this.stream = new ObjectOutputStream(stream);
    }

    public void sendObject(Object object) throws IOException {
        if (object == null) return;
        stream.writeObject(object);
        stream.flush();
    }
}
