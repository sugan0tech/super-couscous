package com.sugan0tech;

import com.sugan0tech.sec01.publisher.PublisherImpl;
import com.sugan0tech.sec01.subscriber.SubscriberImpl;

import java.time.Duration;

public class Main {
    public static void main(String[] args) {
        try {
            demo1();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
    private static void demo1() throws InterruptedException {
        var publisher = new PublisherImpl();
        var subscriber = new SubscriberImpl();
        publisher.subscribe(subscriber);
        subscriber.getSubscription().request(3);
        Thread.sleep(Duration.ofSeconds(1));
        subscriber.getSubscription().request(3);
        Thread.sleep(Duration.ofSeconds(2));
        subscriber.getSubscription().request(3);
        subscriber.getSubscription().request(11);
        subscriber.getSubscription().request(3);
        subscriber.getSubscription().cancel();
        Thread.sleep(Duration.ofSeconds(3));
        subscriber.getSubscription().request(3);
        Thread.sleep(Duration.ofSeconds(1));
        subscriber.getSubscription().request(2);
        Thread.sleep(Duration.ofSeconds(1));
    }
}