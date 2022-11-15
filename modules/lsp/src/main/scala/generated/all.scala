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

package langoustine.lsp 

object all:
  export requests.*
  export structures.*
  export aliases.*
  export enumerations.*

  type DocumentUri = runtime.DocumentUri
  val DocumentUri = runtime.DocumentUri

  type Uri = runtime.Uri
  val Uri = runtime.Uri

  type uinteger = runtime.uinteger
  val uinteger = runtime.uinteger

  type Opt[+A] = runtime.Opt[A]
  val Opt = runtime.Opt
