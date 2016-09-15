import chrome._
import chrome.permissions.Permission
import chrome.permissions.Permission.API
import net.lullabyte.{Chrome, ChromeSbtPlugin}

name := "hwm"

val circeVersion = "0.4.1"

lazy val commonSettings = Seq (
  version := "0.0.1",
  scalaVersion := "2.11.8",
  scalacOptions ++= Seq(
    "-language:implicitConversions",
    "-language:existentials",
    "-Xlint",
    "-deprecation",
    "-Xfatal-warnings",
    "-feature",
    "-language:postfixOps"
  ),
  addCompilerPlugin("org.scalamacros" % "paradise" % "2.1.0" cross CrossVersion.full),
  libraryDependencies ++= Seq(
    "org.scala-js" %%% "scalajs-dom" % "0.9.0",
    "com.lihaoyi" %%% "utest" % "0.4.3" % "test",
    "io.circe" %%% "circe-core" % circeVersion,
    "io.circe" %%% "circe-generic" % circeVersion,
    "io.circe" %%% "circe-parser" % circeVersion
  ),
  testFrameworks += new TestFramework("utest.runner.Framework"),
  relativeSourceMaps := true ,
  scalaJSUseRhino in Global := false,
  jsEnv := PhantomJSEnv(args = Seq("--web-security=no", "")).value
)

lazy val root = (project in file("."))
  .settings(commonSettings:_*)
  .enablePlugins(ChromeSbtPlugin)
  .enablePlugins(ScalaJSPlugin)
  .settings(
    libraryDependencies ++= Seq(
      "net.lullabyte" %%% "scala-js-chrome" % "0.3.0",
      "com.thoughtworks.binding" %%% "dom" % "latest.release"
    ),
    jsDependencies ++= Seq(
      "org.webjars.bower" % "jquery" % "1.12.4" / "1.12.4/dist/jquery.min.js"
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
        title = Some("Hirikumori"),
        popup = Some("assets/html/App.html")
      ))
    }
  ).aggregate(leancloud).dependsOn(leancloud)

lazy val leancloud = (project in file("leancloud"))
  .settings(commonSettings:_*)
  .enablePlugins(ScalaJSPlugin)
  .settings(
    libraryDependencies ++= Seq(
      "xyz.ariwaranosai" %%% "scalajs-hashes" % "0.1.0"
    ),
    jsDependencies ++= Seq(
      RuntimeDOM % "test",
      "org.webjars.bower" % "jshashes" % "1.0.5" / "1.0.5/hashes.min.js" % "test"
    )
  )

