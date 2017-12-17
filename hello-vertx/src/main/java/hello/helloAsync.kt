package hello

import io.reactivex.Observable
import io.reactivex.schedulers.Schedulers

fun helloFuture(name: String) = Observable.just(name)
        .subscribeOn(Schedulers.computation())
        .map a@ { name ->
            println("before sleep")
            Thread.sleep(3000)
            return@a "Hello ${name}"
        }
        .toFuture()

fun test02() {
    Observable.just(helloFuture("Mark"))
            .subscribeOn(Schedulers.computation())
            .map { f -> f.get() }.subscribe { println(it) }
    println("before long sleep")
    Thread.sleep(6000)
}

fun main(args: Array<String>) {
    test02()
}