package com.scala.api.client.main

import com.scala.api.client.common.logger.Logging
import com.scala.api.client.utils.delete.HttpUtils

/**
 * The DELETE Request sends a request to the server to delete an existing resource. In the code, we specify the URL of the resource
 * (e.g., https://jsonplaceholder.typicode.com/posts/1), open an HTTP connection, and set the request method to DELETE for
 * removing the data. Timeout values are set, and upon receiving the response, we check the HTTP response code (200 or 204 for success).
 * If successful, we confirm that the resource has been deleted, ensure the input and output streams are closed, and disconnect the connection.
 * A typical use case for this is deleting a blog post or removing a user from the system.
 */
object DeleteMainApp extends Logging {
  def main(args: Array[String]): Unit = {
    val urlString = "https://jsonplaceholder.typicode.com/posts/1"
    logger.info(s"Sample URL for the DELETE request (e.g., deleting a post): $urlString")

    logger.info("Sending DELETE request to remove the resource")
    // Call the sendDeleteRequest function from HttpUtils to perform the DELETE request
    val response = HttpUtils.sendDeleteRequest(urlString)

    // Log and print the response content
    logger.info("Response Content:")
    println(response) // Print the response to check what the server returns
  }
}
