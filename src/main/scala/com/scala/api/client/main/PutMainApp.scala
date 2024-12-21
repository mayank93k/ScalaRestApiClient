package com.scala.api.client.main

import com.scala.api.client.common.logger.Logging
import com.scala.api.client.utils.common.JsonUtils.readJsonFromFile
import com.scala.api.client.utils.put.HttpUtils

/**
 * The PUT Request sends data to the server to update an existing resource. In the code, we specify the URL of the resource
 * (e.g., https://jsonplaceholder.typicode.com/posts/1), open an HTTP connection, and set the request method to PUT for
 * updating the data. We then include the data to be updated, typically in the form of a JSON payload, and set appropriate
 * headers (e.g., Content-Type). Timeout values are set, and upon receiving the response, we check the HTTP response code
 * (200 or 204 for success). If successful, we ensure the output stream is closed, the response is handled, and the connection
 * is disconnected. A typical use case for this is updating a blog post or modifying user details.
 */
object PutMainApp extends Logging {
  def main(args: Array[String]): Unit = {
    val urlString = "https://jsonplaceholder.typicode.com/posts/1"
    logger.info(s"Sample URL for the PUT request (updating a post): " + urlString)

    logger.info("Read the JSON payload from the file")
    val jsonFilePath = "src/main/resources/put/input/putdata.json"
    val jsonPayload = readJsonFromFile(jsonFilePath)

    logger.info(s"API URL: $urlString")
    logger.info("Sending PUT request to update the resource")

    logger.info("Call the sendPutRequest function from HttpUtils to perform the PUT request")
    val response = HttpUtils.sendPutRequest(urlString, jsonPayload)

    // Log and print the response content
    logger.info("Response Content:")
    println(response)
  }
}
