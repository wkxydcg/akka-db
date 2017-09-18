package com.wkx.myakkaredis;

import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.actor.Props;
import akka.testkit.TestActorRef;
import com.wkx.myakkaredis.actor.AkkaMyDb;
import com.wkx.myakkaredis.vo.SetRequest;
import org.junit.Test;

//@RunWith(SpringRunner.class)
//@SpringBootTest
public class MyAkkaRedisApplicationTests {

	private ActorSystem system=ActorSystem.create();

	@Test
	public void contextLoads() {
		TestActorRef<AkkaMyDb> actorRef=TestActorRef.create(system, Props.create(AkkaMyDb.class));
		actorRef.tell(new SetRequest("key","wkx"), ActorRef.noSender());
		AkkaMyDb akkaMyDb=actorRef.underlyingActor();
		assert akkaMyDb.map.get("key").equals("wkx");
	}

}
