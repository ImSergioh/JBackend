package me.imsergioh.jbackend;

import java.io.*;
import java.net.Socket;

public class SocketConnectionHandler {

    private final Socket connection;
    private final ObjectInputStream in;
    private final ObjectOutputStream out;
    private boolean active = true;
    private Thread inputThread;

    public SocketConnectionHandler(Socket socket) throws IOException {
        this.connection = socket;
        this.out = new ObjectOutputStream(socket.getOutputStream());
        this.in = new ObjectInputStream(socket.getInputStream());
    }

    public void startInputThread() {
        if (inputThread != null) return;
        inputThread = new Thread(() -> {
            while (true) {
                Object received;
                try {
                    if (!(active && (received = in.readObject()) != null)) break;

                } catch (IOException e) {
                    throw new RuntimeException(e);
                } catch (ClassNotFoundException e) {
                    throw new RuntimeException(e);
                }

            }
        });
    }

    public void sendObject(Object value) {
        try {
            out.writeObject(value);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void sendUTF(String str) {
        sendObject(str);
    }
}
