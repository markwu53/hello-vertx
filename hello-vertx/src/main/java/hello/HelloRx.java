package hello;

import io.reactivex.Flowable;
import io.reactivex.Observable;
import io.reactivex.schedulers.Schedulers;

public class HelloRx {

    public static void main(String[] args) {
        test03();
    }
  
    public static void test03() {
        Flowable.just("Alpha", "Beta", "Gamma")
        .subscribeOn(Schedulers.computation())
        .map(String::length).subscribe(v -> {
            System.out.println("received " + v + " on thread " + Thread.currentThread().getName());
        });
        try {
            System.out.println("sleep on " + Thread.currentThread().getName());
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void test02() {
        Flowable.just("Alpha", "Beta", "Gamma").map(String::length).subscribe(v -> {
            System.out.println("received " + v + " on thread " + Thread.currentThread().getName());
        });
    }

    public static void test01() {
        Flowable.just("Hello World").subscribe(System.out::println);
        //Flowable.range(1, 10) .observeOn(Schedulers.computation()) .map(v -> v * v) .blockingSubscribe(System.out::println);
        /*
        Flowable.range(1,  10).flatMap(v ->
        Flowable.just(v).subscribeOn(Schedulers.computation()).map(w -> w * w)
        ).blockingSubscribe(System.out::println);
        */
        Observable<String> observable = Observable.just("Hello");
        observable.subscribe(System.out::println);
        Observable.range(0,  10).buffer(5).flatMap(batch -> {
            return Observable.fromIterable(batch).subscribeOn(Schedulers.computation()).map(item -> {
                return " " + item;
            });
        }).blockingForEach(System.out::print);
    }

}