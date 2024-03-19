package org.example.http;

import java.util.Arrays;

public enum HttpMethod {
    GET("GET"),
    POST("POST");
    private final String name;

    HttpMethod(String name) {
        this.name = name;
    }

    public static HttpMethod of(String value) {
        return Arrays.stream(HttpMethod.values())
                .filter(httpMethod -> httpMethod.equals(value))
                .findAny().orElseThrow(() -> new IllegalArgumentException("Not Allowed Method"));
    }

    public String getName() {
        return name;
    }
}
