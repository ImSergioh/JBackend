package me.imsergioh;

import me.imsergioh.jbackend.BackendServer;

import java.io.IOException;
import java.net.ServerSocket;

public class TestMainServer {

    private static ServerSocket serverSocket;

    public static void main(String[] args) throws IOException {
        BackendServer backendServer = new BackendServer(7777);

        backendServer.start();

    }
}