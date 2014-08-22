package com.weezy.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
// @EnableWebMvc
@ComponentScan(basePackages = { "com.weezy.rest.controller" })
public class MVCConfig {
}