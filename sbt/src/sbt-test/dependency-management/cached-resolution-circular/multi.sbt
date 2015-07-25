lazy val check = taskKey[Unit]("Runs the check")

val sprayV = "1.1.1"
val playVersion = "2.2.0"
val summingbirdVersion = "0.4.0"
val luceneVersion = "4.0.0"
val akkaVersion = "2.3.1"

def commonSettings: Seq[Def.Setting[_]] =
  Seq(
    ivyPaths := new IvyPaths( (baseDirectory in ThisBuild).value, Some((target in LocalRootProject).value / "ivy-cache")),
    scalaVersion := "2.10.4",
    fullResolvers := fullResolvers.value.filterNot(_.name == "inter-project"),
    updateOptions := updateOptions.value.withCachedResolution(true)
  )

lazy val a = project.
  settings(commonSettings: _*).
  settings(
    name := "a",
    libraryDependencies := Seq(
      "commons-io" % "commons-io" % "1.3",
      "org.apache.spark" %% "spark-core" % "0.9.0-incubating"
    )
  )

lazy val b = project.
  settings(commonSettings: _*).
  settings(
    name := "b"
  )

lazy val c = project.
  settings(commonSettings: _*).
  settings(
    name := "c",
    libraryDependencies := Seq(organization.value %% "b" % version.value)
  )

lazy val root = (project in file(".")).
  settings(commonSettings: _*).
  settings(
    organization in ThisBuild := "org.example",
    version in ThisBuild := "1.0-SNAPSHOT"
  )