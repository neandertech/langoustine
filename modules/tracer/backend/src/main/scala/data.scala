package langoustine.tracer

enum SnapshotItem:
  case Message(rm: ReceivedMessage)
  case Log(msg: LogMessage.Stderr)

  def timestamp: Long =
    this match
      case Message(rm)                   => rm.timestamp
      case Log(LogMessage.Stderr(_, ts)) => ts
end SnapshotItem

import com.github.plokhotnyuk.jsoniter_scala.core.*
import com.github.plokhotnyuk.jsoniter_scala.macros.*

object SnapshotItem:
  given JsonValueCodec[SnapshotItem] = JsonCodecMaker.make
