package com.unicomer.demo;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;
import org.springframework.web.WebApplicationInitializer;

@SpringBootApplication
@PropertySources({
	@PropertySource(value="file:./unicomer-services/config/ri-provider.properties", ignoreResourceNotFound=false)
})
public class RiProviderApplication extends SpringBootServletInitializer implements WebApplicationInitializer {
	private static Class<RiProviderApplication> applicationClass = RiProviderApplication.class;
	
	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(applicationClass);
	}
}
