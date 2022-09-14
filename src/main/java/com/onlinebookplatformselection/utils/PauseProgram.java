package com.onlinebookplatformselection.utils;

public class PauseProgram {

    public void delayExecution() {
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
