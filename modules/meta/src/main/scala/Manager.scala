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

package langoustine.meta

import java.io.File
import scala.util.Using
import java.io.FileWriter
import java.nio.file.Paths

class Manager(mm: MetaModel):
  private type IdxVal = Enumeration | TypeAlias | Structure | Request |
    Notification

  val index: Map[String, IdxVal] =
    mm.enumerations.map(e => e.name.value -> e).toMap[String, IdxVal] ++
      mm.structures.map(s => s.name.value -> s).toMap ++
      mm.typeAliases.map(s => s.name.value -> s).toMap ++
      mm.requests.map(r => r.method.value -> r).toMap ++
      mm.notifications.map(r => r.method.value -> r).toMap

  export mm.{
    structures,
    enumerations,
    typeAliases as aliases,
    requests,
    notifications
  }

  def get(s: String) = index.get(s)
end Manager
