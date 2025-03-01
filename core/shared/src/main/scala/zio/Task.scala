package zio

import zio.internal.{ Executor, Platform }

import scala.concurrent.ExecutionContext

object Task {

  /**
   * @see See [[zio.ZIO.absolve]]
   */
  final def absolve[A](v: Task[Either[Throwable, A]]): Task[A] =
    ZIO.absolve(v)

  /**
   * @see See [[zio.ZIO.allowInterrupt]]
   */
  final def allowInterrupt: UIO[Unit] =
    ZIO.allowInterrupt

  /**
   * @see See [[zio.ZIO.apply]]
   */
  def apply[A](a: => A): Task[A] = ZIO.apply(a)

  /**
   * @see See bracket [[zio.ZIO]]
   */
  final def bracket[A](acquire: Task[A]): ZIO.BracketAcquire[Any, Throwable, A] =
    ZIO.bracket(acquire)

  /**
   * @see See bracket [[zio.ZIO]]
   */
  final def bracket[A, B](acquire: Task[A], release: A => UIO[_], use: A => Task[B]): Task[B] =
    ZIO.bracket(acquire, release, use)

  /**
   * @see See bracketExit [[zio.ZIO]]
   */
  final def bracketExit[A](acquire: Task[A]): ZIO.BracketExitAcquire[Any, Throwable, A] =
    ZIO.bracketExit(acquire)

  /**
   * @see See bracketExit [[zio.ZIO]]
   */
  final def bracketExit[A, B](
    acquire: Task[A],
    release: (A, Exit[Throwable, B]) => UIO[_],
    use: A => Task[B]
  ): Task[B] =
    ZIO.bracketExit(acquire, release, use)

  /**
   * @see See [[zio.ZIO.checkInterruptible]]
   */
  final def checkInterruptible[A](f: InterruptStatus => Task[A]): Task[A] =
    ZIO.checkInterruptible(f)

  /**
   * @see See [[zio.ZIO.checkSupervised]]
   */
  final def checkSupervised[A](f: SuperviseStatus => Task[A]): Task[A] =
    ZIO.checkSupervised(f)

  /**
   * @see See [[zio.ZIO.checkTraced]]
   */
  final def checkTraced[A](f: TracingStatus => Task[A]): Task[A] =
    ZIO.checkTraced(f)

  /**
   * @see See [[zio.ZIO.children]]
   */
  final def children: UIO[IndexedSeq[Fiber[_, _]]] = ZIO.children

  /**
   * @see See [[zio.ZIO.collectAll]]
   */
  final def collectAll[A](in: Iterable[Task[A]]): Task[List[A]] =
    ZIO.collectAll(in)

  /**
   * @see See [[zio.ZIO.collectAllPar]]
   */
  final def collectAllPar[A](as: Iterable[Task[A]]): Task[List[A]] =
    ZIO.collectAllPar(as)

  /**
   * @see See [[zio.ZIO.collectAllParN]]
   */
  final def collectAllParN[A](n: Long)(as: Iterable[Task[A]]): Task[List[A]] =
    ZIO.collectAllParN(n)(as)

  /**
   * @see See [[zio.ZIO.die]]
   */
  final def die(t: Throwable): UIO[Nothing] = ZIO.die(t)

  /**
   * @see See [[zio.ZIO.dieMessage]]
   */
  final def dieMessage(message: String): UIO[Nothing] = ZIO.dieMessage(message)

  /**
   * @see See [[zio.ZIO.done]]
   */
  final def done[A](r: Exit[Throwable, A]): Task[A] = ZIO.done(r)

  /**
   * @see See [[zio.ZIO.descriptor]]
   */
  final def descriptor: UIO[Fiber.Descriptor] = ZIO.descriptor

  /**
   * @see See [[zio.ZIO.descriptorWith]]
   */
  final def descriptorWith[A](f: Fiber.Descriptor => Task[A]): Task[A] =
    ZIO.descriptorWith(f)

  /**
   * @see See [[zio.ZIO.effect]]
   */
  final def effect[A](effect: => A): Task[A] = ZIO.effect(effect)

  /**
   * @see See [[zio.ZIO.effectAsync]]
   */
  final def effectAsync[A](register: (Task[A] => Unit) => Unit): Task[A] =
    ZIO.effectAsync(register)

  /**
   * @see See [[zio.ZIO.effectAsyncMaybe]]
   */
  final def effectAsyncMaybe[A](register: (Task[A] => Unit) => Option[Task[A]]): Task[A] =
    ZIO.effectAsyncMaybe(register)

