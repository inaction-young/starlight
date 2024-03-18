package com.starlight.api;

import com.starlight.core.utils.SpringUtils;
import lombok.extern.log4j.Log4j2;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@Log4j2
@MapperScan(basePackages = "com.starlight.mapper")
@SpringBootApplication(scanBasePackages = "com.starlight")
public class StarlightApiAppliction implements CommandLineRunner {
    public static void main(String[] args) {
        SpringApplication.run(StarlightApiAppliction.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        log.info("{} on at {} environment", this.getClass().getSimpleName(), SpringUtils.applicationEnv());
    }
}
