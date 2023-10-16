package com.example.demo.metier;

import java.util.List;
import java.util.concurrent.CompletableFuture;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;

import com.example.demo.entities.Client;
import com.example.demo.entities.Commande;
import com.example.demo.entities.LigneCommande;
import com.example.demo.entities.Produit;
import com.example.demo.remoteApi.IClient;
import com.example.demo.remoteApi.IProduit;
import com.example.demo.repos.ICommande;
import com.example.demo.repos.ILigneCommande;

import io.swagger.v3.oas.annotations.parameters.RequestBody;

@Service
public class CommandeService implements IMetier{

	@Autowired
	ICommande crepo;
	@Autowired
	ILigneCommande lrepo;
	
	@Autowired
	IClient clientapi;
	
	@Autowired
	IProduit produitclient;
	
	@Autowired
	KafkaTemplate<String, String> kafkaTemplate;
	
	@Override
	public  Commande addCmd(@RequestBody Commande c) {
		//Client u = clientapi.findClientById(c.getIdclient());
		kafkaTemplate.send("order-events1", ""+c.getIdclient());
		System.out.println("************"+c.getIdclient());
		
			
		 //c=crepo.save(c);
		 //c.setClient(u);
		
		 return c;
		
		
	}

	@Override
	public LigneCommande addLCommd(LigneCommande lc, long idc) {
		Commande c = crepo.findById(idc).orElseThrow();
		lc.setCommande(c);
		
		Produit p = produitclient.getbyid(lc.getIdproduit());
		if(p!=null)
		return lrepo.save(lc);
		else
			return null;
	}

	@Override
	public List<Commande> allcmd() {
		List<Commande> cs = crepo.findAll();
		cs.forEach(a->{
			a.setClient(clientapi.findClientById(a.getIdclient()));
			a.getLignes().forEach(l->l.setProduit(produitclient.getbyid(l.getIdproduit())));
		});
		
		
		return cs;
		
	}

	@Override
	public Commande cmdById(long id) {
		Commande c = crepo.findById(id).orElseThrow();
		Client u = clientapi.findClientById(c.getIdclient());
		if(c!=null)
		{
			c.setClient(u);
		}
		return c;
	}

	@KafkaListener(topics = "user-events1", groupId = "orderevents")
	public void getuser(String u)
	{
		System.out.println("******************chez commande"+u);
		//System.out.println("************"+u);
		
		
		//  c = crepo.save(c);
		 //c.setClient(u);
	}
}
