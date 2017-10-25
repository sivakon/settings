import ammonite.ops._

import ImplicitWd._

ls!

%git 'status

cwd

cwd/'folder/"file.txt"

ls! cwd | (_.last) | println

ls.rec! cwd | (_.last) |? (_(0)=='.')

ls.rec! cwd |? (_.ext == "sc") |? (_.size > 50) |(_.last) | println

ls.rec! cwd groupBy (_.ext) mapValues (_ | (_.size) sum)


val simpleMap = Set(("x",1),("x",1),("y",2))
simpleMap.groupBy(_._1).mapValues(x => x.map(_._2) sum)

// simpleMap groupBy (_._1) map {case (x,y) => x -> y.values.sum}
simpleMap groupBy (_._1) mapValues (_|(_._2) sum) // think about this later

val b = List(1, 2)
b map {case 1 => "one" case 2 => "two"}

mkdir! cwd/"tempfolder"

write(cwd/"tempfolder"/"file.ext","Hello!!!!!!!")

rm! cwd/"tempfolder"/"file.ext"

write(cwd/"tempfolder"/"file.txt","Hello!!!!")

read! cwd/'tempfolder/"file.txt"

ls! cwd foreach println

// make http requests
import $ivy.`org.scalaj::scalaj-http:2.3.0`

import ammonite.ops._
import scalaj.http._

val resp = Http("https://api.github.com/repos/scala/scala").asString

val parsed = upickle.json.read(resp.body).asInstanceOf[upickle.Js.Obj]

