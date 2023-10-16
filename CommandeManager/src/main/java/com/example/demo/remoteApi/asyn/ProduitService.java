package com.example.demo.remoteApi.asyn;

import java.util.List;
import java.util.function.Function;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.ClientResponse;
import org.springframework.web.reactive.function.client.WebClient;

import com.example.demo.entities.Client;
import com.example.demo.entities.Produit;

import reactor.core.publisher.Mono;

@Service
public class ProduitService implements IServiceCatalogue{

	@Autowired
	@Qualifier("clientcatalogue")
	WebClient clientcatalogue;
	
	@Autowired
	@Qualifier("webclientcustomer")
	WebClient webclientcustomer;
	
	@Override
	public Mono<Produit> findProduit(long id)
	{
		Mono<Produit> p = clientcatalogue.get()
		.uri("/produits/{id}",id)
		.retrieve()
		.bodyToMono(Produit.class);
		return p;
	}
	
	@Override
	public Mono<Produit> findProduit2(long id)
	{
		
		 Mono<Produit> p = clientcatalogue.get()
		.uri("/produits/{id}",id)
		.exchangeToMono(this::abcd);
		 return p;
		
	}
	public  Mono<Produit> abcd(ClientResponse response)
	{
		if(response.statusCode().is2xxSuccessful())
		{
			return response.bodyToMono(Produit.class);
		}else
			if(response.statusCode().is4xxClientError())
			{
				return Mono.error(()->new Exception());
			}
			else
				if(response.statusCode().is5xxServerError())
				{
					return Mono.error(()->new Exception());
				}
		return null;
		
		
	}

	@Override
	public Mono<Client> findClientById(long id) {
		Mono<Client> p = webclientcustomer.get()
				.uri("/profiles/{id}",id)
				.retrieve()
				.bodyToMono(Client.class);
				return p;
	}
	
}
