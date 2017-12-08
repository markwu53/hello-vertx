package hello;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.Vertx;

public class MyApp extends AbstractVerticle {

    private static final Vertx vertx = Vertx.vertx();

    public static void main(String[] args) {
        vertx.deployVerticle(new MyApp());
        //vertx.deployVerticle(MyApp.class, new DeploymentOptions());
        //vertx.deployVerticle("hello.MyApp");
        //new Launcher().dispatch(new String[] {"run", "scripts/echo_server.js"});
        System.out.println("Verticle started");
    }

    @Override
    public void start() throws Exception {
        vertx.createHttpServer().requestHandler(req -> {
            if (req.path().equals("/hello")) {
                try {
                    Thread.sleep(10000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                req.response().putHeader("content-type", "text/plain").end("Hello Vertx");
            } else {
                req.response().putHeader("content-type", "text/plain").end("Home");
            }
        }).listen(8081);
    }

}

