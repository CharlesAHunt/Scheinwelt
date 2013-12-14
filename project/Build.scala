import sbt._
import Keys._
import play.Project._

object ApplicationBuild extends Build {

  val appName         = "log.ic"
  val appVersion      = "0.4"

    val appDependencies = Seq(
      "org.mongodb" %% "casbah" % "2.6.3",
      "org.jasypt" % "jasypt" % "1.9.1",
    javaCore
  )

  val main = play.Project(appName, appVersion, appDependencies).settings(
    // Add your own project settings here      
  )

}
