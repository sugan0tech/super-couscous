package com.sugan0tech.sec06;

import com.sugan0tech.common.Util;
import reactor.core.publisher.Flux;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.logging.Logger;

public class L01ColdPublisher {
    public static final Logger logger = Logger.getLogger(L01ColdPublisher.class.getName());
    public static void main(String[] args) {
        AtomicInteger count = new AtomicInteger(0);
        var flux = Flux.create(sink -> {
            for (int i = 0; i < 10; i++) {
                sink.next(count.incrementAndGet());
            }
            sink.complete();
        });

        flux.subscribe(Util.subscriber("sub1"));
        flux.subscribe(Util.subscriber("sub2"));

    }
}
