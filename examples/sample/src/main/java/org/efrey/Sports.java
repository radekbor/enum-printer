package org.efrey;

import org.radekbor.Wrapper;

public enum Sports {
    Football("team", "ball", "football"),
    Volleyball("team", "ball", "volleyball"),
    Tennis("single", "ball", "tennis");

    private final String group;
    private final String accessory;
    private final Wrapper wrapper;

    Sports(String group, String accessory, String wrapped) {
        this.group = group;
        this.accessory = accessory;
        this.wrapper = new Wrapper(wrapped);
    };
}
