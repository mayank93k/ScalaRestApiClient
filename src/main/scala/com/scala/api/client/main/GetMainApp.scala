package com.scala.api.client.main

import com.scala.api.client.common.logger.Logging
import com.scala.api.client.utils.get.HttpUtils
import java.nio.file.{Files, Paths}
import java.nio.charset.StandardCharsets

/**
 * The GET Request fetches data from the server without modifying it. In the code, we specify the URL of the resource
 * (e.g., https://jsonplaceholder.typicode.com/posts/1), open an HTTP connection, and set the request method to GET for
 * data retrieval. Timeout values are set, and upon receiving the response, we check the HTTP response code (200 for success).
 * If successful, we read the response using a BufferedReader, print it to the console, ensure the input stream is closed,
 * and disconnect the connection. A typical use case for this is retrieving a blog post or user details.
 */
object GetMainApp extends Logging {
  def main(args: Array[String]): Unit = {
    val urlString = "https://jsonplaceholder.typicode.com/posts/1" // Sample API URL
    logger.info(s"Api Url link: " + urlString)

    logger.info("Call the sendGetRequest function from HttpUtils to perform the GET request")
    val response = HttpUtils.sendGetRequest(urlString)

    // File path where the response will be saved
    val filePath = "src/main/resources/get/output/response_output.txt"

    logger.info("Write response content to a file")
    try {
      Files.write(Paths.get(filePath), response.getBytes(StandardCharsets.UTF_8))
      logger.info(s"Response content has been written to $filePath")
    } catch {
      case e: Exception =>
        logger.error("An error occurred while writing the response to the file", e)
    }
  }
}
