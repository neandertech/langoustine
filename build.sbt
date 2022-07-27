Global / excludeLintKeys += logManager
Global / excludeLintKeys += scalaJSUseMainModuleInitializer
Global / excludeLintKeys += scalaJSLinkerConfig
Global / onChangedBuildSource := ReloadOnSourceChanges

inThisBuild(
  List(
    scalafixDependencies += "com.github.liancheng" %% "organize-imports" % "0.6.0",
    semanticdbEnabled          := true,
    semanticdbVersion          := scalafixSemanticdb.revision,
    scalafixScalaBinaryVersion := scalaBinaryVersion.value,
    organization               := "com.indoorvivants",
    organizationName           := "Anton Sviridov",
    homepage := Some(
      url("https://github.com/indoorvivants/scala-library-template")
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

val Scala3        = "3.1.3"
val scalaVersions = List(Scala3)

lazy val root = project
  .aggregate(core.projectRefs*)

lazy val core = projectMatrix
  .in(file("modules/core"))
  .settings(
    name := "core"
  )
  .jvmPlatform(scalaVersions)
  /* .jsPlatform(scalaVersions) */
  /* .nativePlatform(scalaVersions) */
  .settings(
    libraryDependencies += "com.outr"      %%% "scribe"    % "3.10.1",
    libraryDependencies += "com.lihaoyi"   %%% "upickle"   % "2.0.0",
    libraryDependencies += "org.typelevel" %%% "cats-core" % "2.8.0"
  )

lazy val lsp = projectMatrix
  .in(file("modules/lsp"))
  .dependsOn(core)
  .settings(
    name := "lsp",
    scalacOptions ++= Seq("-Xmax-inlines", "64")
  )
  .jvmPlatform(scalaVersions)
/* .jsPlatform(scalaVersions) */
/* .nativePlatform(scalaVersions) */

lazy val generate = projectMatrix
  .in(file("modules/generate"))
  .dependsOn(core)
  .settings(
    name := "generate",
    scalacOptions ++= Seq("-Xmax-inlines", "64")
  )
  .jvmPlatform(scalaVersions)

lazy val docs = projectMatrix
  .in(file("myproject-docs"))
  .jvmPlatform(scalaVersions)
  .settings(
    mdocVariables := Map(
      "VERSION" -> version.value
    )
  )
  .dependsOn(core)
  .enablePlugins(MdocPlugin)

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
  "docs/mdoc",
  "core/scalafmtCheckAll",
  s"core/scalafix --check $scalafixRules",
  "core/headerCheck"
).mkString(";")

val PrepareCICommands = Seq(
  s"core/compile:scalafix --rules $scalafixRules",
  s"core/test:scalafix --rules $scalafixRules",
  "core/test:scalafmtAll",
  "core/compile:scalafmtAll",
  "core/scalafmtSbt",
  "core/headerCreate"
).mkString(";")

addCommandAlias("ci", CICommands)

addCommandAlias("preCI", PrepareCICommands)
