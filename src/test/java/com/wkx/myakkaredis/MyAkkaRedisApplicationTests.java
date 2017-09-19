package com.wkx.myakkaredis;

import com.wkx.myakkaredis.client.JClient;
import org.junit.Test;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

//@RunWith(SpringRunner.class)
//@SpringBootTest
public class MyAkkaRedisApplicationTests {

//	private ActorSystem system=ActorSystem.create();

//	@Test
//	public void contextLoads() {
//		TestActorRef<AkkaMyDb> actorRef=TestActorRef.create(system, Props.create(AkkaMyDb.class));
//		actorRef.tell(new SetRequest("key","wkx"), ActorRef.noSender());
//		AkkaMyDb akkaMyDb=actorRef.underlyingActor();
//		assert akkaMyDb.map.get("key").equals("wkx");
//	}

	@Test
	public void testClient() throws ExecutionException, InterruptedException {
		JClient client=new JClient("127.0.0.1:8090");
		client.set("123","123");
		Object result=((CompletableFuture)client.get("123")).get();
		System.out.println("result:"+result);
	}
}
