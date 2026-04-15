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
package aliases

import langoustine.*
import runtime.{*, given}
import io.circe.*
import scala.reflect.*

/** Inline value information can be provided by different means:
  *   - directly as a text value (class InlineValueText).
  *   - as a name to use for a variable lookup (class InlineValueVariableLookup)
  *   - as an evaluatable expression (class InlineValueEvaluatableExpression)
  *     The InlineValue types combines all inline value types into one type.
  *
  * @since 3.17.0
  */
opaque type InlineValue =
  (structures.InlineValueText | structures.InlineValueVariableLookup |
    structures.InlineValueEvaluatableExpression)
object InlineValue extends codecs.aliases_InlineValue:
  inline def apply(v: structures.InlineValueText): InlineValue           = v
  inline def apply(v: structures.InlineValueVariableLookup): InlineValue = v
  inline def apply(
      v: structures.InlineValueEvaluatableExpression
  ): InlineValue = v

  extension (v: InlineValue)
    inline def value
        : (structures.InlineValueText | structures.InlineValueVariableLookup |
          structures.InlineValueEvaluatableExpression) = v

  given Typeable[InlineValue] with
    def unapply(s: Any): Option[s.type & InlineValue] =
      s match
        case c: structures.InlineValueText =>
          Some(c.asInstanceOf[s.type & structures.InlineValueText])
        case c: structures.InlineValueVariableLookup =>
          Some(c.asInstanceOf[s.type & structures.InlineValueVariableLookup])
        case c: structures.InlineValueEvaluatableExpression =>
          Some(
            c.asInstanceOf[s.type & structures.InlineValueEvaluatableExpression]
          )
        case _ => Option.empty
  end given
end InlineValue
