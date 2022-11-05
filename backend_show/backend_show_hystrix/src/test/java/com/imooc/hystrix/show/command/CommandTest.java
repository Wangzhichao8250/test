package com.imooc.hystrix.show.command;

import org.junit.Test;
import rx.Observable;
import rx.Subscriber;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

/**
 * @author WangZhiChao
 * @date 2022/6/12 22:54
 * @detail
 */
public class CommandTest {

    @Test
    public void executeTest() {
        long beginTime = System.currentTimeMillis();
        CommandDemo commandDemo = new CommandDemo("execute");

        //同步执行Command
        String result = commandDemo.execute();

        long endTime = System.currentTimeMillis();

        System.out.println("result=" + result + ",speeding=" + (endTime - beginTime));
    }

    @Test
    public void queueTest() throws ExecutionException, InterruptedException {
        long beginTime = System.currentTimeMillis();

        CommandDemo commandDemo = new CommandDemo("queue");

        //同步执行Command
        Future<String> queue = commandDemo.queue();

        long endTime = System.currentTimeMillis();

        System.out.println("future end speeding=" + (endTime - beginTime));

        long endTime2 = System.currentTimeMillis();

        System.out.println("result=" + queue.get() + ",speeding=" + (endTime2 - beginTime));
    }

    @Test
    public void observeTest() {
        long beginTime = System.currentTimeMillis();
        CommandDemo commandDemo = new CommandDemo("execute");

        //同步执行Command
        Observable<String> observe = commandDemo.observe();
        String result = observe.toBlocking().single();
        long endTime = System.currentTimeMillis();

        System.out.println("result=" + result + ",speeding=" + (endTime - beginTime));

        //非阻塞式调用
        observe.subscribe(new Subscriber<String>() {
            @Override
            public void onCompleted() {
                System.err.println("observe, onCompleted");
            }

            @Override
            public void onError(Throwable throwable) {
                System.err.println("observe, onError - throwable=" + throwable);
            }

            @Override
            public void onNext(String result) {
                System.err.println("observe, onNext result=" + result);
            }
        });
    }

    @Test
    public void toObserveTest() {
        long beginTime = System.currentTimeMillis();
        CommandDemo commandDemo = new CommandDemo("execute");

        //同步执行Command
        Observable<String> toObservable = commandDemo.toObservable();
        String result = toObservable.toBlocking().single();
        long endTime = System.currentTimeMillis();

        System.out.println("result=" + result + ",speeding=" + (endTime - beginTime));

        //非阻塞式调用
        toObservable.subscribe(new Subscriber<String>() {
            @Override
            public void onCompleted() {
                System.err.println("toObservable, onCompleted");
            }

            @Override
            public void onError(Throwable throwable) {
                System.err.println("toObservable, onError - throwable=" + throwable);
            }

            @Override
            public void onNext(String result) {
                System.err.println("toObservable, onNext result=" + result);
            }
        });
    }

}
