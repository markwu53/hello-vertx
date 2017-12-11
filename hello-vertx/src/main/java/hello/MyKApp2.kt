package hello

import io.vertx.core.AbstractVerticle

class MyKApp2 : AbstractVerticle() {
    override fun start() {
        vertx.createHttpServer().requestHandler{ req ->
            req.response().end(
                    """
 <p>
<h1>hello vertx kotlin</h1>
 </p>
"""
            )
        }.listen(8081)
    }
}