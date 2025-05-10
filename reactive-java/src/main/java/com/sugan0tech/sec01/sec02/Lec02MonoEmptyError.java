package com.sugan0tech.sec01.sec02;

import com.sugan0tech.common.Util;
import reactor.core.publisher.Mono;

import java.util.logging.Logger;

public class Lec02MonoEmptyError {
    public static final Logger logger = Logger.getLogger(Lec02MonoEmptyError.class.getName());
    public static void main(String[] args) {

        getUserName(1).subscribe(Util.subscriber());
        getUserName(2).subscribe(Util.subscriber());
        getUserName(3).subscribe(Util.subscriber());
        getUserName(5).subscribe(System.out::println, error -> logger.warning(error.toString()));

    }

    private static Mono<String> getUserName(int userId){
        return switch (userId){
            case 1 -> Mono.just("Suga");
            case 2 -> Mono.empty();
            default -> Mono.error(new RuntimeException("User not found"));
        };
    }
}
