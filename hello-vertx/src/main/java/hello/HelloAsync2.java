package hello;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.function.Consumer;

import io.reactivex.Observable;
import io.reactivex.schedulers.Schedulers;

public class HelloAsync2 {

    public static void main(String[] args) {
        // test01();
        test02();
    }

    public static void test02() {
        Observable.just(helloFuture("Mark")).subscribeOn(Schedulers.computation()).map(f -> f.get())
                .subscribe(System.out::println);
        try {
            Thread.sleep(8000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static Future<String> helloFuture(String name) {
        return Observable.just(name).subscribeOn(Schedulers.computation()).map(v -> {
            Thread.sleep(3000);
            return "Hello " + name;
        }).toFuture();
    }

    public static void test01() {
        helloCallback("Mark", f -> {
            try {
                System.out.println(f.get());
            } catch (InterruptedException | ExecutionException e) {
                e.printStackTrace();
            }
        });
    }

    public static void helloCallback(String name, Consumer<Future<String>> handler) {
        Future<String> future = Observable.just(name).subscribeOn(Schedulers.computation()).map(v -> {
            Thread.sleep(3000);
            return "Hello " + name;
        }).toFuture();
        System.out.println("submitted");
        handler.accept(future);
        System.out.println("done");
    }
}
