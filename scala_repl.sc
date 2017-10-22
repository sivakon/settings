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


