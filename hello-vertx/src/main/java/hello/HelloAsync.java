package hello;

import java.util.Arrays;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.function.Function;

import io.reactivex.Observable;
import io.reactivex.schedulers.Schedulers;
import io.vertx.core.AsyncResult;
import io.vertx.core.Future;
import io.vertx.core.Handler;

public class HelloAsync {

    public static void main(String[] args) {
        //test01();
        //test02();
        //test03();
        //test04();
        //test05();
        //hello("Mark");
        test06();
    }

    public static void test06() {
        asyncHello("Mark", f -> {
            try {
                System.out.println(f.get());
            } catch (InterruptedException | ExecutionException e) {
                e.printStackTrace();
            }
        });
    }

    public static void asyncHello(String name, Handler<java.util.concurrent.Future<String>> handler) {
        java.util.concurrent.Future<String> future = Observable.just(name).subscribeOn(Schedulers.computation()).map(v -> {
            Thread.sleep(3000);
            return "Hello " + name;
        }).toFuture();
        System.out.println("submitted");
        handler.handle(future);
    }

    public static void hello(String input) {
        concatWithHello(input, f -> {
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(f.result());
        });
        System.out.println("done");
    }

    public static void test05() {
        String result = cget2(cget1("Mark", HelloAsync::get2), HelloAsync::get3);
        System.out.println(result);
    }

    public static String cget1(String input, Function<? super String, ? extends String> f) {
        return f.apply(get1(input));
    }

    public static String cget2(String input, Function<? super String, ? extends String> f) {
        return f.apply(get2(input));
    }

    public static void test04() {
        String result = get3(get2(get1("Mark")));
        System.out.println(result);
    }

    public static void test03() {
        concatWithHello("Mark", f -> {
            concatWithExclamation(f.result(), g -> {
                concatWithISay(g.result(), h -> {
                    System.out.println(h.result());
                });
            });
        });
    }

    public static void test02() {
        Callable<String> a = new Callable<String>() {
            @Override
            public String call() throws Exception {
                System.out.println("running in " + Thread.currentThread().getName());
                return get1("Mark");
            }
        };
        Callable<String> b = new Callable<String>() {
            @Override
            public String call() throws Exception {
                System.out.println("running in " + Thread.currentThread().getName());
                return get2("Mark");
            }
        };
        Observable.fromIterable(Arrays.asList(a, b))
        //.flatMap(f -> Observable.just(f).subscribeOn(Schedulers.computation()).map(g -> g.call())).subscribe(System.out::println)
        .flatMap(f -> Observable.fromCallable(f).subscribeOn(Schedulers.computation())).subscribe(System.out::println)
        ;
        try {
            Thread.sleep(20000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void test01() {
        Observable.concat(
            Observable.fromCallable(() -> {
                return get1("Mark");
            }),
            Observable.fromCallable(() -> {
                return get2("Mark");
            }),
            Observable.fromCallable(() -> {
                return get3("Mark");
            })
        )
        .subscribe(System.out::println);
    }

    public static String get1(String input) {
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("in get1 " + input);
        return "in get1 " + input;
    }

    public static String get2(String input) {
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("in get2 " + input);
        return "in get2 " + input;
    }

    public static String get3(String input) {
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("in get3 " + input);
        return "in get3 " + input;
    }

    public static void concatWithHello(String name, Handler<AsyncResult<String>> handler) {
        handler.handle(Future.<String>succeededFuture("Hello " + name));
    }

    public static void concatWithExclamation(String hello, Handler<AsyncResult<String>> handler) {
        handler.handle(Future.<String>succeededFuture(hello + "!"));
    }

    public static void concatWithISay(String message, Handler<AsyncResult<String>> handler) {
        handler.handle(Future.<String>succeededFuture("I say: \"" + message + "\""));
    }

}
