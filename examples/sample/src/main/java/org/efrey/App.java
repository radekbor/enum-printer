package org.efrey;

import java.util.stream.Stream;

public class App {

    public static void main(String... args) {
        Sports[] sports = Sports.values();

        Stream.of(sports).forEach(
                System.out::println
        );
    }
}
