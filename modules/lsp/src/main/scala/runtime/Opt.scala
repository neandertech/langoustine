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
package runtime

import scala.reflect.TypeTest

import langoustine.*

@deprecated(
  "Opt no longer exists in Langoustine - use Option instead. This alias is left for convenience and will be removed in 0.1.0",
  "0.1.0"
)
type Opt[+A] = Option[A]

@deprecated(
  "Opt no longer exists in Langoustine - use Option instead. This forwarder is left for convenience and will be removed in 0.1.0",
  "0.1.0"
)
val Opt = Option

extension [A](o: Opt.type) 
  
  @deprecated(
    "Opt no longer exists in Langoustine - use Option instead. This forwarder is left for convenience and will be removed in 0.1.0",
    "0.1.0"
  )
  inline def fromOption(opt: Option[A]) = opt
extension [A](o: Opt[A]) 
  @deprecated(
    "Opt no longer exists in Langoustine - use Option instead. This forwarder is left for convenience and will be removed in 0.1.0",
    "0.1.0"
  )
  inline def toOption                     = o
