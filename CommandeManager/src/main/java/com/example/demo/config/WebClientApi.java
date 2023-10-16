package com.example.demo.config;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration

public class WebClientApi {

	@Bean
	@Qualifier("clientcatalogue")
	public WebClient WebClientCatalogue()
	{
		WebClient client=WebClient.builder()
				.baseUrl("http://localhost:8081")
				.build();
		return client;
	}
	
	@Bean
	@Qualifier("webclientcustomer")
	public WebClient WebClientCustomer()
	{
		WebClient client=WebClient.builder()
				.baseUrl("http://localhost:8082")
				.build();
		return client;
	}
	
}
