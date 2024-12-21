package com.scala.api.client.utils.post

import scala.io.Source

object JsonUtils {
  // Function to read JSON content from a file
  def readJsonFromFile(filePath: String): String = {
    try {
      // Read the file and return the content as a string
      Source.fromFile(filePath).getLines().mkString("\n")
    } catch {
      case e: Exception =>
        println(s"Error reading JSON file: ${e.getMessage}")
        ""
    }
  }
}
