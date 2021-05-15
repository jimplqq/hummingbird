package common.rabbitmq.msg.sender

import common.rabbitmq.connection.RabbitConnection

object RabbitMqSender {
  def sendMsg(exchange: String, queue: String, msg: String): Unit = {
    val connection = RabbitConnection.getConnection()
    val channel = connection.createChannel()
    channel.queueDeclare(queue, false, false, false, null)
    channel.basicPublish(exchange, queue, null, msg.getBytes());
  }
}
