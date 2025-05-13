package com.sugan0tech.sec03.helper;

import com.sugan0tech.common.Util;
import reactor.core.publisher.Flux;

import java.time.Duration;
import java.util.List;
import java.util.stream.IntStream;

public class NameGenerator {
    public static String generateName() {
        try {
            Thread.sleep(Duration.ofSeconds(1));
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        return Util.faker.name().firstName();
    }

    public static List<String> generateNamesList(int count){
        return IntStream.rangeClosed(1, count).mapToObj(_ -> generateName()).toList();
    }

    public static Flux<String> generateNamesFlux(int count){
        return Flux.range(1, count).map(_ -> generateName());
    }
}
