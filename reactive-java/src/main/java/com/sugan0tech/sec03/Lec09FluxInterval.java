package com.sugan0tech.sec03;

import com.sugan0tech.common.Util;
import reactor.core.publisher.Flux;

import java.time.Duration;
import java.util.logging.Logger;

public class Lec09FluxInterval {
    public static Logger logger = Logger.getLogger(Lec09FluxInterval.class.getName());
    public static void main(String[] args) throws InterruptedException {
        Flux.interval(Duration.ofMillis(500)).subscribe(Util.subscriber("flux interval"));
        Thread.sleep(Duration.ofSeconds(3));
    }
}