  /**
   * @see See [[zio.ZIO.effectAsyncM]]
   */
  final def effectAsyncM[A](register: (Task[A] => Unit) => UIO[_]): Task[A] =
    ZIO.effectAsyncM(register)

  /**
   * @see See [[zio.ZIO.effectAsyncInterrupt]]
   */
  final def effectAsyncInterrupt[A](register: (Task[A] => Unit) => Either[Canceler, Task[A]]): Task[A] =
    ZIO.effectAsyncInterrupt(register)

  /**
   * @see See [[zio.ZIO.effectTotal]]
   */
  final def effectTotal[A](effect: => A): UIO[A] = ZIO.effectTotal(effect)

  /**
   * @see See [[zio.ZIO.fail]]
   */
  final def fail(error: Throwable): Task[Nothing] = ZIO.fail(error)

  /**
   * @see See [[zio.ZIO.firstSuccessOf]]
   */
  final def firstSuccessOf[A](
    task: Task[A],
    rest: Iterable[Task[A]]
  ): Task[A] =
    ZIO.firstSuccessOf(task, rest)

  /**
   * @see See [[zio.ZIO.flatten]]
   */
  final def flatten[A](task: Task[Task[A]]): Task[A] =
    ZIO.flatten(task)

  /**
   * @see See [[zio.ZIO.foldLeft]]
   */
  final def foldLeft[S, A](in: Iterable[A])(zero: S)(f: (S, A) => Task[S]): Task[S] =
    ZIO.foldLeft(in)(zero)(f)

  /**
   * @see See [[zio.ZIO.foreach]]
   */
  final def foreach[A, B](in: Iterable[A])(f: A => Task[B]): Task[List[B]] =
    ZIO.foreach(in)(f)

  /**
   * @see See [[zio.ZIO.foreachPar]]
   */
  final def foreachPar[A, B](as: Iterable[A])(fn: A => Task[B]): Task[List[B]] =
    ZIO.foreachPar(as)(fn)

  /**
   * @see See [[zio.ZIO.foreachParN]]
   */
  final def foreachParN[A, B](n: Long)(as: Iterable[A])(fn: A => Task[B]): Task[List[B]] =
    ZIO.foreachParN(n)(as)(fn)

  /**
   * @see See [[zio.ZIO.foreach_]]
   */
  final def foreach_[A](as: Iterable[A])(f: A => Task[_]): Task[Unit] =
    ZIO.foreach_(as)(f)

  /**
   * @see See [[zio.ZIO.foreachPar_]]
   */
  final def foreachPar_[A, B](as: Iterable[A])(f: A => Task[_]): Task[Unit] =
    ZIO.foreachPar_(as)(f)

  /**
   * @see See [[zio.ZIO.foreachParN_]]
   */
  final def foreachParN_[A, B](n: Long)(as: Iterable[A])(f: A => Task[_]): Task[Unit] =
    ZIO.foreachParN_(n)(as)(f)

  /**
   * @see See [[zio.ZIO.forkAll]]
   */
  final def forkAll[A](as: Iterable[Task[A]]): UIO[Fiber[Throwable, List[A]]] =
    ZIO.forkAll(as)

  /**
   * @see See [[zio.ZIO.forkAll_]]
   */
  final def forkAll_[A](as: Iterable[Task[A]]): UIO[Unit] =
    ZIO.forkAll_(as)

  /**
   * @see See [[zio.ZIO.fromEither]]
   */
  final def fromEither[A](v: => Either[Throwable, A]): Task[A] =
    ZIO.fromEither(v)

  /**
   * @see See [[zio.ZIO.fromFiber]]
   */
  final def fromFiber[A](fiber: => Fiber[Throwable, A]): Task[A] =
    ZIO.fromFiber(fiber)

  /**
   * @see See [[zio.ZIO.fromFiberM]]
   */
  final def fromFiberM[A](fiber: Task[Fiber[Throwable, A]]): Task[A] =
    ZIO.fromFiberM(fiber)

  /**
   * @see See [[zio.ZIO.fromFuture]]
   */
  final def fromFuture[A](make: ExecutionContext => scala.concurrent.Future[A]): Task[A] =
    ZIO.fromFuture(make)

  /**
   * @see See [[zio.ZIO.fromTry]]
   */
  final def fromTry[A](value: => scala.util.Try[A]): Task[A] =
    ZIO.fromTry(value)

  /**
   * @see See [[zio.ZIO.halt]]
   */
  final def halt(cause: Cause[Throwable]): Task[Nothing] = ZIO.halt(cause)

  /**
   * @see See [[zio.ZIO.haltWith]]
   */
  final def haltWith[E <: Throwable](function: (() => ZTrace) => Cause[E]): Task[Nothing] =
    ZIO.haltWith(function)

