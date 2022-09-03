Global / excludeLintKeys += logManager
Global / excludeLintKeys += scalaJSUseMainModuleInitializer
Global / excludeLintKeys += scalaJSLinkerConfig

inThisBuild(
  List(
    scalafixDependencies += "com.github.liancheng" %% "organize-imports" % "0.6.0",
    semanticdbEnabled          := true,
    semanticdbVersion          := scalafixSemanticdb.revision,
    scalafixScalaBinaryVersion := scalaBinaryVersion.value,
    organization               := "tech.neander",
    organizationName           := "Neandertech",
    sonatypeCredentialHost     := "s01.oss.sonatype.org",
    homepage := Some(
      url("https://github.com/neandertech/langoustine")
    ),
    startYear := Some(2022),
    licenses := List(
      "Apache-2.0" -> url("http://www.apache.org/licenses/LICENSE-2.0")
    ),
    developers := List(
      Developer(
        "keynmol",
        "Anton Sviridov",
        "keynmol@gmail.com",
        url("https://blog.indoorvivants.com")
      )
    )
  )
)

val V = new {
  val scala      = "3.2.0"
  val scribe     = "3.10.3"
  val upickle    = "2.0.0"
  val cats       = "2.8.0"
  val munit      = "1.0.0-M6"
  val jsonrpclib = "0.0.3"
  val fs2        = "3.2.12"
  val http4s     = "0.23.15"
  val laminar    = "0.14.2"
  val decline    = "2.3.0"
}

lazy val noPublishing = Seq(
  publish / skip      := true,
  publishLocal / skip := true
)

val scalaVersions = List(V.scala)

val default = Seq(VirtualAxis.scalaABIVersion(V.scala), VirtualAxis.jvm)

lazy val root = project
  .in(file("."))
  .aggregate(meta.projectRefs*)
  .aggregate(lsp.projectRefs*)
  .aggregate(tracer.projectRefs*)
  .aggregate(tracerShared.projectRefs*)
  .aggregate(tracerTests.projectRefs*)
  .settings(noPublishing)

lazy val meta = projectMatrix
  .in(file("modules/meta"))
  .settings(name := "langoustine-meta")
  .defaultAxes(default*)
  .jvmPlatform(scalaVersions)
  .jsPlatform(scalaVersions)
  .nativePlatform(scalaVersions)
  .settings(
    libraryDependencies += "com.outr"      %%% "scribe"    % V.scribe,
    libraryDependencies += "com.lihaoyi"   %%% "upickle"   % V.upickle,
    libraryDependencies += "org.typelevel" %%% "cats-core" % V.cats
  )

lazy val lsp = projectMatrix
  .in(file("modules/lsp"))
  .defaultAxes(default*)
  .settings(
    name := "langoustine-lsp",
    scalacOptions ++= Seq("-Xmax-inlines", "64"),
    libraryDependencies += "org.scalameta" %%% "munit"     % V.munit % Test,
    libraryDependencies += "com.outr"      %%% "scribe"    % V.scribe,
    libraryDependencies += "com.lihaoyi"   %%% "upickle"   % V.upickle,
    libraryDependencies += "org.typelevel" %%% "cats-core" % V.cats,
    libraryDependencies += "tech.neander" %%% "jsonrpclib-core" % V.jsonrpclib,
    Test / fork := virtualAxes.value.contains(VirtualAxis.jvm)
  )
  .jvmPlatform(scalaVersions)
  .jsPlatform(scalaVersions)
  .nativePlatform(scalaVersions)
  .settings(docsSettings)

lazy val generate = projectMatrix
  .in(file("modules/generate"))
  .dependsOn(meta)
  .defaultAxes(default*)
  .settings(
    name := "generate"
  )
  .jvmPlatform(scalaVersions)
  .settings(noPublishing)

lazy val tracer = projectMatrix
  .in(file("modules/tracer/backend"))
  .dependsOn(lsp, tracerShared)
  .enablePlugins(JavaAppPackaging)
  .defaultAxes(default*)
  .settings(
    name                                   := "langoustine-tracer",
    libraryDependencies += "tech.neander" %%% "jsonrpclib-fs2" % V.jsonrpclib,
    libraryDependencies += "co.fs2"       %%% "fs2-io"         % V.fs2,
    libraryDependencies += "org.http4s"   %%% "http4s-ember-server" % V.http4s,
    libraryDependencies += "org.http4s"   %%% "http4s-dsl"          % V.http4s,
    libraryDependencies += "com.monovore" %%% "decline"             % V.decline,
    // embedding frontend in backend's resources
    Compile / resourceGenerators += {
      Def.task[Seq[File]] {
        val (_, location) = (ThisBuild / frontendOutput).value
        val indexFile =
          (ThisBuild / baseDirectory).value / "modules" / "tracer" / "frontend" / "index.html"
        val outDir = (Compile / resourceManaged).value / "assets"

        val indexFileContents = {
          val lines = IO.readLines(indexFile)

          val newLines = lines.collect {
            case l if l.contains("<!-- REPLACE -->") =>
              """<script type="text/javascript" src="/assets/main.js"></script>"""
            case l => l
          }

          newLines.mkString(System.lineSeparator())
        }

        IO.write(outDir / "index.html", indexFileContents)

        IO.listFiles(location).toList.map { file =>
          val (name, ext) = file.baseAndExt
          val out         = outDir / (name + "." + ext)

          IO.copyFile(file, out)

          out
        } :+ (outDir / "index.html")
      }
    }
  )
  .jvmPlatform(scalaVersions)

