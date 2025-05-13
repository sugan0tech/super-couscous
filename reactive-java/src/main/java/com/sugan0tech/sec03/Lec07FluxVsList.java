package com.sugan0tech.sec03;

import com.sugan0tech.common.DefaultSubscriber;
import com.sugan0tech.common.Util;
import com.sugan0tech.sec03.helper.NameGenerator;

import java.util.logging.Logger;

public class Lec07FluxVsList {
    public static Logger logger = Logger.getLogger(Lec07FluxVsList.class.getName());
    public static void main(String[] args) {
//        var list = NameGenerator.generateNamesList(10);
//        logger.info(list.toString());
        var subscriber = new DefaultSubscriber<String>("Lec07FluxVsList");
        NameGenerator.generateNamesFlux(10).subscribe(subscriber);
        subscriber.getSubscription().request(3);

    }
}
