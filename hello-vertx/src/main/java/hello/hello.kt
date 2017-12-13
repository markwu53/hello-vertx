package hello

import io.vertx.core.AsyncResult
import io.vertx.core.Future
import io.vertx.core.Handler

fun main(args: Array<String>) {
    sayHello("Mark")
}

fun succeededFuture(result: String) = object: AsyncResult<String> {
    override fun result(): String = result
    override fun cause(): Throwable = Throwable()
    override fun succeeded() = true
    override fun failed() = false
}

fun sayHello(name: String) {
    concatWithHello(name, object: Handler<AsyncResult<String>> {
        override fun handle(f: AsyncResult<String>) {
            println(f.result())
        }
    })
}

fun concatWithHello(name: String, handler: Handler<AsyncResult<String>>) {
    handler.handle(succeededFuture("Hello ${name}"))
}

