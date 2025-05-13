package com.sugan0tech.sec03;

import com.sugan0tech.common.AbstractHttpClient;
import com.sugan0tech.sec03.helper.StockObserver;
import reactor.core.publisher.Flux;

import java.time.Duration;

public class StockTrade extends AbstractHttpClient {
    public static void main(String[] args) throws InterruptedException {

        var stockTrade = new StockTrade();
        var subscriber = new StockObserver();
        stockTrade.getStockChanges().subscribe(subscriber);


        Thread.sleep(Duration.ofSeconds(20));
        Flux.range(13, 17).subscribe(System.out::println);
    }

    public Flux<Integer> getStockChanges(){
        return httpClient.get()
                .uri("/demo02/stock/stream")
                .responseContent()
                .asString()
                .map(Integer::parseInt);

    }
}
