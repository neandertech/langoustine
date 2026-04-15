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

// format:off
package langoustine.lsp
package structures

import langoustine.*
import runtime.{*, given}

/** @param globPattern
  *   The glob pattern to watch. See {@link GlobPattern glob pattern} for more
  *   detail.
  *
  * since 3.17.0 support for relative patterns.
  *
  * @param kind
  *   The kind of events of interest. If omitted it defaults to WatchKind.Create |
  *   WatchKind.Change | WatchKind.Delete which is 7.
  */
case class FileSystemWatcher(
    globPattern: aliases.GlobPattern,
    kind: Option[enumerations.WatchKind] = None
)
object FileSystemWatcher extends codecs.structures_FileSystemWatcherCodec
