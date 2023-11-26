package me.imsergioh.listener;

import me.imsergioh.jbackend.api.process.ObjectProcessor;
import me.imsergioh.jbackend.api.process.ProcessorType;

@ProcessorType(classOf = CacheSetRequest.class)
public class CacheSetListener extends ObjectProcessor {

    @Override
    public void process(Object object) {
        CacheSetRequest request = (CacheSetRequest) object;
        System.out.println("SET CACHE REQUEST! " + request.getKey() + " as " + request.getValue());
    }
}
