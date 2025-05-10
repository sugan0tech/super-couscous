package com.sugan0tech.sec03;

import com.sugan0tech.common.Util;
import reactor.core.publisher.Flux;

import java.util.logging.Logger;

public class Lec02MultipleSubscribers {
    private static Logger logger = Logger.getLogger(Lec02MultipleSubscribers.class.getName());
    public static void main(String[] args) {
        var flux = Flux.range(1, 10);
        flux
                .filter(i -> i % 2 == 0)
                .map(i -> i + "a")
                .subscribe(Util.subscriber("sub3"));
        flux.subscribe(Util.subscriber("sub1"));
        flux.subscribe(Util.subscriber("sub2"));
    }
}
