name := "ScalaRestApiClient"

version := "0.1"

scalaVersion := "2.12.12"

libraryDependencies ++= Seq(
  "com.typesafe.play" %% "play-json" % "2.9.2"
)

excludeDependencies ++= Seq(ExclusionRule("ch.qos.logoback", "logoback-classic"))
