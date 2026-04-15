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

/** Provide an inline value through an expression evaluation. If only a range is
  * specified, the expression will be extracted from the underlying document. An
  * optional expression can be used to override the extracted expression.
  *
  * @since 3.17.0
  *
  * @param range
  *   The document range for which the inline value applies. The range is used
  *   to extract the evaluatable expression from the underlying document.
  *
  * @param expression
  *   If specified the expression overrides the extracted expression.
  */
case class InlineValueEvaluatableExpression(
    range: structures.Range,
    expression: Option[String] = None
)
object InlineValueEvaluatableExpression
    extends codecs.structures_InlineValueEvaluatableExpressionCodec
