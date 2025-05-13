package com.sugan0tech.sec04;

import com.sugan0tech.common.Util;
import reactor.core.publisher.Flux;

import java.util.logging.Logger;

public class Lec01FluxCreate {
    public static Logger log = Logger.getLogger(Lec01FluxCreate.class.getName());
    public static void main(String[] args) {
        Flux.create(fluxSink -> {
            String country;
            do{
                country = Util.faker.country().name();
                fluxSink.next(country);
            } while (!country.equals("Canada"));
            fluxSink.complete();
        }).subscribe(Util.subscriber());
    }
}
