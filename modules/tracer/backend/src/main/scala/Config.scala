package langoustine.tracer

import cats.implicits.*
import com.monovore.decline.*
import cats.data.NonEmptyList
import com.comcast.ip4s.*

case class Config(port: Port, host: Host, cmd: NonEmptyList[String])

object Config:
  /** Create a config instance with all optional parameters set to their default
    * values
    *
    * @param lspCommand
    * @return
    *   config
    */
  def create(lspCommand: NonEmptyList[String]): Config =
    Config(Defaults.port, Defaults.host, lspCommand)

  object Defaults:
    val port = port"0"
    val host = host"0.0.0.0"

  private val portOpt =
    Opts
      .option[Int](
        "port",
        "Port to bind tracer to - by default a random port will be selected"
      )
      .mapValidated { portNumber =>
        Port.fromInt(portNumber).toValidNel("Invalid port number")
      }
      .withDefault(Defaults.port)

  private val hostOpt =
    Opts
      .option[String](
        "host",
        s"Host to bind tracer to - by default ${Defaults.host} is used"
      )
      .mapValidated { hostRaw =>
        Host.fromString(hostRaw).toValidNel("Invalid host")
      }
      .withDefault(Defaults.host)

  private val lspOpt = Opts.arguments[String]("lspCommand")

  private val config = (portOpt, hostOpt, lspOpt).mapN(Config.apply)

  val command = Command(
    name = "tracer",
    header = "Launch Langoustine Tracer"
  ) {
    config
  }
end Config
