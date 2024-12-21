package com.scala.api.client.main

import com.scala.api.client.common.logger.Logging
import com.scala.api.client.utils.common.JsonUtils.readJsonFromFile
import com.scala.api.client.utils.put.HttpUtils

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
