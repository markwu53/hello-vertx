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

fun test07() {
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
    test04()
}





