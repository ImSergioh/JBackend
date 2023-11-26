package me.imsergioh.jbackend.api.manager;

import me.imsergioh.jbackend.api.process.ObjectProcessor;

import java.util.*;

public class DataProcessorManager {

    private static final Set<ObjectProcessor> processors = new HashSet<>();

    public static void processObject(Object object) {
        Class<?> type = object.getClass();
        processors.forEach(processor -> {
            if (!processor.getType().equals(type)) return;
            processor.process(object);
        });
    }

    public static void unregister(ObjectProcessor processor) {
        processors.remove(processor);
    }

    public static void register(ObjectProcessor processor) {
        processors.add(processor);
    }
}
