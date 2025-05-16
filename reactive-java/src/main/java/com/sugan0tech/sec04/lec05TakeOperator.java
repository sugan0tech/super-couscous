package com.sugan0tech.sec04;

import com.sugan0tech.common.Util;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.core.publisher.Flux;

public class lec05TakeOperator {
    public static final Logger log = LoggerFactory.getLogger(lec05TakeOperator.class);
    public static void main(String[] args) {
        take();
        takeWhile();
        takeUntil();
    }

    private static void take() {
        Flux.range(1, 10)
                .log("take")
                .take(3)
                .log("sub")
                .subscribe(Util.subscriber());
    }

    private static void takeWhile() { // Take with predicate, can be added to have some conditions
        Flux.range(1, 10)
                .log("take")
                .takeWhile(i -> i < 5)
                .log("sub")
                .subscribe(Util.subscriber());
    }

    private static void takeUntil() { // Stop until the condition met
        Flux.range(1, 10)
                .log("take")
                .takeUntil(i -> i > 5)
                .log("sub")
                .subscribe(Util.subscriber());
    }
}
