package common;

import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import com.google.inject.Inject;
import com.google.inject.Singleton;
import common.nacos.init.nacos.Nacaos;
import common.rabbitmq.init.RabbitInit;
import play.Application;
import play.Logger;
import scala.concurrent.duration.Duration;

import javax.inject.Named;
import java.util.concurrent.TimeUnit;

/** Created by questiny on 17-4-17. */
@Singleton
public class OnStartUp {

  private Logger.ALogger logger = Logger.of(OnStartUp.class);

  @Inject
  public OnStartUp(
      Application application, ActorSystem actorSystem, @Named("my-actor") ActorRef myActor) {
    Nacaos.init();
    RabbitInit.getInstance().init();
    if (application.isDev()) {
      logger.info("dev模式");
    }
    if (application.isProd()) {
      logger.info("prod模式");
    }
    if (application.isTest()) {
      logger.info("test模式");
    }

    actorSystem
        .scheduler()
        .scheduleOnce(
            Duration.create(10, TimeUnit.SECONDS), // initialDelay
            myActor, // interval
            "receiveMq",
            actorSystem.dispatcher(),
            null);
  }
}
