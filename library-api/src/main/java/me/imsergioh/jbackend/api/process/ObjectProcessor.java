package me.imsergioh.jbackend.api.process;

public abstract class ObjectProcessor implements IObjectProcessor {

    private final Class<?> type;

    public ObjectProcessor() {
        type = this.getClass().getDeclaredAnnotation(ProcessorType.class).classOf();
    }

    @Override
    public Class<?> getType() {
        return type;
    }
}
