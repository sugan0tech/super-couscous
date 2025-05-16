package com.sugan0tech.sec04;

import com.sugan0tech.common.Util;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.core.publisher.Flux;

public class Lec07GenerateWithState {
    public static final Logger log = LoggerFactory.getLogger(Lec07GenerateWithState.class);
    public static void main(String[] args) {

        Flux.generate(
                () -> 0,
                (counter, sink ) -> {
                    if (counter.equals(20))
                        sink.complete();

                    var name = Util.faker.country().name();
                    sink.next(name);
                    counter++;
                    return counter;
                }
                )
                .takeUntil((name) -> name.equals("India"))
                .subscribe(Util.subscriber());

    }
}
