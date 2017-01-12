package com.unicomer.demo;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.cloud.netflix.feign.EnableFeignClients;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;
import org.springframework.web.WebApplicationInitializer;

@SpringBootApplication
@EnableFeignClients
@PropertySources({
	@PropertySource(value="file:./unicomer-services/config/swim-provider.properties", ignoreResourceNotFound=false)
})
public class SwimProviderApplication extends SpringBootServletInitializer implements WebApplicationInitializer {
	
	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(applicationClass);
	}
		
	private static Class<SwimProviderApplication> applicationClass = SwimProviderApplication.class;
}
