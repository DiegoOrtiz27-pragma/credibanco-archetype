package com.credibanco.swarch.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;

@Configuration
@ComponentScan(
        basePackages = "com.credibanco.swarch.usecase",
        includeFilters = @ComponentScan.Filter(type = FilterType.REGEX, pattern = ".*UseCase$")
)
public class UseCaseConfig {
}
