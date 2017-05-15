enablePlugins(GatlingPlugin)

name := "gatling-tutorial-sbt"
organization := "io.gatling.demo"
version := "0.1"
scalaVersion := "2.11.8"
val gatlingVersion = "2.2.3"

libraryDependencies += "io.gatling.highcharts" % "gatling-charts-highcharts" % gatlingVersion
libraryDependencies += "io.gatling"            % "gatling-test-framework"    % gatlingVersion
libraryDependencies += "org.postgresql" % "postgresql" % "42.1.0"



    