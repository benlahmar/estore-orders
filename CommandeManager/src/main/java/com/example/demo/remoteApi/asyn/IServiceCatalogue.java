package com.example.demo.remoteApi.asyn;

import com.example.demo.entities.Client;
import com.example.demo.entities.Produit;

import reactor.core.publisher.Mono;

public interface IServiceCatalogue {

	public Mono<Produit> findProduit(long id);

	Mono<Produit> findProduit2(long id);
	
	Mono<Client> findClientById(long id);
}
