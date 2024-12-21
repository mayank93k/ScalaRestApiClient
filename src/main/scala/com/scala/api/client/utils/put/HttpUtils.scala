package com.scala.api.client.utils.put

import com.scala.api.client.common.logger.Logging

import java.io.{BufferedReader, InputStreamReader, OutputStream}
import java.net.{HttpURLConnection, URL}

object HttpUtils extends Logging {
  /**
   * Function to send a PUT request to the server
   */
  def sendPutRequest(urlString: String, jsonPayload: String): String = {
    logger.info("Create a URL object from the string")
    val url = new URL(urlString)

    logger.info("Open a connection to the URL")
    val connection = url.openConnection().asInstanceOf[HttpURLConnection]
    var response = ""

    try {
      logger.info("Set the request method to PUT")
      connection.setRequestMethod("PUT")

      logger.info("Enable input and output streams for sending data in the request body")
      connection.setDoOutput(true)

      logger.info("Set the headers for content type (JSON) and expected response format")
      connection.setRequestProperty("Content-Type", "application/json; utf-8")
      connection.setRequestProperty("Accept", "application/json")

      logger.info("Write the JSON payload to the request body")
      val outputStream: OutputStream = connection.getOutputStream
      outputStream.write(jsonPayload.getBytes("UTF-8")) // Convert JSON string to bytes
      outputStream.close() // Close the output stream after writing

      logger.info("Get the response code from the server")
      val responseCode = connection.getResponseCode
      logger.info(s"Response Code: $responseCode") // Log the response code

      logger.info("Read the server's response if the request was successful (HTTP 200)")
      if (responseCode == HttpURLConnection.HTTP_OK || responseCode == HttpURLConnection.HTTP_CREATED) {
        val inputStream = new BufferedReader(new InputStreamReader(connection.getInputStream))
        response = inputStream.lines().toArray.mkString("\n") // Read response as a single string
        inputStream.close() // Close the reader after reading
      } else {
        logger.info("If the response code indicates failure, read from the error stream")
        val errorStream = new BufferedReader(new InputStreamReader(connection.getErrorStream))
        response = errorStream.lines().toArray.mkString("\n") // Read error response
        errorStream.close() // Close the error stream
      }

    } catch {
      case e: Exception =>
        logger.error("Error occurred while sending the PUT request", e)
    } finally {
      // Disconnect the connection to release resources
      connection.disconnect()
    }

    response
  }
}
