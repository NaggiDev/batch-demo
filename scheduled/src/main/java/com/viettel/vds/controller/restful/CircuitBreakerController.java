package com.viettel.vds.controller.restful;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import vn.com.viettel.vds.arch.controller.restful.BaseController;
import vn.com.viettel.vds.arch.factory.response.GeneralResponse;
import vn.com.viettel.vds.arch.factory.response.ResponseFactory;

@RestController
@RequestMapping("/user-management/v1/api")
@Tag(name = "CircuitBreakerTest", description = "CircuitBreakerTest")
@Slf4j
public class CircuitBreakerController  extends BaseController {
    @Autowired
    ResponseFactory responseFactory;

    @GetMapping(value = "/TestCircuitBreakerCallFallbackMethod")
    @CircuitBreaker(name = "TestCircuitBreakerCallFallbackMethod", fallbackMethod = "fallbackRequest")
    @SuppressWarnings("java:S112")
    public ResponseEntity<GeneralResponse<String>> getData() {
        throw new RuntimeException();
    }

    @SuppressWarnings({"java:S1172", "java:S3400"})
    public ResponseEntity<GeneralResponse<String>> fallbackRequest(Exception ex) {
        return responseFactory.success("Default data");
    }
}
