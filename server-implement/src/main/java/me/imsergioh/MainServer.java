package me.imsergioh;

import me.imsergioh.jbackend.BackendServer;
import me.imsergioh.jbackend.ClientConnectionsHandler;
import me.imsergioh.jbackend.api.manager.DataProcessorManager;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class MainServer {

    private static ServerSocket serverSocket;

    public static void main(String[] args) throws IOException {
        BackendServer backendServer = new BackendServer(7777);
        backendServer.start();
    }
}