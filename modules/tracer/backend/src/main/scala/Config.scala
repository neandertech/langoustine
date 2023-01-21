/*
 * Copyright 2022 Neandertech
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package langoustine.tracer

import cats.implicits.*
import com.monovore.decline.*
import cats.data.NonEmptyList
import com.comcast.ip4s.*

case class ReplayConfig(file: fs2.io.file.Path)
case class TraceConfig(cmd: NonEmptyList[String])

enum Mode:
  case Replay(config: ReplayConfig)
  case Trace(config: TraceConfig)

case class BindConfig(port: Port, host: Host)

case class Config(mode: Mode, bind: BindConfig, debug: Boolean)

object Config:
  /** Create a config instance with all optional parameters set to their default
    * values
    *
    * @param lspCommand
    * @return
    *   config
    */
  def trace(lspCommand: NonEmptyList[String]): Config =
    Config(Mode.Trace(TraceConfig(lspCommand)), Defaults.bind, false)

  def replay(path: fs2.io.file.Path): Config =
    Config(
      Mode.Replay(ReplayConfig(path)),
      Defaults.bind,
      false
    )

  object Defaults:
    val port = port"0"
    val host = host"localhost"
    val bind = BindConfig(port, host)

  export params.command

  private object params:
    val portOpt =
      Opts
        .option[Int](
          "port",
          "Port to bind tracer to - by default a random port will be selected"
        )
        .mapValidated { portNumber =>
          Port.fromInt(portNumber).toValidNel("Invalid port number")
        }
        .withDefault(Defaults.port)

    val hostOpt =
      Opts
        .option[String](
          "host",
          s"Host to bind tracer to - by default ${Defaults.host} is used"
        )
        .mapValidated { hostRaw =>
          Host.fromString(hostRaw).toValidNel("Invalid host")
        }
        .withDefault(Defaults.host)

    val lspOpt = Opts.arguments[String]("lspCommand")
    val pathOpt = Opts.argument[String]("tracer snapshot file").map { raw =>
      fs2.io.file.Path(raw)
    }

    val debugOpt =
      Opts.flag("verbose", short = "v", help = "Enable debug logging").orFalse

    val traceCommand = Command(
      name = "trace",
      header =
        "Tracing mode - runs your LSP, proxies all the requests,\n and gives you a pretty UI to look at"
    )(lspOpt)
      .map(TraceConfig.apply)
      .map(Mode.Trace(_))

    val replayCommand = Command(
      name = "replay",
      header =
        "Replay a tracer snapshot (usually a `*.jsonl.gz` file in the interface"
    )(pathOpt)
      .map(ReplayConfig.apply)
      .map(Mode.Replay(_))

    val modeCommand =
      Opts.subcommands(traceCommand, replayCommand).orElse(traceCommand.options)

    val bind = (portOpt, hostOpt).mapN(BindConfig.apply)

    val config = (modeCommand, bind, debugOpt).mapN(Config.apply)

    val command = Command(
      name = "tracer",
      header = "Langoustine Tracer"
    ) {
      config
    }
  end params
end Config
