package me.imsergioh.jbackend;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class SocketConnectionHandler {

    private final Socket connection;
    private final DataInputStream in;
    private final DataOutputStream out;

    public SocketConnectionHandler(Socket socket) throws IOException {
        this.connection = socket;
        this.in = new DataInputStream(socket.getInputStream());
        this.out = new DataOutputStream(socket.getOutputStream());
    }

}
