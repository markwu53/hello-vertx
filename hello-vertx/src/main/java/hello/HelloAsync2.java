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
        Observable<Future<String>> observable = Observable.just(helloFuture("Mark"));
        System.out.println("after just");
        observable = observable.subscribeOn(Schedulers.computation());
        System.out.println("after subscribeOn");
        Observable<String> obs = observable.map(f -> f.get());
        System.out.println("after map");
        obs.subscribe(System.out::println);
        System.out.println("after subscribe");
        try {
            System.out.println("before sleep");
            Thread.sleep(8000);
            System.out.println("after sleep");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static Future<String> helloFuture(String name) {
        Future<String> future = Observable.just(name).subscribeOn(Schedulers.computation()).map(v -> {
            System.out.println("inside future function");
            Thread.sleep(3000);
            return "Hello " + name;
        }).toFuture();
        System.out.println("returning future");
        return future;
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
