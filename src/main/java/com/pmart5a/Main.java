package com.pmart5a;

import com.pmart5a.myclasses.MyCallable;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

public class Main {

    static final int NUMBER_OF_TASK = 4;

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        List<Callable<String>> myCallables = new ArrayList<>();
        for (int i = 0; i < NUMBER_OF_TASK; i++) {
            myCallables.add(new MyCallable("Задание " + (i + 1)));
        }
        final ExecutorService threadPool = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());
// Используем методы submit() и get() для исполнения задач и получения результата.
        List<Future<String>> listResultOfTasks = new ArrayList<>();
        System.out.println("Результат использования методов submit() и get():");
        for (Callable<String> myCallable : myCallables) {
            listResultOfTasks.add(threadPool.submit(myCallable));
        }
        for (Future<String> resultOfTask : listResultOfTasks) {
            System.out.println(resultOfTask.get());
        }
// Используем методы invokeAll() и get() для исполнения задач и получения результата.
        System.out.println("Результат использования методов invokeAll() и get():");
        listResultOfTasks = threadPool.invokeAll(myCallables);
        for (Future<String> resultTask : listResultOfTasks) {
            System.out.println(resultTask.get());
        }
// Используем метод invokeAny() для исполнения задач и получения результата от самой быстрой задачи.
        System.out.println("Результат использования метода invokeAny():");
        System.out.println(threadPool.invokeAny(myCallables));
        threadPool.shutdown();
    }
}