import org.scalajs.linker.interface.Report
lazy val frontendJS = tracerFrontend.js(V.scala)
lazy val isRelease  = sys.env.get("RELEASE").contains("yesh")

lazy val frontendOutput = taskKey[(Report, File)]("")
ThisBuild / frontendOutput := {
  if (isRelease)
    (frontendJS / Compile / fullLinkJS).value.data ->
      (frontendJS / Compile / fullLinkJS / scalaJSLinkerOutputDirectory).value
  else
    (frontendJS / Compile / fastLinkJS).value.data ->
      (frontendJS / Compile / fastLinkJS / scalaJSLinkerOutputDirectory).value
}

lazy val tracerFrontend = projectMatrix
  .in(file("modules/tracer/frontend"))
  .dependsOn(tracerShared)
  .defaultAxes(default*)
  .settings(
    name                                := "langoustine-tracer-frontend",
    libraryDependencies += "com.raquo" %%% "laminar" % V.laminar,
    scalaJSUseMainModuleInitializer     := true
  )
  .jsPlatform(scalaVersions)

lazy val tracerShared = projectMatrix
  .in(file("modules/tracer/shared"))
  .defaultAxes(default*)
  .settings(
    name := "langoustine-tracer-shared",
    libraryDependencies ++= Seq(
      "com.github.plokhotnyuk.jsoniter-scala" %%% "jsoniter-scala-core" % "2.17.0",
      "com.github.plokhotnyuk.jsoniter-scala" %%% "jsoniter-scala-macros" % "2.17.0" % "compile-internal",
      "tech.neander" %%% "jsonrpclib-core" % V.jsonrpclib
    )
  )
  .jsPlatform(scalaVersions)
  .jvmPlatform(scalaVersions)

lazy val tracerTests = projectMatrix
  .in(file("modules/tracer/tests"))
  .defaultAxes(default*)
  .dependsOn(tracer)
  .settings(
    libraryDependencies += "org.http4s" %%% "http4s-ember-client" % V.http4s % Test,
    libraryDependencies += "com.disneystreaming" %% "weaver-cats" % "0.7.15" % Test,
    libraryDependencies += "org.http4s" %% "http4s-jdk-http-client" % "0.7.0" % Test,
    testFrameworks += new TestFramework("weaver.framework.CatsEffect")
  )
  .jvmPlatform(scalaVersions)
  .settings(noPublishing)

lazy val docs = projectMatrix
  .in(file("docs"))
  .dependsOn(lsp)
  .jvmPlatform(scalaVersions)
  .defaultAxes(default*)
  .settings(noPublishing)

val scalafixRules = Seq(
  "OrganizeImports",
  "DisableSyntax",
  "LeakingImplicitClassVal",
  "ProcedureSyntax",
  "NoValInForComprehension"
).mkString(" ")

val CICommands = Seq(
  "clean",
  "compile",
  "test",
  /* "checkDocs", */
  "scalafmtCheckAll"
  /* s"scalafix --check $scalafixRules", */
  /* "headerCheck" */
).mkString(";")

val PrepareCICommands = Seq(
  /* s"Compile/scalafix --rules $scalafixRules", */
  /* s"Test/scalafix --rules $scalafixRules", */
  "Test/scalafmtAll",
  "Compile/scalafmtAll",
  "scalafmtSbt"
  /* "headerCreate" */
).mkString(";")

addCommandAlias(
  "generateLSP",
  "generate/runMain langoustine.generate.run modules/lsp/src/main/scala/generated/"
)
addCommandAlias("ci", CICommands)
addCommandAlias("checkDocs", "docs/mdoc")
addCommandAlias("preCI", PrepareCICommands)

import sbtwelcome.*

logo :=
  raw"""
    |    _                                       _   _            
    |   | |                                     | | (_)           
    |   | |     __ _ _ __   __ _  ___  _   _ ___| |_ _ _ __   ___ 
    |   | |    / _` | '_ \ / _` |/ _ \| | | / __| __| | '_ \ / _ \
    |   | |___| (_| | | | | (_| | (_) | |_| \__ \ |_| | | | |  __/
    |   |______\__,_|_| |_|\__, |\___/ \__,_|___/\__|_|_| |_|\___|
    |                       __/ |                                 
    |                      |___/                                  
    |
    |${version.value}
    |
    |${scala.Console.YELLOW}Scala ${V.scala}${scala.Console.RESET}
    |
    |""".stripMargin

usefulTasks := Seq(
  UsefulTask("a", "generateLSP", "Regenerate LSP definitions"),
  UsefulTask("a", "checkDocs", "Check documentation compiles"),
  UsefulTask("b", "preCI", "Reformat and apply Scalafix rules"),
  UsefulTask(
    "c",
    "publishLocal",
    "Publish all modules locally"
  )
)

logoColor := scala.Console.MAGENTA

lazy val docsSettings = Seq(
  Compile / doc / scalacOptions ++= Seq(
    "-project",
    "Langoustine",
    "-siteroot",
    "docs",
    "-project-version",
    version.value,
    /* "-project-logo", */
    /* "docs/logo.svg", */
    "-social-links:" +
      "github::https://github.com/neandertech/langoustine",
    "-project-footer",
    s"Copyright (c) 2022, Neandertech",
    "-source-links:github://neandertech/langoustine",
    "-revision",
    "master"
  ),
  Compile / doc := {
    val out = (Compile / doc).value
    IO.copyDirectory((Compile / doc / target).value, file("website"))
    out
  }
)
