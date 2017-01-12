package com.unicomer.demo;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;

import com.unicomer.demo.buying.dao.EbsClient;

public class SoapClientConfig {
	@Value("${ebs.service.endpoint}") String serviceEndpoint;
		
//	@Bean
    public Jaxb2Marshaller marshaller() {
        Jaxb2Marshaller marshaller = new Jaxb2Marshaller();
        marshaller.setContextPath("com.unicomer.demo.buying.message.ebs");
        return marshaller;
    }

//    @Bean
    public EbsClient ebsClient(Jaxb2Marshaller marshaller) {
    	EbsClient client = new EbsClient();
        client.setDefaultUri(serviceEndpoint);
        client.setMarshaller(marshaller);
        client.setUnmarshaller(marshaller);
        return client;
    }
}
