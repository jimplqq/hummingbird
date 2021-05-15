package common.rabbitmq.connection;

import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import common.rabbitmq.init.RabbitInit;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * @author qingyun
 * @date 2021/5/15 1:53 下午
 */
public class RabbitConnection {
  private static Connection connection = null;

  public static Connection getConnection() {
    if (connection == null) {
      ConnectionFactory factory = new ConnectionFactory();
      RabbitInit init = RabbitInit.getInstance();
      factory.setHost(init.getBean().getHost());
      factory.setPort(init.getBean().getPort());
      factory.setUsername(init.getBean().getUser());
      factory.setPassword(init.getBean().getPassword());
      factory.setVirtualHost(init.getBean().getVhost());
      try {
        connection = factory.newConnection();
      } catch (IOException | TimeoutException e) {
        e.printStackTrace();
      }
    }
    return connection;
  }
}
