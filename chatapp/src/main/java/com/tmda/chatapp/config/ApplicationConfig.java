package com.tmda.chatapp.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;

@Configuration
@PropertySource({"other.properties"})  // Will look for application.properties
public class ApplicationConfig {

	@Autowired
	private Environment environment;
	
	@Bean(name="simpleLogger")
	public SimpleLogger getLogger () {
		return new SimpleLogger();
	}
	
	@Bean(name="loggerWithDefaultProperties")
	public SimpleLogger getLoggerWithProperties () {
		return new SimpleLogger(
				environment.getProperty("logger.prefix"),
					environment.getProperty("logger.showDate", Boolean.class));
	}
	
	@Bean(name="loggerWithProperties")
	public SimpleLogger getLoggerWithDefaultProperties () {
		return new SimpleLogger(
				environment.getProperty("otherlogger.prefix","Other default prefix"),
					environment.getProperty("otherlogger.showDate", Boolean.class, true));
	}
}
