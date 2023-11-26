package me.imsergioh.jbackend.api.test;

import me.imsergioh.jbackend.api.process.ObjectProcessor;
import me.imsergioh.jbackend.api.process.ProcessorType;

@ProcessorType(classOf = String.class)
public class StringProcessor extends ObjectProcessor {

    @Override
    public void process(Object object) {
        String str = (String) object;
        System.out.println("Trabajando con string de " + str);
    }
}
