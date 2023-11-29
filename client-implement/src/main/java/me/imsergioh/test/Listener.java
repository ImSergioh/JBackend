package me.imsergioh.test;

import me.imsergioh.jbackend.api.ConnectionHandler;
import me.imsergioh.jbackend.api.process.ObjectProcessor;
import me.imsergioh.jbackend.api.process.ProcessorType;

@ProcessorType(classOf = String.class)
public class Listener extends ObjectProcessor {

    @Override
    public void process(ConnectionHandler receivedFrom, Object object) {
        System.out.println("Received from server! " + object);
    }
}
