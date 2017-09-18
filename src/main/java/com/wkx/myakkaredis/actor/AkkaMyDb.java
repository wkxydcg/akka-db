package com.wkx.myakkaredis.actor;

import akka.actor.AbstractActor;
import com.wkx.myakkaredis.vo.SetRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Map;

public class AkkaMyDb extends AbstractActor{

    private static final Logger LOGGER= LoggerFactory.getLogger(AkkaMyDb.class);

    public Map<String,Object> map=new HashMap<>();

    @Override
    public Receive createReceive() {
        return receiveBuilder().match(SetRequest.class,msg->map.put(msg.getKey(),msg.getValue()))
                .matchAny(o->LOGGER.info("received unknown message:{}",o)).build();
    }
}
