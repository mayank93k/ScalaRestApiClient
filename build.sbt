name := "ScalaRestApiClient"

version := "0.1"

scalaVersion := "2.12.12"

libraryDependencies ++= Seq(
  "com.typesafe.play" %% "play-json" % "2.9.2",
  "org.slf4j" % "slf4j-api" % "1.7.36",
  "org.slf4j" % "slf4j-log4j12" % "1.7.36",
  "log4j" % "log4j" % "1.2.17"
)

excludeDependencies ++= Seq(ExclusionRule("ch.qos.logoback", "logoback-classic"))
