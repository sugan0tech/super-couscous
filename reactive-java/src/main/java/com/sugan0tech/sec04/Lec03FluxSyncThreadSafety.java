package com.sugan0tech.sec04;

import com.sugan0tech.sec04.helper.NameGenerator;
import org.slf4j.LoggerFactory;
import reactor.core.publisher.Flux;

import java.util.ArrayList;

public class Lec03FluxSyncThreadSafety {
    private static final org.slf4j.Logger logger = LoggerFactory.getLogger(Lec03FluxSyncThreadSafety.class);
    public static void main(String[] args) throws InterruptedException {
        notThreadSafe();
        threadSafe();
    }

    private static void notThreadSafe() throws InterruptedException {

        var list = new ArrayList<String>();
        Runnable runnable = () -> {
            for (int i = 0; i < 1000; i++) {
                list.add(Integer.toString(i));
            }
        };

        execParallel(list, runnable);
    }

    private static void threadSafe() throws InterruptedException {
        var list = new ArrayList<String>();
        var generator = new NameGenerator();
        var flux = Flux.create(generator);
        flux.subscribe(list::add);

        Runnable runnable = () -> {
            for (int i = 0; i < 1000; i++) {
                generator.generate();
            }
        };

        execParallel(list, runnable);
    }

    private static void execParallel(ArrayList<String> list, Runnable runnable) throws InterruptedException {
        var threads = new ArrayList<Thread>();
        for (int i = 0; i < 10; i++) {
            threads.add(Thread.ofPlatform().start(runnable));
        }
        for (Thread thread : threads) {
            thread.join();
        }

        logger.info("List size: {}", list.size());
    }
}
