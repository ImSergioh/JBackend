package me.imsergioh.jbackend.api.manager;

import me.imsergioh.jbackend.api.ConnectionHandler;
import me.imsergioh.jbackend.api.process.ObjectProcessor;

import java.util.*;

public class DataProcessorManager {

    private static final Set<ObjectProcessor> processors = new HashSet<>();

    public static void processObject(ConnectionHandler handler, Object object) {
        Class<?> type = object.getClass();
        processors.forEach(processor -> {
            if (!processor.getType().equals(type)) return;
            processor.process(handler, object);
        });
    }

    public static void unregister(ObjectProcessor processor) {
        processors.remove(processor);
    }

    public static void register(ObjectProcessor... all) {
        processors.addAll(Arrays.asList(all));
    }
}
