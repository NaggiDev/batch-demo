package com.viettel.vds.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import com.viettel.vds.service.CacheTestService;
@Service
@Slf4j
public class CacheTestServiceImpl implements CacheTestService {
    @Override
    public String getName(String name) {
        log.info("get new: "+name);
        return name;
    }

    @Override
    public String getName() {
        return "t";
    }
}
