package com.sugan0tech.common;


import com.github.javafaker.Faker;
import org.reactivestreams.Subscriber;

public class Util {
    public static <T>Subscriber<T> subscriber() {
        return new DefaultSubscriber<>("");
    }
    public static <T>Subscriber<T> subscriber(String name) {
        return new DefaultSubscriber<>(name);
    }

    public static Faker faker = Faker.instance();
}
