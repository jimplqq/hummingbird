package common.rabbitmq.bean

import scala.beans.BeanProperty

class RabbitMqBean {
  @BeanProperty
  var host: String = _

  @BeanProperty
  var port: Integer = _

  @BeanProperty
  var vhost: String = _

  @BeanProperty
  var user: String = _

  @BeanProperty
  var password: String = _

  @BeanProperty
  var exchangeType: String = _

  @BeanProperty
  var durable: String = _

  @BeanProperty
  var autoAck: String = _

  @BeanProperty
  var basicQos: String = _

  @BeanProperty
  var retries: String = _

  @BeanProperty
  var msgmapper: String = _

}
