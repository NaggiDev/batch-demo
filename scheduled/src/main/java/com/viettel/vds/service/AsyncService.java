package com.viettel.vds.service;

import java.util.concurrent.CompletableFuture;

public interface AsyncService {
    void test(String msg);

    CompletableFuture<String> testCompletableFuture(String msg);
}
