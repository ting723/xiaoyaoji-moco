package com.github.ting723;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

/**
 * @author zhanglw
 */
@EnableCaching
@SpringBootApplication
public class XiaoyaojiMocoApplication {

    public static void main(String[] args) {
        SpringApplication.run(XiaoyaojiMocoApplication.class, args);
    }
}
