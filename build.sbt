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
  val scala      = "3.1.3"
  val scribe     = "3.10.1"
  val upickle    = "2.0.0"
  val cats       = "2.8.0"
  val verify     = "1.0.0"
  val jsonrpclib = "0.0.1"
}

lazy val noPublishing = Seq(
  publish / skip      := true,
  publishLocal / skip := true
)

val scalaVersions = List(V.scala)

val default = Seq(VirtualAxis.scalaABIVersion(V.scala), VirtualAxis.jvm)

lazy val root = project
  .in(file("."))
  .aggregate((meta.projectRefs ++ lsp.projectRefs)*)
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
    Compile / doc / sources := {
      if (!virtualAxes.value.contains(VirtualAxis.jvm)) Seq.empty
      else (Compile / doc / sources).value
    },
    Compile / doc / target := (ThisBuild / baseDirectory).value / "website" / "api",
    Compile / doc / scalacOptions ++= {
      Seq("-project", "Langoustine")
    },
    scalacOptions ++= Seq("-Xmax-inlines", "64"),
    libraryDependencies += "com.eed3si9n.verify" %%% "verify" % V.verify % Test,
    testFrameworks += new TestFramework("verify.runner.Framework"),
    Test / fork := virtualAxes.value.contains(VirtualAxis.jvm),
    libraryDependencies += "com.outr"      %%% "scribe"          % V.scribe,
    libraryDependencies += "com.lihaoyi"   %%% "upickle"         % V.upickle,
    libraryDependencies += "org.typelevel" %%% "cats-core"       % V.cats,
    libraryDependencies += "tech.neander"  %%% "jsonrpclib-core" % V.jsonrpclib
  )
  .jvmPlatform(scalaVersions)
  .jsPlatform(scalaVersions)
  .nativePlatform(scalaVersions)

lazy val generate = projectMatrix
  .in(file("modules/generate"))
  .dependsOn(meta)
  .defaultAxes(default*)
  .settings(
    name := "generate"
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
