package com.sugan0tech.sec04;

import com.sugan0tech.common.Util;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.nio.file.Path;

public class Assignment {
    public static final Logger logger = LoggerFactory.getLogger(Assignment.class);
    public static void main(String[] args) {
        var path = Path.of("/Users/sugavanesh/Documents/GitHub/super-couscous/reactive-java/src/main/java/com/sugan0tech/sec04/Sample.txt");
        new FileReaderServiceImpl().read(path).subscribe(Util.subscriber());
    }
}
