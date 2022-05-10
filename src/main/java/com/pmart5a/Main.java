package com.pmart5a;

import com.pmart5a.myclasses.MyCallable;

import java.util.List;
import java.util.concurrent.*;

public class Main {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        Callable<String> myCallable1 = new MyCallable("Задание 1", 5);
        Callable<String> myCallable2 = new MyCallable("Задание 2", 7);
        Callable<String> myCallable3 = new MyCallable("Задание 3", 4);
        Callable<String> myCallable4 = new MyCallable("Задание 4", 4);
        final ExecutorService threadPool = Executors.newFixedThreadPool(4);
// Используем методы submit() и get() для исполнения задач и получения результата.
        System.out.println("Результат использования методов submit() и get():");
        final Future<String> task1 = threadPool.submit(myCallable1);
        final Future<String> task2 = threadPool.submit(myCallable2);
        final Future<String> task3 = threadPool.submit(myCallable3);
        final Future<String> task4 = threadPool.submit(myCallable4);
        final String resultOfTask1 = task1.get();
        System.out.println(resultOfTask1);
        final String resultOfTask2 = task2.get();
        System.out.println(resultOfTask2);
        final String resultOfTask3 = task3.get();
        System.out.println(resultOfTask3);
        final String resultOfTask4 = task4.get();
        System.out.println(resultOfTask4);
// Используем методы invokeAll() и get() для исполнения задач и получения результата.
        System.out.println("Результат использования методов invokeAll() и get():");
        List<Future<String>> listResultOfTasks = threadPool.invokeAll(List.of(myCallable1, myCallable2, myCallable3, myCallable4));
        for (Future<String> resultTask : listResultOfTasks) {
            System.out.println(resultTask.get());
        }
// Используем метод invokeAny() для исполнения задач и получения результата от самой быстрой задачи.
        System.out.println("Результат использования метода invokeAny():");
        final String resultOfTasks = threadPool.invokeAny(List.of(myCallable1, myCallable2, myCallable3, myCallable4)); //asList(task1, task2, task3, task4));
        System.out.println(resultOfTasks);
        threadPool.shutdown();
    }
}