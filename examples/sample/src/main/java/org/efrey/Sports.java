package org.efrey;

public enum Sports {
    Football("team", "ball"),
    Volleball("team", "ball"),
    Tennis("single", "ball");

    private final String group;
    private final String accessory;

    Sports(String group, String accessory) {
        this.group = group;
        this.accessory = accessory;
    };
}
