name := "scheinwelt"

version := "0.3.0"

libraryDependencies ++= Seq(
    "org.mongodb" %% "casbah" % "2.6.3",
    "com.novus" %% "salat" % "1.9.4",
    "joda-time" % "joda-time" % "2.3",
    "commons-codec" % "commons-codec" % "1.8",
    cache
)     

play.Project.playScalaSettings
