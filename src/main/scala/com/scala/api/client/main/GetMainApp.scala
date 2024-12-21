package com.scala.api.client.main

import com.scala.api.client.utils.get.HttpUtils

/**
 * The GET Request fetches data from the server without modifying it. In the code, we specify the URL of the resource
 * (e.g., https://jsonplaceholder.typicode.com/posts/1), open an HTTP connection, and set the request method to GET for
 * data retrieval. Timeout values are set, and upon receiving the response, we check the HTTP response code (200 for success).
 * If successful, we read the response using a BufferedReader, print it to the console, ensure the input stream is closed,
 * and disconnect the connection. A typical use case for this is retrieving a blog post or user details.
 */
object GetMainApp {
  def main(args: Array[String]): Unit = {
    val urlString = "https://jsonplaceholder.typicode.com/posts/1" // Sample API URL

    // Call the sendGetRequest function from HttpUtils to perform the GET request
    val response = HttpUtils.sendGetRequest(urlString)

    // Print the response content
    println("Response Content:")
    println(response)
  }
}
