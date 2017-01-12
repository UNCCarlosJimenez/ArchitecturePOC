package com.unicomer.logger;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;
import org.springframework.web.WebApplicationInitializer;

@SpringBootApplication
@PropertySources({
	@PropertySource(value="file:./unicomer-services/config/logger-provider.properties", ignoreResourceNotFound=false)
})
public class LoggerProviderApplication extends SpringBootServletInitializer implements WebApplicationInitializer {
	private static Class<LoggerProviderApplication> applicationClass = LoggerProviderApplication.class;
	
	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(applicationClass);
	}
}
