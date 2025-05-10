package com.sugan0tech.sec01.sec02;

import org.reactivestreams.Subscription;
import reactor.core.publisher.Mono;

import java.util.logging.Logger;

public class Lec03MonoSubscribe {
    private static final Logger log = Logger.getLogger(Lec03MonoSubscribe.class.getName());
    public static void main(String[] args) {
        var mono = Mono.just("value 1");
        mono.subscribe(log::info, err -> log.severe(err.toString()), () -> log.info("completed")); // Auto calls the request, upon getting the subscription
        mono.subscribe(log::info, err -> log.severe(err.toString()), () -> log.info("completed"), Subscription::cancel); // or else we can explicitly call methods in subscription with this optional field
    }
}
