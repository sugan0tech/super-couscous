package com.sugan0tech.sec01.sec02;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.stream.Stream;

public class Lec01LazyStream {
    private static Logger log = LoggerFactory.getLogger(Lec01LazyStream.class);
    public static void main(String[] args) {
        Stream.of(1).peek(i -> log.info("{}", i)).toList();
    }
}
