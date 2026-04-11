package jsonrpclib

import scala.concurrent.Future
import scala.concurrent.ExecutionContext

trait Monadic[F[_]] {
  def doFlatMap[A, B](fa: F[A])(f: A => F[B]): F[B]
  def doPure[A](a: A): F[A]
  def doAttempt[A](fa: F[A]): F[Either[Throwable, A]]
  def doRaiseError[A](e: Throwable): F[A]
}

object Monadic {
  implicit def monadicFuture(implicit ec: ExecutionContext): Monadic[Future] = new Monadic[Future] {
    def doFlatMap[A, B](fa: Future[A])(f: A => Future[B]): Future[B] = fa.flatMap(f)

    def doPure[A](a: A): Future[A] = Future.successful(a)

    def doAttempt[A](fa: Future[A]): Future[Either[Throwable, A]] = fa.map(Right(_)).recover(Left(_))

    def doRaiseError[A](e: Throwable): Future[A] = Future.failed(e)
  }
}
