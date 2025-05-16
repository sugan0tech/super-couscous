package com.sugan0tech.sec05;

import com.sugan0tech.common.Util;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.core.publisher.Flux;

import java.time.Duration;

public class Lec04Delay {
    public static final Logger log = LoggerFactory.getLogger(Lec04Delay.class);
    public static void main(String args[]) throws InterruptedException {
        Flux.range(1, 10)
                .delayElements(Duration.ofSeconds(1))
                .subscribe(Util.subscriber("One"));

        Flux.range(1, 10)
                .log()
                .delayElements(Duration.ofSeconds(1))
                .subscribe(Util.subscriber("Two"));

        Thread.sleep(Duration.ofSeconds(20));
    }
}
