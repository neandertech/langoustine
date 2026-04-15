package langoustine.generate

import langoustine.meta.*
import langoustine.generate.StructureRenderConfig.PrivateCodecs
import cats.syntax.all
import langoustine.meta.Type.OrType
import scala.util.boundary

class Render(manager: Manager, packageName: String = "langoustine.lsp"):
  private val INDENT = "  "
  import Render.*
  import Types.*
  import Type.*

  def codecsPrelude(out: LineBuilder)(using Config): Unit =
    inline def line: Config ?=> Appender = to(out)
    line(s"package $packageName")
    line(s"package codecs")
    line("import io.circe.{Decoder, Encoder, KeyDecoder, KeyEncoder, Json}")
    line("import aliases.*")
    line("import enumerations.*")
    line("import structures.*")
    // line("import json.{*, given}")
    line("import runtime.{*, given}")
  end codecsPrelude

  def exports(out: LineBuilder)(using Config): Unit =
    inline def line: Config ?=> Appender = to(out)
    line(s"package $packageName")
    line(s"package all")
    line("")

    line("// Structures")
    manager.structures
      .filterNot(_.proposed)
      .foreach: struct =>
        line(s"export structures.${struct.name}")

    line("// Aliases")
    manager.aliases
      .filterNot(_.proposed)
      .foreach: struct =>
        line(s"export aliases.${struct.name}")

    line("// Enumerations")
    manager.enumerations
      .filterNot(_.proposed)
      .foreach: struct =>
        line(s"export enumerations.${struct.name}")

    val runtime = List("DocumentUri", "Uri", "uinteger", "Opt")

    line("// runtime")
    runtime.foreach: struct =>
      line(s"export runtime.$struct")

    val requests = List(
      "LSPRequest",
      "LSPNotification",
      "CustomRequest",
      "CustomNotification"
    )

    line("// request base classes")
    requests.foreach: struct =>
      line(s"export requests.$struct")

    val bases =
      (manager.requests.map(_.method).flatMap(_.value.split("/").headOption) ++
        manager.notifications
          .map(_.method)
          .flatMap(_.value.split("/").headOption)).distinct.collect:
        case "$"   => "$DOLLAR"
        case other => other

    line("// request base scopes")
    bases.foreach: struct =>
      line(s"export requests.$struct")

  end exports


  def requests(
      out: LineBuilder,
      codecsOut: LineBuilder
  )(using
      Config
  ): Unit = renderRequests(manager, packageName, out, codecsOut)


  def structures(
      codecsOut: LineBuilder
  )(using Config): Iterator[(String, LineBuilder)] =

    given Context = Context.global(manager, List("structures"))

    manager.structures
      .filterNot(_.proposed)
      .sortBy(_.name.value)
      .iterator
      .map { s =>
        val out = LineBuilder()
        inline def line: Config ?=> Appender = to(out)

        line("// format:off")
        line(s"package $packageName")
        line(s"package structures")
        line("")
        line("import langoustine.*")
        line("import runtime.{*, given}")
        line("")
        renderStructure(manager, s, out, codecsOut)

        ("structures/" + s.name.value + ".scala", out)
      }
  end structures

  def aliases(
      codecsOut: LineBuilder
  )(using Config): Iterator[(String, LineBuilder)] =
    given ctx: Context = Context.global(manager, List("aliases"))

    manager.aliases
      .filter(!_.proposed)
      .sortBy(_.name.value)
      .iterator
      .map: alias =>
        val out                              = LineBuilder()
        inline def line: Config ?=> Appender = to(out)

        line("// format:off")
        line(s"package $packageName")
        line(s"package aliases")
        line("")
        line("import langoustine.*")
        line("import runtime.{*, given}")
        line("import io.circe.*")
        line("import scala.reflect.*")
        line("")

        renderAlias(manager, out, codecsOut, packageName, alias)

        ("aliases/" + alias.name.value + ".scala", out)
  end aliases

  def enumerations()(using
      Config
  ) =

    manager.enumerations.filterNot(_.proposed).iterator.map { a =>

      val out = LineBuilder()

      inline def line: Config ?=> Appender = to(out)

      line(s"package $packageName")
      line(s"package enumerations")
      line("")
      line("import runtime.{*, given}")
      line("import io.circe.*")
      line("import scala.reflect.Typeable")
      line("import scala.annotation.switch")
      line("")

      given ctx: Context = Context.global(manager, List("enumerations"))

      renderEnumeration(a, out)

      ("enumerations/" + a.name.value + ".scala", out)
    }
  end enumerations

end Render

case class StructureRenderConfig(privateCodecs: PrivateCodecs)
object StructureRenderConfig:
  inline def default = StructureRenderConfig(privateCodecs = PrivateCodecs.No)

  opaque type PrivateCodecs = Boolean
  object PrivateCodecs extends YesNo[PrivateCodecs]
