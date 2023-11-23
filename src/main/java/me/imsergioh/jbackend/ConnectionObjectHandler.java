package me.imsergioh.jbackend;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

public class ConnectionObjectHandler<T extends Object> {

    private final Set<ObjectListener<T>> registry = new HashSet<>();

    public void register(ObjectListener<T>... listeners) {
        Collections.addAll(registry, listeners);
    }

    public void processObject(T object) {
        for (ObjectListener<T> listener : registry) {
            if (!object.getClass().equals(listener.getClass())) continue;
            listener.onReceive(object);
        }
    }

}
