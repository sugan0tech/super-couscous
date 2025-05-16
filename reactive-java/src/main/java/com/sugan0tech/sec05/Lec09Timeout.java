package com.sugan0tech.sec05;

import com.sugan0tech.common.Util;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.core.publisher.Mono;

import java.time.Duration;

public class Lec09Timeout {
    public static final Logger log = LoggerFactory.getLogger(Lec09Timeout.class);
    public static void main(String[] args) throws InterruptedException {

        getUserName()
                .timeout(Duration.ofSeconds(1), getUserNameFallback())
                .subscribe(Util.subscriber());

        Thread.sleep(Duration.ofSeconds(3));
    }

    public static Mono<String> getUserName(){
        return Mono.fromSupplier(() -> Util.faker.name().firstName())
                .delayElement(Duration.ofSeconds(2));
    }

    public static Mono<String> getUserNameFallback(){
        return Mono.fromSupplier(() -> "fallback-" + Util.faker.name().firstName())
                .delayElement(Duration.ofSeconds(1));
    }
}
