package me.imsergioh;

import me.imsergioh.jbackend.BackendConnection;
import me.imsergioh.jbackend.api.manager.DataProcessorManager;
import me.imsergioh.listener.CacheSetListener;

import java.io.IOException;
import java.util.HashMap;

public class MainClient {
    public static void main(String[] args) throws IOException, InterruptedException {
        BackendConnection connection = new BackendConnection("127.0.0.1", 7777);
        DataProcessorManager.register(new CacheSetListener());
        connection.run();
        HashMap<String, Integer> testMap = new HashMap<>();
        testMap.put("user1", 10);
        testMap.put("user2", 70);
        testMap.put("user3", 7);
        connection.send("String aidjsofiajsojds");
        int count = 1;
    }
}