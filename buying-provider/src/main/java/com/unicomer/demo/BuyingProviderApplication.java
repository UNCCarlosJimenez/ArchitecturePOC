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
	@PropertySource(value="file:./unicomer-services/config/buying-provider.properties", ignoreResourceNotFound=false)
})
public class BuyingProviderApplication extends SpringBootServletInitializer implements WebApplicationInitializer {
	private static Class<BuyingProviderApplication> applicationClass = BuyingProviderApplication.class;
	
	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(applicationClass);
	}
		
	
}
