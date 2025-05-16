package com.sugan0tech.sec06;

import com.sugan0tech.common.Util;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.core.publisher.Flux;

import java.time.Duration;

public class Lec02HotPublisher {
    public static final Logger logger = LoggerFactory.getLogger(Lec02HotPublisher.class);
    public static void main(String[] args) throws InterruptedException {

        var movie = movieStream().publish().autoConnect(0).share();

        Thread.sleep(Duration.ofSeconds(1));

        movie
                .take(1)
                .subscribe(Util.subscriber("Sam"));

        Thread.sleep(Duration.ofSeconds(3));

        movie
                .subscribe(Util.subscriber("Suga"));

        Thread.sleep(Duration.ofSeconds(5));
    }

    public static Flux<String> movieStream(){
        return Flux.generate(
                () -> {
                    logger.info("Starting the movie");
                    return 0;
                },
                (scene, syncSink) -> {
                    syncSink.next("Movie@Scene" + scene);
                    logger.info("Playing Scene : {}", scene);
                    return ++scene;
                }
        )
                .take(10)
                .cast(String.class)
                .delayElements(Duration.ofSeconds(1));
    }
}
