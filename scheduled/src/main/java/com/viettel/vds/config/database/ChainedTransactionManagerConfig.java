package com.viettel.vds.config.database;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.transaction.ChainedTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;

@Configuration
public class ChainedTransactionManagerConfig {
    @Bean(name = "chainedTransactionManager")
    public ChainedTransactionManager transactionManagerUserAndAddress(@Qualifier("addressTransactionManager") PlatformTransactionManager ds1,
                                                                      @Qualifier("userTransactionManager") PlatformTransactionManager ds2) {
        return new ChainedTransactionManager(ds1, ds2);
    }
}
