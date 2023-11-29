package me.imsergioh;

import me.imsergioh.jbackend.BackendConnection;

public class MainClient {
    public static void main(String[] args) {
        BackendConnection connection = new BackendConnection("127.0.0.1", 7777);
    }
}