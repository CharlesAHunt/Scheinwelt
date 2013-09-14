import sbt._
import Keys._
import play.Project._

object ApplicationBuild extends Build {

  val appName         = "scheinwelt"
  val appVersion      = "1.0-SNAPSHOT"

  val appDependencies = Seq(
    "org.mongodb" %% "casbah" % "2.6.2",
    "org.reactivemongo" %% "reactivemongo" % "0.9",
    "joda-time" % "joda-time" % "2.3",
    "commons-codec" % "commons-codec" % "1.8"
  )


  val main = play.Project(appName, appVersion, appDependencies).settings(
    // Add your own project settings here      
  )

}
