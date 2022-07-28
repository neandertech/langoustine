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
    organization               := "com.indoorvivants.langoustine",
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

organization        := "com.indoorvivants.langoustine"
sonatypeProfileName := "com.indoorvivants"

lazy val publishing = Seq(
  organization        := "com.indoorvivants.langoustine",
  sonatypeProfileName := "com.indoorvivants"
)

lazy val noPublishing = Seq(
  publish / skip := true
)

val Scala3        = "3.1.3"
val scalaVersions = List(Scala3)

lazy val root = project
  .aggregate((meta.projectRefs ++ lsp.projectRefs)*)
  .settings(noPublishing)

lazy val meta = projectMatrix
  .in(file("modules/meta"))
  .settings(name := "meta")
  .settings(publishing)
  .jvmPlatform(scalaVersions)
  .jsPlatform(scalaVersions)
  .nativePlatform(scalaVersions)
  .settings(
    libraryDependencies += "com.outr"      %%% "scribe"    % "3.10.1",
    libraryDependencies += "com.lihaoyi"   %%% "upickle"   % "2.0.0",
    libraryDependencies += "org.typelevel" %%% "cats-core" % "2.8.0"
  )

lazy val lsp = projectMatrix
  .in(file("modules/lsp"))
  .dependsOn(meta)
  .settings(publishing)
  .settings(
    name := "lsp",
    scalacOptions ++= Seq("-Xmax-inlines", "64"),
    libraryDependencies += "com.eed3si9n.verify" %% "verify" % "1.0.0" % Test,
    testFrameworks += new TestFramework("verify.runner.Framework")
  )
  .jvmPlatform(scalaVersions)
  .jsPlatform(scalaVersions)
  .nativePlatform(scalaVersions)

lazy val sample = projectMatrix
  .in(file("modules/sample"))
  .dependsOn(lsp)
  .settings(noPublishing)
  .settings(
    name                               := "sample",
    libraryDependencies += "com.outr" %%% "scribe-file" % "3.10.1"
  )
  .jvmPlatform(scalaVersions)
  .nativePlatform(scalaVersions)

lazy val generate = projectMatrix
  .in(file("modules/generate"))
  .dependsOn(meta)
  .settings(
    name := "generate"
  )
  .jvmPlatform(scalaVersions)
  .settings(noPublishing)

lazy val docs = projectMatrix
  .in(file("myproject-docs"))
  .jvmPlatform(scalaVersions)
  .settings(
    mdocVariables := Map(
      "VERSION" -> version.value
    )
  )
  .dependsOn(meta)
  .enablePlugins(MdocPlugin)
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
  "docs3/mdoc",
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

addCommandAlias("ci", CICommands)

addCommandAlias("preCI", PrepareCICommands)
