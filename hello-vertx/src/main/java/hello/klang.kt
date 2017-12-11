package hello

fun my2varFunc(a: String, b: (String) -> String) = a + b("world")
fun my2stringVarFunction(a: String, b: String) = a + " " + b

fun main(args: Array<String>) {
    //val result = my2varFunc("hello", "world")
    //val result = my2varFunc("hello", {a: String -> ", " + a})
    //val result = my2varFunc("hello"){a: String -> " curry " + a}
    //val result = my2varFunc("hello"){" parameter type infer "}
    //val result = my2varFunc("hello"){a -> " my func " + a}
    val result = my2stringVarFunction("hello", "world")
    println(result)
}
