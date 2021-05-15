package common.rabbitmq.msg.consumer;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import common.rabbitmq.connection.RabbitConnection;

import java.io.IOException;

/**
 * @author qingyun
 * @date 2021/5/15 4:17 下午
 */
public class RabbitReceive {
  public void receive(String queue) {
    Connection connection = RabbitConnection.getConnection();
    Channel channel;
    try {
      channel = connection.createChannel();
      channel.basicQos(0, 1, false);
      RabbitConsumer consumer = new RabbitConsumer(channel);
      channel.basicConsume(queue, true, consumer);
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}
