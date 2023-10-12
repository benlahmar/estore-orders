package com.example.demo.repos;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.entities.Commande;

public interface ICommande extends JpaRepository<Commande, Long>{

}
