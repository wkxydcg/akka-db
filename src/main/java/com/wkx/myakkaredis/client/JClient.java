package com.wkx.myakkaredis.client;

import akka.actor.ActorSelection;
import akka.actor.ActorSystem;
import akka.pattern.Patterns;
import com.typesafe.config.ConfigFactory;
import com.wkx.myakkaredis.vo.GetRequest;
import com.wkx.myakkaredis.vo.SetRequest;
import scala.compat.java8.FutureConverters;
import scala.concurrent.Future;

import java.util.concurrent.CompletionStage;

public class JClient {

    private final ActorSelection remoteDB;

    public JClient(String remote){
        String url="akka.tcp://myAkka@"+remote+"/user/db";
        System.out.println(url);
        ActorSystem system = ActorSystem.create("LocalSystem", ConfigFactory.load("client"));
        remoteDB= system.actorSelection(url);
    }

    public CompletionStage set(String key,Object value){
        Future<Object> future=Patterns.ask(remoteDB,new SetRequest(key,value),2000);
        return FutureConverters.toJava(future);
    }

    public CompletionStage get(String key){
        Future<Object> future=Patterns.ask(remoteDB,new GetRequest(key),2000);
        return FutureConverters.toJava(future);
    }

}
