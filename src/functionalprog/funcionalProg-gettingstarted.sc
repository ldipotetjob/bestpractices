/**
 * ref:
 *    scala unified data types:
 *      https://docs.scala-lang.org/resources/images/tour/unified-types-diagram.svg
 *    scala type casting:
 *      https://docs.scala-lang.org/resources/images/tour/type-casting-diagram.svg
 */

object MyModule {
  def abs (n: Int): Int = if (n < 0) -n else n

  def formatResult(name: String, n: Int, f: Int => Int) = {
    val msg = "The %s of %d is %d."
    msg.format(name, n, f(n))
  }
  def formatAbs (x: Int) = {
    val msg = "The absolute value of %d is %d"
    msg.format(x, abs(x))
  }
}

import MyModule._
 println(formatAbs(-42))

/** Anonymous func*/

println(formatResult("absolute value",-42,abs))
println(formatResult("absolute value plus one",-42,(x:Int)=> x + 1))
/** monomorphic functions: operate on only ONE TYPE of data   */
/**
 * function literal:
 * Object with apply method can be called as methods
 * */

val lessThan =  (a: Int, b: Int) => a < b
val lessThanV1: (Int, Int) => Boolean = (a: Int, b: Int) => a < b

def lessThanV2(a: Int, b: Int): Boolean = a < b

val lessThanWithApply = new Function2[Int, Int, Boolean] {
  def apply(a: Int, b: Int) = a < b
}
lessThan(10,6)
lessThanV1(10,6)
lessThanWithApply(10,6)
lessThanWithApply.apply(10,6)

/** polymorphic functions: operate on only ANY TYPE of data   */
/**
 * @specialized to increase runtime performance in our Scala code
 *
 * ref: https://dzone.com/articles/type-specialization-in-scala
 *
 * */

def binarySearch[@specialized A](as: Array[A], key: A, gt: (A,A) => Boolean): Int = {
  as.filter(gt(_,key)).size
}

binarySearch(Array(20,299,40,20),20,(x: Int, y: Int) => x == y)

binarySearch(Array("Peter","Jake","Mark","Louis"),"Ma1rk",(x: String, y: String) => x == y)

/**
 * Currying : The process of converting a function with multiple arguments into a sequence of functions
 * that take one argument. Each function returns another function that consumes the following argument.
 */

/** Currying example(I) */
def add(a: Int)(b: Int) = a + b;
val sum = add(20)_
println(s"Curried value ${sum(30)}")

//exc 2.3
/**
 * Implement partial1 and write down a concrete usage of it.
 * Important to note: Talking here about partially-applied function NOT partial function
 *
 * ref:
 * https://danielwestheide.com/blog/the-neophytes-guide-to-scala-part-11-currying-and-partially-applied-functions/
 */
def partial1[A,B,C](a: A, f: (A,B) => C): B => C = (b: B) => f(a,b) // f(a,b) lambda function

/** better implementation of  Currying example(I) L67*/
val  partial1Int = partial1[Int, Int, Int](20, _ + _)
partial1Int(30)

/** More useful example */

def htmlSkeleton (fix: String, html: String) = s"<$fix>" + html + s"</$fix>"

val partial1htmlPage = partial1[String, String, String]("div", htmlSkeleton)
partial1htmlPage("hello")

// exc 2.4 implement:
def curry[A,B,C](f: (A, B) => C): A => (B => C) = a => b => f(a,b)

// a => b => f(a,b) two step to call func
val curryExample = curry[Int, Int, Int](_ + _ )
val curryExampleStep1 = curryExample(10)
val curryExampleStep2 = curryExampleStep1(20)

// Function2 can be curried
val lessThanWithApply = new Function2[Int, Int, Boolean] {
  def apply(a: Int, b: Int) = a < b
}
val lessThanWithApplyCurried = lessThanWithApply.curried
val lessThanWithApplyCurriedStep1 = lessThanWithApplyCurried(10)
val lessThanWithApplyCurriedStep2 =  lessThanWithApplyCurriedStep1(20)


// exc 2.5 implement:
def uncurry[A,B,C](f: A => B => C): (A, B) => C = (a, b) => f(a)(b)
val uncurriedFunc = uncurry(lessThanWithApplyCurried)

uncurriedFunc(32,20)

// exc 2.6 implement:
/** like andThen implementation in Function1 */
def compose[A,B,C](f: B => C, g: A => B): A => C = a => f(g(a))

def f(b: Int): Int = b / 2
def g(a: Int): Int = a + 2

compose(f, g)(10)
/** ref: https://www.scala-lang.org/api/current/scala/Function1.html#andThen[A](g:R=%3EA):T1=%3EA */
val anonfun1 = new Function1[Int, Int] {
  def apply(x: Int): Int = x + 1
}

val anonstep1 = anonfun1.andThen(_*20)
anonstep1(3) // Int = 80

