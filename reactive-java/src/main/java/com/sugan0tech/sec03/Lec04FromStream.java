package com.sugan0tech.sec03;

import com.sugan0tech.common.Util;
import reactor.core.publisher.Flux;

import java.util.List;
import java.util.logging.Logger;

public class Lec04FromStream {
    public static Logger logger = Logger.getLogger(Lec04FromStream.class.getName());
    public static void main(String[] args) {
        var list = List.of(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
        var flux = Flux.fromStream(list::stream);
        flux.subscribe(Util.subscriber());
        flux.subscribe(System.out::println);
    }
}
