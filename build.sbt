Global / excludeLintKeys += logManager
Global / excludeLintKeys += scalaJSUseMainModuleInitializer
Global / excludeLintKeys += scalaJSLinkerConfig

inThisBuild(
  List(
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
  val scala           = "3.3.3"
  val scribe          = "3.13.1"
  val upickle         = "2.0.0"
  val cats            = "2.10.0"
  val jsonrpclib      = "0.0.7"
  val fs2             = "3.10.0"
  val http4s          = "0.23.26"
  val laminar         = "0.14.5"
  val decline         = "2.4.1"
  val jsoniter        = "2.20.3"
  val weaver          = "0.8.4"
  val circe           = "0.14.5"
  val http4sJdkClient = "0.9.1"
  val organizeImports = "0.6.0"
  val fansi           = "0.4.0"
  val detective       = "0.0.2"

  val scalaVersions = List(scala)

  val default =
    Seq(VirtualAxis.scalaABIVersion(scala), VirtualAxis.jvm)

  val LSP_PROTOCOL = "3.17"
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
  .aggregate(`e2e-tests`.projectRefs*)
  .settings(noPublishing)

lazy val docs = project
  .in(file("target/docs"))
  .settings(scalaVersion := V.scala)
  .settings(docsSettings)
  .dependsOn(app.jvm(V.scala), lsp.jvm(V.scala))
  .aggregate(app.jvm(V.scala), lsp.jvm(V.scala))
  .settings(
    ScalaUnidoc / unidoc / unidocProjectFilter := inProjects(
      app.jvm(V.scala),
      lsp.jvm(V.scala)
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
  .jvmPlatform(V.scalaVersions)
  .jsPlatform(V.scalaVersions)
  .nativePlatform(V.scalaVersions)
  .settings(
    libraryDependencies += "com.outr"      %%% "scribe"    % V.scribe,
    libraryDependencies += "com.lihaoyi"   %%% "upickle"   % V.upickle,
    libraryDependencies += "org.typelevel" %%% "cats-core" % V.cats,
    test                                    := {}
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
    test                                    := {}
  )
  .jvmPlatform(V.scalaVersions)
  .jsPlatform(V.scalaVersions)
  .nativePlatform(V.scalaVersions)
  .settings(
    tastyMiMaPreviousArtifacts += {
      organization.value %% name.value % "0.0.21"
    }
  )

lazy val app = projectMatrix
  .in(file("modules/app"))
  .dependsOn(lsp)
  .defaultAxes(V.default*)
  .settings(enableSnapshots)
  .settings(
    name                                   := "langoustine-app",
    libraryDependencies += "tech.neander" %%% "jsonrpclib-fs2" % V.jsonrpclib,
    libraryDependencies += "co.fs2"       %%% "fs2-io"         % V.fs2,
    libraryDependencies += "com.outr"     %%% "scribe-cats"    % V.scribe,
    test                                   := {}
  )
  .jvmPlatform(V.scalaVersions)
  .jsPlatform(V.scalaVersions)
  .nativePlatform(V.scalaVersions)

lazy val `e2e-tests` = projectMatrix
  .in(file("modules/e2e-tests"))
  .dependsOn(app, example)
  .defaultAxes(V.default*)
  .settings(enableSnapshots)
  .jvmPlatform(
    V.scalaVersions,
    Seq.empty,
    _.dependsOn(tracer.jvm(V.scala))
  )
  .settings(noPublishing)
  .settings(
    libraryDependencies += "org.http4s" %% "http4s-jdk-http-client" % V.http4sJdkClient % Test,
    libraryDependencies += "com.disneystreaming" %%% "weaver-cats" % V.weaver % Test,
    Test / fork := virtualAxes.value.contains(VirtualAxis.jvm),
    Test / envVars := Map(
      "EXAMPLE_NATIVE" -> (example.native(
        V.scala
      ) / Compile / nativeLink).value.toString,
      "EXAMPLE_JVM" -> (example.jvm(
        V.scala
      ) / Compile / assembly).value.toString,
      "EXAMPLE_JS" -> (example.js(
        V.scala
      ) / Compile / fastOptJS).value.data.toString
    )
  )

lazy val tests = projectMatrix
  .in(file("modules/tests"))
  .dependsOn(app)
  .defaultAxes(V.default*)
  .settings(enableSnapshots)
  .jvmPlatform(
    V.scalaVersions,
    Seq.empty,
    _.dependsOn(tracer.jvm(V.scala))
  )
  .jsPlatform(V.scalaVersions)
  .nativePlatform(V.scalaVersions)
  .settings(noPublishing)
  .settings(
    libraryDependencies += "org.http4s" %% "http4s-jdk-http-client" % V.http4sJdkClient % Test,
    libraryDependencies += "com.disneystreaming" %%% "weaver-cats" % V.weaver % Test,
    libraryDependencies += "com.lihaoyi" %%% "pprint" % "0.8.1" % Test,
    libraryDependencies += "org.typelevel" %%% "shapeless3-deriving" % "3.4.1" % Test,
    libraryDependencies += "org.scalacheck" %%% "scalacheck" % "1.17.0" % Test,
    libraryDependencies += "io.github.irevive" %%% "union-derivation-core" % "0.1.0" % Test,
    scalaJSLinkerConfig ~= (_.withModuleKind(ModuleKind.CommonJSModule)),
    Test / fork             := virtualAxes.value.contains(VirtualAxis.jvm),
    snapshotsPackageName    := "tests.core",
    snapshotsForceOverwrite := !sys.env.contains("CI"),
    scalacOptions += "-Yretain-trees"
  )
  .enablePlugins(SnapshotsPlugin)

lazy val example = projectMatrix
  .in(file("modules/example"))
  .dependsOn(app)
  .defaultAxes(V.default*)
  .settings(enableSnapshots)
  .jvmPlatform(V.scalaVersions)
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
  .settings(doc / sources := Seq.empty)
  .settings(nativeConfig ~= (_.withIncrementalCompilation(true)))

lazy val generate = projectMatrix
  .in(file("modules/generate"))
  .dependsOn(meta)
  .defaultAxes(V.default*)
  .settings(
    name := "generate"
  )
  .jvmPlatform(V.scalaVersions)
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
    libraryDependencies += "com.outr"     %%% "scribe-cats"         % V.scribe,
    libraryDependencies += "com.indoorvivants.detective" %% "platform" % V.detective,
    Compile / doc / sources := Seq.empty,
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
  .jvmPlatform(V.scalaVersions)

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
    Compile / doc / sources               := Seq.empty,
    name                                  := "langoustine-tracer-frontend",
    libraryDependencies += "com.raquo"   %%% "laminar"       % V.laminar,
    libraryDependencies += "io.circe"    %%% "circe-scalajs" % V.circe,
    libraryDependencies += "com.lihaoyi" %%% "fansi"         % V.fansi,
    scalaJSUseMainModuleInitializer       := true
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
  .jvmPlatform(V.scalaVersions)

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
  "testsNative/test",
  "e2e-tests/test"
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
addCommandAlias("testE2E", "e2e-tests/test")

lazy val buildTracer = taskKey[Unit]("Build tracer and print out a path to it")

ThisBuild / buildTracer := {
  val loc = (tracer.jvm(V.scala) / stage).value / "bin" / "langoustine-tracer"

  println(s"\n\nTracer is built, use this launcher: $loc")
}

lazy val updateModelFiles = taskKey[Unit]("")
updateModelFiles := {
  def baseUrl(filename: String) =
    s"https://raw.githubusercontent.com/microsoft/language-server-protocol/gh-pages/_specifications/lsp/${V.LSP_PROTOCOL}/metaModel/$filename"

  val schemaFile = "metaModel.schema.json"
  val modelFile  = "metaModel.json"

  val schemaDestination = (ThisBuild / baseDirectory).value / schemaFile
  val modelDestination  = (ThisBuild / baseDirectory).value / modelFile

  val log = sLog.value

  log.info(
    s"Writing schema file [$schemaDestination] from URL [${baseUrl(schemaFile)}]"
  )
  IO.write(
    schemaDestination,
    scala.io.Source.fromURL(baseUrl(schemaFile)).mkString
  )

  log.info(
    s"Writing model file [$modelDestination] from URL [${baseUrl(modelFile)}]"
  )
  IO.write(
    modelDestination,
    scala.io.Source.fromURL(baseUrl(modelFile)).mkString
  )

}

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
  UsefulTask("bt", "buildTracer", "Build Tracer for local usage"),
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

Global / concurrentRestrictions += Tags.limit(
  ScalaNativePlugin.autoImport.NativeTags.Link,
  2
)
