package hello

import io.vertx.core.AbstractVerticle

class MyKApp : AbstractVerticle() {
    override fun start() {
        vertx.createHttpServer().requestHandler({ req ->
            req.response().end("hello vertx kotlin")
        }).listen(8081)
    }
}