package com.sugan0tech.sec07threads;

import com.sugan0tech.common.Util;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.core.publisher.Flux;
import reactor.core.scheduler.Scheduler;
import reactor.core.scheduler.Schedulers;

public class Lec04VirtualThreads {
    public static final Logger log = LoggerFactory.getLogger(Lec04VirtualThreads.class);
    public static void main(String[] args) {
        var flux = Flux.create(sink -> {
            for (int i = 0; i < 10; i++) {
               log.info("Greeting: {}", i);
               sink.next(i);
            }
            sink.complete();
        });

        flux
                .subscribeOn(Schedulers.newParallel("Parallel"))
                .subscribe(Util.subscriber("suga"));

        flux
                .subscribeOn(Schedulers.newParallel("Parallel"))
                .subscribe(Util.subscriber("sub2"));
    }
}
