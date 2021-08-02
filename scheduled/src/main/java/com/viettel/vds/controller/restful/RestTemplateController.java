package com.viettel.vds.controller.restful;

import datadog.trace.api.Trace;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import vn.com.viettel.vds.arch.controller.restful.BaseController;

@RestController
@RequestMapping("/user-management/v1/api")
@Tag(name = "testTemplate", description = "testTemplate")
@Slf4j
public class RestTemplateController extends BaseController {
    @Autowired
    RestTemplate restTemplate;

    @GetMapping(value = "/getRestDemo")
    @Trace
    @com.newrelic.api.agent.Trace
    public Object getRestDemo() {
        log.info("test new test");
        ResponseEntity<Object> a = restTemplate.exchange("http://127.0.0.1:8080/actuator/health", HttpMethod.GET, null, Object.class);
        return a.getBody();
    }
}
