package com.stylefeng.guns;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.ComponentScan;

/**
 * SpringBoot方式启动类
 *
 * @author stylefeng
 * @Date 2017/5/21 12:06
 */
@Slf4j
@ComponentScan(basePackages = {"com.stylefeng.guns"})
//开启缓存
@EnableCaching
@SpringBootApplication
public class GunsApplication {

    public static void main(String[] args) {
        SpringApplication.run(GunsApplication.class, args);
        log.info("GunsApplication is success!");
    }
}
