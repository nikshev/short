package common.utils

import com.typesafe.config.ConfigFactory

trait AppConfiguration {
  val config = ConfigFactory.load()
}
