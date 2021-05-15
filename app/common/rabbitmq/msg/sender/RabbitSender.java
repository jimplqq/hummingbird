package common.rabbitmq.msg.sender;

import akka.actor.AbstractActor;
import akka.actor.Props;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import common.rabbitmq.bean.MessageBean;
import common.rabbitmq.connection.RabbitConnection;

import java.io.IOException;

/**
 * @author qingyun
 * @date 2021/5/15 2:11 下午
 */
public class RabbitSender extends AbstractActor {

  /**
   * Props props.
   *
   * @return the props
   */
  public static Props props() {
    return Props.create(RabbitSender.class);
  }

  @Override
  public Receive createReceive() {
    return receiveBuilder().match(MessageBean.class, this::send).build();
  }

  private <P> void send(MessageBean bean) {
    Connection connection = RabbitConnection.getConnection();
    Channel channel;
    try {
      channel = connection.createChannel();
      channel.queueDeclare(bean.getQueue(), false, false, false, null);
      channel.basicPublish(bean.getExchange(), bean.getQueue(), null, bean.getMsg().getBytes());
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}
