package com.sugan0tech.sec04;

import com.sugan0tech.common.Util;
import com.sugan0tech.sec01.subscriber.SubscriberImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.core.publisher.Flux;

import java.time.Duration;


public class Lec04FluxCreateDownstreamDemand {
    private static final Logger logger = LoggerFactory.getLogger(Lec04FluxCreateDownstreamDemand.class);
    public static void main(String[] args) throws InterruptedException {
        produceEarly();
        produceOnDemand();
    }

    private static void produceEarly(){
        var subscriber = new SubscriberImpl();
        Flux.<String>create(fluxSink -> {
            for (int i = 0; i <10; i++) {
                var name = Util.faker.name().firstName();
                logger.info("generate: {}", name);
                fluxSink.next(name);
            }
            fluxSink.complete();
        }).subscribe(subscriber);
        subscriber.getSubscription().request(11);
    }

    private static void produceOnDemand() throws InterruptedException {
        var subscriber = new SubscriberImpl();
        Flux.<String>create(sink -> {
            sink.onRequest(req -> {
                for (int i = 0; i < req && !sink.isCancelled() ; i++) {
                    var name = Util.faker.name().firstName();
                    logger.info("generate: {}", name);
                    sink.next(name);
                }
                sink.complete();
            });
        }).subscribe(subscriber);
        Thread.sleep(Duration.ofSeconds(2));
        subscriber.getSubscription().request(2);
        Thread.sleep(Duration.ofSeconds(2));
        subscriber.getSubscription().cancel();
        Thread.sleep(Duration.ofSeconds(2));
        subscriber.getSubscription().request(3); // shouldn't generate on the producer run since as it is canceled
    }
}
