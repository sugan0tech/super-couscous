package com.sugan0tech.sec01.sec02;


import com.sugan0tech.common.Util;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.core.publisher.Mono;

import java.util.List;


public class Lec05MonoFromSuppiler {
    public static final Logger logger = LoggerFactory.getLogger(Lec05MonoFromSuppiler.class);
    public static void main(String[] args) {
        var list = List.of(1, 2, 3, 4, 5);
        Mono.just(sum(list)).subscribe(Util.subscriber());
        Mono.just(sum(list)); // This just invokes the Sum() but we don't have any subscriber
        Mono.fromSupplier(() -> sum(list)); // only the sum() will be called if there is subscriber subscribed
        Mono.fromSupplier(() -> sum(list)).subscribe(Util.subscriber());
    }

    private static int sum(List<Integer> list){
        logger.info("doing sum for {}", list);
        return list.stream().mapToInt(Integer::intValue).sum();
    }
}
