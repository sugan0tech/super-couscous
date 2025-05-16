package com.sugan0tech.sec05;

import com.sugan0tech.common.Util;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public class Lec06ErrorHandeling {
    public static final Logger LOGGER = LoggerFactory.getLogger(Lec06ErrorHandeling.class);
    public static void main(String[] args) {
        Flux.range(1, 10)
                .map(i -> i == 5 ? 5/0 : i)
                .onErrorReturn(IllegalArgumentException.class, -1)
                .onErrorReturn(ArithmeticException.class, -1)
                .subscribe(Util.subscriber());

        // With fallback
        Flux.range(1, 10)
                .map(i -> i == 5 ? 5/0 : i)
                .onErrorContinue((ex, val) -> LOGGER.warn("==>, {}", val, ex))
                .subscribe(Util.subscriber());
//                .onErrorResume(ex -> fallback())
    }

    private static Flux<Integer> fallback(){
        return Flux.<Integer>create((sink) -> sink.next(Util.faker.number().numberBetween(1, 100)));
    }
}
