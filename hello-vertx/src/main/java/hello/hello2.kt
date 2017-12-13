package hello

import io.vertx.core.AsyncResult
import io.vertx.core.Future
import io.vertx.core.Handler

fun main(args: Array<String>) {
    sayHello2("Mark")
}

fun sayHello2(name: String) = concatWithHello2(name) { future ->
    println(future.result())
}

fun concatWithHello2(name: String, handle: (AsyncResult<String>) -> Unit) {
    handle(succeededFuture("Hello ${name}"))
}
