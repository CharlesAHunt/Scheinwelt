import sbt._
import Keys._
import play.Project._

object ApplicationBuild extends Build {

  val appName         = "scheinwelt"
  val appVersion      = "1.0-SNAPSHOT"

  val appDependencies = Seq(
    "org.mongojack" %% "play-mongojack" % "2.0.0-RC2",
    "org.mongodb" % "mongo-java-driver" % "2.11.2",
    "commons-codec" % "commons-codec" % "1.8"
  )


  val main = play.Project(appName, appVersion, appDependencies).settings(
    // Add your own project settings here      
  )

}
