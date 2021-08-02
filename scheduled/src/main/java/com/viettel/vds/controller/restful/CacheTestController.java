package com.viettel.vds.controller.restful;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import vn.com.viettel.vds.arch.controller.restful.BaseController;
import vn.com.viettel.vds.arch.factory.response.GeneralResponse;
import vn.com.viettel.vds.arch.factory.response.ResponseFactory;
import com.viettel.vds.service.CacheTestService;

@RestController
@RequestMapping("/user-management/v1/api/testCache")
@Slf4j
@Tag(name = "CacheTestController", description = "CacheTestController")
@CacheConfig(cacheNames = {"CacheTestController"}, cacheManager = "localCacheManager")
public class CacheTestController  extends BaseController {
    @Autowired
    CacheTestService cacheTestService;
    @Autowired
    @Qualifier("localCacheManager")
    CacheManager localCacheManager;
    @Autowired
    ResponseFactory responseFactory;

    @GetMapping(value = "/getDemoCache")
    public ResponseEntity<GeneralResponse<String>> getDemoCache(@RequestParam("data") String data) {
        return responseFactory.success(cacheTestService.getName(data));
    }

    @Cacheable(value = "getNamePram", sync = true, key = "#name")
    @GetMapping(value = "/getDemoCache2")
    public ResponseEntity<GeneralResponse<String>> getDemo(String name) {
        log.info("get new cache: " + name);
        return responseFactory.success(name);
    }

    @GetMapping(value = "/getDemoCacheManual")
    public ResponseEntity<GeneralResponse<String>> getDemoManual(String name) {
        // lấy về cache và put mới nếu chưa có
        localCacheManager.getCache("getDemoManual").putIfAbsent(name, "test: " + name);
        // lấy cache trả về cho client
        return responseFactory.success(localCacheManager.getCache("getDemoManual")
                .get(name, String.class));
    }

}
