package me.imsergioh.jbackend.api.process;


import me.imsergioh.jbackend.api.ConnectionHandler;

public interface IObjectProcessor {

    void process(ConnectionHandler receivedFrom, Object object);

    Class<?> getType();
}
