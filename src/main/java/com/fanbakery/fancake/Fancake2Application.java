package com.fanbakery.fancake;

import com.fanbakery.fancake.config.ApplicationConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@SpringBootApplication(exclude={DataSourceAutoConfiguration.class})
//@EnableConfigurationProperties({ApplicationConfig.class})
public class Fancake2Application {

    public static void main(String[] args) {
        SpringApplication.run(Fancake2Application.class, args);
    }

}
