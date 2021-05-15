package common.actor;

import akka.actor.AbstractActor;
import akka.actor.Props;
import common.rabbitmq.msg.consumer.RabbitReceive;

import javax.inject.Inject;
import javax.inject.Singleton;

/**
 * @author qingyun
 * @date 2021/5/15 4:25 下午
 */
@Singleton
public class JobActor extends AbstractActor {

  @Inject private RabbitReceive rabbitReceive;

  /**
   * Props props.
   *
   * @return the props
   */
  public static Props props() {
    return Props.create(JobActor.class);
  }

  @Override
  public Receive createReceive() {
    return receiveBuilder().matchEquals("receiveMq", this::receiveMq).build();
  }

  private <P> void receiveMq(P p) {
    rabbitReceive.receive("hummingbird");
  }
}
