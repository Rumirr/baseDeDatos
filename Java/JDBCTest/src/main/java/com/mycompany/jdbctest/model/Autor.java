/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.jdbctest.model;

/**
 *
 * @author horabaixa
 */
public class Autor {
    
    private int idAutot;
    private String nom;

    public Autor() {
    }

    

    public int getIdAutot() {
        return idAutot;
    }

    public String getNom() {
        return nom;
    }

    public void setIdAutot(int idAutot) {
        this.idAutot = idAutot;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }
  
}
