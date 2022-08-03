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
    organization               := "com.neandertech.langoustine",
    organizationName           := "Neandertech",
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

organization        := "com.neandertech.langoustine"
sonatypeProfileName := "com.neandertech"

val V = new {
  val scala      = "3.1.3"
  val scribe     = "3.10.1"
  val upickle    = "2.0.0"
  val cats       = "2.8.0"
  val verify     = "1.0.0"
  val jsonrpclib = "0.0.1"
}

lazy val publishing = Seq(
  organization        := "com.neandertech.langoustine",
  sonatypeProfileName := "com.neandertech"
)

lazy val noPublishing = Seq(
  publish / skip := true
)

val scalaVersions = List(V.scala)

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
    libraryDependencies += "com.outr"      %%% "scribe"    % V.scribe,
    libraryDependencies += "com.lihaoyi"   %%% "upickle"   % V.upickle,
    libraryDependencies += "org.typelevel" %%% "cats-core" % V.cats
  )

lazy val lsp = projectMatrix
  .in(file("modules/lsp"))
  .settings(publishing)
  .settings(
    Compile / doc / sources := Seq.empty,
    name                    := "lsp",
    scalacOptions ++= Seq("-Xmax-inlines", "64"),
    libraryDependencies += "com.eed3si9n.verify" %%% "verify" % V.verify % Test,
    testFrameworks += new TestFramework("verify.runner.Framework"),
    Test / fork := virtualAxes.value.contains(VirtualAxis.jvm),
    libraryDependencies += "com.outr"      %%% "scribe"    % V.scribe,
    libraryDependencies += "com.lihaoyi"   %%% "upickle"   % V.upickle,
    libraryDependencies += "org.typelevel" %%% "cats-core" % V.cats,
    libraryDependencies += "com.neandertech" %%% "jsonrpclib-core" % V.jsonrpclib
  )
  .jvmPlatform(scalaVersions)
  .jsPlatform(scalaVersions)
  .nativePlatform(scalaVersions)

lazy val sample = projectMatrix
  .in(file("modules/sample"))
  .dependsOn(lsp)
  .settings(noPublishing)
  .settings(
    name                                          := "sample",
    libraryDependencies += "com.eed3si9n.verify" %%% "verify" % V.verify % Test,
    testFrameworks += new TestFramework("verify.runner.Framework")
  )
  .jvmPlatform(scalaVersions)
  .jsPlatform(scalaVersions)
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
