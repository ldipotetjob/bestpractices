/**
 * Important 2 know:
 * variances:
 * ref: https://docs.scala-lang.org/tour/variances.html
 *
 *  Class GenericClass[A] // generic class = invariant class
 *  Class GenericCovariantClass[+A] // covariant class
 *  Class GenericContravariantClass[+A] // contravariant class
 */

/**
 * Why [+A]:
 *  ListExamp[+A] because Nil extends List[Nothing].
 *  Nothing is a subtype of all types, which means that in conjunction with
 *  the variance annotation,
 *  Nil can be considered a List[Int], a List[Double], and so on, exactly as we want.
 */
sealed trait ListExamp[+A]
/** ref: https://docs.scala-lang.org/overviews/scala-book/case-objects.html*/

case object Nil extends ListExamp[Nothing]
case class Cons[+A](head: A, tail: ListExamp[A]) extends ListExamp[A]
object ListExamp {
  def sum(ints: ListExamp[Int]): Int = ints match {
    case Nil => 0
    case Cons(x,xs) => x + sum(xs)
  }
  def product(ds: ListExamp[Double]): Double = ds match {
    case Nil => 1.0
    case Cons(0.0, _) => 0.0
    case Cons(x,xs) => x * product(xs)
  }
  // exc 3.2
  /** removing first element of the list*/
  def tail[A](l: ListExamp[A]): ListExamp[A] = l match {
    case Nil => sys.error("tail of empty list")
    case Cons(_,t) => t
  }

  // exc 3.3
  /** drop first n elements of the list*/
  def drop[A](l: ListExamp[A], n: Int): ListExamp[A] =
    if (n <= 0) l
      else l match {
    case Nil => Nil
    case Cons(_,t) => drop(t, n-1)
    }
  // exec 3.4
  /** remove element from the List prefix as long as they match a predicate */
  /**
   *  Grouping the arguments this way is to assist with type inference. If
   *  we do this, Scala can determine the type of f without any annotation,
   *  based on what it knows about the type of the List, which makes the
   *  function more convenient to use, especially when passing a function
   *  literal like x => x > 34 in for f, which would otherwise require an
   *  annotation like (x: Int) => x > 34.
   *  Type inference means that you can infers the type :
   *  x => x > 34
   *  otherwise you should code:
   *  (x: Int) => x > 34
   */
  def dropWhile[A](l: ListExamp[A], f: A => Boolean): ListExamp[A] = l match {
    case Cons(h,t) if f(h) => dropWhile(t, f)
    case _ => l
  }

  // exec 3.5
  /** replacing the first element of a List with a different value */
  def setHead[A](l: ListExamp[A], a1: A): ListExamp[A] = l match {
    case Nil => l
    case Cons(_, t) => Cons(a1, t)
  }

  def apply[A](as: A*): ListExamp[A] =
    if (as.isEmpty) Nil
    else Cons(as.head, apply(as.tail: _*))
}

import ListExamp._

val example = Cons(1, Cons(2, Cons(3, Nil)))
val example2 = ListExamp(1,2,3,4,5)
val total = sum(example2)

//example exc 3.2
example.tail
ListExamp.tail(example2)

//example exc 3.3
ListExamp.drop(example,1)

//example exc 3.4
ListExamp.dropWhile[Int](example2, _ < 3) // ListExamp[Int] = Cons(3,Cons(4,Cons(5,Nil)))

// TODO => Chapter 3 (page 41) exc 3.6

