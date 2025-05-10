package com.sugan0tech.common;

import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DefaultSubscriber<T> implements Subscriber<T> {
    private static final Logger log = LoggerFactory.getLogger(DefaultSubscriber.class);
    private final String name;
    private Subscription subscription;

    public DefaultSubscriber(String name) {
        this.name = name;
    }

    public Subscription getSubscription() {
        return subscription;
    }

    @Override
    public void onSubscribe(Subscription subscription) {
        subscription.request(Long.MAX_VALUE); // This is what reactor does by default
        this.subscription = subscription;
    }

    @Override
    public void onNext(T item) {
        log.info("{} received: {}", name, item);
    }

    @Override
    public void onError(Throwable throwable) {
        log.error("{} error: {}", name, throwable);

    }

    @Override
    public void onComplete() {
        log.info("{} receive completed", name);
    }
}
