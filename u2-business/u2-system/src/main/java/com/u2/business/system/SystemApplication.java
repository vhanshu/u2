package com.u2.business.system;


import com.u2.common.core.annotation.EnableCustomConfig;
import com.u2.common.core.annotation.EnableRyFeignClients;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * 管理服务
 *
 * @author vhans
 */
@SpringBootApplication
@EnableCustomConfig
@EnableRyFeignClients
public class SystemApplication {
    public static void main(String[] args) {
        SpringApplication.run(SystemApplication.class, args);
    }
}
