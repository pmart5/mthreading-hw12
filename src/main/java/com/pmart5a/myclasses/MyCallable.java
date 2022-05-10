package com.pmart5a.myclasses;

import java.util.concurrent.Callable;

public class MyCallable implements Callable<String> {
    private final String nameTask;
    private final int numberOfMessages;

    public MyCallable(String nameTask, int numberOfMessages) {
        this.nameTask = nameTask;
        this.numberOfMessages = numberOfMessages;
    }

    @Override
    public String call() throws InterruptedException {
        int numberOfMessagesSent = 0;
        for (int i = 0; i < numberOfMessages; i++) {
            Thread.sleep(2000);
            System.out.printf("Я %s. Выполняю %s. Это %d сообщение из %d.\n", Thread.currentThread().getName(), nameTask,
                    ++numberOfMessagesSent, numberOfMessages);
        }
        return String.format("Я %s. %s выполнено. Отправлено в консоль %d сообщений из %d.",
                Thread.currentThread().getName(), nameTask, numberOfMessagesSent, numberOfMessages);
    }
}