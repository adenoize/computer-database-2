package com.excilys.cdb.service.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import com.excilys.cdb.persistence.config.PersistenceConfig;

@Configuration
@Import(PersistenceConfig.class)
@ComponentScan(basePackages = { "com.excilys.cdb.service" })
public class ServiceConfig {
}