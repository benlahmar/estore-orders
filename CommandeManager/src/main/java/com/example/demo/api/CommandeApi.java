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

import com.example.demo.entities.Commande;
import com.example.demo.metier.IMetier;

@RestController
public class CommandeApi {

	@Autowired
	IMetier service;
	
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
}
