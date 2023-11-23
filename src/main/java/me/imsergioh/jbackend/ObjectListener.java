package me.imsergioh.jbackend;

public interface ObjectListener<T extends Object> {

    void onReceive(T object);

}
