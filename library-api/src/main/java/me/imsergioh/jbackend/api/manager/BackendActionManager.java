package me.imsergioh.jbackend.api.manager;

import me.imsergioh.jbackend.api.ConnectionHandler;

import java.util.HashSet;
import java.util.Set;
import java.util.function.Consumer;

public class BackendActionManager {

    private static final Set<Consumer<ConnectionHandler>> actionsAtConnecting = new HashSet<>();
    private static final Set<Consumer<ConnectionHandler>> actionsAtDisconnection = new HashSet<>();
    public static void registerConnectAction(Consumer<ConnectionHandler> consumer) {
        actionsAtConnecting.add(consumer);
    }

    public static void registerDisconnectAction(Consumer<ConnectionHandler> consumer) {
        actionsAtDisconnection.add(consumer);
    }

    public static void registerDisconnectConnection(ConnectionHandler handler) {
        executeFor(actionsAtDisconnection, handler);
    }

    public static void registerEstablishedConnection(ConnectionHandler handler) {
        executeFor(actionsAtConnecting, handler);
    }

    private static void executeFor(Set<Consumer<ConnectionHandler>> set, ConnectionHandler handler) {
        set.forEach(a -> a.accept(handler));
    }

}
