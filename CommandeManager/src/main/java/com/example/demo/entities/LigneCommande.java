package com.example.demo.entities;

import org.hibernate.annotations.ManyToAny;
import org.springframework.data.annotation.ReadOnlyProperty;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Transient;

@Entity
public class LigneCommande {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	//@ReadOnlyProperty
	long id;
	int qte;
	double prix;
	
	public double getPrix() {
		return prix;
	}

	public void setPrix(double prix) {
		this.prix = prix;
	}

	public long getIdproduit() {
		return idproduit;
	}

	public void setIdproduit(long idproduit) {
		this.idproduit = idproduit;
	}

	@ManyToOne
	Commande commande;
	
	long idproduit;
	
	@Transient
	Produit produit;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public int getQte() {
		return qte;
	}

	public void setQte(int qte) {
		this.qte = qte;
	}

	public Commande getCommande() {
		return commande;
	}

	public void setCommande(Commande commande) {
		this.commande = commande;
	}

	public Produit getProduit() {
		return produit;
	}

	public void setProduit(Produit produit) {
		this.produit = produit;
	}
	
	
	
	
}
