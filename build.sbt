name := "scala-interview-prep"

scalaVersion := "2.12.6"

libraryDependencies ++= Seq(
  "com.stripe"%"stripe-java"%"7.0.0",
  "org.typelevel" %% "cats-core" %"1.4.0",
  "org.typelevel" %% "cats-effect"%"1.0.0",
  "io.circe" %% "circe-core"%"0.10.0",
  "io.circe" %%"circe-generic"      %"0.10.0",
  "io.circe" %%"circe-generic-extras"  %"0.10.0",
  "io.circe" %%"circe-java8"    %"0.10.0",
  "io.circe" %%  "circe-jawn"   %"0.10.0",
  "com.softwaremill.sttp" %% "async-http-client-backend-cats" %"1.3.6",
  "com.softwaremill.sttp" %% "core" %"1.3.6",
  "com.softwaremill.sttp" %% "circe" %"1.3.6",
  "com.google.guava" % "guava" % "16.0.1",
  "junit" % "junit" % "4.10" % "test",
  "org.mockito" % "mockito-core" % "1.10.19" % "test",
  "org.scalacheck" %% "scalacheck" % "1.13.5" % "test",
  "org.scalatest" %% "scalatest" % "3.0.5" % "test"
)
