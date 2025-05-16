package com.sugan0tech.sec04;

import reactor.core.publisher.Flux;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class FileReaderServiceImpl implements FileReaderService {
    @Override
    public Flux<String> read(Path path) {
        BufferedReader reader = null;
        try {
            reader = Files.newBufferedReader(path);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        BufferedReader finalReader = reader;
        return Flux.generate(()-> finalReader, (readr, sink) -> {
            try {
                var line = readr.readLine();

                if (line != null) {
                    sink.next(line);
                }else{
                    sink.complete();
                    readr.close();
                }
            } catch (IOException e) {
                sink.error(e);
            }
            return readr;
        });
    }
}
