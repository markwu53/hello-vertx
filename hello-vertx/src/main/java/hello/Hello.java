package hello;

import io.vertx.core.AsyncResult;
import io.vertx.core.Future;
import io.vertx.core.Handler;

public class Hello {

    public static void main(String[] args) {
        new Hello().sayHello("Mark", future -> {
            System.out.println(future.result());
        });
    }

    public void sayHello2(String name, Handler<AsyncResult<String>> handler) {
        Future<String> future = Future.<String>future();
        future.setHandler(handler);

        Future<String> hello = Future.<String>future();
        concatWithHello(name, hello);

        hello.compose(v -> {
            Future<String> exclamation = Future.<String>future();
            concatWithExclamation(v, exclamation.completer());
            return exclamation;
        }).compose(v -> {
            concatWithISay(v, future.completer());
        }, future);
    }

    public void sayHello(String name, Handler<AsyncResult<String>> handler) {
        concatWithHello(name, hello -> {
            concatWithExclamation(hello.result(), exclamation -> {
                concatWithISay(exclamation.result(), handler);
            });
        });
    }

    public void concatWithHello(String name, Handler<AsyncResult<String>> handler) {
        handler.handle(Future.<String>succeededFuture("Hello " + name));
    }

    public void concatWithExclamation(String hello, Handler<AsyncResult<String>> handler) {
        handler.handle(Future.<String>succeededFuture(hello + "!"));
    }

    public void concatWithISay(String message, Handler<AsyncResult<String>> handler) {
        handler.handle(Future.<String>succeededFuture("I say: \"" + message + "\""));
    }

}
