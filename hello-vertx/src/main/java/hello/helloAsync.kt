package hello

import io.reactivex.Completable
import io.reactivex.Observable
import io.reactivex.schedulers.Schedulers

val helloFuture2 = { name: String ->
    Observable.just(name)
            .subscribeOn(Schedulers.computation())
            .map({ println("before sleep"); Thread.sleep(3000); "Hello ${it}"; })
            .toFuture()
}

fun mt(bin: (Int, Int) -> Int): Unit {
    println(bin(3, 6))
}

fun nt(uni: (Int) -> Int): Unit {
    println(uni(5))
}

fun nt2(a: (Int) -> Int) = println(a(5))

fun five(uni: (Int) -> Unit) = uni(5)
val five2 = { a: (Int) -> Unit -> a(5) }
fun ma(uni: (Int) -> Unit) = uni(5)

fun helloFuture(name: String) = Observable.just(name)
        .subscribeOn(Schedulers.computation())
        .map {
            println("before sleep")
            Thread.sleep(3000)
            "Hello ${it}"
        }
        .toFuture()

val test01 = {
    Observable.just(helloFuture2("Mark"))
            .subscribeOn(Schedulers.computation())
            .map { it.get() }.subscribe { println(it) }
    println("before long sleep")
    Thread.sleep(6000)
}

fun test02() {
    Observable.just(helloFuture("Mark"))
            .subscribeOn(Schedulers.computation())
            .map { it.get() }.subscribe { println(it) }
    println("before long sleep")
    Thread.sleep(6000)
}

fun test04() {
    Observable.just("a")
            .map({
                println("first sleep 3 seconds")
                Thread.sleep(3000)
                println("then continue next")
                it
            })
            .map({
                println("second sleep another 3 seconds")
                Thread.sleep(3000)
                println("then continue next")
                it
            })
            .subscribe()

}

fun test03() = println("hello")

val test = { a: String -> "append $a" }("hello")
val test2 = { "message" }()

//fun test07() = println("calling test07")
//fun test07() = { println("calling test07") }()
//fun test07() { return { println("calling test07") }() }
//fun test07() { return println("calling 07") }
//fun test07(): () -> Unit { return {} }
//fun test07(): (Int) -> Unit { return { a: Int -> println(a) } }
//fun test07(): (Int, Int) -> Unit { return { a: Int, b: Int -> println(a + b) } }
fun test07(): (Int, Int) -> String { return { a: Int, b: Int -> "${a + b}" } }
//fun test07() = { println("calling test07") }
//val test07 = { println("calling test07") }
//fun test07() { "hello" }
//fun test07(): String { return "hello" }
//val test07 = { "hello" }

fun test08() {
    val a = listOf<String>("hello", "world").joinToString(separator = " ")
    println(a)
}

fun <T> myList(a: T): T {
    return a
}
fun test09() {
    val a = myList("Hello")
    println(a)
}

fun main(args: Array<String>) {
    //test01()
    //test02()
    //println(test2)
    //mt({ a, b -> a * b })
    //mt { a, b -> a * b }
    //nt { it * it }
    //nt { it.times(1.5).toInt() }
    //five { println(it * it) }
    //five2 { println(it * 2) }
    //test04()
    //println(test07())
    //test08()
    test09()
}





