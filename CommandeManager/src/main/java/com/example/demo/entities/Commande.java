package com.example.demo.entities;

import java.time.LocalDate;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Transient;

@Entity
public class Commande {

	@Id
	
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	long id;
	
	LocalDate date;
	@OneToMany(fetch = FetchType.EAGER, mappedBy = "commande", cascade = CascadeType.ALL)
	List<LigneCommande> lignes;
	
	long idclient ;
	
	@Transient
	Client client;
	
	State state;

	public long getIdclient() {
		return idclient;
	}

	public void setIdclient(long idclient) {
		this.idclient = idclient;
	}

	public State getState() {
		return state;
	}

	public void setState(State state) {
		this.state = state;
	}

	public long getId() {
		
		
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public LocalDate getDate() {
		return date;
	}

	public void setDate(LocalDate date) {
		this.date = date;
	}

	public List<LigneCommande> getLignes() {
		return lignes;
	}

	public void setLignes(List<LigneCommande> lignes) {
		this.lignes = lignes;
	}

	public Client getClient() {
		return client;
	}

	public void setClient(Client client) {
		this.client = client;
	}
	
	
}