for((k, v) <- parsed.value) write(pwd/'tempfolder/k, upickle.json.write(v))

ls! pwd/'tempfolder foreach println

read! cwd/'tempfolder/'archive_url

//Scraping stuff
import $ivy.`org.jsoup:jsoup:1.7.2`

import org.jsoup._
import collection.JavaConversions._

val doc = Jsoup.connect("http://en.wikipedia.org/").get()

doc.select("h1")

doc.select("h2")

doc.select("h2").map(_.text) foreach println

//java swing
import javax.swing._
import java.awt.event._

val frame = new JFrame("Hello world Windows")

import $ivy.`org.apache.poi:poi-ooxml:3.13`
import ammonite.ops._
import org.apache.poi.xwpf.usermodel._

//val path = pwd/'amm/'src/'test/'resources/'testdata/"Resume.docx"

//import $file.Documents.settings.MultiMain

import $ivy.`org.apache.spark::spark-sql:2.2.0`
import org.apache.spark.sql.SparkSession
import org.apache.spark.sql.Row
val spark = SparkSession.builder.appName("SimpleApp").config("spark.master","local").getOrCreate()

val logFile = "/usr/local/spark/README.md"
val data = spark.read.textFile(logFile).cache()

val numAs = data.filter(line => line.contains("a")).count()

spark.stop()

/**
    load data with sqlContext or SparkSession (preferred) to infer the schema
 */

import spark.implicits._

val df = spark.read.json("/usr/local/spark/examples/src/main/resources/people.json")
df.show()

df.printSchema()

case class Person(name: String, age: Long)

val persons = df.as[Person] //df is a dataframe, we convert to dataset

persons.show()

val rel = persons.select($"age",$"name")
rel.show()

rel.filter($"age" > 20).show()

val textFile = spark.read.text("/usr/local/spark/README.md")
textFile.show()
textFile.printSchema()
// |- value: string
import org.apache.spark.sql.Row
val words = textFile.
             select("value").
             flatMap{case Row(value: String) => value.split(" ")}
val counts = words.
             groupBy($"value").
             count() 

val words = textFile.as[String].
            flatMap(_.split(" ")).
            filter(_ != "").
            groupBy("value").
            count()

// Filtering on Datasets are not efficient. Convert this to DF and filter
// (thereby loading only the data) and do stuff and convert it back to Dataset
//This is wrong. Conversion to DF is for pre-2.0 compatibilty. Some DF functions

df.groupBy("age").agg(min("hours-per-week"),
                      max("hours-per-week"))
//implicit conversion example here
val happiness = ds.toDF().filter($"happy" === true).as[RawPanda] //







// import java.util.{Arrays, List, ArrayList}
// import java.util.stream.{Stream, IntStream}

// val myList = Arrays.asList("a1","b2","c3")
// myList.stream
(1 to 9).filter(_>3)

(1 to 5) filter {_%2 == 0} map {_*2}

def zscore = (mean:Int, sd:Int) => (x:Int) => (x-mean)/sd //without sugar
zscore(10,1)(10)


def zscore(mean:Int, sd:Int)(x:Int) = (x-mean)/sd
zscore(10,1)(0)

pow
// import java.util.{Date => _, _} //import everything except Date

val xs:List[Int] = List(1,2,3)
val xs = List(1,2,3)
val ys = List(11,12,13)

(xs zip ys) map { case (x,y) => x*y } 

(xs zip ys) //get ((1,11),(2,12),(3,13))

1 :: List(1,2)

1 to 5

1 until 6

(1 to 10) map { x => pow(x,2) } 

val v42 = 42
Some(42) match {
  case Some(`v42`) => println("42")
  case _ => println("Not 42")
}

Some(3) match {
    case Some(`v42`) => println("42")
    case _ => println("Not 42")
}

v42.isInstanceOf[Int] //true is printed

v42.asInstanceOf[Double] //type casting

class Point(var x: Int = 0, var y:Int = 0)
val point2 = new Point(y=2)

println(point2.y)

class Point {
    private var _x = 0
    private var _y = 0
    private val bound = 100

    def left = _x
    def left_= (newValue: Int ) :Unit = {
        if (newValue < bound) _x = newValue else printWarning
    }

    def y = _y
    def y_= (newValue: Int) :Unit = {
        if (newValue < bound) _y = newValue else printWarning
    }

    private def printWarning = println("WARNING!!")
}

val point1 = new Point
point1.left = 99 //setting
point1.y = 1000


//traits - generic types
trait HairColor

trait Iterator[A] {
    def hasNext: Boolean
    def next(): A
}

class InitIterator(to: Int) extends Iterator[Int] {
    private var current = 0
    override def hasNext: Boolean = current < to
    override def next(): Int = {
        if (hasNext) {
            val t = current
            current+=1
            t
        } else 0
    }
}

val iterator = new InitIterator(10)
iterator.next()
iterator.next()

//subtyping
import scala.collection.mutable.ArrayBuffer

trait Pet {
    val name: String
}

class Cat(val a: String) extends Pet {
    override val name: String = a
}
// class Cat(val name: String) extends Pet both are same
class Dog(val name: String) extends Pet

val dog = new Dog("Harry")
val cat = new Cat("Sally")

val animals = ArrayBuffer.empty[Pet]

animals.append(dog)
animals.append(cat)

animals.foreach(pet => println(pet.name))


//Writemonkey - macros
// {siva}Sivaram Konanki
// Tokyo
// Japan
// [date]


// MIXINS - Mixin is a class that contains methods for use by other classes without having to be the parent class of those other classes.

abstract class A {
    val message: String
}

class B extends A {
    val message = "I'm  an instance of B"
}
trait C extends A { //this is a mixin
    val loudMessage = message.toUpperCase()
}
class temp extends A {
    val message = "temp"
}

class D extends B with C
class D extends temp with C

val d = new D
d.message
d.loudMessage


// a real example
abstract class AbsIterator {
    type T
    def hasNext: Boolean
    def next(): T
}

//implement concrete Class StringIterator
class StringIterator(s: String) extends AbsIterator {
    type T = Char
    private var i = 0
    def hasNext = i < s.length
    def next() = {
        val ch = s charAt i
        i += 1
        ch
    }
}

trait RichIterator extends AbsIterator {
    def foreach(f: T => Unit): Unit = while(hasNext) f(next())
}

class RichStringIter extends StringIterator("Hello") with RichIterator

val richStringIter = new RichStringIter
richStringIter.foreach(println)
richStringIter foreach println

//think of mixin as adding extra stuff to the parent class (no init within mixin)