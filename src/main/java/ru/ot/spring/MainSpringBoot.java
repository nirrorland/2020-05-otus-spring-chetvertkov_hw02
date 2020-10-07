package ru.ot.spring;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import ru.ot.spring.config.YamlConf;

@SpringBootApplication
@EnableConfigurationProperties(YamlConf.class)
public class MainSpringBoot {

    public static void main(String[] args) {
        SpringApplication.run(MainSpringBoot.class, args);
    }
}
