package com.example.demo.repos;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.entities.LigneCommande;

public interface ILigneCommande extends JpaRepository<LigneCommande, Long>{

}
