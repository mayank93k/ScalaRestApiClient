package com.scala.api.client.common.logger

import org.slf4j.{Logger, LoggerFactory}

trait Logging {
  /**
   * Creates an SLF4J logger using the class name of the class that extends this trait.
   */
  val logger: Logger = LoggerFactory.getLogger(this.getClass)
}
