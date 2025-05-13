package com.sugan0tech.sec03.helper;

import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class StockObserver implements Subscriber<Integer> {
    public static final Logger logger = LoggerFactory.getLogger(StockObserver.class.getName());
    public static int quantity;
    public static int balance = 1000;
    private Subscription subscription;
    @Override
    public void onSubscribe(Subscription subscription) {
        subscription.request(Long.MAX_VALUE);
        this.subscription = subscription;
    }

    @Override
    public void onNext(Integer price) {
        if(price < 90 && balance >= price){
            quantity++;
            balance = balance - price;
            logger.info("Bought stock at {}, total quantity {}, remaining balance {}", price, quantity, balance);
        }else if ( price > 110 && quantity > 0){
            balance = balance + quantity*price;
            quantity = 0;
            logger.info("Sold stock at: {}, total quantity {}, remaining balance {}", price, quantity, balance);
            subscription.cancel();
            logger.info("profit: {}", balance - 1000);
        }
    }

    @Override
    public void onError(Throwable throwable) {
        logger.error("Error", throwable);
    }

    @Override
    public void onComplete() {
        logger.info("Completed");
    }
}
