ThisBuild / version := "0.1.0-SNAPSHOT"

ThisBuild / scalaVersion := "3.4.2"

val zioVersion = "2.0.19"

lazy val root = (project in file("."))
  .settings(
    name := "AOC-2023"
  )

libraryDependencies ++= Seq(
  "dev.zio"       %% "zio"            % zioVersion,
  "com.github.sbt" % "junit-interface" % "0.13.3" % "test"
)
