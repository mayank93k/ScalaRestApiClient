package com.scala.api.client.main

import com.scala.api.client.common.logger.Logging
import com.scala.api.client.utils.common.JsonUtils
import com.scala.api.client.utils.post.HttpUtils

/**
 * The POST Request sends data to the server to create a new resource. In the code, we specify the server endpoint URL
 * (e.g., https://jsonplaceholder.typicode.com/posts), open an HTTP connection, and set the request method to POST for
 * data submission. Headers are set to specify the content type (application/json) and expected response format.
 * By enabling output (setDoOutput(true)), we send the JSON payload (e.g., title, body, userId) using an OutputStream.
 * The HTTP response code is checked, and if successful, the response is read with a BufferedReader and printed to the console.
 * After ensuring the output stream is closed, the connection is disconnected. This approach is commonly used for tasks like
 * adding a new blog post, registering a user, or submitting form data.
 */
object PostMainApp extends Logging {
  def main(args: Array[String]): Unit = {
    val urlString = "https://jsonplaceholder.typicode.com/posts" // Example API URL
    logger.info(s"Api Url link: " + urlString)

    logger.info("Read the JSON payload from a file")
    val jsonPayload = JsonUtils.readJsonFromFile("src/main/resources/post/input/postdata.json")

    logger.info("If the JSON was successfully read, make the POST request")
    if (jsonPayload.nonEmpty) {
      val response = HttpUtils.sendPostRequest(urlString, jsonPayload)
      logger.info("Response Content:")
      println(response)
    } else {
      logger.error("Failed to read JSON from file.")
    }
  }
}
