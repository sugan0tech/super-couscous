package com.sugan0tech.sec01.publisher;

import com.github.javafaker.Faker;
import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SubscriptionImpl implements Subscription {
    private static final Logger log = LoggerFactory.getLogger(SubscriptionImpl.class);
    private static final int MAX_PUBLISH = 10;
    private final Subscriber<? super String> subscriber;
    private final Faker faker = new Faker();
    private int count = 0;
    private boolean isCancelled;
    public SubscriptionImpl(Subscriber<? super String> subscriber) {
        this.subscriber = subscriber;
    }

    @Override
    public void request(long requested) {
        if (isCancelled) return;
        if (requested > MAX_PUBLISH) {
            subscriber.onError(new RuntimeException("Validation Failed"));
            isCancelled = true;
            return;
        }
        log.info("Subscribe requested: {}", requested);
        for (int i = 0; i < requested && count < MAX_PUBLISH; i++) {
            count++;
            subscriber.onNext(faker.internet().emailAddress());
        }

        if (count == MAX_PUBLISH) {
            log.info("no more data to produce");
            subscriber.onComplete();
            this.isCancelled = true;
        }

    }

    @Override
    public void cancel() {
        log.info("Cancelling subscription");
        this.isCancelled = true;
    }
}
