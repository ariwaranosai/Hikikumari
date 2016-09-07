import chrome._
import chrome.permissions.Permission
import chrome.permissions.Permission.API
import net.lullabyte.{Chrome, ChromeSbtPlugin}

val circeVersion = "0.4.1"

lazy val root = project.in(file("."))
  .enablePlugins(ChromeSbtPlugin)
  .enablePlugins(ScalaJSPlugin)
  .settings(
    name := "hwm",
    version := "0.0.1",
    scalaVersion := "2.11.8",
    scalacOptions ++= Seq(
      "-language:implicitConversions",
      "-language:existentials",
      "-Xlint",
      "-deprecation",
      "-Xfatal-warnings",
      "-feature"
    ),
    javacOptions ++= Seq (
      "-Xms12288M",
      "-Xmx12288M",
      "-XX:NewSize=3072M",
      "-XX:MaxNewSize=3072M",
      "-XX:ParallelGCThreads=8"
    ),
    persistLauncher := true,
    persistLauncher in Test := false,
    relativeSourceMaps := true,
    libraryDependencies ++= Seq(
      "org.scala-js" %%% "scalajs-dom" % "0.9.0",
      "net.lullabyte" %%% "scala-js-chrome" % "0.3.0",
      "com.lihaoyi" %%% "utest" % "0.4.3" % "test",
      "com.thoughtworks.binding" %%% "dom" % "latest.release",
      "xyz.ariwaranosai" %%% "scalajs-hashes" % "0.0.1-SNAPSHOT",
      "io.circe" %%% "circe-core" % circeVersion,
      "io.circe" %%% "circe-generic" % circeVersion,
      "io.circe" %%% "circe-parser" % circeVersion
    ),
    testFrameworks += new TestFramework("utest.runner.Framework"),
    jsDependencies ++= Seq(
      "org.webjars.bower" % "jshashes" % "1.0.5" / "1.0.5/hashes.min.js"
    ),
    chromeManifest := new ExtensionManifest {
      val name = Keys.name.value
      val version = Keys.version.value
      val background = Background(
        scripts = Chrome.defaultScripts
      )
      override val permissions: Set[Permission] = Set(
        API.Tabs
      )
      override val browserAction = Some(BrowserAction(
        title = Some("Save"),
        popup = Some("assets/html/App.html")
      ))
    }
  )

addCompilerPlugin("org.scalamacros" % "paradise" % "2.1.0" cross CrossVersion.full)

