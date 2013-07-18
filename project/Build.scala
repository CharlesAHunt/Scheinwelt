import sbt._
import Keys._
import play.Project._

object ApplicationBuild extends Build {

  val appName         = "Scheinwelt"
  val appVersion      = "1.0-SNAPSHOT"

  val appDependencies = Seq(
    "net.vz.mongodb.jackson" % "mongo-jackson-mapper" % "1.4.2",
    "org.mongodb" % "mongo-java-driver" % "2.11.2",
    javaCore,
    javaJdbc,
    javaEbean
  )

  val main = play.Project(appName, appVersion, appDependencies).settings(
    // Add your own project settings here      
  )

}
