package me.imsergioh;

import me.imsergioh.jbackend.BackendConnection;
import me.imsergioh.jbackend.api.manager.BackendActionManager;
import me.imsergioh.jbackend.api.manager.DataProcessorManager;
import me.imsergioh.test.Listener;

import java.util.UUID;

public class TestMainClient {

    private static UUID uuid;

    public static void main(String[] args) throws Exception {
        uuid = UUID.randomUUID();

        DataProcessorManager.register(new Listener());
        BackendConnection connection = new BackendConnection("127.0.0.1", 7777);

        connection.start();
        Thread.sleep(250);
        connection.send("generateUUID");
    }
}