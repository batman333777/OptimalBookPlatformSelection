package com.onlinebookplatformselection.enums;

public enum PlatformNames {

    AMAZON("Amazon"),

    FLIPKART("Flipkart");

    public String platformName;

    PlatformNames(String platformName) {
        this.platformName=platformName;
    }

    public String getPlatformName() {
        return platformName;
    }
}
