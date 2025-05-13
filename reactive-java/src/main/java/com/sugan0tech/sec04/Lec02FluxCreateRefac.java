package com.sugan0tech.sec04;

import com.sugan0tech.common.Util;
import com.sugan0tech.sec04.helper.NameGenerator;
import reactor.core.publisher.Flux;

import java.util.logging.Logger;

public class Lec02FluxCreateRefac {
    public static Logger log = Logger.getLogger(Lec02FluxCreateRefac.class.getName());
    public static void main(String[] args) {
        var generator = new NameGenerator();
        var flux = Flux.create(generator);
        flux.subscribe(Util.subscriber());

        for (int i = 0; i < 10; i++) {
            generator.generate();
        }

    }
}
