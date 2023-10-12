package com.example.demo.metier;

import java.util.List;

import com.example.demo.entities.Commande;
import com.example.demo.entities.LigneCommande;

public interface IMetier {

	
	public Commande addCmd( Commande c);
	public LigneCommande addLCommd(LigneCommande lc, long idc);
	
	public List<Commande> allcmd();
	public Commande cmdById(long id);
	
}
