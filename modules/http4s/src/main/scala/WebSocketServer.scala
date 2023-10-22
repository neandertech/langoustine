package langoustine.http4s

import org.http4s.websocket.WebSocketFrame
import cats.effect.Concurrent
import fs2.Stream
import fs2.Pipe
import langoustine.lsp.LSPBuilder
import jsonrpclib.Channel
import jsonrpclib.fs2.FS2Channel
import cats.effect.kernel.Deferred
import cats.syntax.all.*
import jsonrpclib.fs2.*
import jsonrpclib.fs2.given
import jsonrpclib.Payload
import scodec.bits.ByteVector

object WebSocketServer:
  def make[F[_]: Concurrent](
      builder: LSPBuilder[F],
      bufferSize: Int
  ): Pipe[F, WebSocketFrame, WebSocketFrame] = inputFrames =>
    Stream
      .eval(Deferred[F, Boolean])
      .flatMap { latch =>
        FS2Channel(bufferSize, None)
          .flatMap { channel =>
            Stream.eval(builder.bind(channel, latch.complete(true).void))
          }
          .flatMap { channel =>
            channel.output
              .map(p => WebSocketFrame.Text(ByteVector(p.array)))
              .concurrently(
                inputFrames
                  .map(frame => Payload(frame.data.toArray))
                  .through(channel.input)
              )
              .interruptWhen(Stream.eval(latch.get))

          }
      }

end WebSocketServer
