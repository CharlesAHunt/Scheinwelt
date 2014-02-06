name := "logic"

version := "1.0-SNAPSHOT"

libraryDependencies ++= Seq(
  "org.mongodb" %% "casbah" % "2.6.3",
  "org.jasypt" % "jasypt" % "1.9.1",
  "com.novus" %% "salat" % "1.9.4",
  "org.apache.logging.log4j" % "log4j-api" % "2.0-beta9",
  "org.apache.logging.log4j" % "log4j-core" % "2.0-beta9",
  cache
)     

play.Project.playScalaSettings
