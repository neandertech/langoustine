Global / excludeLintKeys += logManager
Global / excludeLintKeys += scalaJSUseMainModuleInitializer
Global / excludeLintKeys += scalaJSLinkerConfig

inThisBuild(
  List(
    scalafixDependencies += "com.github.liancheng" %% "organize-imports" % V.organizeImports,
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
  val scala           = "3.2.1"
  val scalaNightly    = "3.2.1"
  val scribe          = "3.10.5"
  val upickle         = "2.0.0"
  val cats            = "2.9.0"
  val jsonrpclib      = "0.0.4"
  val fs2             = "3.4.0"
  val http4s          = "0.23.16"
  val laminar         = "0.14.5"
  val decline         = "2.4.0"
  val jsoniter        = "2.18.0"
  val weaver          = "0.8.1"
  val http4sJdkClient = "0.7.0"
  val organizeImports = "0.6.0"

  /** TODO: remove all the nightly hacks once the deliciously decadent scaladoc
    * facelift is released (3.2.1?)
    */

  val dynScalaVersion =
    if (sys.env.contains("USE_SCALA_NIGHTLY")) scalaNightly
    else scala

  val jvmScalaVersions = List(dynScalaVersion)
  val scalaVersions    = List(scala)

  val default =
    Seq(VirtualAxis.scalaABIVersion(dynScalaVersion), VirtualAxis.jvm)
}

lazy val noPublishing = Seq(
  publish / skip      := true,
  publishLocal / skip := true
)

lazy val enableSnapshots = Seq(
  resolvers ++= Resolver
    .sonatypeOssRepos("snapshots")
    .filter(_ => !sys.env.contains("CI"))
)

lazy val root = project
  .in(file("."))
  .aggregate(meta.projectRefs*)
  .aggregate(lsp.projectRefs*)
  .aggregate(app.projectRefs*)
  .aggregate(tracer.projectRefs*)
  .aggregate(tracerShared.projectRefs*)
  .aggregate(tracerFrontend.projectRefs*)
  .aggregate(tests.projectRefs*)
  .settings(noPublishing)

lazy val docs = project
  .in(file("target/docs"))
  .settings(scalaVersion := V.dynScalaVersion)
  .settings(docsSettings)
  .dependsOn(app.jvm(V.dynScalaVersion), lsp.jvm(V.dynScalaVersion))
  .aggregate(app.jvm(V.dynScalaVersion), lsp.jvm(V.dynScalaVersion))
  .settings(
    ScalaUnidoc / unidoc / unidocProjectFilter := inAnyProject -- inProjects(
      (tracer.projectRefs ++
        tracerShared.projectRefs ++
        meta.projectRefs ++
        generate.projectRefs ++
        tracerFrontend.projectRefs)*
    ),
    Compile / unidoc := {
      val out = (Compile / unidoc).value
      val to  = (ThisBuild / baseDirectory).value / "website"
      IO.copyDirectory(out.head, to)

      sLog.value.info(s"The site is live at `$to`")

      out
    }
  )
  .enablePlugins(ScalaUnidocPlugin)

lazy val meta = projectMatrix
  .in(file("modules/meta"))
  .settings(name := "langoustine-meta")
  .defaultAxes(V.default*)
  .jvmPlatform(V.jvmScalaVersions)
  .jsPlatform(V.scalaVersions)
  .nativePlatform(V.scalaVersions)
  .settings(
    libraryDependencies += "com.outr"      %%% "scribe"    % V.scribe,
    libraryDependencies += "com.lihaoyi"   %%% "upickle"   % V.upickle,
    libraryDependencies += "org.typelevel" %%% "cats-core" % V.cats
  )

lazy val lsp = projectMatrix
  .in(file("modules/lsp"))
  .defaultAxes(V.default*)
  .settings(enableSnapshots)
  .settings(
    name := "langoustine-lsp",
    scalacOptions ++= Seq("-Xmax-inlines", "64"),
    testFrameworks += new TestFramework("weaver.framework.CatsEffect"),
    libraryDependencies += "com.disneystreaming" %%% "weaver-cats" % V.weaver % Test,
    libraryDependencies += "com.outr"      %%% "scribe"          % V.scribe,
    libraryDependencies += "com.lihaoyi"   %%% "upickle"         % V.upickle,
    libraryDependencies += "org.typelevel" %%% "cats-core"       % V.cats,
    libraryDependencies += "tech.neander"  %%% "jsonrpclib-core" % V.jsonrpclib,
    Test / fork := virtualAxes.value.contains(VirtualAxis.jvm)
  )
  .jvmPlatform(V.jvmScalaVersions)
  .jsPlatform(V.scalaVersions)
  .nativePlatform(V.scalaVersions)

lazy val app = projectMatrix
  .in(file("modules/app"))
  .dependsOn(lsp)
  .defaultAxes(V.default*)
  .settings(enableSnapshots)
  .settings(
    name                                   := "langoustine-app",
    libraryDependencies += "tech.neander" %%% "jsonrpclib-fs2" % V.jsonrpclib,
    libraryDependencies += "co.fs2"       %%% "fs2-io"         % V.fs2,
    Test / fork := virtualAxes.value.contains(VirtualAxis.jvm)
  )
  .jvmPlatform(V.jvmScalaVersions)
  .jsPlatform(V.scalaVersions)
  .nativePlatform(V.scalaVersions)

lazy val tests = projectMatrix
  .in(file("modules/tests"))
  .dependsOn(app)
  .defaultAxes(V.default*)
  .settings(enableSnapshots)
  .jvmPlatform(
    V.jvmScalaVersions,
    Seq.empty,
    _.dependsOn(tracer.jvm(V.dynScalaVersion))
  )
  .jsPlatform(V.scalaVersions)
  .nativePlatform(V.scalaVersions)
  .settings(noPublishing)
  .settings(
    libraryDependencies += "org.http4s" %% "http4s-jdk-http-client" % V.http4sJdkClient % Test,
    libraryDependencies += "com.disneystreaming" %%% "weaver-cats" % V.weaver % Test,
    testFrameworks += new TestFramework("weaver.framework.CatsEffect"),
    Test / fork := virtualAxes.value.contains(VirtualAxis.jvm),
    Test / envVars := Map(
      "EXAMPLE_NATIVE" -> (example.native(
        V.dynScalaVersion
      ) / Compile / nativeLink).value.toString,
      "EXAMPLE_JVM" -> (example.jvm(
        V.dynScalaVersion
      ) / Compile / assembly).value.toString,
      "EXAMPLE_JS" -> (example.js(
        V.dynScalaVersion
      ) / Compile / fastOptJS).value.data.toString
    )
  )

lazy val example = projectMatrix
  .in(file("modules/example"))
  .dependsOn(app)
  .defaultAxes(V.default*)
  .settings(enableSnapshots)
  .jvmPlatform(V.jvmScalaVersions)
  .jsPlatform(
    V.scalaVersions,
    Seq(
      scalaJSUseMainModuleInitializer := true,
      scalaJSLinkerConfig ~= (_.withModuleKind(ModuleKind.CommonJSModule))
    )
  )
  .nativePlatform(V.scalaVersions)
  .settings(noPublishing)
  .settings(version := "dev")

lazy val generate = projectMatrix
  .in(file("modules/generate"))
  .dependsOn(meta)
  .defaultAxes(V.default*)
  .settings(
    name := "generate"
  )
  .jvmPlatform(V.jvmScalaVersions)
  .settings(noPublishing)

lazy val tracer = projectMatrix
  .in(file("modules/tracer/backend"))
  .dependsOn(lsp, tracerShared)
  .enablePlugins(JavaAppPackaging)
  .defaultAxes(V.default*)
  .settings(enableSnapshots)
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
  .jvmPlatform(V.jvmScalaVersions)

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
  .defaultAxes(V.default*)
  .settings(
    Compile / doc / sources             := Seq.empty,
    name                                := "langoustine-tracer-frontend",
    libraryDependencies += "com.raquo" %%% "laminar" % V.laminar,
    scalaJSUseMainModuleInitializer     := true
  )
  .jsPlatform(V.scalaVersions)

lazy val tracerShared = projectMatrix
  .in(file("modules/tracer/shared"))
  .defaultAxes(V.default*)
  .settings(enableSnapshots)
  .settings(
    name := "langoustine-tracer-shared",
    libraryDependencies ++= Seq(
      "com.github.plokhotnyuk.jsoniter-scala" %%% "jsoniter-scala-core" % V.jsoniter,
      "com.github.plokhotnyuk.jsoniter-scala" %%% "jsoniter-scala-macros" % V.jsoniter % "compile-internal",
      "tech.neander" %%% "jsonrpclib-core" % V.jsonrpclib
    )
  )
  .jsPlatform(V.scalaVersions)
  .jvmPlatform(V.jvmScalaVersions)

val scalafixRules = Seq(
  "OrganizeImports",
  "DisableSyntax",
  "LeakingImplicitClassVal",
  "ProcedureSyntax",
  "NoValInForComprehension"
).mkString(" ")

val CICommands = Seq(
  "scalafmtCheckAll",
  "clean",
  "compile",
  "tests/test",
  "testsJS/test",
  "testsNative/test"
).mkString(";")

val PrepareCICommands = Seq(
  "Test/scalafmtAll",
  "Compile/scalafmtAll",
  "scalafmtSbt"
).mkString(";")

addCommandAlias(
  "generateLSP",
  "generate/runMain langoustine.generate.run modules/lsp/src/main/scala/generated/"
)
addCommandAlias("ci", CICommands)
addCommandAlias("buildWebsite", "docs/unidoc")
addCommandAlias("preCI", PrepareCICommands)
addCommandAlias("testTracer", "tests/testOnly tests.tracer.*")
addCommandAlias("testCore", "tests/testOnly tests.core.*")
addCommandAlias("testE2E", "tests/testOnly tests.e2e.*")

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
  UsefulTask("gl", "generateLSP", "Regenerate LSP definitions"),
  UsefulTask("bw", "buildWebsite", "Build website"),
  UsefulTask("tt", "testTracer", "Run Tracer's backend tests"),
  UsefulTask(
    "te",
    "testE2E",
    "Run LangoustineApp E2E tests that launch a separate process"
  ),
  UsefulTask("fx", "preCI", "Reformat and apply Scalafix rules"),
  UsefulTask(
    "p",
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
    "-Yapi-subdirectory",
    "-Ygenerate-inkuire",
    "-project-version",
    version.value,
    "-social-links:" +
      "github::https://github.com/neandertech/langoustine",
    "-project-footer",
    s"Copyright (c) 2022, Neandertech",
    "-source-links:github://neandertech/langoustine",
    "-authors",
    "-revision",
    "main",
    "-snippet-compiler:compile"
  )
)
