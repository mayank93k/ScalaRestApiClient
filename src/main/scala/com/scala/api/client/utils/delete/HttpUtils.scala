package com.scala.api.client.utils.delete

import com.scala.api.client.common.logger.Logging

import java.io.{BufferedReader, InputStreamReader}
import java.net.{HttpURLConnection, URL}

object HttpUtils extends Logging {
  /**
   * Function to send a DELETE request to the server
   */
  def sendDeleteRequest(urlString: String): String = {
    logger.info("Create a URL object from the string")
    val url = new URL(urlString)

    logger.info("Open a connection to the URL")
    val connection = url.openConnection().asInstanceOf[HttpURLConnection]
    var response = ""

    try {
      logger.info("Set the request method to DELETE")
      connection.setRequestMethod("DELETE")

      logger.info("Set the headers for content type (optional, depending on the server)")
      connection.setRequestProperty("Accept", "application/json")

      logger.info("Get the response code from the server")
      val responseCode = connection.getResponseCode
      logger.info(s"Response Code: $responseCode") // Log the response code

      logger.info("Read the server's response if the request was successful (HTTP 200 or HTTP 204)")
      val inputStream = if (responseCode < HttpURLConnection.HTTP_BAD_REQUEST) {
        connection.getInputStream
      } else {
        connection.getErrorStream
      }

      val reader = new BufferedReader(new InputStreamReader(inputStream))
      response = reader.lines().toArray.mkString("\n") // Read response as a single string
      reader.close() // Close the reader after reading

    } catch {
      case e: Exception =>
        logger.error("Error occurred while sending the DELETE request", e)
    } finally {
      // Disconnect the connection to release resources
      connection.disconnect()
    }

    response
  }
}
