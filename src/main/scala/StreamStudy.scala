trait StreamStudy[+A] {

  def headOption: Option[A] = this match {
    case Cons(h, _) => Some(h())
    case EmptyStream => None
  }

  def tail: StreamStudy[A] = this match {
    case Cons(_, t) => t()
    case EmptyStream => throw new UnsupportedOperationException("tail of empty stream")
  }

}

case object EmptyStream extends StreamStudy[Nothing]

case class Cons[+A](h: () => A, t: () => StreamStudy[A]) extends StreamStudy[A]

object StreamStudy {

  def cons[A](h: => A, t: => StreamStudy[A]): StreamStudy[A] = Cons(() => h, () => t)

  def empty[A]: StreamStudy[A] = EmptyStream

}
