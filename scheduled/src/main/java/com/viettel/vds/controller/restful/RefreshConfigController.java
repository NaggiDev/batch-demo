package com.viettel.vds.controller.restful;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import vn.com.viettel.vds.arch.controller.restful.BaseController;
import vn.com.viettel.vds.arch.factory.response.GeneralResponse;
import vn.com.viettel.vds.arch.factory.response.ResponseFactory;

@RefreshScope
@RestController
@RequestMapping("/user-management/v1/api")
@Tag(name = "RefreshConfigController", description = "RefreshConfigController")
@Slf4j
public class RefreshConfigController  extends BaseController {
    @Autowired
    RestTemplate restTemplatel;
    @Autowired
    ResponseFactory responseFactory;
    @Value("${app.async-executor-core-pool-size:30}")
    private int asyncExecutorCorePoolSize;

    @GetMapping(value = "/demo-refresh")
    public void getRestDemo() {
        new Thread(() -> {
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);

            HttpEntity<String> entity = new HttpEntity<>(null, headers);

             restTemplatel.exchange("http://127.0.0.1:8080/actuator/bus-refresh", HttpMethod.POST, entity, Object.class);

        }).start();
    }

    @GetMapping(value = "/demo-refresh-data")
    public ResponseEntity<GeneralResponse<Integer>> getData() {
        return responseFactory.success(asyncExecutorCorePoolSize);
    }
}
