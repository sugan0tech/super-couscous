package com.sugan0tech.sec01.sec02;

import com.sugan0tech.common.Util;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.core.publisher.Mono;

import java.time.Duration;
import java.util.List;

public class Lec10MonoDefer {
    public static Logger logger = LoggerFactory.getLogger(Lec10MonoDefer.class);
    public static void main(String[] args) {
        Mono.defer(Lec10MonoDefer::createPublisher).subscribe(Util.subscriber("deferSubscriber"));
        logger.info("Lec10MonoDefer");
    }

    private static Mono<Integer> createPublisher(){
        logger.info("Creating publisher");
        var list = List.of(1, 2, 3, 4, 5);
        try {
            Thread.sleep(Duration.ofSeconds(2));
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        return Mono.fromCallable(() -> sum(list));

    }
    // Time consuming business logic
    private static int sum(List<Integer> list) throws InterruptedException {
        logger.info("doing sum for {}", list);
        Thread.sleep(Duration.ofSeconds(2));
        return list.stream().mapToInt(Integer::intValue).sum();
    }
}
