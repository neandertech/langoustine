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

enum SnapshotItem:
  case Message(rm: ReceivedMessage)
  case Log(msg: LogMessage.Stderr)

  def timestamp: Long =
    this match
      case Message(rm)                   => rm.timestamp
      case Log(LogMessage.Stderr(_, ts)) => ts
end SnapshotItem

import com.github.plokhotnyuk.jsoniter_scala.core.*
import com.github.plokhotnyuk.jsoniter_scala.macros.*

object SnapshotItem:
  given JsonValueCodec[SnapshotItem] = JsonCodecMaker.make
