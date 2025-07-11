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

package tests.core

import weaver.Expectations

// This is a sample integration for Weaver
trait WeaverSnapshotsIntegration:
  self: weaver.FunSuite =>

  def expectSnapshot(name: String, contents: String): Expectations =
    Snapshots.read(name) match
      case None =>
        Snapshots.write(name, contents)
        success

      case Some(value) =>
        if contents != value then
          if !Snapshots.forceOverwrite then
            Snapshots.recordChanges(name, contents, "<no diff available>")
            failure("Snapshots had different contents")
          else
            Snapshots.write(name, contents)
            success
        else
          Snapshots.clearChanges(name)
          success

  def assertSnapshotContents(
      name: String,
      check: String => Expectations
  ): Expectations =
    Snapshots.read(name) match
      case None =>
        failure(s"Snapshot $name not found")

      case Some(value) =>
        check(value)

end WeaverSnapshotsIntegration
