package com.example.demo.metier;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entities.Client;
import com.example.demo.entities.Commande;
import com.example.demo.entities.LigneCommande;
import com.example.demo.remoteApi.IClient;
import com.example.demo.repos.ICommande;
import com.example.demo.repos.ILigneCommande;

@Service
public class CommandeService implements IMetier{

	@Autowired
	ICommande crepo;
	@Autowired
	ILigneCommande lrepo;
	
	@Autowired
	IClient clientapi;
	@Override
	public  Commande addCmd(Commande c) {
		Client u = clientapi.findClientById(c.getIdclient());
		System.out.println("************"+u);
		if(u!=null)
		{
			
		 c=crepo.save(c);
		 c.setClient(u);
		 return c;
		}
		else
			return null;
		
	}

	@Override
	public LigneCommande addLCommd(LigneCommande lc, long idc) {
		Commande c = crepo.findById(idc).orElseThrow();
		lc.setCommande(c);
		return lrepo.save(lc);
	}

	@Override
	public List<Commande> allcmd() {
		List<Commande> cs = crepo.findAll();
		cs.forEach(a->a.setClient(clientapi.findClientById(a.getIdclient())));
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

}
