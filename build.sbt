name := "logic"

version := "1.0-SNAPSHOT"

libraryDependencies ++= Seq(
  "org.mongodb" %% "casbah" % "2.6.3",
  "org.jasypt" % "jasypt" % "1.9.1",
  cache
)     

play.Project.playScalaSettings
