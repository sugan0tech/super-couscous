package com.sugan0tech.sec01.sec02;


import com.sugan0tech.common.Util;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.core.publisher.Mono;

import java.time.Duration;
import java.util.concurrent.CompletableFuture;


public class Lec08MonoComputableFuture {
    public static final Logger logger = LoggerFactory.getLogger(Lec08MonoComputableFuture.class);
    public static void main(String[] args) throws InterruptedException {
        Mono.fromFuture(getNameFromFuture()).subscribe(Util.subscriber("subToFuture"));
        Mono.fromFuture(Lec08MonoComputableFuture::getNameFromFuture).subscribe(Util.subscriber("Future")); // also comes with supplier option
        Thread.sleep(Duration.ofSeconds(1));
    }

    public static CompletableFuture<String> getNameFromFuture() {
        return CompletableFuture.supplyAsync(() -> {
            logger.info("getNameFromFuture");
            return Util.faker.funnyName().name();
        });
    }
}
