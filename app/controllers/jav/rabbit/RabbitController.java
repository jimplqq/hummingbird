package controllers.jav.rabbit;

import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import common.rabbitmq.bean.MessageBean;
import common.rabbitmq.msg.sender.RabbitSender;
import play.mvc.Controller;
import play.mvc.Result;

import javax.inject.Inject;
import java.util.UUID;
import java.util.concurrent.ExecutionException;

/**
 * @author qingyun
 * @date 2021/5/15 11:35 上午
 */
public class RabbitController extends Controller {

  private ActorRef rabbitSenderActor;

  @Inject
  public RabbitController(ActorSystem actorSystem) {
    this.rabbitSenderActor = actorSystem.actorOf(RabbitSender.props());
  }

  public Result send() throws ExecutionException, InterruptedException {
    MessageBean messageBean = new MessageBean();
    messageBean.setExchange("");
    messageBean.setQueue("hummingbird");
    messageBean.setMsg(UUID.randomUUID().toString());
    //    Object o = Patterns.ask(rabbitSenderActor, messageBean,
    // Duration.ofSeconds(10)).toCompletableFuture().get();
    rabbitSenderActor.tell(messageBean, ActorRef.noSender());
    //        RabbitMqSender.sendMsg("", "hummingbird", "hello world");
    return ok();
  }
}
