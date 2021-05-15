package common.rabbitmq.bean;

import lombok.Data;

/**
 * @author qingyun
 * @date 2021/5/15 2:12 下午
 */
@Data
public class MessageBean {
  private String msg;
  private String exchange;
  private String queue;

}
