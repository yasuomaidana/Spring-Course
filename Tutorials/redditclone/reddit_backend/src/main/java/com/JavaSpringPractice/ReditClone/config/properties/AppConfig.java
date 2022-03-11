package com.JavaSpringPractice.ReditClone.config.properties;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@EnableConfigurationProperties
@ConfigurationProperties(prefix = "app")
@Getter @Setter
public class AppConfig {
    private FrontEndConfig frontend;
    private BackendConfig backend;
}
