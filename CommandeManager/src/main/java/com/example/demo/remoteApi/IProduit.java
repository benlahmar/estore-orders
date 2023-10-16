package com.example.demo.remoteApi;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.example.demo.entities.Produit;

@FeignClient(name="catalogue-service")
public interface IProduit {

	@GetMapping("/produits/{id}")
	public Produit getbyid(@PathVariable long id);
}
