package com.excilys.cdb.binding.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@ComponentScan(basePackages = { "com.excilys.cdb.binding.mapper" })
public class BindingConfig {}