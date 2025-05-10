package com.sugan0tech.sec03;

import com.sugan0tech.common.Util;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.core.publisher.Flux;

public class Lec01FluxJust {
    public static Logger logger = LoggerFactory.getLogger(Lec01FluxJust.class);
    public static void main(String[] args) {
        Flux.just(1, 2, 3, 4, 5, 6, 7, 8, 9, 10).subscribe(Util.subscriber());
    }
}
