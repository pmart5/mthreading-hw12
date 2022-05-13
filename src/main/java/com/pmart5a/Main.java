package com.pmart5a;

import com.pmart5a.myclasses.MyCallable;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

public class Main {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        Callable<String> myCallable1 = new MyCallable("Задание 1", 5);
        Callable<String> myCallable2 = new MyCallable("Задание 2", 7);
        Callable<String> myCallable3 = new MyCallable("Задание 3", 4);
        Callable<String> myCallable4 = new MyCallable("Задание 4", 4);
        List<Callable<String>> myCallables = List.of(myCallable1, myCallable2, myCallable3, myCallable4);
        final ExecutorService threadPool = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());
// Используем методы submit() и get() для исполнения задач и получения результата.
        System.out.println("Результат использования методов submit() и get():");
        List<Future<String>> listResultOfTasksSubmit = new ArrayList<>();
        for (Callable<String> myCallable : myCallables) {
            listResultOfTasksSubmit.add(threadPool.submit(myCallable));
        }
        for (Future<String> resultOfTaskSubmit : listResultOfTasksSubmit) {
            System.out.println(resultOfTaskSubmit.get());
        }
// Используем методы invokeAll() и get() для исполнения задач и получения результата.
        System.out.println("Результат использования методов invokeAll() и get():");
        List<Future<String>> listResultOfTasks = threadPool.invokeAll(myCallables);
        for (Future<String> resultTask : listResultOfTasks) {
            System.out.println(resultTask.get());
        }
// Используем метод invokeAny() для исполнения задач и получения результата от самой быстрой задачи.
        System.out.println("Результат использования метода invokeAny():");
        final String resultOfTasks = threadPool.invokeAny(myCallables);
        System.out.println(resultOfTasks);
        threadPool.shutdown();
    }
}