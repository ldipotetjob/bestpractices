/**
 * Monoid:
 * - Some type A
 * - Binary associative operation: take two values and combine
 *   them into one
 * _ A is an identity for that operation
 * */

trait Monoid[A] {
  def op(a1: A, a2: A): A
  def unit: A
}

// example
object FromMonoid{

  implicit val stringMonoid = new Monoid[String] {
    def op(a1: String, a2: String) = a1 + a2
    def unit = ""
  }

  implicit val intMonoid = new Monoid[Int] {
    def op(a1: Int, a2: Int) = a1 + a2
    def unit = 0
  }

  def sum[A](l: List[A])(implicit m: Monoid[A]): A ={
    if (l.isEmpty) m.unit
    else m.op(l.head, sum(l.tail))
  }
}

import FromMonoid._

println(sum(List("a", "b", "c")))
println(sum(List(12, 10, 5)))

//TODO => pending monoid !!!