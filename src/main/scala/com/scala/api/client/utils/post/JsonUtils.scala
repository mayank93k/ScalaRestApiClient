package com.scala.api.client.utils.post

import com.scala.api.client.common.logger.Logging

import scala.io.Source

object JsonUtils extends Logging {
  // Function to read JSON content from a file
  def readJsonFromFile(filePath: String): String = {
    try {
      logger.info("Read the file and return the content as a string")
      Source.fromFile(filePath).getLines().mkString("\n")
    } catch {
      case e: Exception =>
        logger.error(s"Error reading JSON file: ${e.getMessage}")
        ""
    }
  }
}
