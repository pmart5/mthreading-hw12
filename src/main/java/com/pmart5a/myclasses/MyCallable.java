package com.pmart5a.myclasses;

import java.util.Random;
import java.util.concurrent.Callable;

public class MyCallable implements Callable<String> {

    public static final int MAX_NUMBER_OF_MESSAGES = 7;
    public static final int DELAY_TIME = 2000;
    private final String taskName;
    private final int numberOfMessages;

    public MyCallable(String taskName) {
        this.taskName = taskName;
        numberOfMessages = getRandomNumberOfMessages();
    }

    private int getRandomNumberOfMessages() {
        return new Random().nextInt(MAX_NUMBER_OF_MESSAGES) + 1;
    }

    @Override
    public String call() throws InterruptedException {
        int numberOfMessagesSent = 0;
        for (int i = 0; i < numberOfMessages; i++) {
            Thread.sleep(DELAY_TIME);
            System.out.printf("Я %s. Выполняю %s. Это %d сообщение из %d.\n", Thread.currentThread().getName(), taskName,
                    ++numberOfMessagesSent, numberOfMessages);
        }
        return String.format("Я %s. %s выполнено. Отправлено в консоль %d сообщений из %d.",
                Thread.currentThread().getName(), taskName, numberOfMessagesSent, numberOfMessages);
    }
}