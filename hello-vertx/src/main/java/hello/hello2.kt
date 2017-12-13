package hello

import io.vertx.core.AsyncResult
import io.vertx.core.Future
import io.vertx.core.Handler

fun main(args: Array<String>) {
    sayHello2("Mark")
}

fun sayHello2(name: String) {
    concatWithHello2(name) { hello ->
        concatWithExclamation2(hello.result()) { exclamation ->
            concatWithISay2(exclamation.result()) { future ->
                println(future.result())
            }
        }
    }
}

fun concatWithHello2(name: String, handle: (AsyncResult<String>) -> Unit) {
    handle(succeededFuture("Hello ${name}"))
}

fun concatWithExclamation2(hello: String, handle: (AsyncResult<String>) -> Unit) {
    handle(succeededFuture("${hello}!"))
}

fun concatWithISay2(message: String, handle: (AsyncResult<String>) -> Unit) {
    handle(succeededFuture("""I say: "${message}""""))
}
