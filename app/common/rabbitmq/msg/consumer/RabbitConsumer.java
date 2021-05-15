package common.rabbitmq.msg.consumer;

import com.rabbitmq.client.AMQP.BasicProperties;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.DefaultConsumer;
import com.rabbitmq.client.Envelope;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

/**
 * @author qingyun
 * @date 2021/5/15 2:00 下午
 */
public class RabbitConsumer extends DefaultConsumer {

  private static final Logger logger = LoggerFactory.getLogger(RabbitConsumer.class);

  /**
   * Constructs a new instance and records its association to the passed-in channel.
   *
   * @param channel the channel to which this consumer is attached
   */
  public RabbitConsumer(Channel channel) {
    super(channel);
  }

  @Override
  public void handleDelivery(
      String consumerTag, Envelope envelope, BasicProperties properties, byte[] body)
      throws IOException {
    logger.info("-----------consume message----------");
    logger.info("consumerTag: {}", consumerTag);
    logger.info("envelope: {}", envelope);
    logger.info("properties: {}", properties);
    logger.info("body: {}", new String(body));
  }
}
