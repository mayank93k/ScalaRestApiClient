package com.scala.api.client.utils.get

import java.io.{BufferedReader, InputStreamReader}
import java.net.{HttpURLConnection, URL}

object HttpUtils {
  // Function for performing a GET request
  def sendGetRequest(urlString: String): String = {
    val url = new URL(urlString)
    val connection = url.openConnection().asInstanceOf[HttpURLConnection]

    try {
      // Set HTTP request method to GET
      connection.setRequestMethod("GET")

      // Set timeout values for the connection and reading the response
      connection.setConnectTimeout(5000) // Timeout for establishing the connection (5 seconds)
      connection.setReadTimeout(5000) // Timeout for reading the response (5 seconds)

      // Get the response code from the server
      val responseCode = connection.getResponseCode
      println(s"Response Code: $responseCode")

      // If the response code indicates success (HTTP 200 OK)
      if (responseCode == HttpURLConnection.HTTP_OK) {
        // Create a BufferedReader to read the response content line by line
        val in = new BufferedReader(new InputStreamReader(connection.getInputStream))
        val content = new StringBuilder
        var inputLine: String = null

        // Read the response content line by line
        while ( {
          inputLine = in.readLine()
          inputLine != null
        }) {
          content.append(inputLine) // Append each line to the content
        }

        in.close() // Close the BufferedReader after reading the response
        content.toString // Return the complete response content
      } else {
        // Handle non-successful response codes
        s"Request failed! Response Code: $responseCode"
      }
    } catch {
      case e: Exception =>
        e.printStackTrace()
        "Error occurred while making the GET request."
    } finally {
      connection.disconnect() // Always disconnect the connection to release resources
    }
  }
}
