package com.example.demo.remoteApi;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.example.demo.entities.Client;

@FeignClient(name = "customer-service", url = "http://localhost:8082")
public interface IClient {

	@GetMapping("/profiles/{id}")
	public Client findClientById(@PathVariable long id);
}
