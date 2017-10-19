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