/** partial function: it is not defined for some inputs.*/

sealed trait OptionExamp[+A]
case class SomeExamp[+A](get: A) extends OptionExamp[A]
case object NoneExamp extends OptionExamp[Nothing]

def mean(xs: Seq[Double]): OptionExamp[Double] =
  if (xs.isEmpty)
    NoneExamp
  else
    SomeExamp(xs.sum / xs.length)


/**
 * upper type bound T <: A
 * ref: https://docs.scala-lang.org/tour/upper-type-bounds.html
 *
 * lower type bound B >: A
 * ref: https://docs.scala-lang.org/tour/lower-type-bounds.html
 * */
/**
 *  Passing parameter by name
 *  : => Specific_Type
 *  example: default: => B
 *  ref: https://docs.scala-lang.org/tour/by-name-parameters.html
 */

//exc 4.1
trait OptionExamp[+A] {
  /** implement `flatMap` with explicit pattern matching. */
  def map[B](f: A => B): OptionExamp[B] = this match {
    case NoneExamp => NoneExamp
    case SomeExamp(a) => SomeExamp(f(a))
  }

  def flatMap[B](f: A => OptionExamp[B]): OptionExamp[B] = map(f) getOrElse NoneExamp

  def flatMap_1[B](f: A => OptionExamp[B]): OptionExamp[B] = this match {
    case NoneExamp => NoneExamp
    case SomeExamp(a) => f(a)
  }
  def getOrElse[B >: A](default: => B): B = this match {
    case NoneExamp => default
    case SomeExamp(a) => a
  }
  def orElse[B >: A](ob: => OptionExamp[B]): OptionExamp[B] = this map (SomeExamp(_)) getOrElse ob
  /** implement orElse with explicit pattern matching. */
  def orElse_1[B>:A](ob: => OptionExamp[B]): OptionExamp[B] = this match {
    case NoneExamp => ob
    case _ => this
  }
  def filter(f: A => Boolean): OptionExamp[A]= this match {
    case SomeExamp(a) if f(a) => this
    case _ => NoneExamp
  }
}

//exc 4.1
// TODO => implement examples

object OptionTest extends OptionExamp[String]
