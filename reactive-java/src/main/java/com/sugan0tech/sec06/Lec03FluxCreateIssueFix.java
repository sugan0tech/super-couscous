package com.sugan0tech.sec06;

import com.sugan0tech.common.Util;
import com.sugan0tech.sec04.helper.NameGenerator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.core.publisher.Flux;

public class Lec03FluxCreateIssueFix {
    public static final Logger log = LoggerFactory.getLogger(Lec03FluxCreateIssueFix.class);
    public static void main(String[] args) {

        var generator = new NameGenerator();
//        var pub = Flux.create(generator);
        var pub = Flux.create(generator).share();
        pub.subscribe(Util.subscriber("one"));
        pub.subscribe(Util.subscriber("two"));

        for (int i = 0; i < 10; i++) {
            generator.generate();
        }

    }
}
