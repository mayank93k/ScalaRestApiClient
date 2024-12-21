package com.scala.api.client.utils.get

import com.scala.api.client.common.logger.Logging

import java.io.{BufferedReader, InputStreamReader}
import java.net.{HttpURLConnection, URL}

object HttpUtils extends Logging {
  /**
   * Function for performing a GET request
   */
  def sendGetRequest(urlString: String): String = {
    val url = new URL(urlString)
    val connection = url.openConnection().asInstanceOf[HttpURLConnection]

    try {
      logger.info("Set HTTP request method to GET")
      connection.setRequestMethod("GET")

      logger.info("Set timeout values for the connection and reading the response")
      connection.setConnectTimeout(5000) // Timeout for establishing the connection (5 seconds)
      connection.setReadTimeout(5000) // Timeout for reading the response (5 seconds)

      logger.info("Get the response code from the server")
      val responseCode = connection.getResponseCode
      logger.info(s"Response Code: $responseCode")

      logger.info("If the response code indicates success (HTTP 200 OK)")
      if (responseCode == HttpURLConnection.HTTP_OK) {
        logger.info("Create a BufferedReader to read the response content line by line")
        val in = new BufferedReader(new InputStreamReader(connection.getInputStream))
        val content = new StringBuilder
        var inputLine: String = null

        logger.info("Read the response content line by line")
        while ( {
          inputLine = in.readLine()
          inputLine != null
        }) {
          content.append(inputLine) // Append each line to the content
        }

        in.close() // Close the BufferedReader after reading the response
        content.toString // Return the complete response content
      } else {
        logger.info("Handle non-successful response codes")
        s"Request failed! Response Code: $responseCode"
      }
    } catch {
      case e: Exception => e.printStackTrace()
        "Error occurred while making the GET request."
    } finally {
      connection.disconnect() // Always disconnect the connection to release resources
    }
  }
}
