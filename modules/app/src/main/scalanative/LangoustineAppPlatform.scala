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

package langoustine.lsp.app

import cats.effect.IO
import scala.concurrent.duration.*
import fs2.{Chunk, Stream}

import scalanative.posix
import scalanative.libc
import scalanative.unsigned.*
import scala.scalanative.runtime.ByteArray
import fs2.Pull
import langoustine.lsp.app.LangoustineApp.Shutdown

private[app] trait LangoustineAppPlatform:
  self: LangoustineApp.Config =>

  /** The size (in bytes) of the input buffer
    *
    * @return
    */
  def inBufferSize: Int = 512

  /** Overridable duration to wait if the input stream doesn't have bytes
    * available. On Scala Native it's _very_ important to have non-zero wait
    * time otherwise the application will block and not send any output out.
    *
    * A common symptom of issues with this value is application hanging after
    * receiving first payload.
    *
    * @return
    *   duration of pause when non-blocking STDIN raises an error
    */
  def stdinDebounceRate: FiniteDuration = 50.millis

  private val enableNonBlocking =
    IO(posix.fcntl.fcntl(0, posix.fcntl.F_SETFL, posix.fcntl.O_NONBLOCK).toByte)

  def in: fs2.Stream[cats.effect.IO, Byte] =
    fs2.Stream.eval(enableNonBlocking) >>
      fs2.io
        .stdin[IO](inBufferSize)
        .chunks
        .attempts(fs2.Stream.constant(stdinDebounceRate))
        .collect { case Right(ch) =>
          ch
        }
        .unchunks
end LangoustineAppPlatform