  /**
   * @see See [[zio.ZIO.interrupt]]
   */
  final val interrupt: UIO[Nothing] = ZIO.interrupt

  /**
   * @see See [[zio.ZIO.interruptible]]
   */
  final def interruptible[A](task: Task[A]): Task[A] =
    ZIO.interruptible(task)

  /**
   * @see See [[zio.ZIO.interruptibleMask]]
   */
  final def interruptibleMask[A](k: ZIO.InterruptStatusRestore => Task[A]): Task[A] =
    ZIO.interruptibleMask(k)

  /**
   *  @see See [[zio.ZIO.left]]
   */
  final def left[A](a: A): Task[Either[A, Nothing]] = ZIO.left(a)

  /**
   * @see See [[zio.ZIO.lock]]
   */
  final def lock[A](executor: Executor)(task: Task[A]): Task[A] =
    ZIO.lock(executor)(task)

  /**
   * @see See [[zio.ZIO.mergeAll]]
   */
  final def mergeAll[A, B](in: Iterable[Task[A]])(zero: B)(f: (B, A) => B): Task[B] =
    ZIO.mergeAll(in)(zero)(f)

  /**
   * @see See [[zio.ZIO.mergeAllPar]]
   */
  final def mergeAllPar[A, B](in: Iterable[Task[A]])(zero: B)(f: (B, A) => B): Task[B] =
    ZIO.mergeAllPar(in)(zero)(f)

  /**
   * @see See [[zio.ZIO.never]]
   */
  final val never: UIO[Nothing] = ZIO.never

  /**
   * @see See [[zio.ZIO.none]]
   */
  final val none: Task[Option[Nothing]] = ZIO.none

  /**
   * @see See [[zio.ZIO.raceAll]]
   */
  final def raceAll[A](task: Task[A], ios: Iterable[Task[A]]): Task[A] =
    ZIO.raceAll(task, ios)

  /**
   * @see See [[zio.ZIO.reduceAll]]
   */
  final def reduceAll[A](a: Task[A], as: Iterable[Task[A]])(f: (A, A) => A): Task[A] =
    ZIO.reduceAll(a, as)(f)

  /**
   * @see See [[zio.ZIO.reduceAllPar]]
   */
  final def reduceAllPar[A](a: Task[A], as: Iterable[Task[A]])(f: (A, A) => A): Task[A] =
    ZIO.reduceAllPar(a, as)(f)

  /**
   * @see See [[zio.ZIO.replicate]]
   */
  def replicate[A](n: Int)(effect: Task[A]): Iterable[Task[A]] =
    ZIO.replicate(n)(effect)

  /**
   * @see See [[zio.ZIO.require]]
   */
  final def require[A](error: Throwable): Task[Option[A]] => Task[A] =
    ZIO.require[Throwable, A](error)

  /**
   * @see See [[zio.ZIO.reserve]]
   */
  final def reserve[A, B](reservation: Task[Reservation[Any, Throwable, A]])(use: A => Task[B]): Task[B] =
    ZIO.reserve(reservation)(use)

  /**
   *  @see [[zio.ZIO.right]]
   */
  def right[B](b: B): Task[Either[Nothing, B]] = ZIO.right(b)

  /**
   * @see See [[zio.ZIO.runtime]]
   */
  final def runtime: UIO[Runtime[Any]] = ZIO.runtime

  /**
   * @see See [[zio.ZIO.succeed]]
   */
  final def succeed[A](a: A): UIO[A] = ZIO.succeed(a)

  /**
   * @see See [[zio.ZIO.succeedLazy]]
   */
  final def succeedLazy[A](a: => A): UIO[A] = ZIO.succeedLazy(a)

  /**
   * @see See [[zio.ZIO.supervised]]
   */
  final def supervised[A](task: Task[A]): Task[A] =
    ZIO.supervised(task)

  /**
   * @see See [[zio.ZIO.superviseStatus]]
   */
  final def superviseStatus[A](status: SuperviseStatus)(task: Task[A]): Task[A] =
    ZIO.superviseStatus(status)(task)

  /**
   * @see See [[zio.ZIO.interruptChildren]]
   */
  final def interruptChildren[A](task: Task[A]): Task[A] =
    ZIO.interruptChildren(task)

  /**
   * @see See [[zio.ZIO.handleChildrenWith]]
   */
  final def handleChildrenWith[A](task: Task[A])(supervisor: IndexedSeq[Fiber[_, _]] => UIO[_]): Task[A] =
    ZIO.handleChildrenWith(task)(supervisor)

