import sbt._
import Keys._
import play.Project._

object ApplicationBuild extends Build {

  val appName         = "Scheinwelt"
  val appVersion      = "1.0-SNAPSHOT"

  val appDependencies = Seq(
    "net.vz.mongodb.jackson" % "mongo-jackson-mapper" % "1.4.2",
    "org.mongodb" % "mongo-java-driver" % "2.11.2",
    "commons-codec" % "commons-codec" % "1.8",
    "com.fasterxml.jackson.core" % "jackson-annotations" % "2.2.2",
    "com.fasterxml.jackson.core" % "jackson-core" % "2.2.2"
  )

  val main = play.Project(appName, appVersion, appDependencies).settings(
    // Add your own project settings here      
  )

}