package com.sugan0tech.sec01.sec02;

import com.sugan0tech.sec01.subscriber.SubscriberImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.core.publisher.Mono;

public class Lec02MonoJust {

    private static Logger log = LoggerFactory.getLogger(Lec01LazyStream.class);
    public static void main(String[] args) {
        Mono<String> mono = Mono.just("suga"); // Easiest way to create a publisher
        var subscriber = new SubscriberImpl();
        mono.subscribe(subscriber);
        subscriber.getSubscription().request(10);
        log.info("mono = {}", mono);
    }
}
