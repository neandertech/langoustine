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

import _root_.fs2 as FS2
import cats.effect.IO

private[app] trait LangoustineAppPlatform:
  self: LangoustineApp.Config =>

  def inBufferSize: Int = 512

  def in: fs2.Stream[IO, Byte] =
    FS2.io.stdin[IO](inBufferSize)
end LangoustineAppPlatform
