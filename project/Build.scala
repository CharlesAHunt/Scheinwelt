import sbt._
import Keys._
import play.Project._

object ApplicationBuild extends Build {

  val appName         = "compensation-menu"
  val appVersion      = "0.4"

    val appDependencies = Seq(
      "org.mongodb" %% "casbah" % "2.6.3",
    javaCore
  )

  val main = play.Project(appName, appVersion, appDependencies).settings(
    // Add your own project settings here      
  )

}
