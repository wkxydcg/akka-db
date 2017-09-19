package com.wkx.myakkaredis.actor;

import akka.actor.AbstractActor;
import akka.actor.ActorSystem;
import akka.actor.Props;
import akka.actor.Status;
import com.typesafe.config.ConfigFactory;
import com.wkx.myakkaredis.vo.GetRequest;
import com.wkx.myakkaredis.vo.KeyNotFoundException;
import com.wkx.myakkaredis.vo.SetRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.ConcurrentHashMap;

public class AkkaMyDb extends AbstractActor{

    private static final Logger LOGGER= LoggerFactory.getLogger(AkkaMyDb.class);

    public ConcurrentHashMap<String,Object> map=new ConcurrentHashMap<>();

    @Override
    public Receive createReceive() {
        return receiveBuilder().match(SetRequest.class,msg->{
            System.out.println("receive SetRequest message");
            map.put(msg.getKey(),msg.getValue());
            sender().tell(new Status.Success(msg.getKey()),self());
        }).match(GetRequest.class,msg->{
            System.out.println("receive GetRequest message");
            Object value=map.get(msg.key);
            Object response=value!=null?value:new Status.Failure(new KeyNotFoundException(msg.key));
            sender().tell(response,self());
        }).matchAny(o->{
            System.out.println("receive error message");
            sender().tell(new ClassNotFoundException(),self());
        }).build();
    }

    public static void main(String args[]){
        ActorSystem system=ActorSystem.create("myAkka", ConfigFactory.load("server"));
        system.actorOf(Props.create(AkkaMyDb.class),"db");
    }
}
