package com.excilys.cdb.console.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.Profile;

import com.excilys.cdb.service.config.ServiceConfig;

@Configuration
@Import(value = ServiceConfig.class)
@ComponentScan(basePackages = { "com.excilys.cdb.console" })
public class ConsoleConfig {}