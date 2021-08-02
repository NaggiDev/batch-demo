package com.viettel.vds.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import com.viettel.vds.service.AsyncService;

import java.security.SecureRandom;
import java.util.concurrent.CompletableFuture;

@Slf4j
@Service
public class AsyncServiceImpl implements AsyncService {
    @Async("threadPoolTaskExecutor")
    public void test(String msg) {
        try {
            Thread.sleep(randomBetweenTwo(100, 2000));
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }
        log.info(Thread.currentThread().getName() + " test Async " + msg);
    }

    @Async("threadPoolTaskExecutor")
    public CompletableFuture<String> testCompletableFuture(String msg) {
        try {
            Thread.sleep(randomBetweenTwo(100, 2000));
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }
        log.info(Thread.currentThread().getName() + " test Async " + msg);
        return CompletableFuture.completedFuture(null);
    }

    @SuppressWarnings("java:S2119")
    public int randomBetweenTwo(int min, int max) {
        max += 1;
        SecureRandom random = new SecureRandom();
        return random.nextInt(max - min) + min;
    }
}
