package com.u2.business.enterprise;

import com.u2.common.core.annotation.EnableCustomConfig;
import com.u2.common.core.annotation.EnableRyFeignClients;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * 产品服务
 *
 * @author vhans
 */
@SpringBootApplication
@EnableCustomConfig
@EnableRyFeignClients
public class EnterpriseApplication {
    public static void main(String[] args) {
        SpringApplication.run(EnterpriseApplication.class, args);
    }
}
