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
    val host = host"localhost"

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
