package tests.core

// This is a sample integration for Weaver
trait WeaverSnapshotsIntegration:
  self: weaver.FunSuite =>

  def expectSnapshot(name: String, contents: String) =
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
end WeaverSnapshotsIntegration
