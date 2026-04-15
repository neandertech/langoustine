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

/** Represents an outgoing call, e.g. calling a getter from a method or a method
  * from a constructor etc.
  *
  * @since 3.16.0
  *
  * @param to
  *   The item that is called.
  *
  * @param fromRanges
  *   The range at which this item is called. This is the range relative to the
  *   caller, e.g the item passed to
  *   {@link CallHierarchyItemProvider.provideCallHierarchyOutgoingCalls `provideCallHierarchyOutgoingCalls`}
  *   and not {@link CallHierarchyOutgoingCall.to `this.to`}.
  */
case class CallHierarchyOutgoingCall(
    to: structures.CallHierarchyItem,
    fromRanges: Vector[structures.Range]
)
object CallHierarchyOutgoingCall
    extends codecs.structures_CallHierarchyOutgoingCallCodec
