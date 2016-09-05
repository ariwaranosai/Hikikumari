import chrome.Impl._
import chrome.permissions.APIPermission._
import net.lullabyte.{Chrome, ChromeSbtPlugin}

lazy val root = project.in(file("."))
  .enablePlugins(ChromeSbtPlugin)
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
    persistLauncher := true,
    persistLauncher in Test := false,
    relativeSourceMaps := true,
    libraryDependencies ++= Seq(
      "org.scala-js" %%% "scalajs-dom" % "0.9.0",
      "net.lullabyte" %%% "scala-js-chrome" % "0.2.0"
    ),
    chromeManifest := AppManifest(
      name = name.value,
      version = version.value,
      app = App(
        background = Background(
          scripts = List("main.js")
        )
      ),
      permissions = Set(
        Tabs
      )
    )
  )

