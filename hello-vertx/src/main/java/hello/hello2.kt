package hello

import io.vertx.core.AsyncResult

fun main(args: Array<String>) {
    sayHello2("Mark")
}

fun sayHello2(name: String) {
    concatWithHello2(name) { f1 ->
        concatWithExclamation2(f1.result()) { f2 ->
            concatWithISay2(f2.result()) { f3 ->
                println(f3.result())
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
