package common.rabbitmq.init;

import com.typesafe.config.Config;
import com.typesafe.config.ConfigFactory;
import common.rabbitmq.bean.RabbitMqBean;
import lombok.Data;

/**
 * @author qingyun
 * @date 2021/5/15 11:49 上午
 */
@Data
public class RabbitInit {
  private RabbitMqBean bean;

  public RabbitInit() {}

  private static class RabbitHolder {
    static RabbitInit init = new RabbitInit();
  }

  public static RabbitInit getInstance() {
    return RabbitHolder.init;
  }

  public synchronized void init() {
    Config config = ConfigFactory.load().getConfig("rabbitmq");
    if (this.bean == null) {
      this.bean = new RabbitMqBean();
    }
    if (this.bean.getHost() == null) {
      this.bean.setHost(config.getString("host"));
    }
    if (this.bean.getPort() == null) {
      this.bean.setPort(config.getInt("port"));
    }
    if (this.bean.getUser() == null) {
      this.bean.setUser(config.getString("user"));
    }
    if (this.bean.getPassword() == null) {
      this.bean.setPassword(config.getString("password"));
    }
    if (this.bean.getVhost() == null) {
      this.bean.setVhost(config.getString("vhost"));
    }
  }
}
