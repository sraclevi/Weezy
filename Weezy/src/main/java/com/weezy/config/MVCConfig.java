package com.weezy.config;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
// @EnableWebMvc
@EnableAutoConfiguration
@ComponentScan(basePackages = { "com.weezy.rest.controller" })
public class MVCConfig {
}