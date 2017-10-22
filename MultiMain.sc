import ammonite.ops._
val x = 1

@main
def mainA() = {
    println("Hello!" + x)
}

@doc("This explains what the function does!!")

@main
def funtionB(i:Int @doc("how many times?"),
            s: String @doc("the string is open"),
            path: Path = pwd) = {
                println(s"Hello! ${ s * i } ${path.relativeTo(pwd)}.")


            }