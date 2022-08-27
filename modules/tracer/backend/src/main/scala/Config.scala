package langoustine.tracer

import cats.implicits.*
import com.monovore.decline.*
import cats.data.NonEmptyList

case class Config(port: Int, cmd: NonEmptyList[String])

object Config:

  val portOpt =
    val portHelo = "Port to start Tracer on"
    Opts
      .option[Int]("port", portHelo)
      .withDefault(0)

  val lsp = Opts.arguments[String]("lspCommand")

  val config = (portOpt, lsp).mapN(Config.apply)

  val command = Command(
    name = "tracer",
    header = "Launch Langoustine Tracer"
  ) {
    config
  }
end Config
