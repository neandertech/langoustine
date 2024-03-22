package tests.core

import langoustine.lsp.*

import jsonrpclib.*

import cats.MonadThrow
import scala.util.*
import scala.annotation.tailrec
import java.util.concurrent.atomic.AtomicReference
import jsonrpclib.InputMessage.RequestMessage
import jsonrpclib.InputMessage.NotificationMessage

given [F[_]](using MonadThrow[F]): Monadic[F] with
  def doFlatMap[A, B](fa: F[A])(f: A => F[B]): F[B] =
    MonadThrow[F].flatMap(fa)(f)

  def doPure[A](a: A): F[A] = MonadThrow[F].pure(a)

  def doAttempt[A](fa: F[A]): F[Either[Throwable, A]] =
    MonadThrow[F].attempt(fa)

  def doRaiseError[A](e: Throwable): F[A] = MonadThrow[F].raiseError(e)
end given

trait RefLike[F[_], A]:
  def get: F[A]
  def update(f: A => A): F[Unit]
  def set(other: A): F[Unit] = update(_ => other)

trait RefConstructor[F[_]]:
  def apply[A](a: A): F[RefLike[F, A]]

object RefLike:
  def apply[F[_]: RefConstructor]: Constructor[F] = new Constructor[F]

  class Constructor[F[_]: RefConstructor]:
    def apply[A](a: A): F[RefLike[F, A]] = summon[RefConstructor[F]].apply(a)

import cats.syntax.all.*

import requests.*

case class CollectNotifications[F[_]: MonadThrow] private (
    sent: RefLike[F, Map[LSPNotification, List[Any]]]
) extends Communicate[F]:
  def notification[X <: LSPNotification](notif: X, in: notif.In): F[Unit] =
    sent.update { mp =>
      mp.get(notif) match
        case None      => mp.updated(notif, List(in))
        case Some(lst) => mp.updated(notif, lst :+ in)
    }

  def request[X <: LSPRequest](req: X, in: req.In): F[req.Out] =
    Communicate.drop[F].request(req, in)

  def shutdown = ???

  def collect[T <: LSPNotification](req: T): F[List[req.In]] =
    sent.get.map(_.get(req).toList.flatten.map(_.asInstanceOf[req.In]))
end CollectNotifications

object CollectNotifications:
  def create[F[_]: MonadThrow: RefConstructor]: F[CollectNotifications[F]] =
    RefLike[F].apply(Map.empty[LSPNotification, List[Any]]).map { rf =>
      new CollectNotifications[F](rf)
    }

def request[F[_]: RefConstructor: MonadThrow, T <: requests.LSPRequest](
    builder: LSPBuilder[F],
    id: CallId,
    req: T,
    in: req.In
) =
  val F = MonadThrow[F]
  CollectNotifications.create[F].flatMap { communicate =>
    builder
      .build(communicate)
      .find(_.method == req.requestMethod)
      .collectFirst {
        case Endpoint.RequestResponseEndpoint(_, run, inc, erc, outc) =>
          val encoded = Payload(upickle.default.write(in).getBytes())
          for
            lifted <- F.fromEither(inc.decode(Some(encoded)))
            msg = RequestMessage(
              method = req.requestMethod,
              callId = id,
              params = Some(encoded)
            )
            res <- run(msg, lifted).flatMap {
              case Left(err) => F.raiseError(erc.encode(err))
              case Right(res) =>
                F.catchNonFatal {
                  upickle.default.read[req.Out](outc.encode(res).array)
                }
            }
          yield res -> communicate
          end for
      }
      .getOrElse(
        F.raiseError(
          new Throwable(s"Method ${req.requestMethod} is not handled")
        )
      )
  }
end request

def notification[F[
    _
]: RefConstructor: MonadThrow, T <: requests.LSPNotification](
    builder: LSPBuilder[F],
    req: T,
    in: req.In
): F[CollectNotifications[F]] =
  val F = MonadThrow[F]
  CollectNotifications.create[F].flatMap { communicate =>
    builder
      .build(communicate)
      .find(_.method == req.notificationMethod)
      .collectFirst { case Endpoint.NotificationEndpoint(_, run, inc) =>
        val encoded = Payload(upickle.default.write(in).getBytes())
        for
          lifted <- F.fromEither(inc.decode(Some(encoded)))
          msg = NotificationMessage(
            method = req.notificationMethod,
            params = Some(encoded)
          )
          _ <- run(msg, lifted)
        yield communicate
        end for
      }
      .getOrElse(
        F.raiseError(
          new Throwable(s"Method ${req.notificationMethod} is not handled")
        )
      )
  }
end notification

given RefConstructor[Try] with
  def apply[A](a: A): Try[RefLike[scala.util.Try, A]] = Try {
    new RefLike[Try, A]:
      var ar                   = new AtomicReference(a)
      override def get: Try[A] = Try(ar.get())
      override def update(f: A => A): Try[Unit] =
        @tailrec
        def spin(): Unit =
          val a = ar.get
          val u = f(a)
          if !ar.compareAndSet(a, u) then spin()
        Try(spin())

  }
end given
