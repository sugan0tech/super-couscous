package com.sugan0tech.sec03;

import com.sugan0tech.common.Util;
import reactor.core.publisher.Flux;

import java.util.logging.Logger;

public class Lec06Log {
    public static Logger log = Logger.getLogger(Lec06Log.class.getName());
    public static void main(String[] args) {
        Flux.range(1, 10)
                .log("Range Producer")
                .filter(e -> e % 2 == 0)
                .log("Filter Producer")
                .subscribe(Util.subscriber());
    }
}
