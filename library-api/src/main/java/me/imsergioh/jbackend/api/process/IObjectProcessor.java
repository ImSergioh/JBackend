package me.imsergioh.jbackend.api.process;


public interface IObjectProcessor {

    void process(Object object);

    Class<?> getType();
}
