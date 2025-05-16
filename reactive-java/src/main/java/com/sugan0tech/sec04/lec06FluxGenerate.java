package com.sugan0tech.sec04;

import com.sugan0tech.common.Util;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.core.publisher.Flux;

import java.time.Clock;

public class lec06FluxGenerate {
    public static final Logger log = LoggerFactory.getLogger(lec06FluxGenerate.class);
    public static void main(String[] args) {

        Flux.generate(synchronousSink -> {
            synchronousSink.next(Clock.systemUTC().millis());
        }).take(400).subscribe(Util.subscriber());

        generateNames();
    }

    public static void generateNames(){
        Flux.<String>generate(syncSink -> {
                    var name = Util.faker.name().firstName();
                    syncSink.next(name);
                })
                .takeUntil(name -> name.equals("Sam"))
                .subscribe(Util.subscriber());
    }
}
