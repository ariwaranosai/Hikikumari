import chrome._
import chrome.permissions.Permission
import chrome.permissions.Permission.API
import net.lullabyte.{Chrome, ChromeSbtPlugin}

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
    persistLauncher := true,
    persistLauncher in Test := false,
    relativeSourceMaps := true,
    libraryDependencies ++= Seq(
      "org.scala-js" %%% "scalajs-dom" % "0.9.0",
      "net.lullabyte" %%% "scala-js-chrome" % "0.3.0",
      "com.thoughtworks.binding" %%% "dom" % "latest.release"
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

