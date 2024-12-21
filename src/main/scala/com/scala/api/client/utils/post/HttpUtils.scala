package com.scala.api.client.utils.post

import java.io.{BufferedReader, InputStreamReader, OutputStream}
import java.net.{HttpURLConnection, URL}

object HttpUtils {
  // Function for performing a POST request
  def sendPostRequest(urlString: String, jsonPayload: String): String = {
    val url = new URL(urlString)
    val connection = url.openConnection().asInstanceOf[HttpURLConnection]

    try {
      // Set HTTP request method to POST
      connection.setRequestMethod("POST")
      connection.setDoOutput(true)

      // Set request headers
      connection.setRequestProperty("Content-Type", "application/json; utf-8")
      connection.setRequestProperty("Accept", "application/json")

      // Write the JSON payload to the output stream
      val outputStream: OutputStream = connection.getOutputStream
      outputStream.write(jsonPayload.getBytes("UTF-8"))
      outputStream.close()

      // Get the HTTP response code
      val responseCode = connection.getResponseCode
      println(s"Response Code: $responseCode")

      // Read the server's response based on response code
      val inputStream = if (responseCode < HttpURLConnection.HTTP_BAD_REQUEST) {
        connection.getInputStream
      } else {
        connection.getErrorStream
      }

      val reader = new BufferedReader(new InputStreamReader(inputStream))
      val response = reader.lines().toArray.mkString("\n")
      reader.close()

      response
    } catch {
      case e: Exception =>
        e.printStackTrace()
        ""
    } finally {
      connection.disconnect()
    }
  }
}