  /**
   *  See [[zio.ZIO.sequence]]
   */
  final def sequence[A](in: Iterable[Task[A]]): Task[List[A]] =
    ZIO.sequence(in)

  /**
   *  See [[zio.ZIO.sequencePar]]
   */
  final def sequencePar[A](as: Iterable[Task[A]]): Task[List[A]] =
    ZIO.sequencePar(as)

  /**
   *  See [[zio.ZIO.sequenceParN]]
   */
  final def sequenceParN[A](n: Long)(as: Iterable[Task[A]]): Task[List[A]] =
    ZIO.sequenceParN(n)(as)

  /**
   *  @see [[zio.ZIO.some]]
   */
  def some[A](a: A): Task[Option[A]] = ZIO.some(a)

  /**
   * @see See [[zio.ZIO.suspend]]
   */
  final def suspend[A](task: => Task[A]): Task[A] =
    ZIO.suspend(task)

  /**
   * [[zio.ZIO.suspendWith]]
   */
  final def suspendWith[A](task: Platform => UIO[A]): UIO[A] =
    new ZIO.SuspendWith(task)

  /**
   * @see See [[zio.ZIO.trace]]
   * */
  final def trace: UIO[ZTrace] = ZIO.trace

  /**
   * @see See [[zio.ZIO.traced]]
   */
  final def traced[A](task: Task[A]): Task[A] = ZIO.traced(task)

  /**
   * @see See [[zio.ZIO.traverse]]
   */
  final def traverse[A, B](in: Iterable[A])(f: A => Task[B]): Task[List[B]] =
    ZIO.traverse(in)(f)

  /**
   * @see See [[zio.ZIO.traversePar]]
   */
  final def traversePar[A, B](as: Iterable[A])(fn: A => Task[B]): Task[List[B]] =
    ZIO.traversePar(as)(fn)

  /**
   * Alias for [[ZIO.foreachParN]]
   */
  final def traverseParN[A, B](
    n: Long
  )(as: Iterable[A])(fn: A => Task[B]): Task[List[B]] =
    ZIO.traverseParN(n)(as)(fn)

  /**
   * @see See [[zio.ZIO.traverse_]]
   */
  final def traverse_[A](as: Iterable[A])(f: A => Task[_]): Task[Unit] =
    ZIO.traverse_(as)(f)

  /**
   * @see See [[zio.ZIO.traversePar_]]
   */
  final def traversePar_[A](as: Iterable[A])(f: A => Task[_]): Task[Unit] =
    ZIO.traversePar_(as)(f)

  /**
   * @see See [[zio.ZIO.traverseParN_]]
   */
  final def traverseParN_[A](
    n: Long
  )(as: Iterable[A])(f: A => Task[_]): Task[Unit] =
    ZIO.traverseParN_(n)(as)(f)

  /**
   * @see See [[zio.ZIO.unit]]
   */
  final val unit: UIO[Unit] = ZIO.unit

  /**
   * @see See [[zio.ZIO.uninterruptible]]
   */
  final def uninterruptible[A](task: Task[A]): Task[A] =
    ZIO.uninterruptible(task)

  /**
   * @see See [[zio.ZIO.uninterruptibleMask]]
   */
  final def uninterruptibleMask[A](k: ZIO.InterruptStatusRestore => Task[A]): Task[A] =
    ZIO.uninterruptibleMask(k)

  /**
   * @see See [[zio.ZIO.untraced]]
   */
  final def untraced[A](task: Task[A]): Task[A] = ZIO.untraced(task)

  /**
   * @see See [[zio.ZIO.when]]
   */
  final def when(b: Boolean)(task: Task[_]): Task[Unit] =
    ZIO.when(b)(task)

  /**
   * @see See [[zio.ZIO.whenCase]]
   */
  final def whenCase[R, E, A](a: A)(pf: PartialFunction[A, ZIO[R, E, _]]): ZIO[R, E, Unit] =
    ZIO.whenCase(a)(pf)

  /**
   * @see See [[zio.ZIO.whenCaseM]]
   */
  final def whenCaseM[R, E, A](a: ZIO[R, E, A])(pf: PartialFunction[A, ZIO[R, E, _]]): ZIO[R, E, Unit] =
    ZIO.whenCaseM(a)(pf)

  /**
   * @see See [[zio.ZIO.whenM]]
   */
  final def whenM(b: Task[Boolean])(task: Task[_]): Task[Unit] =
    ZIO.whenM(b)(task)

  /**
   * @see See [[zio.ZIO.yieldNow]]
   */
  final val yieldNow: UIO[Unit] = ZIO.yieldNow
}
