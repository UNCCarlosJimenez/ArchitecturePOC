package com.unicomer.process.buy;

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
	@PropertySource(value="file:./unicomer-services/config/buy-service.properties", ignoreResourceNotFound=false)
})
public class BuyServiceApplication  extends SpringBootServletInitializer implements WebApplicationInitializer {
	private static Class<BuyServiceApplication> applicationClass = BuyServiceApplication.class;
	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(applicationClass);
	}
}
