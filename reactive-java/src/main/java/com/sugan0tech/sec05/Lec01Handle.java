package com.sugan0tech.sec05;

import com.sugan0tech.common.Util;
import reactor.core.publisher.Flux;

public class Lec01Handle {
    public static void main(String[] args) {
        var flux = Flux.range(1, 10);
        flux.handle((item, sink) -> {
            switch (item){
                case 1 -> sink.next(-2);
                case 4 ->  {}
                case 7 -> sink.error(new RuntimeException("oooups"));
                default -> sink.next(item);
                }
                })
                .subscribe(Util.subscriber());
    }
}
