package com.example.demo.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entities.Client;
import com.example.demo.entities.Commande;
import com.example.demo.entities.LigneCommande;
import com.example.demo.entities.Produit;
import com.example.demo.metier.IMetier;
import com.example.demo.remoteApi.asyn.IServiceCatalogue;

import reactor.core.publisher.Mono;

@RestController
public class CommandeApi {

	@Autowired
	IMetier service;
	
	@Autowired
	IServiceCatalogue servicecatalogue;
	
	@PostMapping("/commandes")
	public ResponseEntity<Commande> addcmd(@RequestBody Commande c)
	{
		
		c= service.addCmd(c);
		if(c!=null)
		{
			return new ResponseEntity<Commande>(c,HttpStatus.OK);	
		}
		else
			return new ResponseEntity<Commande>(HttpStatus.NO_CONTENT);
	}
	@GetMapping("/commandes/{id}")
	public Commande findcmd(@PathVariable long id)
	{
		return service.cmdById(id);
	}
	
	@GetMapping("/commandes")
	public List<Commande> allcmd()
	{
		return service.allcmd();
	}
	
	@PostMapping("/commandes/{id}/lignes")
	public ResponseEntity<LigneCommande> addlgcmd(@RequestBody LigneCommande c, @PathVariable long id)
	{
		c=service.addLCommd(c, id);
		return new ResponseEntity<LigneCommande>(c,HttpStatus.OK);
	}
	
	@GetMapping("/catalogues/produits/{id}")
	public Mono<Produit> getProduitAsyn(@PathVariable long id)
	{
		System.out.println("avant");
		 Mono<Produit> p = servicecatalogue.findProduit(id);
		 System.out.println(p.toString());
		 System.out.println("apres");
		 return p;
	}
	
	
	
	@GetMapping("/customer/clients/{id}")
	public Mono<Client> getClientAsyn(@PathVariable long id)
	{
		System.out.println("avant");
		 Mono<Client> p = servicecatalogue.findClientById(id);
		 System.out.println(p.toString());
		 System.out.println("apres");
		 return p;
	}
}
