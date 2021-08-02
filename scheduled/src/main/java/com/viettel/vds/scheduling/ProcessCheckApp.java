package com.viettel.vds.scheduling;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.Scheduled;
import vn.com.viettel.vds.arch.config.scheduling.RefreshScheduler;
import com.viettel.vds.service.AsyncService;

import java.util.concurrent.CompletableFuture;

@Configuration
@RefreshScope
@Slf4j
public class ProcessCheckApp implements RefreshScheduler {
    @Autowired
    AsyncService asyncService;

    @Scheduled(fixedDelay = (1000 * 10))
    public void check() {
        long timeRandom = System.nanoTime();
        log.info(timeRandom + " scheduling test  ");
        asyncService.test("test Async 1 " + timeRandom);
        asyncService.test("test Async 2 " + timeRandom);
        log.info(timeRandom + " scheduling test end " + "\n");


    }

    @Scheduled(fixedDelay = (1000 * 14))
    public void check2() {
        long timeRandom = System.nanoTime();

        log.info(timeRandom + " scheduling test Sync  ");
        CompletableFuture<String> completableFuture1 = asyncService.testCompletableFuture("test Sync 1 " + timeRandom);
        CompletableFuture<String> completableFuture2 = asyncService.testCompletableFuture("test Sync 2 " + timeRandom);
        CompletableFuture.allOf(completableFuture1, completableFuture2).join();
        log.info(timeRandom + " scheduling test Sync end " + "\n");
    }
}
