package common.rabbitmq.config

import com.typesafe.config.{Config, ConfigFactory}

object RabbitMqConfig {

  val config: Config = ConfigFactory.load.getConfig("rabbitmq")
}
