package hello;

import io.vertx.core.AbstractVerticle;

public class MyApp extends AbstractVerticle {

    @Override
    public void start() throws Exception {
        vertx.createHttpServer().requestHandler(req -> {
            req.response().end("<h1>Hello</h1>");
        }).listen(8081);
    }

}
