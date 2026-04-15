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

/** Moniker definition to match LSIF 0.5 moniker definition.
  *
  * @since 3.16.0
  *
  * @param scheme
  *   The scheme of the moniker. For example tsc or .Net
  *
  * @param identifier
  *   The identifier of the moniker. The value is opaque in LSIF however schema
  *   owners are allowed to define the structure if they want.
  *
  * @param unique
  *   The scope in which the moniker is unique
  *
  * @param kind
  *   The moniker kind if known.
  */
case class Moniker(
    scheme: String,
    identifier: String,
    unique: enumerations.UniquenessLevel,
    kind: Option[enumerations.MonikerKind] = None
)
object Moniker extends codecs.structures_MonikerCodec
