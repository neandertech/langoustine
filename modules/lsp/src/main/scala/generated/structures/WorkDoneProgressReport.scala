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

/** @param kind
  * @param cancellable
  *   Controls enablement state of a cancel button.
  *
  * Clients that don't support cancellation or don't support controlling the
  * button's enablement state are allowed to ignore the property.
  *
  * @param message
  *   Optional, more detailed associated progress message. Contains
  *   complementary information to the `title`.
  *
  * Examples: "3/25 files", "project/src/module2", "node_modules/some_dep". If
  * unset, the previous progress message (if any) is still valid.
  *
  * @param percentage
  *   Optional progress percentage to display (value 100 is considered 100%). If
  *   not provided infinite progress is assumed and clients are allowed to
  *   ignore the `percentage` value in subsequent report notifications.
  *
  * The value should be steadily rising. Clients are free to ignore values that
  * are not following this rule. The value range is [0, 100].
  */
case class WorkDoneProgressReport(
    kind: "report",
    cancellable: Option[Boolean] = None,
    message: Option[String] = None,
    percentage: Option[runtime.uinteger] = None
)
object WorkDoneProgressReport
    extends codecs.structures_WorkDoneProgressReportCodec
