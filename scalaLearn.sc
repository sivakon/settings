// run amm --watch foo.sc in terminal to execute the code live
import $ivy.`com.lihaoyi::scalatags:0.6.5`
import scalatags.Text.all._

"Hello World" |> println

println(h1("Hello this is a html tag"))

val sample:Double = 8 * 5 + 1

0.5 * sample

import scala.math._

2.0 |> sqrt |> println

// addOne is a function that takes (x:Int) as input and return x + 1

(x:Int) => x + 1 //this is a function

val addOne = (x:Int) => x + 1

2 |> addOne |> println

//Diff between functions and methods
// Methods in Scala are not values, but not functions
// Methods always accept arguments
// argument to function is optional

//Functions
val add = (x:Int, y:Int) => x + y

add(1,2) |> println

//Methods

//1. Takes two integers and returns an int (straight forward)
def add(x:Int, y:Int):Int = x + y

//2. After currying
def add(x:Int) = (y:Int) => x + y
// the above function takes x as input and outputs a function that takes y as an input and returns x + y
// It can also be written as
def add(x:Int)(y:Int): Int = x + y
add(2)(3) 

// normal methods add(x,y) can also be converted to curryied methods


def add1(x: Int, y: Int) = x + y // uncurried one
// add1 _ returns (Int, Int) => Int

def add2(x: Int)(y: Int) = x + y // curried one
// add2 _ returns Int => Int => Int

val curriedAdd1 = (add1 _).curried
// returns Int => Int => Int
// converted (Int, Int) => Int to Int => Int => Int

curriedAdd1 _ //outputs function
3 |> (2 |> curriedAdd1) //5  curriedAdd1 is a function

3 |> (2 |> add2 _) //5  add2 _ is a function

3 |> (2 |> add2) //5 add2 is a method

add2 _

// the above yield the same result

// Important question
curriedAdd1(2) _ // returns an error

//Why?
val i = curriedAdd1(23)
i _

	
//partial apply
def add(x:Int, y:Int, z:Int) = x + y + z
 
val addFive = add(5, _:Int, _:Int)
addFive(3, 1)    // 9

 /**

 The rule of thumb is if addFive _ returns () => (Int, Int) => Int, then it is a function and arguments are optional
 If addFive _ returns (Int, Int) => Int then it is a method and always accepts arguments
 
  */


///////////////////////////////////////////////////

// All types are classes
1.toString() //string

1.to(10) //range

"Hello".intersect("World")

1 + 2

1.+(2) // + is a method

2 |> 1.+ // see + is a method

// a.method(b) is shorthand for a method b

1 to 10

val x:Int = 100

x.*(x).*(x) 
x*x*x

"Hello".intersect("World")

"Hello" intersect "World"

// Here "Hello" is a string object. Calling intersect method on string object 

import scala.math._
// import math._
2.0 |> sqrt

pow(2,4)
(2,4) |> pow // Error, can only do this to the curried function
(2.0,4.0) |> (pow _) // How to do |> on multiple parameters???

min(3, Pi)

(math.pow _) (2.0,3.0) // this is same as
math.pow(2.0,3.0)

// also scala.math.min(3,Pi)
scala.math.min(3,Pi)

BigInt.probablePrime(10, scala.util.Random)

val s = "Hello"
s(4)

s.apply(4)

s apply 4

s(4).isUpper

(s _)

s.count(_.isUpper) //discussed later

s(4).isUpper //How to do this for every character
//Found it!

"Hello".count((x:Char) => x.isUpper)
"Hello".count((x) => x.isUpper)
"Hello".count(x => x.isUpper)
"Hello".count(_.isUpper)

//One more example. This is awesome.
def simpleFun(x: Int): Boolean = {
    if(x > 10) true else false
}

simpleFun(20)

val a = List(1,2,3)

a.count(x => simpleFun(x))
a.count(simpleFun _)

"Hello".map( x => x.isUpper) // think about this later

"pass" + "asdasd"

(1 to 9).map(0.1 * _)

(1 to 9).map( x => "*" * x )
(1 to 9).map("*" * _).foreach(println _)

(1 to 9).filter(_ % 2 == 0).foreach(println _)

(1 to 9).reduceLeft(_*_)


// println function
val p = 10
println(s"the value of p is ${p}")
println("the value of p is " + p)

//Some more currying
val a = Array("Hello","World")
val b = Array("hello","world")

a.corresponds(b)( ( a: String, b: String ) => a.equalsIgnoreCase(b) )
a.corresponds(b)(_.equalsIgnoreCase(_))

//baby steps for the above expression
val tmp2 = ( a: String, b:String ) => a.equalsIgnoreCase(b)
a.corresponds(b) (tmp2)


/**
    One exercise Scala for impatient
    Write a function values(fun: (Int) => Int, low: Int, high: Int) that yields 
    a collection of function inputs and outputs in a given range. For example, values(x => x * x, -5, 5) 
    should produce a collection of pairs (-5, 25), (-4, 16), (-3, 9), . . . , (5, 25).
 */

 def values(fun: (Int) => Int, low: Int, high: Int) = {
     //(low to high).map(fun(_)).foreach(println _)
    val a = (low to high).map(fun(_))
    val b = (low to high)
    def printPairs[A, B](a: A, b: B) = {
        a
    }
 }

 def values2(fun: (Int) => Int, low: Int, high: Int) = {
     for (i <- low to high)
        yield (i, fun(i))
 }

println(values2(x => x * x, -5 , 5))

/**
Currying (named after logician Haskell Brooks Curry) is the process of turning a function 
that takes two arguments into a function that takes one argument. 
That function returns a function that consumes the second argument.

Currying started above only.

 */


/**
    HIGHER ORDER FUNCTIONS ALEADY

 */

//Higher order functions - Functions returning a functions

//def valueAtOneQuarter(f: (Double) => Double) = f(0.25)
def mulBy(factor: Double) = (x: Double) => factor * x

mulBy(5)(20)
val a = mulBy(5)
a(20)

def simpleFun(x: Int): Boolean = {
    if(x > 10) true else false
}

simpleFun(20)

val a = List(1,2,3)

a.count(x => simpleFun(x))
a.count(simpleFun _)

"Hello".count( x => x.isUpper)


//Objects are like classes, where you can instantiate only methods you want


// Why companion objects or companion class
// Why? You may want to give users convenient way of constructing 
// instances of the class that don't involve calling a constructor

// Usually
object Point {
    def apply(x: Double, y: Double) = new Point(x,y)
}

// Client does not have to call new
val p = Point(3,4) * factor
// prettier than new Point (3,4)


// Traits can have abstract fields.
// ---- A concrete implementing class must supply them

// Traits can have concrete fields
// ---- Concrete trait fields are added to the implementing class

// Traits cannot have contruction parameters
// ---- Techinically, this is the only difference between classes and traits


/*
    Mixins -::::- 
    Order an ice-cream, order mixins like chocolate chip cookie, sprinkles

*/


trait Logged {
    def log(msg: String) {}
}

trait ConsoleLogger extends Logged {
    override def log(msg: String) { println(msg) }
}

class SavingsAccount extends Logged {
    private var balance: Double = 0
    def withdraw(amount: Double) {
        if (amount > balance) log("Insufficient funds")
        else balance -= amount
    }
}

val acct = new SavingsAccount with ConsoleLogger

// log method is some abstract method that we don't know


